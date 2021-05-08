package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registration_Doctor extends AppCompatActivity {

    String NAME,SURNAME, QUALIFICATION, UNIVERSITY, EMAIL,PASSWORD,COMFIRMPASS,PHONENUM;
    private EditText name,surname,qualification,unversity,email,password,comfirmpass,phone;
    Button registration_button;

    Random random = new Random();

    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private AutoCompleteTextView special;
    private ImageView views;

    private EditText number;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__doctor);

        int temp = random.nextInt(500);
        String temp1 = Integer.toString(temp);

        registration_button = findViewById(R.id.doctor_create_account);
        name =  findViewById(R.id.first_name_doctor);
        surname = findViewById(R.id.last_name_doctor);
        qualification = findViewById(R.id.qualification);
        unversity = findViewById(R.id.qual_obtained_where);
        email = findViewById(R.id.doctor_email_address);
        password = findViewById(R.id.doctor_password);
        comfirmpass = findViewById(R.id.doctor_pass_confirm);
        phone = findViewById(R.id.phone_number);


        special = (AutoCompleteTextView) findViewById(R.id.specialization);
        views = (ImageView) findViewById(R.id.drop_down);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        special.setThreshold(1);
        special.setAdapter(adapter);

        number = (EditText) findViewById(R.id.phone_number);

        String SPECIALIZATION = special.getText().toString();

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                special.showDropDown();
            }
        });



        OkHttpClient client = new OkHttpClient();


        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NAME = name.getText().toString();
                SURNAME = surname.getText().toString();
                QUALIFICATION = qualification.getText().toString();
                UNIVERSITY = unversity.getText().toString();
                EMAIL = email.getText().toString();
                PASSWORD = password.getText().toString();
                COMFIRMPASS = comfirmpass.getText().toString();
                PHONENUM = phone.getText().toString();



                RequestBody body = new FormBody.Builder()
                        .add("firstname",NAME)
                        .add("lastname",SURNAME)
                        .add("specialization", SPECIALIZATION)
                        .add("qualification",QUALIFICATION)
                        .add("phone",PHONENUM)
                        .add("email",EMAIL)
                        .add("password",PASSWORD)
                        .add("grad_at",UNIVERSITY)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/regDoctor.php")
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
                        Registration_Doctor.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                registration_function(responseData);

                            }
                        });


                    }
                });






            }
        });


    }


    public void registration_function(String response) {

        if (COMFIRMPASS.equals(PASSWORD)){
            if (response.equals("Successfully Registered")) {

                Toast.makeText(Registration_Doctor.this,
                        response, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(Registration_Doctor.this, MainActivity.class);
                startActivity(intent);


            }
            else if (response.equals("Some of the fields are empty")){
                Toast.makeText(Registration_Doctor.this,
                        "One of the fields is empty", Toast.LENGTH_LONG).show();
            }

            else if(response.equals("Invalid email")){
                Toast.makeText(Registration_Doctor.this,
                        "Invalid email", Toast.LENGTH_LONG).show();
            }
            else if (response.equals("Email already exist")){
                Toast.makeText(Registration_Doctor.this,
                        "Email already exist", Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(Registration_Doctor.this,
                        "Failed to register", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(Registration_Doctor.this,
                    "Password doesn't match", Toast.LENGTH_LONG).show();

        }

    }





}