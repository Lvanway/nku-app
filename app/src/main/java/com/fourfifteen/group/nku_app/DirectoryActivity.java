package com.fourfifteen.group.nku_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
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
                                Intent intent = new Intent(DirectoryActivity.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.googleMap:
                                Intent mapIntent = new Intent(DirectoryActivity.this, MapsActivity.class);
                                startActivity(mapIntent);
                                return true;

                            case R.id.calendar:
                                Intent calendarIntent = new Intent(DirectoryActivity.this, CalendarActivity.class);
                                startActivity(calendarIntent);
                                return true;

                            case R.id.tasks:
                                Intent tasksIntent = new Intent(DirectoryActivity.this, TasksActivity.class);
                                startActivity(tasksIntent);
                                return true;

                            case R.id.directory:
                                Intent directoryIntent = new Intent(DirectoryActivity.this, DirectorySearchActivity.class);
                                startActivity(directoryIntent);
                                return true;

                            case R.id.parking:
                                Intent parkingIntent = new Intent(DirectoryActivity.this, Parking.class);
                                startActivity(parkingIntent);
                                return true;

                            case R.id.home:
                                Intent home = new Intent(DirectoryActivity.this, MainActivity.class);
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
        actionbar.setTitle("Directory");


        Bundle bundle = this.getIntent().getExtras();
        ArrayList<String> searchResults = bundle.getStringArrayList("filteredList");

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, searchResults);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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