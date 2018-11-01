package com.fourfifteen.group.nku_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar;
    TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        calendar = (CalendarView) findViewById(R.id.calendar);
        currentDate = (TextView) findViewById(R.id.currentDate);
        currentDate.setText("Date: ");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                currentDate.setText("Date: " + i1 + " / " + i2 + " / " + i);

                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Month = " + i1 + "\n" + "Day = " + i2 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });
    }
}