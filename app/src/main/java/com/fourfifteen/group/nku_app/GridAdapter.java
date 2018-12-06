package com.fourfifteen.group.nku_app;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class GridAdapter extends ArrayAdapter {
    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<EventObjects> allEvents;

    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, List<EventObjects> allEvents) {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if (displayMonth == currentMonth && displayYear == currentYear) {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        //Add day to calendar
        TextView cellNumber = (TextView) view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));
        //Add events to the calendar
        TextView eventIndicator = (TextView) view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        for (int i = 0; i < allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {

                if (allEvents.get(i).getEventType().equals("Homework") || allEvents.get(i).getEventType().equals("homework"))
                    eventIndicator.setBackgroundColor(Color.parseColor("#00FF00"));
                else if (allEvents.get(i).getEventType().equals("Test") || allEvents.get(i).getEventType().equals("test"))
                    eventIndicator.setBackgroundColor(Color.parseColor("#00FFFF"));
                else if (allEvents.get(i).getEventType().equals("Project") || allEvents.get(i).getEventType().equals("project"))
                    eventIndicator.setBackgroundColor(Color.parseColor("#FFFF33"));
                else
                    eventIndicator.setBackgroundColor(Color.parseColor("#e50000"));

                setOnClick(eventIndicator, allEvents.get(i).getMessage(), allEvents.get(i).getTime(), allEvents.get(i).getEventType());
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }

    private void setOnClick(final TextView text, final String description, final String time, final String eventType) {
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), EventActivity.class);
                Bundle extras = new Bundle();
                extras.putString("description", description);
                extras.putString("time", time);
                extras.putString("eventType", eventType);
                intent.putExtras(extras);
                v.getContext().startActivity(intent);

            }
        });
    }
}
