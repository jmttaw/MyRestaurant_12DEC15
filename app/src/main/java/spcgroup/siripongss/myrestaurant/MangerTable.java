package spcgroup.siripongss.myrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.security.Provider;

/**
 * Created by siripong.ss on 12/12/2558.
 */
public class MangerTable {

    //EXPLICTT

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase weiteSqLiteDatabase,readSqLiteDatabase;



    public MangerTable(Context context) {


        //connected Database

        objMyOpenHelper = new MyOpenHelper(context);
        weiteSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    }//Constructor


    //Main Class
}
