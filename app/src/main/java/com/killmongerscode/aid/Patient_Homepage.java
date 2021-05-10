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

public class Patient_Homepage extends AppCompatActivity {

    GridLayout Patient_homepage;
    private TextView welcome;
    Verification verification = new Verification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__homepage);

        Patient_homepage = (GridLayout)findViewById(R.id.Patient_homepage);
        welcome = findViewById(R.id.title_view);

        setSingleEvent(Patient_homepage);

        Bundle bundle = getIntent().getExtras();
        String EMAIL = bundle.getString("email");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("email",EMAIL)
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
                        startActivity(intent);
                    }
                });
            }

            if(i == 2){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_Homepage.this,list_of_patients.class);
                        startActivity(intent);
                    }
                });
            }
            else if(i == 3){
                CardView cardView = (CardView)Patient_homepage.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_Homepage.this,Booking_System.class);
                        startActivity(intent);
                    }
                });
            }
        }


}
    public void get_method(String response) throws JSONException {

        String temp = verification.JSONFUCTION(response);
        String temp1 = verification.get_patient_name();
        String temp2 = verification.get_patient_surname();
        welcome.setText("Hi "+ temp1+ "!");
    }

}