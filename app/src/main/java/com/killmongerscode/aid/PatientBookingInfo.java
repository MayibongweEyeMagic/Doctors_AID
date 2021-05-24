package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.TextView;

public class PatientBookingInfo extends AppCompatActivity {

    Context context;
    private Button accept, reject;
    private TextView name, surname, phone, location, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_info);

        TextView textView = (TextView) findViewById(R.id.DATE);
        textView.setText(DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_12HOUR));

        accept = findViewById(R.id.Accept);
        reject = findViewById(R.id.Reject);

    }
}