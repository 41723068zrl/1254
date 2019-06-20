package cn.edu.swufe.newwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public RecordManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(RecordItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Record", item.getRecord());
        db.insert(TBNAME, null, values);
        db.close();

    }

    public void addAll(List<RecordItem> list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (RecordItem item : list) {
            ContentValues values = new ContentValues();
            values.put("Record", item.getRecord());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }


    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<RecordItem> ListAll() {
        List<RecordItem> recordList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);

        if (cursor != null) {
            recordList = new ArrayList<RecordItem>();
            while (cursor.moveToNext()) {
                RecordItem item = new RecordItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setRecord(cursor.getString(cursor.getColumnIndex("RECORD")));
                recordList.add(item);
            }
            cursor.close();
        }
        db.close();
        return recordList;
    }
}