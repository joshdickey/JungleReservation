package com.warpgatetechnologies.TheJungle;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class ReservationActivity extends AppCompatActivity {

    TextView txtStartDate, txtEndDate, txtemail;
    int mYear, mMonth, mDay, endYear, endMonth, endDay;
    static final int DILOG_ID = 0;
    boolean endDateSet, startDateSet;
    Button btnSendEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.warpgatetechnologies.TheJungle.R.layout.activity_reservation);

        //sets up the calendar pickers to default to the current date
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        //booleans to keep track of which set date button is pressed
        endDateSet = false;
        startDateSet = false;

        //Sets the start and end date buttons and the send email button
        txtStartDate = (TextView) findViewById(com.warpgatetechnologies.TheJungle.R.id.txtStartDate);
        txtEndDate = (TextView) findViewById(com.warpgatetechnologies.TheJungle.R.id.txtEndDate);
        btnSendEmail = (Button)findViewById(com.warpgatetechnologies.TheJungle.R.id.btnEmail);

        //shows the chosen dates in the respective textView
        showStartDateDialogOnClick();
        showEndDateDialogOnClick();

        //allows the user to choose what app will send the email when clicked
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    /*
    *sends the email and sets the subject and body of the message
     */
    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Jungle Reservation Request");
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage());

        try{
            startActivity(Intent.createChooser(intent, "Send email..."));
            finish();
        }catch (android.content.ActivityNotFoundException e){
            Toast.makeText(ReservationActivity.this, "There is no email client installed", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    *Method that displays the chosen start date in the start date textView
     */
    public void showStartDateDialogOnClick(){

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startDateSet = true;
                showDialog(DILOG_ID);

            }
        });
    }

    /*
    *Method that displays the chosen end date in the end date textView
     */
    public void showEndDateDialogOnClick(){

        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateSet = true;
                showDialog(DILOG_ID);
                //shows preview of email message
                //setEmailPreview();
            }
        });
    }

    /*
    *
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID) {
            return new DatePickerDialog(this, endPickerListener, mYear,  mMonth, mDay);
        }else {
            return null;
        }
    }

    private   DatePickerDialog.OnDateSetListener endPickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            //check to see if the start/end date has been set.
            //if true, set the global variables to the chosen date
            //show a toast of the start/end date
            //add the start/end date to the start/edn date textView
            if (startDateSet){
                mYear = year;
                mDay = monthOfYear;
                mMonth = dayOfMonth;

                txtStartDate.setText("Starting Date: " + mDay + "/" + mMonth + "/" + mYear);
                Toast.makeText(ReservationActivity.this, "Starting Date: " +mDay + "/" + mMonth + "/" + mYear, Toast.LENGTH_SHORT).show();

                startDateSet = false;
            }
            if (endDateSet){
                endYear = year;
                endDay = monthOfYear;
                endMonth = dayOfMonth;

                txtEndDate.setText("Ending Date: " + endDay + "/" + endMonth + "/" + endYear);
                Toast.makeText(ReservationActivity.this, "Ending Date: " + endDay + "/" + endMonth + "/" + endYear, Toast.LENGTH_SHORT).show();

                endDateSet = false;
            }
        }
    };

    /*
    *Method to compose the body of the reservation request message.
     */
    private String emailMessage(){
        String message = "Dear Grandpa,\n\n" +
                "\tI would like to reserve the Jungle from: \n\n\t" + txtStartDate.getText() +
                "\n\t\t\t to \n\t" + txtEndDate.getText() + "\n\n\nThank You!";

        return  message;
    }
}
