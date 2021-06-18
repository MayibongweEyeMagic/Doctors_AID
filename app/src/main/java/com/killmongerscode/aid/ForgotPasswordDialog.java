package com.killmongerscode.aid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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

public class ForgotPasswordDialog extends AppCompatDialogFragment {


    EditText confirm;
    OkHttpClient client = new OkHttpClient();

    Activity activity;
    public ForgotPasswordDialog(Activity activity){
        this.activity =activity;


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.forgot_password_dialog, null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        /*post this email to the database to verify it*/ confirm =view.findViewById(R.id.confirm_email);


        builder.setPositiveButton("Check", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Check if email exist or not and do something
                String holder [] = confirm.getText().toString().split(" ");
                String email = holder[0];
                RequestBody body = new FormBody.Builder()
                        .add("email",email)
                        .build();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/home/s2090040/exist.php")
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

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(responseData.equals("doctor's email") || responseData.equals("patient's email")){
                                    Intent intent =new Intent(activity, ForgotPassword.class);
                                    intent.putExtra("email", email);
                                    activity.startActivity(intent);
                                }

                                else{
                                    Toast.makeText(activity, "INVALID EMAIL", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

                    }

                });

            }
        });



        return builder.create();
    }


}
