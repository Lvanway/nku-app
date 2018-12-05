package com.fourfifteen.group.nku_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectorySearchActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    public LinkedList<Student> directory = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_search);


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
                                Intent intent = new Intent(DirectorySearchActivity.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.googleMap:
                                Intent mapIntent = new Intent(DirectorySearchActivity.this, MapsActivity.class);
                                startActivity(mapIntent);
                                return true;

                            case R.id.calendar:
                                Intent calendarIntent = new Intent(DirectorySearchActivity.this, CalendarActivity.class);
                                startActivity(calendarIntent);
                                return true;

                            case R.id.tasks:
                                Intent tasksIntent = new Intent(DirectorySearchActivity.this, TasksActivity.class);
                                startActivity(tasksIntent);
                                return true;

                            case R.id.directory:
                                Intent directoryIntent = new Intent(DirectorySearchActivity.this, DirectorySearchActivity.class);
                                startActivity(directoryIntent);
                                return true;

                            case R.id.parking:
                                Intent parkingIntent = new Intent(DirectorySearchActivity.this, Parking.class);
                                startActivity(parkingIntent);
                                return true;

                            case R.id.home:
                                Intent home = new Intent(DirectorySearchActivity.this, MainActivity.class);
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







        Button button = findViewById(R.id.searchButton);

        createDirectory();

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(DirectorySearchActivity.this,
                        DirectoryActivity.class);

                ArrayList<String> searchResults = getSearchResults();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filteredList", searchResults);
                //bundle.putStringArray("filteredList", searchResults);
                myIntent.putExtras(bundle);

                startActivity(myIntent);
            }
        });

    }

    private void createDirectory() {
        Student Evan = new Student(), James = new Student(), Jake = new Student(), Garrett = new Student(), Logan = new Student(), Joseph = new Student(), Micheal = new Student();
        Evan.setStudent("Evan", "Berkemeyer", "berkemeyee1@nku.edu");
        James.setStudent("James", "Kennedy", "kennedyj16@nku.edu");
        Jake.setStudent("Jake", "Frommeyer", "frommeyerj4@nku.edu");
        Garrett.setStudent("Garrett", "Foister", "foisterg1@nku.edu");
        Logan.setStudent("Logan", "Vanway", "wanwayl1@nku.edu");
        Joseph.setStudent("Joseph", "Wernke", "wernkej11@nku.edu");
        Micheal.setStudent("Micheal", "Clark", "clarkm4@nku.edu");

        directory.add(Evan);
        directory.add(James);
        directory.add(Jake);
        directory.add(Garrett);
        directory.add(Logan);
        directory.add(Joseph);
        directory.add(Micheal);
    }

    private ArrayList<String> getSearchResults() {
        ArrayList<String> searchResults = new ArrayList<>();

        String fName = ((EditText)findViewById(R.id.searchFirstName)).getText().toString();
        String lName = ((EditText)findViewById(R.id.searchLastName)).getText().toString();

        if (fName.length() > 0 && lName.length() == 0){
            for (int i = 0; i < directory.size(); i++){
                if (directory.get(i).firstName.toLowerCase().contains(fName.toLowerCase())){
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        } else if (fName.length() == 0 && lName.length() > 0) {
            //put code for only last name search here
            for (int i = 0; i < directory.size(); i++){
                if (directory.get(i).lastName.toLowerCase().contains(lName.toLowerCase())){
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        } else if (fName.length() > 0 && lName.length() > 0) {
            //put code for first and last name search here
            for (int i = 0; i < directory.size(); i++){
                if (directory.get(i).firstName.toLowerCase().contains(fName.toLowerCase()) && directory.get(i).lastName.toLowerCase().contains(lName.toLowerCase())){
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        }

        return searchResults;
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
