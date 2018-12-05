package com.fourfifteen.group.nku_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Parking extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private int time;

    private String[] garages = {"Welcome Center Parking Garage", "University Drive Parking Garage", "Kenton Drive Parking Garage"};
    private static int[] garageSizes = {1200, 600, 800};
    private int whichGarage = 0;
    private boolean forApp = false;

    private UpdateSpotsEveryMinute updater = new UpdateSpotsEveryMinute();

    private Calendar rightNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        int id = menuItem.getItemId();
                        switch (id) {

                            case R.id.sign_out:
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent intent = new Intent(Parking.this, LoginActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.googleMap:
                                Intent mapIntent = new Intent(Parking.this, MapsActivity.class);
                                startActivity(mapIntent);
                                return true;

                            case R.id.calendar:
                                Intent calendarIntent = new Intent(Parking.this, CalendarActivity.class);
                                startActivity(calendarIntent);
                                return true;

                            case R.id.tasks:
                                Intent tasksIntent = new Intent(Parking.this, TasksActivity.class);
                                startActivity(tasksIntent);
                                return true;

                            case R.id.directory:
                                Intent directoryIntent = new Intent(Parking.this, DirectorySearchActivity.class);
                                startActivity(directoryIntent);
                                return true;

                            case R.id.parking:
                                Intent parkingIntent = new Intent(Parking.this, Parking.class);
                                startActivity(parkingIntent);
                                return true;

                            case R.id.home:
                                Intent home = new Intent(Parking.this, MainActivity.class);
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
        actionbar.setTitle("Parking");

        rightNow = Calendar.getInstance();
        rightNow.getTime();
        forApp = true;


        //Setting the textViews
        TextView viewDate = findViewById(R.id.textView_date);
        viewDate.setText(rightNow.getTime().toString());

        //set the first garage by default
        final TextView garageName = findViewById(R.id.textView_garageName);
        garageName.setText(garages[0]);

        //first garage by default
        final TextView garageSize = findViewById(R.id.textView_NumberSpots);
        determineSpots(whichGarage, garages[whichGarage], garageSizes[whichGarage]);
        updater.start(whichGarage, garages[whichGarage], garageSizes[whichGarage]);

        final Button prevGarageBtn = findViewById(R.id.button_garagePrevious);
        final Button nextGarageBtn = findViewById(R.id.button_garageNext);
        final Button refreshBtn = findViewById(R.id.button_refresh);


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refresh
                determineSpots(whichGarage, garages[whichGarage], garageSizes[whichGarage]);
            }
        });


        nextGarageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whichGarage < 2) {

                    if (!prevGarageBtn.isEnabled()) {
                        prevGarageBtn.setEnabled(true);
                    }
                    whichGarage++; //to figure out which garage we be on
                    determineSpots(whichGarage, garages[whichGarage], garageSizes[whichGarage]);

                    UpdateSpotsEveryMinute updater = new UpdateSpotsEveryMinute();
                    updater.stop();
                    updater.start(whichGarage,garages[whichGarage], garageSizes[whichGarage]);

                    //set the name of the garage
                    final TextView garageName = findViewById(R.id.textView_garageName);
                    garageName.setText(garages[whichGarage]);

                } else {
                    nextGarageBtn.setEnabled(false);
                }
            }
        });


        prevGarageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whichGarage > 0) {

                    if (!nextGarageBtn.isEnabled()) {
                        nextGarageBtn.setEnabled(true);
                    }

                    whichGarage--; //to figure out which garage we be on
                    determineSpots(whichGarage, garages[whichGarage], garageSizes[whichGarage]);


                    //set the name of the garage
                    final TextView garageName = findViewById(R.id.textView_garageName);
                    garageName.setText(garages[whichGarage]);

                } else {
                    prevGarageBtn.setEnabled(false);
                }
                //move onto the next garage with features  and update/refresh
            }
        });


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


    public int updateSpots(double percentTakenSpots, int whichGarage, int garageSpaces) {

        int spotsLeft = (int) (garageSpaces - (percentTakenSpots * garageSpaces));
        int randomSpots = randomizeSpots();
        spotsLeft = spotsLeft + randomSpots;

        if (spotsLeft < 0) {
            spotsLeft = 0;
        }

        if (forApp) {
            final TextView garageSize = findViewById(R.id.textView_NumberSpots);
            garageSize.setText(String.valueOf((spotsLeft)));
        }

        return spotsLeft;
    }


    public int determineSpots(int whichGarage, String garageName, int garageSize) {

        int spotsLeft = 0;
        rightNow = Calendar.getInstance();
        rightNow.getTime();

        //Calendar stuff --- might need to move -- probably will have to move


        //this means it is a weekday--classes are not normally held on a weekend so the garages will be pretty much empty if it is Sat/Sun -- at least for these assumptions
        if (rightNow.get(Calendar.DAY_OF_WEEK) != 1 || rightNow.get(Calendar.DAY_OF_WEEK) != 7) {
            ;
            //DO SOMETHIHNG WITH THREADS TO RANDOMLY CHANGE NUMBER OF SLOTS EVERY MINUTE WITH RANDOM FUNCTION OR HAVE TIMER TASK
            switch (rightNow.get(Calendar.HOUR_OF_DAY)) {
                case 7:
                    spotsLeft = updateSpots(.05, whichGarage, garageSize);
                    break;
                case 8:
                    spotsLeft = updateSpots(.20, whichGarage, garageSize);
                    break;
                case 9:
                    spotsLeft = updateSpots(.40, whichGarage, garageSize);
                    break;
                case 10:
                    spotsLeft = updateSpots(.70, whichGarage, garageSize);
                    break;
                case 11:
                    spotsLeft = updateSpots(.98, whichGarage, garageSize);
                    break;
                case 12:
                    spotsLeft = updateSpots(1, whichGarage, garageSize);
                    break;
                case 13:
                    spotsLeft = updateSpots(.98, whichGarage, garageSize);
                    break;
                case 14:
                    spotsLeft = updateSpots(.94, whichGarage, garageSize);
                    break;
                case 15:
                    spotsLeft = updateSpots(.90, whichGarage, garageSize);
                    break;
                case 16:
                    spotsLeft = updateSpots(.85, whichGarage, garageSize);
                    break;
                case 17:
                    spotsLeft = updateSpots(.60, whichGarage, garageSize);
                    break;
                case 18:
                    spotsLeft = updateSpots(.60, whichGarage, garageSize);
                    break;
                case 19:
                    spotsLeft = updateSpots(.55, whichGarage, garageSize);
                    break;
                case 20:
                    spotsLeft = updateSpots(.55, whichGarage, garageSize);
                    break;
                case 21:
                    spotsLeft = updateSpots(.30, whichGarage, garageSize);
                    break;
                case 22:
                    spotsLeft = updateSpots(.10, whichGarage, garageSize);
                    break;
                default:
                    spotsLeft = updateSpots(.15, whichGarage, garageSize);

            }


        }
        //It is a saturday or Sunday so the available spaces will be handled differently.
        else {

            switch (rightNow.get(Calendar.HOUR_OF_DAY)) {
                case 10:
                    spotsLeft = updateSpots(.40, whichGarage, garageSize);
                    break;
                case 11:
                    spotsLeft = updateSpots(.60, whichGarage, garageSize);
                    break;
                case 12:
                    spotsLeft = updateSpots(.65, whichGarage, garageSize);
                    break;
                case 13:
                    spotsLeft = updateSpots(.55, whichGarage, garageSize);
                    break;
                default:
                    spotsLeft = updateSpots(.15, whichGarage, garageSize);


            }


        }

        if (forApp) {
            TextView viewDate = findViewById(R.id.textView_date);
            viewDate.setText(rightNow.getTime().toString());
        }


        return spotsLeft;
    }


    public int randomizeSpots() {
        //first ditch efforts at making it somewhat random
        Random r = new Random();

        int num = r.nextInt(10);

        int pos_neg = r.nextInt(2);

        if (pos_neg == 1) {
            num = num * -1;
        }


        return num;
    }

    public int getGarageSpots(int id) {
        int spotsLeft = -150;

        switch (id) {
            case 0:
                spotsLeft = determineSpots(0, "Welcome Center Parking Garage", 1200);
                break;
            case 1:
                spotsLeft = determineSpots(1, "University Drive Parking Garage", 600);
                break;
            case 2:
                spotsLeft = determineSpots(2, "Kenton Drive Parking Garage", 800);
                break;
        }

        return spotsLeft;

    }

    public class UpdateSpotsEveryMinute {

        long delay = 10 * 1000; // delay in milliseconds  --- every 10 seconds
        LoopTask task = new LoopTask();
        Timer timer = new Timer("TaskName");


        private int whichGarage;
        private String garageName;
        private int garageSize;

        public void start(int whichGarage, String garageName, int garageSize) {
            this.whichGarage = whichGarage;
            this.garageName = garageName;
            this.garageSize = garageSize;

            timer.cancel();
            //timer.purge();

            timer = new Timer("TaskName");
            Date executionDate = new Date(); // no params = now
            timer.scheduleAtFixedRate(task, executionDate, delay);
        }

        public void stop(){
            timer.cancel();
            timer.purge();
        }

        private class LoopTask extends TimerTask {
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        determineSpots(whichGarage, garageName, garageSize);
                        System.out.println("this message prints every 10 segundos bitches");
                    }
                });

            }
        }
    }
}
