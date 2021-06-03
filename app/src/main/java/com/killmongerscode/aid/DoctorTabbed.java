package com.killmongerscode.aid;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.killmongerscode.aid.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class DoctorTabbed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_tabbed);
        ViewPager viewPager = findViewById(R.id.view_pager1);
        TabLayout tabs = findViewById(R.id.tabs1);

        DocCompleteFrag docCompleteFrag =new DocCompleteFrag();
        DocIncompleteFrag docIncompleteFrag =new DocIncompleteFrag();
        Bundle args = new Bundle();

        args.putString("email", getIntent().getExtras().getString("email"));
        docCompleteFrag.setArguments(args);
        docIncompleteFrag.setArguments(args);

        ArrayList<String> tabTitles = new ArrayList<>(Arrays.asList("Incomplete appointments", "complete appointments"));
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(docIncompleteFrag, docCompleteFrag));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabTitles, fragments);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);




    }
}