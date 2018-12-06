package com.fourfifteen.group.nku_app;

        import android.content.ContentValues;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;

        import java.util.ArrayList;
        import java.util.LinkedList;

public class TasksActivity extends AppCompatActivity {

    private static final String TAG = "TasksActivity";

    private TaskStorageHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;

    FloatingActionButton addTaskButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tasks);
        mHelper = new TaskStorageHelper(TasksActivity.this);
        mTaskListView = (ListView)findViewById(R.id.taskList);
        addTaskButton = findViewById (R.id.addTaskButton);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        int id = menuItem.getItemId();
                        switch(id){

                            case R.id.sign_out:
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent intent = new Intent(TasksActivity.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.googleMap:
                                Intent mapIntent = new Intent(TasksActivity.this, MapsActivity.class);
                                startActivity(mapIntent);
                                return true;

                            case R.id.calendar:
                                Intent calendarIntent = new Intent(TasksActivity.this, CalendarActivity.class);
                                startActivity(calendarIntent);
                                return true;

                            case R.id.tasks:
                                Intent tasksIntent = new Intent(TasksActivity.this, TasksActivity.class);
                                startActivity(tasksIntent);
                                return true;

                            case R.id.directory:
                                Intent directoryIntent = new Intent(TasksActivity.this, DirectorySearchActivity.class);
                                startActivity(directoryIntent);
                                return true;

                            case R.id.parking:
                                Intent parkingIntent = new Intent(TasksActivity.this, Parking.class);
                                startActivity(parkingIntent);
                                return true;

                            case R.id.home:
                                Intent home = new Intent(TasksActivity.this, MainActivity.class);
                                startActivity(home);
                                return true;
                        }




                        menuItem.setChecked(true);

                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setTitle("Tasks");


        SQLiteDatabase db = mHelper.getReadableDatabase ();
        Cursor cursor = db.query(TaskStorage.TaskEntry.TABLE,
                new String[]{TaskStorage.TaskEntry._ID,
        TaskStorage.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
            while(cursor.moveToNext()) {
                int index = cursor.getColumnIndex (TaskStorage.TaskEntry.COL_TASK_TITLE);
                Log.d (TAG, "Task: " + cursor.getString (index));
            }

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final EditText taskEditText = new EditText(TasksActivity.this);
               AlertDialog alertDialog = new AlertDialog.Builder(TasksActivity.this).create();
               alertDialog.setTitle("Create a Task");
               alertDialog.setMessage("What would you like to add?");
               alertDialog.setView(taskEditText);
               alertDialog.setButton (DialogInterface.BUTTON_POSITIVE, "Create", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       String task = String.valueOf (taskEditText.getText ());
                       SQLiteDatabase db = mHelper.getWritableDatabase();
                       ContentValues values = new ContentValues();
                       values.put(TaskStorage.TaskEntry.COL_TASK_TITLE, task);
                       db.insertWithOnConflict(TaskStorage.TaskEntry.TABLE,
                               null,
                               values,
                               SQLiteDatabase.CONFLICT_REPLACE);
                       db.close();
                       updateUI();


                   }
               });
               alertDialog.setButton (DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
               });
               alertDialog.create();

               alertDialog.show();
            }
        });
            cursor.close();
            db.close();
            updateUI();
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskStorage.TaskEntry.TABLE,
                new String[]{TaskStorage.TaskEntry._ID,
                TaskStorage.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex(TaskStorage.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(index));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<> (this,
                    R.layout.tasklist_item,
                    R.id.taskTextView,
                    taskList);
            mTaskListView.setAdapter (mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void clearTasks(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.taskTextView);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskStorage.TaskEntry.TABLE,
                TaskStorage.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            //case android.R.id.
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
