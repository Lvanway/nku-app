package com.fourfifteen.group.nku_app;

import android.content.Context;
import android.database.Cursor;
import com.fourfifteen.group.nku_app.EventObjects;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class DatabaseQuery extends DatabaseObject{
    private static final String TAG = Database.class.getSimpleName();
    public DatabaseQuery(Context context) {
        super(context);
    }
    public List<EventObjects> getAllFutureEvents(){
        Date dateToday = new Date();
        List<EventObjects> events = new ArrayList<>();
        String query = "select * from events";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                String eventType = cursor.getString(cursor.getColumnIndexOrThrow("eventType"));

                //convert start date to date object
                Date reminderDate = convertStringToDate(date);
                if(reminderDate.after(dateToday) || reminderDate.equals(dateToday)){
                    events.add(new EventObjects(id, description, reminderDate, time, eventType));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }
    private Date convertStringToDate(String dateInString){
        DateFormat format = new SimpleDateFormat("MM-d-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}