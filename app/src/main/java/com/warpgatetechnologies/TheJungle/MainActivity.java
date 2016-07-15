package com.warpgatetechnologies.TheJungle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.warpgatetechnologies.TheJungle.R.layout.activity_main);

        //assigns a textView to open the users default calendar
        TextView calendarTextView = (TextView) findViewById(com.warpgatetechnologies.TheJungle.R.id.current_reservations_text_view);
        calendarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleCalendar();
            }
        });

        //assigns a textview to open the reservation activity when clicked on
        TextView requestTextView = (TextView) findViewById(com.warpgatetechnologies.TheJungle.R.id.request_reservation_text_view);
        requestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestReservation(view);
            }
        });
    }

    //Method that opens the Reservation Activity
    public void requestReservation(View view){
        Intent intent = new Intent(this,ReservationActivity.class);
        startActivity(intent);
    }

    //opens a default calendar
    private void googleCalendar() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_CALENDAR);

        //TODO: find a way to open the Jungle Reservation Google Calendar
       //intent.putExtra(CalendarContract.Calendars._ID, "m0n1tst1vjm9ot56jb2ajk47bg@group.calendar.google.com");

        startActivity(intent);
    }

}
