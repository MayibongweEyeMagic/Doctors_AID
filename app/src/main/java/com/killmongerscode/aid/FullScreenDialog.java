package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FullScreenDialog extends DialogFragment implements View.OnClickListener {

    private static String spec;
    private Choose_Doctor choose_doctor;
    private RecyclerView recyclerView;
    private TextView special;

    public FullScreenDialog(String spec){
        FullScreenDialog.spec =spec;
    }

    static FullScreenDialog newInstance(){
        return new FullScreenDialog(spec);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fullscreen_dialog, container, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.list_of_doctors);
        special =view.findViewById(R.id.selected_spec);
        special.setText(spec);
        choose_doctor =new Choose_Doctor();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(choose_doctor);
        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);
        close.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("specialisation",spec)
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
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONArray jsonArray = new JSONArray(responseData);
                            for(int i=0; i< jsonArray.length();++i) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String first_name = jsonObject.getString("DOCTOR_FNAME");
                                String last_name = jsonObject.getString("DOCTOR_LNAME");
                                String special = jsonObject.getString("DOCTOR_SPEC");
                                String qlf = jsonObject.getString("DOCTOR_QLF");
                                String phone =jsonObject.getString("DOCTOR_PHONE");
                                String email =jsonObject.getString("DOCTOR_EMAIL");
                                String grad_at =jsonObject.getString("GRAD_AT");
                                String token =jsonObject.getString("TOKEN");
                                choose_doctor.addDocAppointment(new MakeABooking(first_name, last_name,email,special,
                                        qlf, phone, grad_at, token));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;
        }
    }
}
