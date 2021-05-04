package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView clicks;
    Button patient_login, doctor_login;
    EditText username, password;
    String USERNAME, PASSWORD;

    Verification verification = new Verification();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);

        patient_login = (Button)findViewById(R.id.log_in_patient);
        doctor_login = (Button)findViewById(R.id.patient_create_account);

        clicks = (TextView) findViewById(R.id.register_path);


        clicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

        OkHttpClient client = new OkHttpClient();
        patient_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/loginPatient.php")
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
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        loginPatient(responseData);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }



                    });

            }
        });



        doctor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/loginDoctor.php")
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
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    loginDoctor(responseData);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }



                });

            }
        });





    }





    //this function right here creates your dialog so you can choose
    //who to register as
    public void openDialog(){
        DirectionDialog directionDialog = new DirectionDialog();
        directionDialog.show(getSupportFragmentManager(), "registration pages");
    }



    public void loginPatient(String response) throws JSONException {

        ArrayList<String>holder = new ArrayList<>();

        USERNAME = username.getText().toString();
        PASSWORD = password.getText().toString();

        String thing = USERNAME + PASSWORD;
        holder = verification.JSONFUCTION(response);

        if(holder.contains(thing)){



            Toast.makeText(MainActivity.this,
                    "WELCOME", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, Patient_Homepage.class);
            startActivity(intent);

        }

        else {

            Toast.makeText(MainActivity.this,
                    "SOMETHING WENT WRONG !", Toast.LENGTH_LONG).show();
        }

    }





    public void loginDoctor(String response) throws JSONException {

        ArrayList<String>holder = new ArrayList<>();

        USERNAME = username.getText().toString();
        PASSWORD = password.getText().toString();

        String thing = USERNAME + PASSWORD;
        holder = verification.JSONFUCTION_DOCTOR(response);

        if(holder.contains(thing)){

            Toast.makeText(MainActivity.this,
                    "WELCOME", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, Doctor_Homepage.class);
            startActivity(intent);

        }

        else {

            Toast.makeText(MainActivity.this,
                    "SOMETHING WENT WRONG !", Toast.LENGTH_LONG).show();
        }

    }

}