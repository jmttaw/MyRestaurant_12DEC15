package spcgroup.siripongss.myrestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class OrderListView extends AppCompatActivity {
    //Explict

    private TextView officerTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString,deskString,foodString, itemString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_view);


        //Bind widget
        bindwiget();

        //Show Officer
        showofficer();

        //Create spinner
        createspinner();

        //create Listview
        creatrLisview();
    }//Main Method

    private void creatrLisview() {

        MangerTable objMangerTable = new MangerTable(this);

        final String[] strfood = objMangerTable.readAlldata(1);
        String[] strSource = objMangerTable.readAlldata(2);
        String[] strprice = objMangerTable.readAlldata(3);

        FoodAdapter objFoodAdapter = new FoodAdapter(OrderListView.this, strfood, strSource, strprice);
        foodListView.setAdapter(objFoodAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                foodString = strfood[i];

                chooseitem();
            }
        });

    }//cretelisview

    private void chooseitem() {
        CharSequence[] objCharSequences = {"1 Set","2 Set","3 Set"};
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(foodString);
        objBuilder.setSingleChoiceItems(objCharSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemString = Integer.toString(i + 1);

                dialogInterface.dismiss();
                //update Mysql
                updateMySql();
            }//Event
        });
        objBuilder.show();
    }

    private void updateMySql() {

        StrictMode.ThreadPolicy myPolice = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolice);




        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePairs.add(new BasicNameValuePair("Officer", officerString));
            objNameValuePairs.add(new BasicNameValuePair("Desk",deskString));
            objNameValuePairs.add(new BasicNameValuePair("Food", foodString));
            objNameValuePairs.add(new BasicNameValuePair("Item", itemString));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/12dec/php_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs,"UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(OrderListView.this,"Update Successful",Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Log.d("Rest", "Mysql==>" + e.toString());
            MyAlertDialng objAlertDialng = new MyAlertDialng();
            objAlertDialng.errorDialog(OrderListView.this, "Error", "Cannot update New Value to Mysql");
        }

    }//updatetSql

    private void createspinner() {
        //setup Desk

        final String[] strMyDesk = new String[10];
        strMyDesk[0] = "โต๊ะที่ 1";
        strMyDesk[1] = "โต๊ะที่ 2";
        strMyDesk[2] = "โต๊ะที่ 3";
        strMyDesk[3] = "โต๊ะที่ 4";
        strMyDesk[4] = "โต๊ะที่ 5";
        strMyDesk[5] = "โต๊ะที่ 6";
        strMyDesk[6] = "โต๊ะที่ 7";
        strMyDesk[7] = "โต๊ะที่ 8";
        strMyDesk[8] = "โต๊ะที่ 9";
        strMyDesk[9] = "โต๊ะที่ 10";

        ArrayAdapter<String> objadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strMyDesk);
        deskSpinner.setAdapter(objadapter);


        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deskString = strMyDesk[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                deskString = strMyDesk[0];
            }
        });

    }//sppinner

    private void showofficer() {
        officerString = getIntent().getStringExtra("Officer");
        officerTextView.setText("Welcome " + officerString);
    }

    private void bindwiget() {
        officerTextView = (TextView) findViewById(R.id.textView);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);


    }
}//Main Class

