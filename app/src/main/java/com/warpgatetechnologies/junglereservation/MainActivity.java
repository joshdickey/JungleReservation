package com.warpgatetechnologies.junglereservation;

import android.content.Intent;
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
                openCalendar(view);
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


}
