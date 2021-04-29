package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Registration_Patient extends AppCompatActivity {

    String FNAME, LNAME, EMAILADRESS,PASSWORD, CONFIRMPASSWORD, HOMEADDRESS,dateofbirth;
    EditText name, surname, email, password, homeadress, dob, confirmpassword;
    Button registra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__patient);

        // fudumeza amazi
        registra = (Button)findViewById(R.id.patient_create_account);

        name = (EditText)findViewById(R.id.first_name_patient);
        surname = (EditText)findViewById(R.id.last_name_patient);
        email = (EditText)findViewById(R.id.patient_email_address);
        password = (EditText)findViewById(R.id.patient_password);
        homeadress = (EditText)findViewById(R.id.location);
        dob = (EditText)findViewById(R.id.date_of_birth);




        OkHttpClient client = new OkHttpClient();


        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody body = new FormBody.Builder()
                        .add("fullname","asshole")
                        .add("lastname","ngubeni")
                        .add("address", "286 something something ")
                        .add("phone","1995-03-20")
                        .add("dob","cruchtime")
                        .add("email","mayibongwe.bafoly.mb@gmail.com")
                        .add("salt","123")
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/registerpatient.php")
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }


                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }

                        final String responseData = response.body().string();
                        Registration_Patient.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Registration_Patient.this,
                                        responseData, Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                });





            }
        });







    }


    // okhttp post request






}