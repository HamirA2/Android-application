package com.example.cs360projecttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Database for storing daily weight, goal weight, date, and account information
public class DBHandler extends SQLiteOpenHelper {

    // Variables for declaring database and column names, and version of database
    private static final String DATABASE_NAME = "weight.db";

    private static final int VERSION = 1;

    private static final String TABLE_NAME = "progress";

    private static final String ID_COL = "id";

    private static final String DAILY_WEIGHT_COL = "dailyweight";

    private static final String GOAL_WEIGHT_COL = "goalweight";

    private static final String DATE_COL = "date";

    private static final String ACCOUNT_COL = "account";

    private static final String PASWRD_COL = "password";

    // Database constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + " (" + ID_COL + " integer primary key autoincrement, "
                + DAILY_WEIGHT_COL + " text, " + GOAL_WEIGHT_COL + " text, " + DATE_COL + " text, "
                + ACCOUNT_COL + " text, " + PASWRD_COL + " text)";

        db.execSQL(query);
    }

    // Function for adding data to the weight database
    public void addWeight(String dailyWeight, String goalWeight, String account,
                          String password) {
        // Variable to write data into database
        SQLiteDatabase db = this.getWritableDatabase();

        // Date format to format the date as a string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdf.format(new Date());

        // Variable to set content values
        ContentValues values = new ContentValues();

        // Insert values with key and value pair
        values.put(DAILY_WEIGHT_COL, dailyWeight);
        values.put(GOAL_WEIGHT_COL, goalWeight);
        values.put(DATE_COL, formatDate);
        values.put(ACCOUNT_COL, account);
        values.put(PASWRD_COL, password);

        // Pass values to the table
        db.insert(TABLE_NAME, null, values);

        // Close the database
        db.close();
    }

    // Function for reading values in the database
    public ArrayList<DBValues> readWeight() {
        // Get readable data from database
        SQLiteDatabase db = this.getReadableDatabase();

        // Set cursor to query the progress data table
        Cursor cursorWeight = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // Create new array list
        ArrayList<DBValues> weightArrayList = new ArrayList<DBValues>();

        // Move cursor to first position if it is applicable
        if (cursorWeight.moveToFirst()) {
            // Store string values while cursor has next position to move to
            do {
                weightArrayList.add(new DBValues(cursorWeight.getString(1),
                                                 cursorWeight.getString(2),
                                                 cursorWeight.getString(3),
                                                 cursorWeight.getString(4),
                                                 cursorWeight.getString(5)));
            } while (cursorWeight.moveToNext());
        }
        // Close the cursor
        cursorWeight.close();

        // Return the values of the array list
        return weightArrayList;
    }

    // Function for updating daily or goal weight, while changing the date within the weight database
    public boolean updateWeight(String originalDailyWeight, String dailyWeight, String goalWeight) {
        // Variable to write data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Date format to format the date as a string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdf.format(new Date());

        // Variable to set content value
        ContentValues values = new ContentValues();

        values.put(DAILY_WEIGHT_COL, dailyWeight);
        values.put(GOAL_WEIGHT_COL, goalWeight);
        values.put(DATE_COL, formatDate);

        // Update row with values
        int rowUpdated = db.update(TABLE_NAME, values, "dailyweight=?",
                                   new String[] {originalDailyWeight});
        // Close database
        db.close();

        // Return true or false if 1 row was updated
        return rowUpdated < 0;
    }

    // Function used to delete weight data from the database
    public boolean deleteWeight(String weightRow) { // String weightRow
        // Variable to alter data within the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete weight data in table using the row id
        int rowDeleted = db.delete(TABLE_NAME, "dailyweight=?",
                                   new String[] {weightRow});
        // Close database
        db.close();

        // Return true if 1 item was deleted
        return rowDeleted > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
