package spcgroup.siripongss.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MangerTable objMangerTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Explicit

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connectDatabase
        objMangerTable = new MangerTable(this);

        //testerAddValue();

        //deleteALlSqlite

        deleteALLSQLite();

    }//Main Method

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
