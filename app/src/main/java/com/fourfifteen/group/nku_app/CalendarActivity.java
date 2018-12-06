package com.fourfifteen.group.nku_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    CalendarView calendar;
    TextView currentDate;
    SQLiteAssetHelper databaseHelper;
    private DatabaseQuery mQuery;
    private Context context;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = context;
        DatabaseObject databaseObject = new DatabaseObject(getApplicationContext());
        databaseObject.getDbConnection();
         mQuery = new DatabaseQuery(getApplicationContext());
         List<EventObjects> mEvents = mQuery.getAllFutureEvents();
        //databaseObject.removeEvent("12-10-2018");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarCustomView mView = (CalendarCustomView) findViewById(R.id.calendar);
        mEvents = mQuery.getAllFutureEvents();

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
                                Intent intent = new Intent(CalendarActivity.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.googleMap:
                                Intent mapIntent = new Intent(CalendarActivity.this, MapsActivity.class);
                                startActivity(mapIntent);
                                return true;

                            case R.id.calendar:
                                Intent calendarIntent = new Intent(CalendarActivity.this, CalendarActivity.class);
                                startActivity(calendarIntent);
                                return true;

                            case R.id.tasks:
                                Intent tasksIntent = new Intent(CalendarActivity.this, TasksActivity.class);
                                startActivity(tasksIntent);
                                return true;

                            case R.id.directory:
                                Intent directoryIntent = new Intent(CalendarActivity.this, DirectorySearchActivity.class);
                                startActivity(directoryIntent);
                                return true;

                            case R.id.parking:
                                Intent parkingIntent = new Intent(CalendarActivity.this, Parking.class);
                                startActivity(parkingIntent);
                                return true;

                            case R.id.home:
                                Intent home = new Intent(CalendarActivity.this, MainActivity.class);
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
        actionbar.setTitle("Calendar");







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
}