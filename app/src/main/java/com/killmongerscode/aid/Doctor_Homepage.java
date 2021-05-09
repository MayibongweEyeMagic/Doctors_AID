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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;

public class Doctor_Homepage extends AppCompatActivity {

    GridLayout doctor_home;
    TextView welcome;

    String message;
    Verification verification = new Verification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__homepage);

        welcome = (TextView)findViewById(R.id.title_view);
        doctor_home = (GridLayout)findViewById(R.id.doctor_home);

        setSingleEvent(doctor_home);

        Bundle bundle = getIntent().getExtras();
        String EMAIL = bundle.getString("email");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("email",EMAIL)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/loginDoctor.php")
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
                Doctor_Homepage.this.runOnUiThread(new Runnable() {
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
        for (int i = 0; i < doctor_home.getChildCount();i++)
        {
            if(i == 2){
            CardView cardView = (CardView)doctor_home.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Doctor_Homepage.this,list_of_patients.class);
                    startActivity(intent);
                }
            });
            }
            else if(i == 4){
            CardView cardView = (CardView)doctor_home.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Doctor_Homepage.this,Booking_System.class);
                    startActivity(intent);
                }
            });
        }
        }
    }

    public void get_method(String response) throws JSONException {

        String temp = verification.JSONFUCTION_DOCTOR(response);
        String temp1 = verification.get_doctor_name();
        String temp2 = verification.get_doctor_surname();
         welcome.setText("Hi "+ temp1+ "!");
    }



}