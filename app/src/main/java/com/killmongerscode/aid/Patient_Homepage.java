 package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.EventListener;

public class Patient_Homepage extends AppCompatActivity implements SignOutDialog.SignOutDialogListener {

    GridLayout Patient_homepage;
    private TextView welcome;
    Verification verification = new Verification();

    ProgressDialog progressDialog;


    String name, surname, dob, phoneNo, emailAddress, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__homepage);

        Patient_homepage = (GridLayout)findViewById(R.id.Patient_homepage);
        welcome = findViewById(R.id.title_view);

        Bundle bundle = getIntent().getExtras();
        emailAddress = bundle.getString("email");

        setSingleEvent(Patient_homepage);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("email",emailAddress)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/loginPatient.php")
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
                Patient_Homepage.this.runOnUiThread(new Runnable() {
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

    private void setSingleEvent(GridLayout Patient_homepage) {
        for (int i = 0; i < Patient_homepage.getChildCount();i++)
        {
            if(i == 0){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Patient_Homepage.this,PatientProfile.class);
                        intent.putExtra("name", name);
                        intent.putExtra("surname", surname);
                        intent.putExtra("dateOfbirth", dob);
                        intent.putExtra("cellNo", phoneNo);
                        intent.putExtra("emailAdd", emailAddress);
                        intent.putExtra("homeAddress", location);
                        startActivity(intent);
                    }
                });
            }

            if(i == 2){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_Homepage.this,PatientMedicalFolder.class);
                        startActivity(intent);
                    }
                });
            }
            else if(i == 3){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_Homepage.this,Booking_Patient.class);
                        intent.putExtra("email", emailAddress);
                        startActivity(intent);
                    }
                });
            }

            else if(i == 4){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_Homepage.this, TabbedActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }


}
    public void get_method(String response) throws JSONException {

        verification.JSONFUCTION(response);
        name = verification.get_patient_name();
        surname= verification.get_patient_surname();
        dob = verification.get_patient_dob();
        phoneNo = verification.get_patient_phone();
        location =verification.get_patient_location();

        welcome.setText("Hi "+ name+ "!");
    }

    @Override
    public void onBackPressed() {
        openDialog();
    }

    private void openDialog() {
        SignOutDialog signOutDialog =new SignOutDialog();
        signOutDialog.show(getSupportFragmentManager(),"sign out");
    }


    @Override
    public void onYesClicked() {

        String signOff ="signout";

        progressDialog =new ProgressDialog(Patient_Homepage.this);

        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Thread timer =new Thread(){

            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(Patient_Homepage.this, MainActivity.class);
                    intent.putExtra("signedout", signOff);
                    startActivityForResult(intent,1);
                    progressDialog.dismiss();
                    super.run();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }
}