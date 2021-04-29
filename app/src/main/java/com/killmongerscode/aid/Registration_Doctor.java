package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Registration_Doctor extends AppCompatActivity {

    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private AutoCompleteTextView special;
    private ImageView views;

    Button registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__doctor);

        registration = (Button)findViewById(R.id.button);

        special = (AutoCompleteTextView) findViewById(R.id.specialization);
        views = (ImageView) findViewById(R.id.drop_down);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        special.setThreshold(1);
        special.setAdapter(adapter);

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                special.showDropDown();
            }
        });

//        OkHttpClient client = new OkHttpClient();

        OkHttpClient client = new OkHttpClient();


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestBody body = new FormBody.Builder()
                        .add("firstname","mayibongwe")
                        .add("lastname","bafoly")
                        .add("specialization", "dentist")
                        .add("qualification","doctor")
                        .add("phone","wits")
                        .add("email","mayibongwe.bafoly.mb@gmail.com")
                        .add("password","bitchass")
                        .add("salt","1234")
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/insert.php")
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
                                Toast.makeText(Registration_Doctor.this,
                                        responseData, Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                });






            }
        });
















    }

    // okhttp requests






}