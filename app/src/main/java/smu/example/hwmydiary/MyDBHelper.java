package smu.example.hwmydiary;

import static smu.example.hwmydiary.GaMainActivity.KEY_CONTEXT;
import static smu.example.hwmydiary.GaMainActivity.KEY_DATE;
import static smu.example.hwmydiary.GaMainActivity.KEY_ID;
import static smu.example.hwmydiary.GaMainActivity.KEY_PRICE;
import static smu.example.hwmydiary.GaMainActivity.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context) {
        super(context, "My_Account_Data.db", null, 4);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s ("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "%s TEXT, "
                + "%s INTEGER, "
                + "%s TEXT);", TABLE_NAME, KEY_ID, KEY_CONTEXT, KEY_PRICE, KEY_DATE);
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }
}