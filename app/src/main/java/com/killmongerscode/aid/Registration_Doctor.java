package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Registration_Doctor extends AppCompatActivity {

    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private AutoCompleteTextView special;
    private ImageView views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__doctor);

        special = (AutoCompleteTextView) findViewById(R.id.specialization);
        views = (ImageView) findViewById(R.id.drop_down);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        special.setThreshold(1);
        special.setAdapter(adapter);

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                special.showDropDown();
            }
        });

    }
}