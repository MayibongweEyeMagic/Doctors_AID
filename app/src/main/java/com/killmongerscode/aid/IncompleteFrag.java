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

import java.io.IOException;
import java.util.ArrayList;

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

public class IncompleteFrag  extends Fragment {

    String email;
    View view;
    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private RecyclerView recyclerView;
    String patientBio;

    private IncompleteRecyclerView incompleteRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.incomplete_2_layout, container, false);
            Bundle args = getArguments();
            email = args.getString("email");
            recyclerView =(RecyclerView) view.findViewById(R.id.incomplete_recycler);
            incompleteRecyclerView = new IncompleteRecyclerView(getContext(), usersList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(incompleteRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("emails",email)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/accepted_booking.php")
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray = new JSONArray(responseData);
                            for(int i=0; i< jsonArray.length();++i) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String first_name = jsonObject.getString("DOCTOR_FNAME");
                                String last_name = jsonObject.getString("DOCTOR_LNAME");
                                String emailAddress = jsonObject.getString("DOCTOR_EMAIL");
                                String id ="1";
                                incompleteRecyclerView.addAppointment(new InOrComplete(first_name, last_name, emailAddress, id));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }

        });

    }

}
