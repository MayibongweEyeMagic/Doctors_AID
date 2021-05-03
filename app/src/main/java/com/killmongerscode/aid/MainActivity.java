package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView clicks;
    Button patient_login, doctor_login;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                                    Toast.makeText(MainActivity.this,
                                            responseData, Toast.LENGTH_LONG).show();
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
                                Toast.makeText(MainActivity.this,
                                        responseData, Toast.LENGTH_LONG).show();
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
}