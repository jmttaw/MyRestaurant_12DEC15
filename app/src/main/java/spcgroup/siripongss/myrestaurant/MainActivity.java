package spcgroup.siripongss.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {
    private MangerTable objMangerTable;
    private EditText userEditText, passwordEditText;
    private  String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Explicit

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bindwidget();

        //connectDatabase
        objMangerTable = new MangerTable(this);

        //testerAddValue();

        //deleteALlSqlite

        deleteALLSQLite();

        //Synchroze JSON to SQLite
        SynjsontoSQLite();

    }//Main Method

    private void Bindwidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void ClickLogin(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        //CheckSpace
        if (userString.equals("")||passwordString.equals("") )  {
            //Have True
            MyAlertDialng objMyAlertDialng = new MyAlertDialng();
            objMyAlertDialng.errorDialog(MainActivity.this,"Have Space","Please Fill All Every Black");
        } else {
        }
        //No Space

    }//clicklogin

    private void SynjsontoSQLite() {
        //Setup myPolice
        StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myThreadPolicy);

        int inttable = 1;
        while (inttable <=2) {

            InputStream objInputStream = null;
            String strJSON = null;
            String strURLuser = "http://swiftcodingthai.com/12dec/php_get_data_siripong.php";
            String strURLFood = "http://swiftcodingthai.com/12dec/php_get_data_food.php";
            HttpPost objHttpPost = null;

            //1. Create InputStream
            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (inttable) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLFood);
                        break;
                }


                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();


            } catch (Exception e) {
                Log.d("Rest", "InputStream==>" + e.toString());
            }

            //2. Create strJSON

            try {
                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine())!=null ) {
                    objStringBuilder.append(strLine);
                }//Whlie
                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d("Rest", "StrJSON==>" + e.toString());
            }



            //3, Update to sqlite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject object = objJsonArray.getJSONObject(i);
                    switch (inttable) {
                        case 1:
                            //for usertable
                            String strUser = object.getString("user");
                            String strPassword = object.getString("password");
                            String strname = object.getString("name");
                            objMangerTable.addValueTouser(strUser, strPassword, strname);

                            break;
                        case 2:
                            //for foodtable
                            String strFood= object.getString("Food");
                            String strSource = object.getString("Source");
                            String strPrice = object.getString("Price");
                            objMangerTable.addValueFood(strFood, strSource, strPrice);

                            break;

                    }//switch
                }

            } catch (Exception e) {
                Log.d("Rest", "Update==>" + e.toString());

            }

            inttable += 1;
        }//while




    }//sysJSontoSQLie

    private void deleteALLSQLite() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("Restaurant.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("userTable", null, null);
        objSqLiteDatabase.delete("FooTable", null, null);

    }


    private void testerAddValue() {
        objMangerTable.addValueTouser("User", "Pass", "ศิริพงษ์");
        objMangerTable.addValueFood("Food", "Soune", "price");
    }
}//Main class
