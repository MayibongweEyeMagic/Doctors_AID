package com.killmongerscode.aid;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Appointment_detail extends AppCompatActivity {

  private EditText et_date, et_time, diagnosis, treatment;
  String ID, date, time, TREAT, DIAG, thing;
  int t1Hour, t1Minute;

  Button button;

  DatePickerDialog.OnDateSetListener listener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_appointment_detail);

    et_date = findViewById(R.id.date_visit);
    et_time = findViewById(R.id.add_time);
    diagnosis = findViewById(R.id.add_diagnosis);
    treatment = findViewById(R.id.add_treatment);
    button = findViewById(R.id.doctor_complete_appointment);

    et_time.setInputType(0);
    et_date.setInputType(0);

    selectDate();
    selectTime();

    Bundle bundle = getIntent().getExtras();
    ID = "";
    if (bundle != null) ID = bundle.getString("ID");

    OkHttpClient client = new OkHttpClient();

    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            String temp = form();

            RequestBody body =
                new FormBody.Builder()
                    .add("booking number", ID)
                    .add("status", "FULFILLED")
                    .add("outcome", "PJJUU  ")
                    .build();

            Request request =
                new Request.Builder()
                    .url("https://lamp.ms.wits.ac.za/home/s2090040/update_status.php")
                    .post(body)
                    .build();

            client
                .newCall(request)
                .enqueue(
                    new Callback() {
                      @Override
                      public void onFailure(@NotNull Call call, @NotNull IOException e) {}

                      @Override
                      public void onResponse(@NotNull Call call, @NotNull Response response)
                          throws IOException {

                        if (!response.isSuccessful()) {
                          throw new IOException("Unexpected code " + response);
                        }

                        final String responseData = response.body().string();
                        Appointment_detail.this.runOnUiThread(
                            new Runnable() {
                              @Override
                              public void run() {}
                            });
                      }
                    });
          }
        });
  }

  public void selectDate() {

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    et_date.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                    Appointment_detail.this,
                    new DatePickerDialog.OnDateSetListener() {
                      @Override
                      public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + day;
                        et_date.setText(date);
                      }
                    },
                    year,
                    month,
                    day);
            datePickerDialog.show();
          }
        });
  }

  public void selectTime() {

    et_time.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            TimePickerDialog timePickerDialog =
                new TimePickerDialog(
                    Appointment_detail.this,
                    new TimePickerDialog.OnTimeSetListener() {
                      @Override
                      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(0, 0, 0, t1Hour, t1Minute);
                        et_time.setText(DateFormat.format("hh:mm aa", calendar1));
                      }
                    },
                    12,
                    0,
                    false);

            timePickerDialog.updateTime(t1Hour, t1Minute);
            timePickerDialog.show();
          }
        });
  }

  public String form() {
    String complete = "";
    ArrayList<String> holder = new ArrayList<>();

    date = et_date.getText().toString();
    time = et_time.getText().toString();
    DIAG = diagnosis.getText().toString();
    TREAT = treatment.getText().toString();
    holder.add(date);
    holder.add(time);
    holder.add(DIAG);
    holder.add(TREAT);

    if (!holder.contains("")) {

      thing = date + " " + time + " " + DIAG + " " + TREAT;
      complete = "FULFILLED";

    } else {
      Toast.makeText(this, "SOME OF THE FIELDS ARE EMPTY", Toast.LENGTH_SHORT).show();
    }

    return complete;
  }
}
