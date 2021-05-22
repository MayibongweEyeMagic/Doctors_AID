package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Type_of_Doctor extends AppCompatActivity {

    String doctorBio;

    ArrayList<SelectByProfession> usersList = new ArrayList<>();

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_doctor);

        recyclerView =findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Bundle bundle =getIntent().getExtras();

        String specialization =bundle.getString("specialization");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("specialisation",specialization)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/doctors_list.php")
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
                Type_of_Doctor.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setSelectedDoctors(responseData);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                });

            }



        });



    }

    public void setSelectedDoctors(String json) throws JSONException{
        ArrayList<String> holder =new ArrayList<>();
        holder =getSelectedDoctors(json);

        for(int i=0; i< holder.size();i++){
            String[] thing = holder.get(i).split(":");

            String name = thing[0];
            String surname = thing[1];
            String email = thing[2];

            usersList.add(new SelectByProfession(name,surname,email));
        }

        Choose_Doctor choose_doctor = new Choose_Doctor(usersList, Type_of_Doctor.this);
        recyclerView.setAdapter(choose_doctor);

    }

    public ArrayList getSelectedDoctors(String json) throws JSONException {
        ArrayList<String> List =new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i){

            JSONObject jsonObject = jsonArray.getJSONObject(i);


            String first_name = jsonObject.getString("DOCTOR_FNAME");
            String surname = jsonObject.getString("DOCTOR_LNAME");
            String doctor_email = jsonObject.getString("DOCTOR_EMAIL");


            doctorBio =  first_name + ":" + surname + ":" + doctor_email;

            List.add(doctorBio);
        }

        return List;
    }
}