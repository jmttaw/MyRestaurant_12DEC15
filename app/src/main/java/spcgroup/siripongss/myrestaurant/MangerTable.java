package spcgroup.siripongss.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.Provider;

/**
 * Created by siripong.ss on 12/12/2558.
 */
public class MangerTable {

    //EXPLICTT

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase weiteSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_USER = "userTable";
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_User = "User";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_Name = "Name";


    public static final String TABLE_FOOD = "FooTable";
    public static final String COLUMN_Food = "Food";
    public static final String COLUMN_Source = "Source";
    public static final String COLUMN_Price = "Price";



    public MangerTable(Context context) {


        //connected Database

        objMyOpenHelper = new MyOpenHelper(context);
        weiteSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public String[] readAlldata(int intColum) {

        String[] strResult = null;
        Cursor objCursor = readSqLiteDatabase.query(TABLE_FOOD,
                new String[]{COLUMN_id,COLUMN_Food,COLUMN_Source,COLUMN_Price},
                null,null,null,null,null);

        if (objCursor !=null) {
            objCursor.moveToFirst();
            strResult = new String[objCursor.getCount()];
           for (int i=0;i<objCursor.getCount();i++) {

               switch (intColum) {
                   case 1:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_Food));
                       break;
                   case 2:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_Source));
                       break;
                   case 3:
                       strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_Price));
                       break;
               }//switch

               objCursor.moveToNext();


           }//for

        }//if

        objCursor.close();
        return strResult;
    }


    public String[] searchuser(String strUser) {

        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_USER,
                    new String[]{COLUMN_id,COLUMN_Name,COLUMN_Password,COLUMN_Name},
                    COLUMN_User + "=?",
                    new String[]{String.valueOf(strUser)},
                    null,null,null,null);

            if (objCursor !=null) {

                if (objCursor.moveToFirst()) {
                    strResult = new String[4];
                    for(int i=0;i<4;i++) {
                        strResult[i] = objCursor.getString(i);

                    }


                } //if 2

            }//if 1

            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }


     //   return new String[0];
    }

    public long addValueTouser(String strUser, String strpassword, String strname) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_User, strUser);
        objContentValues.put(COLUMN_Password, strpassword);
        objContentValues.put(COLUMN_Name, strname);


        return weiteSqLiteDatabase.insert(TABLE_USER, null, objContentValues);
    }

    public long addValueFood(String strfood, String strSource, String strPrice) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_Food, strfood);
        objContentValues.put(COLUMN_Source, strSource);
        objContentValues.put(COLUMN_Price, strPrice);
        return weiteSqLiteDatabase.insert(TABLE_FOOD, null, objContentValues);

    }


    //Main Class
}
