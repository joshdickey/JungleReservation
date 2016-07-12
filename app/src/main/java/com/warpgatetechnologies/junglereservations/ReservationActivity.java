package com.warpgatetechnologies.junglereservations;

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
        setContentView(R.layout.activity_reservation);

        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        endDateSet = false;
        startDateSet = false;

        txtStartDate = (TextView) findViewById(R.id.txtStartDate);
        txtEndDate = (TextView) findViewById(R.id.txtEndDate);
        txtemail = (TextView) findViewById(R.id.emailPreview);
        btnSendEmail = (Button)findViewById(R.id.btnEmail);

        showStartDateDialogOnClick();
        showEndDateDialogOnClick();

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

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

    private void setEmailPreview() {

        txtemail.setText(emailMessage());
    }

    public void showStartDateDialogOnClick(){

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startDateSet = true;
                showDialog(DILOG_ID);

            }
        });
    }

    public void showEndDateDialogOnClick(){

        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDateSet = true;
                showDialog(DILOG_ID);
                setEmailPreview();
            }
        });
    }

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

            if (startDateSet){
                mYear = year;
                mDay = monthOfYear;
                mMonth = dayOfMonth;

                txtStartDate.setText("Starting Date: " + mDay + "/" + mMonth + "/" + mYear);
                Toast.makeText(ReservationActivity.this, "Starting Date: " +mDay + "/" + mMonth + "/" + mYear, Toast.LENGTH_SHORT).show();

                startDateSet = false;
                setEmailPreview();
            }
            if (endDateSet){
                endYear = year;
                endDay = monthOfYear;
                endMonth = dayOfMonth;

                txtEndDate.setText("Ending Date: " + endDay + "/" + endMonth + "/" + endYear);
                Toast.makeText(ReservationActivity.this, "Ending Date: " + endDay + "/" + endMonth + "/" + endYear, Toast.LENGTH_SHORT).show();

                endDateSet = false;
                setEmailPreview();
            }


        }

    };


    private String emailMessage(){
        String message = "Dear Grandpa,\n\n" +
                "\tI would like to reserve the Jungle from: \n\n\t" + txtStartDate.getText() +
                "\n\t\t\t to \n\t" + txtEndDate.getText() + "\n\n\nThank You!";

        return  message;
    }
}
