package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class Doctor_Homepage extends AppCompatActivity {

    GridLayout doctor_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__homepage);

        doctor_home = (GridLayout)findViewById(R.id.doctor_home);

        setSingleEvent(doctor_home);
    }

    private void setSingleEvent(GridLayout doctor_home) {
        for (int i = 0; i < doctor_home.getChildCount();i++)
        {

            CardView cardView = (CardView)doctor_home.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {}
            });
        }
    }
}