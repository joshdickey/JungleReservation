package com.warpgatetechnologies.junglereservations;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView calendarTextView = (TextView) findViewById(R.id.current_reservations_text_view);
        calendarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleCalendar();
            }
        });

        TextView requestTextView = (TextView) findViewById(R.id.request_reservation_text_view);
        requestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestReservation(view);
            }
        });
    }

    public void openCalendar(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void requestReservation(View view){
        Intent intent = new Intent(this,ReservationActivity.class);
        startActivity(intent);

    }

    private void googleCalendar() {
        String calID = "m0n1tst1vjm9ot56jb2ajk47bg@group.calendar.google.com";

//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(intent.CATEGORY_APP_CALENDAR);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(intent.CATEGORY_APP_CALENDAR);

        //not sure the following is actually working
        intent.putExtra(CalendarContract.Calendars._ID, "m0n1tst1vjm9ot56jb2ajk47bg@group.calendar.google.com");

        startActivity(intent);
    }

}
