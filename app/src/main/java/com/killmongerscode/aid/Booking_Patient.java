package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Booking_Patient extends AppCompatActivity {


    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private EditText choose_doc;
    private AutoCompleteTextView spec_field;

    private EditText et_date, et_time, bookie, firstName, lastName, reason;
    private AppCompatButton button;
    int t1Hour, t1Minute;

    DatePickerDialog.OnDateSetListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_patient);

        spec_field =findViewById(R.id.doctor_spec);
        choose_doc =findViewById(R.id.doctors);

        bookie =findViewById(R.id.bookie_email);

        firstName =findViewById(R.id.name);
        lastName =findViewById(R.id.last_name);

        reason =findViewById(R.id.Reason);

        et_date = findViewById(R.id.select_date);
        et_time =findViewById(R.id.select_time);

        button = findViewById(R.id.button_show2);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        spec_field.setThreshold(1);
        spec_field.setInputType(0);
        spec_field.setAdapter(adapter);
        et_time.setInputType(0);
        et_date.setInputType(0);

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
                         String date = year+"-"+month+"-"+day;
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
                        t1Hour = hourOfDay;
                        t1Minute =minute;

                        Calendar calendar1 =Calendar.getInstance();
                        calendar1.set(0,0,0,t1Hour,t1Minute);
                        et_time.setText(DateFormat.format("hh:mm aa", calendar1));
                    }
                        },12,0,false
                );

                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });

        choose_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spec = spec_field.getText().toString();
                if (spec.isEmpty()) {
                    Toast.makeText(Booking_Patient.this, "Select Specialization", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Booking_Patient.this, Type_of_Doctor.class);
                    intent.putExtra("specialization", spec);
                    startActivity(intent);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String patient_email =bookie.getText().toString();
                    String date = et_date.getText().toString() + " " + et_time.getText().toString();;
                    String lastname  =lastName.getText().toString();
                    String firstname =firstName.getText().toString();
                    String docEmail =choose_doc.getText().toString();
                    String appointReason =reason.getText().toString();
                    String spec =spec_field.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("email", patient_email)
                        .add("date", date)
                        .add("lastname", lastname)
                        .add("firstname", firstname)
                        .add("doc_email", docEmail)
                        .add("reason", appointReason)
                        .add("specialisation", spec)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/create_booking.php")
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }

                        final String responseData = response.body().string();
                        Booking_Patient.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    OnSuccess(responseData);
                            }
                        });

                    }



                });

            }
        });
    }

    private void OnSuccess(String responseData) {
        if (responseData.equals("Booking was created successfully!")) {
            Toast.makeText(this, "Booking was created successfully", Toast.LENGTH_SHORT).show();

            Intent intent =new Intent(Booking_Patient.this, Patient_Homepage.class);
            startActivity(intent);

        }
        else if(responseData.equals("Booking was not created successfully!")){
            Toast.makeText(this, "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
        }
        else if(responseData.equals("There may be empty inputs")) {
            Toast.makeText(this, "There may be empty fields", Toast.LENGTH_SHORT).show();
        }
    }
}