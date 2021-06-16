package com.killmongerscode.aid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WhyRejectDialog extends AppCompatDialogFragment {

    EditText reason_for_reject;
    String booking_no, token;
    int position;




    public WhyRejectDialog(String booking_no, String token, int position) {
        this.booking_no = booking_no;
        this.token = token;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.why_reject_booking, null);

        reason_for_reject =view.findViewById(R.id.reason_to_reject);

        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String reason_why =reason_for_reject.getText().toString();

                        OkHttpClient client = new OkHttpClient();
                        RequestBody body1 = new FormBody.Builder()
                                .add("booking_number",booking_no)
                                .add("token", token)
                                .add("why_reject", reason_why)
                                .add("status","REJECTED")
                                .build();

                        Request request1 = new Request.Builder()
                                .url("https://lamp.ms.wits.ac.za/home/s2090040/update_status.php")
                                .post(body1)
                                .build();

                        client.newCall(request1).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                                if (!response.isSuccessful()) {
                                    throw new IOException("Unexpected code " + response);
                                }

                                final String responseData = response.body().string();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), responseData, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        ((pending_bookings) requireActivity()).RemoveItem(position);
                    }
                });

        return builder.create();
    }
}
