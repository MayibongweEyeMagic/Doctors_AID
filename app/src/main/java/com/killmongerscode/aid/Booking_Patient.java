package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Booking_Patient extends AppCompatActivity {


    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private EditText choose_doc;
    private AutoCompleteTextView spec_field;

    private EditText et_date, et_time;

    int t1Hour, t1Minute;

    DatePickerDialog.OnDateSetListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_patient);

        spec_field =findViewById(R.id.doctor_spec);
        choose_doc =findViewById(R.id.doctors);

        et_date =findViewById(R.id.select_date);
        et_time = findViewById(R.id.select_time);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        spec_field.setThreshold(1);
        spec_field.setInputType(0);
        spec_field.setAdapter(adapter);

        spec_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spec_field.showDropDown();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final  int year =calendar.get(Calendar.YEAR);
        final  int month =calendar.get(Calendar.MONTH);
        final  int day =calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog datePickerDialog =new DatePickerDialog(
                         Booking_Patient.this, new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker view, int year, int month, int day) {
                         month =month+1;
                         String date = day+"/"+month+"/"+year;
                         et_date.setText(date);
                     }
                 },year,month,day);
                 datePickerDialog.show();
            }
        });

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(
                        Booking_Patient.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour =hourOfDay;
                        t1Minute =minute;

                        Calendar calendar1 =Calendar.getInstance();

                        calendar1.set(0,0,0, t1Hour,t1Minute);

                        et_time.setText(DateFormat.format("hh:mm aa",calendar1));
                    }
                }, 12, 0, false
                );

                timePickerDialog.updateTime(t1Hour, t1Minute);

                timePickerDialog.show();
            }
        });

        choose_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spec = spec_field.getText().toString();

                Intent intent =new Intent(Booking_Patient.this, Type_of_Doctor.class);
                intent.putExtra("specialization", spec);
                startActivityForResult(intent, 1);

            }
        });
    }
}