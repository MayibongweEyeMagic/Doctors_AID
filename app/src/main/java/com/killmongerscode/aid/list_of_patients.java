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

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class list_of_patients extends AppCompatActivity {
    private ArrayList<Patient> usersList;
    private RecyclerView recyclerView;
    private list_adapter.RecyclerViewClickListner Listner;
    private String patBio;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patients);
        recyclerView =findViewById(R.id.list);
        usersList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        message = bundle.getString("email");

        setAdapter();


        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("email",message)
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

    private void setAdapter() {
        setOnClickListner();
        list_adapter adapter = new list_adapter(usersList, Listner);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListner() {
        Listner = new list_adapter.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getApplicationContext(), PatientDetail.class);
                intent.putExtra("Patient Name",usersList.get(position).getPatient_name());
                startActivity(intent);
            }

        };
    }

    private void setPatientInfo(String name, String surname, String email) {
        usersList.add(new Patient(name, surname, email));
    }


    private void setField(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String first_name = jsonObject.getString("PATIENT_FNAME");
            String surname = jsonObject.getString("PATIENT_LNAME");
            String patient_dob = jsonObject.getString("PATIENT_DOB");
            String home_address = jsonObject.getString("PATIENT_ADDRESS");
            String patient_email =jsonObject.getString("PATIENT_EMAIL");
            String patient_phone = jsonObject.getString("PATIENT_PHONE");
            String reason = jsonObject.getString("REASON");
            String outcome = jsonObject.getString("OUTCOME");
            String booking_date = jsonObject.getString("BOOKING_DATE");

            // set the fields name, surname and email of the patient
            setPatientInfo(first_name,surname, patient_email);

            patBio = first_name + ":" + surname + ":" + patient_email + ":" + reason+ ":" + outcome + ":" + home_address + ":" + patient_phone + ":" + patient_dob + ":" + booking_date;
        }
    }
}