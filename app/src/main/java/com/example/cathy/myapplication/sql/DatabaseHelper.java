package com.example.cathy.myapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cathy.myapplication.model.User;
import com.example.cathy.myapplication.model.Profile;

import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {

    //version, database name and table name
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "UserManager.db";


    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String TABLE_PROFILE = "profile";
    private static final String COLUMN_PROFILE_ID = "profile_id";
    private static final String COLUMN_PROFILE_NAME = "profile_name";
    private static final String COLUMN_PROFILE_DAM = "profile_dam";
    private static final String COLUMN_PROFILE_SIRE = "profile_sire";
    private static final String COLUMN_PROFILE_AGE = "profile_age";
    private static final String COLUMN_PROFILE_GENDER = "profile_gender";
    private static final String COLUMN_PROFILE_PADDOCK = "profile_paddock";

    // create table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    //creating profiles table
    private String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "(" + COLUMN_PROFILE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PROFILE_NAME + " TEXT," + COLUMN_PROFILE_DAM
            + " TEXT," + COLUMN_PROFILE_SIRE + " TEXT," + COLUMN_PROFILE_AGE + " TEXT," + COLUMN_PROFILE_GENDER + " TEXT,"
            + COLUMN_PROFILE_PADDOCK + " TEXT" + ")";

    // drop table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PROFILE_TABLE = "DROP TABLE IF EXISTS " + TABLE_PROFILE;

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist and create table again
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PROFILE_TABLE);
        onCreate(db);

    }

    //creates user record
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //returns a list of all users
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting by ascending order
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // queries for the user table

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    //update user record

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    // delete user record

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    // check user exists or not

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

   /* public static void changePassword(String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE "+DatabaseHelper.TABLE_USER+" SET "
                +DatabaseHelper.COLUMN_USER_PASSWORD
                +" = '"+password+"' WHERE "+ DatabaseHelper.COLUMN_USER_EMAIL+" = ?",new String[]{email}); *This is the input on form
    }*/
    // ----------------- PROFILE TABLE METHODS ---------------------------

    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_NAME, profile.getName());
        values.put(COLUMN_PROFILE_SIRE, profile.getSire());
        values.put(COLUMN_PROFILE_DAM, profile.getDam());
        values.put(COLUMN_PROFILE_AGE, profile.getAge());
        values.put(COLUMN_PROFILE_GENDER, profile.getGender());
        values.put(COLUMN_PROFILE_PADDOCK, profile.getPaddock());

        // Inserting Row
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public List<Profile> getAllProfile() {
        // creating a new list to retrieve horse details
        String[] columns = {
                COLUMN_PROFILE_ID,
                COLUMN_PROFILE_NAME,
                COLUMN_PROFILE_DAM,
                COLUMN_PROFILE_SIRE,
                COLUMN_PROFILE_AGE,
                COLUMN_PROFILE_GENDER,
                COLUMN_PROFILE_PADDOCK
        };
        // sorting by ascending order
        String sortOrder =
                COLUMN_PROFILE_ID + " ASC";
        List<Profile> profileList = new ArrayList<Profile>(); //creating instance

        SQLiteDatabase db = this.getReadableDatabase();

        // queries for the profile table

        Cursor cursor = db.query(TABLE_PROFILE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list, calling setter methods
        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile();
                profile.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_ID))));
                profile.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_NAME)));
                profile.setDam(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_DAM)));
                profile.setSire(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_SIRE)));
                profile.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_AGE)));
                profile.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_GENDER)));
                profile.setPaddock(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_PADDOCK)));
                // Adding user record to list
                profileList.add(profile); //add objects to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return profile list
        return profileList;
    }

}

