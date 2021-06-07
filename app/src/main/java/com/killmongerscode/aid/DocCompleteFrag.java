package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
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

public class DocCompleteFrag extends Fragment {

    View view;
    String email;
    private RecyclerView recyclerView;
    private DocCompleteRecyclerView docCompleteRecyclerView;
    private ArrayList<InOrComplete> usersList =new ArrayList<>();
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doc_complete_frag, container, false);
        Bundle args =getArguments();
        email =args.getString("email");
        recyclerView =(RecyclerView) view.findViewById(R.id.doc_complete_recycler);
        docCompleteRecyclerView = new DocCompleteRecyclerView(getContext(), usersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(docCompleteRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        docCompleteRecyclerView.setDocCompleteCLickLster(new DocCompleteRecyclerView.DocCompleteRecyclerClickListner() {
            @Override
            public void OnCLickLister(int position) {
                ViewCompletedForm viewCompletedForm =new ViewCompletedForm(docCompleteRecyclerView.getIdNumber(position), email);
                viewCompletedForm.show(getChildFragmentManager(), "someSupport");
            }
        });

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("patient_email",email)
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
                                docCompleteRecyclerView.addDocAppointmentComp(new InOrComplete(first_name, last_name, emailAddress,id));
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
