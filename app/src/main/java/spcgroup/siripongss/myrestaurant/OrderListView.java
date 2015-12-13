package spcgroup.siripongss.myrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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


    }//Main Method

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

