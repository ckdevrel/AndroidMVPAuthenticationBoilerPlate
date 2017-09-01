package com.takeoffandroid.mvpauthentication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.takeoffandroid.mvpauthentication.models.Authentication;

import java.util.ArrayList;


public class DBAccess extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME="database";

    //UserMaster Table
    public static final String TABLE_AUTHENTICATION = "authenticationtable";

    public static final String KEY_ID = "id";

    public static final String KEY_FIRST_NAME = "firstname";

    public static final String KEY_LAST_NAME = "lastname";

    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_USER_TYPE = "usertype";


    public static final String CREATE_TABLE_AUTHENTICATION =
            "CREATE TABLE "+ TABLE_AUTHENTICATION +" ("+
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_FIRST_NAME + " TEXT, "+
                    KEY_LAST_NAME + " TEXT, "+

                    KEY_MOBILE + " TEXT, "+
                    KEY_EMAIL + " TEXT, "+
                    KEY_PASSWORD + " TEXT, "+

                    KEY_USER_TYPE + " TEXT)";


    public static final String[] COLUMNS_AUTHENTICATION = {
            KEY_ID,
            KEY_FIRST_NAME,
            KEY_LAST_NAME,
            KEY_MOBILE,
            KEY_EMAIL,
            KEY_PASSWORD,
            KEY_USER_TYPE};

    private static DBAccess mInstance;

    public DBAccess (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBAccess init(Context context) {


        if (mInstance == null)
            synchronized (DBAccess.class) {
                if (mInstance == null) {
                    mInstance = new DBAccess(context);

                }
            }
        return mInstance;
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL (CREATE_TABLE_AUTHENTICATION);


    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public void insertUserMasterObject (Authentication authentication){

        SQLiteDatabase db = getWritableDatabase ();
        String INSERT = "insert into " + TABLE_AUTHENTICATION + " ("+KEY_FIRST_NAME + ","+ KEY_LAST_NAME+ ","+ KEY_MOBILE+","+ KEY_EMAIL+","+KEY_PASSWORD+","+ KEY_USER_TYPE+")" + " values (?,?,?,?,?,?)";
        SQLiteStatement insertStatement = db.compileStatement (INSERT);
        insertStatement.bindString (1,authentication.getFirstName ());
        insertStatement.bindString (2,authentication.getLastName ());

        insertStatement.bindString (3,authentication.getMobile ());
        insertStatement.bindString (4,authentication.getEmail ());
        insertStatement.bindString (5,authentication.getPassword ());
        insertStatement.bindString (6,authentication.getUserType ());

        insertStatement.executeInsert ();
        insertStatement.clearBindings ();
        db.close ();
        close();
    }

    public Authentication getAuthenticationDetails () {

        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor cursor = db.query (TABLE_AUTHENTICATION, // a. table
                COLUMNS_AUTHENTICATION, // b. column names
                " id = ?",
                new String[] { String.valueOf(1)},
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor != null )
            cursor.moveToFirst ();

        if(cursor.getCount ()>0) {
            Authentication authentication = new Authentication ();
            authentication.setFirstName (cursor.getString (1));
            authentication.setLastName (cursor.getString (2));

            authentication.setMobile (cursor.getString (3));
            authentication.setEmail (cursor.getString (4));
            authentication.setPassword (cursor.getString (5));
            authentication.setUserType (cursor.getString (6));

            return authentication;
        }else
            return null;
    }

    public Authentication getAuthenticationDetails (String COLUMN_NAME, String COLUMN_VALUE) {

        SQLiteDatabase db = this.getReadableDatabase ();

        Cursor cursor = db.query (TABLE_AUTHENTICATION, // a. table
                COLUMNS_AUTHENTICATION, // b. column names
                COLUMN_NAME+ " = ?", // c. selections
                new String[]{String.valueOf (COLUMN_VALUE)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor != null )
            cursor.moveToFirst ();

        if(cursor.getCount ()>0) {
            Authentication authentication = new Authentication ();
            authentication.setFirstName (cursor.getString (1));
            authentication.setLastName (cursor.getString (2));

            authentication.setMobile (cursor.getString (3));
            authentication.setEmail (cursor.getString (4));
            authentication.setPassword (cursor.getString (5));
            authentication.setUserType (cursor.getString (6));

            return authentication;
        }else
            return null;
    }


    public void insertUserMasterList (ArrayList<Authentication> authList){

        SQLiteDatabase db = getWritableDatabase ();
        String INSERT = "insert into " + TABLE_AUTHENTICATION + " ("+KEY_FIRST_NAME + ","+KEY_LAST_NAME+","+ KEY_MOBILE+","+ KEY_EMAIL+","+KEY_PASSWORD+","+ KEY_USER_TYPE+")" + " values (?,?,?,?,?,?)";
        SQLiteStatement insertStatement = db.compileStatement (INSERT);
        for(Authentication authentication: authList) {
            insertStatement.bindString (1, authentication.getFirstName ());
            insertStatement.bindString (2, authentication.getLastName ());

            insertStatement.bindString (3, authentication.getMobile ());
            insertStatement.bindString (4, authentication.getEmail ());
            insertStatement.bindString (5, authentication.getPassword ());
            insertStatement.bindString (6, authentication.getUserType ());

            insertStatement.executeInsert ();
            insertStatement.clearBindings ();
        }

        db.close ();
        close();
    }


    public ArrayList<Authentication> getUserMasterList(){

        ArrayList<Authentication> allDetails = new ArrayList<Authentication> ();
        SQLiteDatabase db = getReadableDatabase ();
        Cursor cursor = db.query (TABLE_AUTHENTICATION, null, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst ()) {
            if(cursor.getCount () > 0) {
                do{
                    Authentication authentication = new Authentication ();
                    authentication.setFirstName (cursor.getString (1));
                    authentication.setLastName (cursor.getString (2));

                    authentication.setMobile (cursor.getString (3));
                    authentication.setEmail (cursor.getString (4));
                    authentication.setPassword (cursor.getString (5));
                    authentication.setUserType (cursor.getString (6));

                    allDetails.add (authentication);
                }while (cursor.moveToNext ());

            }
        }
        cursor.close ();
        db.close ();
        close ();
        return allDetails;
    }

    public void updatePassword(String mobile, String oldPassword, String newPassword){

        SQLiteDatabase db = getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put (KEY_PASSWORD,newPassword);
        db.update (TABLE_AUTHENTICATION, values, KEY_MOBILE + "=?" + " AND " + KEY_PASSWORD + "=?", new String[]{ mobile, oldPassword });
        db.close ();
        close ();
    }


    public void updateMobile(String email, String oldMobileNumber, String newMobileNumber){

        SQLiteDatabase db = getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put (KEY_MOBILE,newMobileNumber);
        db.update (TABLE_AUTHENTICATION, values, KEY_EMAIL + "=?" + " AND " + KEY_MOBILE + "=?", new String[]{ email, oldMobileNumber });
        db.close ();
        close ();
    }


    //Get password based on particular username
    public String getPasswordFromMobile(String mobile){

        String password = "";
        SQLiteDatabase db = getReadableDatabase ();
        Cursor cursor = db.query (TABLE_AUTHENTICATION, new String[]{ KEY_PASSWORD }, KEY_MOBILE + "='" + mobile + "'", null, null, null, null);
        if(cursor != null && cursor.moveToFirst ()){
            if(cursor.getCount ()>0) {
                int columnIndex = cursor.getColumnIndex (KEY_PASSWORD);
                password = cursor.getString (columnIndex);
            }
        }

        cursor.close ();
        db.close ();
        close ();
        return password;
    }

    public boolean isVerifyUserByEmailAndPassword (String email, String password){

        SQLiteDatabase db = getReadableDatabase ();
        String Query = "Select * from " + TABLE_AUTHENTICATION + " where " + KEY_EMAIL + " = " + "'"+email+"'" + " AND "+KEY_PASSWORD + " = "+"'"+password+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean isMobileAlreadyExists(String mobile){

        SQLiteDatabase db = getReadableDatabase ();
        String Query = "Select * from " + TABLE_AUTHENTICATION + " where " + KEY_MOBILE + " = " + "'"+mobile+"'";
        Cursor cursor = db.rawQuery (Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close ();
        return true;
    }


    public boolean isEmailAlreadyExists(String email){

        SQLiteDatabase db = getReadableDatabase ();
        String Query = "Select * from " + TABLE_AUTHENTICATION + " where " + KEY_EMAIL + " = " + "'"+email+"'";
        Cursor cursor = db.rawQuery (Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close ();
        return true;
    }
}
