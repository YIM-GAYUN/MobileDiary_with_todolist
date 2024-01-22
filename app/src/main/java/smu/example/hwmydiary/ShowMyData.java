// 메모장
package smu.example.hwmydiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowMyData extends Activity {

    int nowData = 0;
    Cursor cursor;
    TextView date;
    TextView t1;
    String diary_content;
    String diary_date;
    int numburOfData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();

            // 커서 만들어서 불러오기
            cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            numburOfData = cursor.getCount();
            cursor.moveToFirst();

            if (numburOfData == 0) nowData = 0;
            else nowData = 1;

            if (cursor.getCount() > 0) {
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();


        } catch (SQLiteException e) {
        }
        date.setText(diary_content);
        t1.setText(diary_date);
    }

    public void nextData(View v) {
        try {
            DBManager dbmgr = new DBManager(this);

            SQLiteDatabase sdb = dbmgr.getReadableDatabase();

            cursor = sdb.query("diaryTB", null, null, null, null, null, null);

            if (numburOfData == 0) nowData = 0;
            if (cursor.getCount() > 0 && nowData <= numburOfData) {
                nowData += 1;
                if (nowData >= numburOfData) nowData = numburOfData;
                cursor.moveToPosition(nowData - 1);
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e) {
        }
        date.setText(diary_content);
        t1.setText(diary_date);
    }

    public void previousData(View v) {
        try {
            DBManager dbmgr = new DBManager(this);

            SQLiteDatabase sdb = dbmgr.getReadableDatabase();

            cursor = sdb.query("diaryTB", null, null, null, null, null, null);


            if (numburOfData == 0) nowData = 0;
            if (cursor.getCount() > 0 && nowData > 1) {
                nowData -= 1;
                if (nowData <= 1) nowData = 1;
                cursor.moveToPosition(nowData - 1);
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();

        } catch (SQLiteException e) {
        }
        date.setText(diary_content);
        t1.setText(diary_date);
    }

    public void modifyData(View v) {
        Intent it = new Intent();
        it = new Intent(this, ModifyMyData.class);
        String msg = nowData + "";
        it.putExtra("it_name", msg);
        startActivity(it);
        finish();
    }
}