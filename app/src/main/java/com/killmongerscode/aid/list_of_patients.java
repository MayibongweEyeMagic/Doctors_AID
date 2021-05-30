package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class list_of_patients extends AppCompatActivity {
    private ArrayList<Patient> usersList =new ArrayList<>();
    private RecyclerView recyclerView;


    private String message, patientBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patients);

        recyclerView =findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();

        message = bundle.getString("email");


        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("dr_email",message)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/fulfilled_patientList.php")
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
                list_of_patients.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setField(responseData);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }



        });
    }

    private void setField(String json) throws JSONException{

        ArrayList<String> holder =new ArrayList<>();
        holder =getSeenPatients(json);

            for (int i=0; i < holder.size();i++){

                String[] thing =holder.get(i).split(":");

                String name = thing[0];
                String surname =thing[1];
                String email = thing[2];
                String patient_dob = thing[3];
                String home_address =thing[4];
                String patient_phone =thing[5];
                String reason =thing[6];
                String outcome =thing[7];
                String booking_date =thing[8];
                usersList.add(new Patient(name, surname, email, patient_dob, home_address, patient_phone, reason, outcome, booking_date));
            }
            list_adapter adapter =new list_adapter(usersList, list_of_patients.this);
            recyclerView.setAdapter(adapter);
    }

    public ArrayList<String> getSeenPatients(String json) throws JSONException{
        ArrayList<String> getPatients =new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String first_name = jsonObject.getString("PATIENT_FNAME");
            String surname = jsonObject.getString("PATIENT_LNAME");
            String patient_email =jsonObject.getString("PATIENT_EMAIL");
            String patient_dob = jsonObject.getString("PATIENT_DOB");
            String home_address = jsonObject.getString("PATIENT_ADDRESS");
            String patient_phone = jsonObject.getString("PATIENT_PHONE");
            String reason = jsonObject.getString("REASON");
            String outcome = jsonObject.getString("OUTCOME");
            String booking_date = jsonObject.getString("BOOKING_DATE");

            patientBio = first_name + ":" + surname+ ":" + patient_email
                    + ":" + patient_dob + ":" + home_address+ ":" + patient_phone+ ":" + reason + ":" + outcome+ ":"+ booking_date;
            getPatients.add(patientBio);
        }
        return getPatients;
    }
}