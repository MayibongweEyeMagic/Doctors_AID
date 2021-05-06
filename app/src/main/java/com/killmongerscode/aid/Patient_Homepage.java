package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.EventListener;

public class Patient_Homepage extends AppCompatActivity {

    GridLayout Patient_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__homepage);

        Patient_homepage = (GridLayout)findViewById(R.id.Patient_homepage);

        setSingleEvent(Patient_homepage);
    }

    private void setSingleEvent(GridLayout Patient_homepage) {
        for (int i = 0; i < Patient_homepage.getChildCount();i++)
        {

            CardView cardView = (CardView)Patient_homepage.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {}
            });
        }
    }
}