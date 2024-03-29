package com.killmongerscode.aid;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

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

public class Booking_Patient extends AppCompatActivity {

  ArrayList<SelectByProfession> usersList = new ArrayList<>();
  ArrayList<String> thing = new ArrayList<>();
  ArrayList<String> temp = new ArrayList<>();
  String docEmail = "";

  String doctorBio, emailAddress, doc_email, doc_token;
  private static final String CHANNEL_ID = "101";

  String[] predefined = {"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
  private AutoCompleteTextView spec_field;

  private EditText et_date, et_time, reason, choose_doc;
  private AppCompatButton button;
  int t1Hour, t1Minute;

  DatePickerDialog.OnDateSetListener listener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_booking_patient);

    spec_field = findViewById(R.id.doctor_spec);
    choose_doc = findViewById(R.id.doctors);
    reason = findViewById(R.id.Reason);
    et_date = findViewById(R.id.select_date);
    et_time = findViewById(R.id.select_time);
    button = findViewById(R.id.button_show2);

    Bundle bundle = getIntent().getExtras();
    emailAddress = "";

    if (bundle != null) emailAddress = bundle.getString("email");

    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
    spec_field.setThreshold(1);
    spec_field.setInputType(0);
    spec_field.setAdapter(adapter);
    et_time.setInputType(0);
    et_date.setInputType(0);

    spec_field.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            spec_field.showDropDown();
          }
        });

    selectDate();

    selectTime();

    choose_doc.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String toString = spec_field.getText().toString();
            if (!toString.isEmpty()) {
              FullScreenDialog fullScreenDialog = new FullScreenDialog(toString);
              DialogFragment dialog = FullScreenDialog.newInstance();
              dialog.show(getSupportFragmentManager(), "tag");
            } else {
              Toast.makeText(Booking_Patient.this, "Select specialization", Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });

    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            // Toast.makeText(Booking_Patient.this, emailAddress, Toast.LENGTH_SHORT).show();

            createNotificationChannel();
              postTotheLamp(emailAddress);
          }
        });
  }

  private void OnSuccess(String responseData) {
    if (responseData.equals("Bookingwascreatedsuccessfully!")) {
      Toast.makeText(this, "Booking was created successfully", Toast.LENGTH_SHORT).show();

      Intent intent = new Intent(Booking_Patient.this, Patient_Homepage.class);
      intent.putExtra("email", emailAddress);
      startActivity(intent);

    }else if(responseData.equals("Cannotcreatebooking.Youalreadyhaveabookinginprogress.")){
        Toast.makeText(this, "Cannot create booking. You already have a booking in progress", Toast.LENGTH_SHORT)
                .show();
    }
    else if (responseData.equals("Failedtocreatebooking!")) {
      Toast.makeText(this, "Something went wrong please try again later", Toast.LENGTH_SHORT)
          .show();
    }else if(responseData.equals("Thereareillegalbookingsmade")){
        Toast.makeText(this, "This booking is illegal until you complete the one still in progress", Toast.LENGTH_SHORT)
                .show();
    }
    else if (responseData.equals("Theremaybeemptyinputs")) {
      Toast.makeText(this, "There may be empty fields", Toast.LENGTH_SHORT).show();
    }
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
                    Booking_Patient.this,
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
              // disabled past date
              datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
              datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000 + (1000*60*60*24*7));
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
                    Booking_Patient.this,
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

  public void postTotheLamp(String email) {

    String date = et_date.getText().toString();
    String time = et_time.getText().toString();
    String appointReason = reason.getText().toString();
    String spec = spec_field.getText().toString();

    OkHttpClient client = new OkHttpClient();

    RequestBody body =
        new FormBody.Builder()
            .add("email", email)
            .add("date", date)
            .add("time", time)
            .add("doc_email", getSelectedEmail())
            .add("reason", appointReason)
            .add("specialisation", spec)
            .add("token", getSelectedToken())
            .build();

    Request request =
        new Request.Builder()
            .url("https://lamp.ms.wits.ac.za/home/s2090040/new.php")
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
                String holder[] = responseData.split(" ");
                String thing = holder[0];
                Booking_Patient.this.runOnUiThread(
                    new Runnable() {
                      @Override

                      public void run() {
                        //  Toast.makeText(Booking_Patient.this, responseData, Toast.LENGTH_SHORT).show();
                          OnSuccess(thing);
                      }
                    });
              }
            });
  }

  private void createNotificationChannel() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      CharSequence name = "FirebaseNotificationChannel";

      String description = "Receive Firebase Notification";
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
      channel.setDescription(description);

      NotificationManager notificationManager = getSystemService(NotificationManager.class);
      notificationManager.createNotificationChannel(channel);
    }
  }

  public void setSelected(String token, String email) {
    this.doc_token = token;
    this.doc_email = email;
    choose_doc.setText(email);
  }

  public String getSelectedEmail() {
    return doc_email;
  }

  public String getSelectedToken() {
    return doc_token;
  }
}
