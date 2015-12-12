package spcgroup.siripongss.myrestaurant;

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
        testerAddValue();

    }//Main Method
    private void testerAddValue() {
        objMangerTable.addValueTouser("User", "Pass", "ศิริพงษ์");
        objMangerTable.addValueFood("Food", "Soune", "price");
    }
}//Main class
