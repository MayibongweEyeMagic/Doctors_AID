package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompleteFrag  extends Fragment {

    View view;

    String email;

    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private RecyclerView recyclerView;
    private String patientBio;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.complete_1_layout,container,false);
        textView = view.findViewById(R.id.someText);

        textView.setText(email);
        /*recyclerView =(RecyclerView) view.findViewById(R.id.complete_appoints);
        CompleteRecyclerView completeRecyclerView = new CompleteRecyclerView(usersList, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(completeRecyclerView);*/
        return view;
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("emails",email)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/fulfilled_booking.php")
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
                requireActivity().runOnUiThread(new Runnable() {
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

    private void setField(String responseData) throws JSONException{
        ArrayList<String> holder =new ArrayList<>();
        holder =getCompleteDoctors(responseData);

        for (int i=0; i < holder.size();i++){

            String[] thing =holder.get(i).split(":");

            String name = thing[0];
            String surname =thing[1];
            String email = thing[2];
            usersList.add(new InOrComplete(name, surname, email));
        }

    }

    public ArrayList<String> getCompleteDoctors(String json) throws JSONException{
        ArrayList<String> getPatients =new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for(int i=0; i< jsonArray.length();++i) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String first_name = jsonObject.getString("DOCTOR_FNAME");
            String last_name = jsonObject.getString("DOCTOR_LNAME");
            String emailAddress = jsonObject.getString("DOCTOR_EMAIL");

            patientBio = first_name +" "+ last_name +" "+ emailAddress;
            getPatients.add(patientBio);
        }
        return getPatients;
    }*/

    public void setMail(String email){
        this.email = email;
    }
}
