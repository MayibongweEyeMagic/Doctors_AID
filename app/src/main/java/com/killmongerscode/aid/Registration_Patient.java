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
import java.io.RandomAccessFile;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registration_Patient extends AppCompatActivity {

    String FNAME, LNAME, EMAILADRESS,PASSWORD, CONFIRMPASSWORD, HOMEADDRESS,dateofbirth,PHONENUM;
    EditText name, surname, email, password, homeadress, dob, confirmpassword;
    Button registration_button;

    Random random = new Random();

    private EditText number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__patient);

        registration_button = (Button)findViewById(R.id.patient_create_account);
        int temp = random.nextInt(500);
        String temp1 = Integer.toString(temp);

        name = (EditText)findViewById(R.id.first_name_patient);
        surname = (EditText)findViewById(R.id.last_name_patient);
        email = (EditText)findViewById(R.id.patient_email_address);
        password = (EditText)findViewById(R.id.patient_password);
        confirmpassword = (EditText)findViewById(R.id.patient_pass_confirm);
        homeadress = (EditText)findViewById(R.id.location);
        dob = (EditText)findViewById(R.id.date_of_birth);
        number = (EditText) findViewById(R.id.phone_no);


        FNAME = name.getText().toString();
        LNAME = surname.getText().toString();
        EMAILADRESS = email.getText().toString();
        PASSWORD = password.getText().toString();
        CONFIRMPASSWORD = confirmpassword.getText().toString();
        HOMEADDRESS = homeadress.getText().toString();
        dateofbirth = dob.getText().toString();
        PHONENUM = number.getText().toString();







        OkHttpClient client = new OkHttpClient();


        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody body = new FormBody.Builder()
                        .add("fullname",FNAME)
                        .add("lastname",LNAME)
                        .add("address", HOMEADDRESS)
                        .add("phone",PHONENUM)
                        .add("dob",dateofbirth)
                        .add("email",EMAILADRESS)
                        .add("password",PASSWORD)
                        .add("salt",temp1)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/registerPatient.php")
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