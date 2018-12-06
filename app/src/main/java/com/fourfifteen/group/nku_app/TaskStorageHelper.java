package com.fourfifteen.group.nku_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskStorageHelper extends SQLiteOpenHelper {

    public TaskStorageHelper(Context context) {
        super(context, TaskStorage.DB_NAME, null, TaskStorage.DB_VERSION); {
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = (" CREATE TABLE " + TaskStorage.TaskEntry.TABLE + " ( " +
                TaskStorage.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskStorage.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);");

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskStorage.TaskEntry.TABLE);
        onCreate(db);
    }
}
