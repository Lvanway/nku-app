package com.fourfifteen.group.nku_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class DatabaseObject {
    private static Database dbHelper;
    private SQLiteDatabase db;

    public DatabaseObject(Context context) {
        dbHelper = new Database(context);
        this.dbHelper.getWritableDatabase();
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection() {
        return this.db;
    }

    public void closeDbConnection() {
        if (this.db != null) {
            this.db.close();
        }
    }

    public void insertValues(String description, String date, String time, String eventType) {
        ContentValues values = new ContentValues();
        values.put("description", description);
        values.put("date", date);
        values.put("time", time);
        values.put("eventType", eventType);
        db.insert("events", null, values);
    }

    public void removeEvent(int id) {
        db.delete("events", "id = ?", new String[]{String.valueOf(id)});
    }

    public void removeEvent(String date) {
        db.delete("events", "date = ?", new String[]{date});
    }
}


