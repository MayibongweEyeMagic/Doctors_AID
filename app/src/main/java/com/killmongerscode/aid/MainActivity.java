package com.killmongerscode.aid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicks = (TextView) findViewById(R.id.register_path);

        clicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    //this function right here creates your dialog so you can choose
    //who to register as
    public void openDialog(){
        DirectionDialog directionDialog = new DirectionDialog();
        directionDialog.show(getSupportFragmentManager(), "registration pages");
    }
}