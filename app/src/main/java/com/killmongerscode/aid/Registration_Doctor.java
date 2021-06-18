package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

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

    String NAME,SURNAME, QUALIFICATION, UNIVERSITY, EMAIL,PASSWORD,COMFIRMPASS,PHONENUM, SPECIALIZATION, TOKEN,ANSWERS,GOD ;
    private EditText name,surname,qualification,unversity,email,password,comfirmpass,phone,answer;
    String question;
    Button registration_button;
    String TAG ="pushnotification";
    private static final String CHANNEL_ID = "101";


    Random random = new Random();

    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private AutoCompleteTextView special;
    private ImageView views;

    private EditText number;

    String[] fined ={"what was your first dog's name?", "what is your favourite colour", "what is your dream car's brand name", "what is your favourite shoe brand"};

    private AutoCompleteTextView questionsr;
    private ImageView views_for_questionsr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__doctor);





        int temp = random.nextInt(500);
        String temp1 = Integer.toString(temp);

       // createNotificationChannel();
        getToken();

        questionsr =(AutoCompleteTextView) findViewById(R.id.safe);
        views_for_questionsr =findViewById(R.id.drop_down1);

        ArrayAdapter<String> tempart =new ArrayAdapter<>(this, android.R.layout.select_dialog_item,fined);
        questionsr.setThreshold(1);
        questionsr.setAdapter(tempart);


        registration_button = findViewById(R.id.doctor_create_account);
        name =  findViewById(R.id.first_name_doctor);
        surname = findViewById(R.id.last_name_doctor);
        qualification = findViewById(R.id.qualification);
        unversity = findViewById(R.id.qual_obtained_where);
        email = findViewById(R.id.doctor_email_address);
        password = findViewById(R.id.doctor_password);
        comfirmpass = findViewById(R.id.doctor_pass_confirm);
          answer = findViewById(R.id.answer);
        phone = findViewById(R.id.phone_number);


        special = (AutoCompleteTextView) findViewById(R.id.specialization);
        views = (ImageView) findViewById(R.id.drop_down);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        special.setThreshold(1);
        special.setAdapter(adapter);

        number = (EditText) findViewById(R.id.phone_number);

        views_for_questionsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionsr.showDropDown();
            }
        });

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
                SPECIALIZATION = special.getText().toString();
                ANSWERS = questionsr.getText().toString();
                GOD = answer.getText().toString();

                if (name.getText().toString().trim().equals("") && surname.getText().toString().trim().equals("") &&
                        qualification.getText().toString().trim().equals("") && unversity.getText().toString().trim().equals("") &&
                        email.getText().toString().trim().equals("") && password.getText().toString().trim().equals("")
                        && phone.getText().toString().trim().equals("") && special.getText().toString().trim().equals("")) {

                    name.setError("Please Enter a name");
                    surname.setError("Please Enter a surname");
                    qualification.setError("Please Enter your qualification");
                    unversity.setError("Please Enter university");
                    email.setError("Please Enter your email");
                    password.setError("Please Enter password");
                    phone.setError("Please Enter phone number");
                    special.setError("Please Enter specialization");

                    return;
                }
                if( PHONENUM.trim().length() != 10)
                {
                    phone.setError("Please Enter a 10 digit number");
                    return;
                }



                RequestBody body = new FormBody.Builder()
                        .add("firstname",NAME)
                        .add("lastname",SURNAME)
                        .add("specialization", SPECIALIZATION)
                        .add("qualification",QUALIFICATION)
                        .add("phone",PHONENUM)
                        .add("email",EMAIL)
                        .add("password",PASSWORD)
                        .add("grad_at",UNIVERSITY)
                        .add("token",TOKEN)
                        .add("security_question", ANSWERS)
                        .add("answer_question",GOD)
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

        }
        else {
            Toast.makeText(Registration_Doctor.this,
                    "Password doesn't match", Toast.LENGTH_LONG).show();
        }

    }


    private void getToken(){


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete( Task<String> task) {

                if(!task.isSuccessful()){


                }

                TOKEN = task.getResult();
                Log.d(TAG,TOKEN);



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







}