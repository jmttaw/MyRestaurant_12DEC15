package spcgroup.siripongss.myrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by siripong.ss on 12/12/2558.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    private static final String DATABSAE_NAME = "Restaurant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_USER = "create table userTable (" +
            "_id integer primary key," +
            "User text," +
            "Password text," +
            "Name text);";
    private static final String CREATE_TABLE_FOOD="create table FooTable(" +
            "_Id integer primary key," +
            "Food text ," +
            "Source text," +
            "Price text);";

    public MyOpenHelper(Context context) {
        super(context,DATABSAE_NAME,null,DATABASE_VERSION);
    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_FOOD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Main Class

}
