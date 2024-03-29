package com.killmongerscode.aid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Doctor_Homepage extends AppCompatActivity
    implements SignOutDialog.SignOutDialogListener {

  GridLayout doctor_home;
  TextView welcome;
  String get_name, get_surname, get_phonenumber, get_specialization, get_qualification;

  ProgressDialog progressDialog;

  String message;
  Verification verification = new Verification();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_doctor__homepage);

    welcome = (TextView) findViewById(R.id.title_view);
    doctor_home = (GridLayout) findViewById(R.id.doctor_home);

    setSingleEvent(doctor_home);
    message = "";

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) message = bundle.getString("email");

    OkHttpClient client = new OkHttpClient();

    RequestBody body = new FormBody.Builder().add("email", message).build();

    Request request =
        new Request.Builder()
            .url("https://lamp.ms.wits.ac.za/home/s2090040/loginDoctor.php")
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
                Doctor_Homepage.this.runOnUiThread(
                    new Runnable() {
                      @Override
                      public void run() {
                        try {
                          get_method(responseData);
                        } catch (JSONException e) {
                          e.printStackTrace();
                        }
                      }
                    });
              }
            });
  }

  private void setSingleEvent(GridLayout doctor_home) {
    for (int i = 0; i < doctor_home.getChildCount(); i++) {

      if (i == 0) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Intent intent = new Intent(Doctor_Homepage.this, DoctorProfile.class);
                intent.putExtra("email", message);
                intent.putExtra("name", get_name);
                intent.putExtra("surname", get_surname);
                intent.putExtra("qualification", get_qualification);
                intent.putExtra("cellNo", get_phonenumber);
                intent.putExtra("specialization", get_specialization);
                startActivity(intent);
              }
            });
      } else if (i == 1) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Intent intent = new Intent(Doctor_Homepage.this, list_of_patients.class);
                intent.putExtra("email", message);
                startActivity(intent);
              }
            });
      } else if (i == 2) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Intent intent = new Intent(Doctor_Homepage.this, DoctorChats.class);
              }
            });
      } else if (i == 3) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                Intent intent = new Intent(Doctor_Homepage.this, pending_bookings.class);
                intent.putExtra("email", message);
                startActivity(intent);
              }
            });
      } else if (i == 4) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Intent intent = new Intent(Doctor_Homepage.this, DoctorTabbed.class);
                intent.putExtra("email", message);
                startActivity(intent);
              }
            });
      } else if (i == 5) {
        CardView cardView = (CardView) doctor_home.getChildAt(i);
        cardView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Intent intent = new Intent(Doctor_Homepage.this, DoctorAccess.class);
                startActivity(intent);
              }
            });
      }
    }
  }

  public void get_method(String response) throws JSONException {

    verification.JSONFUCTION_DOCTOR(response);

    get_name = verification.get_doctor_name();
    get_surname = verification.get_doctor_surname();
    get_phonenumber = verification.get_doctor_phone();
    get_specialization = verification.get_doctor_spectialization();
    get_qualification = verification.get_doctor_qualification();
    welcome.setText("Hi " + get_name + "!");
  }

  @Override
  public void onBackPressed() {
    openDialog();
  }

  private void openDialog() {
    SignOutDialog signOutDialog = new SignOutDialog();
    signOutDialog.show(getSupportFragmentManager(), "sign out");
  }

  @Override
  public void onYesClicked() {
    String signOff = "signout";

    progressDialog = new ProgressDialog(Doctor_Homepage.this);

    progressDialog.show();

    progressDialog.setContentView(R.layout.progress_dialog);
    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    Thread timer =
        new Thread() {

          @Override
          public void run() {
            try {
              sleep(2000);
              Intent intent = new Intent(Doctor_Homepage.this, MainActivity.class);
              intent.putExtra("signedout", signOff);
              startActivityForResult(intent, 1);
              progressDialog.dismiss();
              super.run();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };
    timer.start();
  }
}
