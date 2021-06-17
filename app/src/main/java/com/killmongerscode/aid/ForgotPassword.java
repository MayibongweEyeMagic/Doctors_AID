package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ForgotPassword extends AppCompatActivity {

    EditText new_pass;
    TextView question;
    EditText security_question;
    Button update_pass;
    OkHttpClient client =new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        new_pass =findViewById(R.id.update_password);
        update_pass =findViewById(R.id.update);
        security_question =findViewById(R.id.security_edit);
        question = findViewById(R.id.security_question);


        Bundle bundle =getIntent().getExtras();
        String email =bundle.getString("email");

        RequestBody body = new FormBody.Builder()
                .add("email",email)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/get_answer.php")
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

                ForgotPassword.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ForgotPassword.this, responseData, Toast.LENGTH_SHORT).show();

                        try {
                         JSONArray jsonArray = new JSONArray(responseData);


                            for(int i=0; i< jsonArray.length();++i){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String first_name = jsonObject.getString("SECURITY_QUESTION");
                                question.setText(first_name);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                });

            }

        });




        String temp = security_question.getText().toString();

        update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_password =new_pass.getText().toString();



                RequestBody body = new FormBody.Builder()
                        .add("email",email)
                        .add("answer_question",temp)
                        .add("new_password", new_password)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/update_pass.php")
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

                        ForgotPassword.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                              //  Toast.makeText(ForgotPassword.this, responseData, Toast.LENGTH_SHORT).show();

                                if(responseData.equals("Password has been updated")){
                                    Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(ForgotPassword.this, "your password has been changed", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(ForgotPassword.this, "something went wrong please try again later", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }

                });

            }
        });



    }
}