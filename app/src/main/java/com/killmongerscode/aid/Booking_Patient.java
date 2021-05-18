package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class Booking_Patient extends AppCompatActivity {


    String[] predefined ={"General", "Optometrist", "Cardiologist", "Pediatrician", "Dentist"};
    private EditText choose_doc;
    private AutoCompleteTextView spec_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_patient);

        spec_field =findViewById(R.id.doctor_spec);
        choose_doc =findViewById(R.id.doctors);

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.select_dialog_item, predefined);
        spec_field.setThreshold(1);
        spec_field.setInputType(0);
        choose_doc.setInputType(0);
        spec_field.setAdapter(adapter);

        spec_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spec_field.showDropDown();

                choose_doc.setVisibility(View.VISIBLE);

            }
        });

    }
}