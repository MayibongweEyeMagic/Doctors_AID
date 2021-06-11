package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView clicks;
    Button patient_login, doctor_login;
    EditText username, password;
    String USERNAME, PASSWORD;

    ProgressDialog progressDialog;

    Verification verification = new Verification();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);

        patient_login = findViewById(R.id.log_in_patient);
        //doctor_login = (Button)findViewById(R.id.patient_create_account);

        clicks =  findViewById(R.id.register_path);


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

                USERNAME =username.getText().toString();
                PASSWORD =password.getText().toString();



                RequestBody body = new FormBody.Builder()
                        .add("email",USERNAME)
                        .add("password",PASSWORD)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/dProfile.php")
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

                           String [] holder = responseData.split(" ");

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        loginPatient(holder[0]);
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

 //       Toast.makeText(this, response, Toast.LENGTH_SHORT).show();

        if (response.equals("Invalidemail")){
            Toast.makeText(MainActivity.this,
                    "Invalid email", Toast.LENGTH_LONG).show();
        }
        else if(response.equals("Fieldsareempty")){
            Toast.makeText(MainActivity.this,
                    "One or two fields are empty", Toast.LENGTH_LONG).show();
        }
         if (response.equals("Patient")){

            progressDialog =new ProgressDialog(MainActivity.this);

            progressDialog.show();

            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent
            );

            Thread timer =new Thread(){

                @Override
                public void run() {
                    try {
                        sleep(3500);
                        Intent intent = new Intent(MainActivity.this, Patient_Homepage.class);
                        intent.putExtra("email", USERNAME);
                        startActivity(intent);
                        progressDialog.dismiss();
                        super.run();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            timer.start();

            username.getText().clear();
            password.getText().clear();
        }
        else if(response.equals("Doctor")){

            progressDialog =new ProgressDialog(MainActivity.this);

            progressDialog.show();

            progressDialog.setContentView(R.layout.progress_dialog);
           progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent
            );

            Thread timer =new Thread(){

                @Override
                public void run() {
                    try {
                        sleep(3500);
                        Intent intent = new Intent(MainActivity.this, Doctor_Homepage.class);
                        intent.putExtra("email", USERNAME);
                        startActivity(intent);
                        progressDialog.dismiss();
                        super.run();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            timer.start();

            username.getText().clear();
            password.getText().clear();
        }
        else if(response.equals("Passwordisincorrect")){
            Toast.makeText(MainActivity.this,
                    "Password is incorrect", Toast.LENGTH_LONG).show();
        }
        else if(response.equals("Emaildoesnotexistpleaseregister!")){
            Toast.makeText(MainActivity.this,
                    "Account does not exist please register", Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onBackPressed() {
        Bundle bundle =getIntent().getExtras();
        String singout =bundle.toString();

        if(singout.isEmpty()){

            super.onBackPressed();
        }

    }
}