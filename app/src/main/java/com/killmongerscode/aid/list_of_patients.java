package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class list_of_patients extends AppCompatActivity {

    ListView listview;

    String List[]= {"Phindulo Makhado","Sibabalo Luqhide","Mayibongwe Bafoly","Test4","Test5","Test6","Test7","Test8","Test9","Test10","Test11","Test12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofpatient);

        Intent intent = getIntent();

        listview = (ListView)findViewById(R.id.listofpatients);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,List);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String select = listview.getItemAtPosition(position).toString();

                Intent intent = new Intent(list_of_patients.this,PatientDetail.class);
                intent.putExtra("select",select);
                startActivity(intent);

            }
        });


    }
}