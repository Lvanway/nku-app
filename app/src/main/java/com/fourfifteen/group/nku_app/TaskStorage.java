package com.fourfifteen.group.nku_app;

import android.provider.BaseColumns;

public class TaskStorage {
    public static final String DB_NAME = "com.fourfifteen.group.nku_app.taskstorage";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}
