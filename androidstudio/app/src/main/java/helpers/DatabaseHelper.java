package helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BodyBlueprint.db";
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_USER_CURRENT_WEIGHT = "user_current_weight";
    private static final String COLUMN_USER_CURRENT_WEIGHT_TYPE = "user_current_weight_type";
    private static final String COLUMN_USER_HEIGHT_FEET = "user_height_feet";
    private static final String COLUMN_USER_HEIGHT_INCHES = "user_height_inches";
    private static final String COLUMN_USER_LIFESTYLE = "user_lifestyle";
    private static final String COLUMN_USER_TARGET_WEIGHT = "user_target_weight";
    private static final String COLUMN_USER_TARGET_WEIGHT_TYPE = "user_target_weight_type";
    private static final String COLUMN_USER_BASAL_METABOLIC_RATE = "user_basal_metabolic_rate";
    private static final String COLUMN_USER_DAILY_ACTIVE_BURN = "user_daily_active_burn";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_AGE + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_CURRENT_WEIGHT + " TEXT,"
            + COLUMN_USER_CURRENT_WEIGHT_TYPE + " TEXT,"
            + COLUMN_USER_HEIGHT_FEET + " TEXT,"
            + COLUMN_USER_HEIGHT_INCHES + " TEXT,"
            + COLUMN_USER_LIFESTYLE + " TEXT,"
            + COLUMN_USER_TARGET_WEIGHT + " TEXT,"
            + COLUMN_USER_TARGET_WEIGHT_TYPE + " TEXT,"
            + COLUMN_USER_BASAL_METABOLIC_RATE + " TEXT,"
            + COLUMN_USER_DAILY_ACTIVE_BURN + " TEXT"
            + ")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_CURRENT_WEIGHT, user.getCurrentWeight());
        values.put(COLUMN_USER_CURRENT_WEIGHT_TYPE, user.getCurrentWeightType());
        values.put(COLUMN_USER_HEIGHT_FEET, user.getHeightFeet());
        values.put(COLUMN_USER_HEIGHT_INCHES, user.getHeightInches());
        values.put(COLUMN_USER_LIFESTYLE, user.getLifestyle());
        values.put(COLUMN_USER_TARGET_WEIGHT, user.getTargetWeight());
        values.put(COLUMN_USER_TARGET_WEIGHT_TYPE, user.getTargetWeightType());
        values.put(COLUMN_USER_BASAL_METABOLIC_RATE, user.getBMR());
        values.put(COLUMN_USER_DAILY_ACTIVE_BURN, user.getDAB());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_AGE,
                COLUMN_USER_GENDER,
                COLUMN_USER_CURRENT_WEIGHT,
                COLUMN_USER_CURRENT_WEIGHT_TYPE,
                COLUMN_USER_HEIGHT_FEET,
                COLUMN_USER_HEIGHT_INCHES,
                COLUMN_USER_LIFESTYLE,
                COLUMN_USER_TARGET_WEIGHT,
                COLUMN_USER_TARGET_WEIGHT_TYPE,
                COLUMN_USER_BASAL_METABOLIC_RATE,
                COLUMN_USER_DAILY_ACTIVE_BURN
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_ID + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
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
                user.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_AGE))));
                user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                user.setCurrentWeight(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CURRENT_WEIGHT))));
                user.setCurrentWeightType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CURRENT_WEIGHT_TYPE)));
                user.setHeightFeet(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HEIGHT_FEET))));
                user.setHeightInches(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HEIGHT_INCHES))));
                user.setLifestyle(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LIFESTYLE)));
                user.setTargetWeight(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TARGET_WEIGHT))));
                user.setTargetWeightType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TARGET_WEIGHT_TYPE)));
                user.setBMR(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BASAL_METABOLIC_RATE))));
                user.setDAB(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DAILY_ACTIVE_BURN))));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public boolean checkFirstUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // query user table to fetch the first user
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                null,                       //no selection criteria
                null,                       //no selection arguments
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //no sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }
//    public void addUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//        // Inserting Row
//        db.insert(TABLE_USER, null, values);
//        db.close();
//    }
//    /**
//     * This method to update user record
//     *
//     * @param user
//     */
//    public void updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//    /**
//     * This method is to delete user record
//     *
//     * @param user
//     */
//    public void deleteUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @return true/false
//     */
//    public boolean checkUser(String email) {
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?";
//        // selection argument
//        String[] selectionArgs = {email};
//        // query user table with condition
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                      //filter by row groups
//                null);                      //The sort order
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//        return false;
//    }
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @param password
//     * @return true/false
//     */
//    public boolean checkUser(String email, String password) {
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
//        // selection arguments
//        String[] selectionArgs = {email, password};
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//        return false;
//    }
}