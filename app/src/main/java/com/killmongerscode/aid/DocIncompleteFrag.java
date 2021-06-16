package com.killmongerscode.aid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class DocIncompleteFrag extends Fragment {

    View view;
    String email;
    private DocIncompleteRecyclerView.DocRecyclerClickListner docIncompleteRecycler;
    DocIncompleteRecyclerView docIncompleteRecyclerView;
    private RecyclerView recyclerView;
    String ID, booking_no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doc_incomplete_frag, container, false);
        Bundle args =getArguments();
        email =args.getString("email");
        recyclerView =(RecyclerView) view.findViewById(R.id.doc_incomplete_recycler);
        docIncompleteRecyclerView = new DocIncompleteRecyclerView(getContext(), docIncompleteRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(docIncompleteRecyclerView);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        docIncompleteRecyclerView.setDocCLickLster( docIncompleteRecycler =new DocIncompleteRecyclerView.DocRecyclerClickListner() {
            @Override
            public void DocOnCLickLister(int position) {
                ID =docIncompleteRecyclerView.getIdNumber(position);
                DocComplete_form docComplete_form =new DocComplete_form(position, docIncompleteRecyclerView, ID, getActivity());
                docComplete_form.show(getChildFragmentManager(), "someSupport");
            }

            @Override
            public void DocOnClickListerView(int position) {
                booking_no =docIncompleteRecyclerView.getIdNumber(position);
                ViewDetailsOfBooking viewDetailsOfBooking =new ViewDetailsOfBooking(email, booking_no);
                viewDetailsOfBooking.show(getChildFragmentManager(), "someSupport");
            }
        }
        );


        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("patient_email",email)
                .build();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2090040/accepted_patientList.php")
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

                            JSONArray jsonArray = new JSONArray(responseData);
                            for(int i=0; i< jsonArray.length();++i) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String first_name = jsonObject.getString("PATIENT_FNAME");
                                String last_name = jsonObject.getString("PATIENT_LNAME");
                                String emailAddress = jsonObject.getString("PATIENT_EMAIL");
                                String id = jsonObject.getString("BOOKING_NO");
                                docIncompleteRecyclerView.addDocAppointment(new InOrComplete(first_name, last_name, emailAddress,id));
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
