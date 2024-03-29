package com.killmongerscode.aid;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.killmongerscode.aid.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        IncompleteFrag frag = new IncompleteFrag();
        CompleteFrag comFrag = new CompleteFrag();
        Bundle args = new Bundle();

        args.putString("email", getIntent().getExtras().getString("email"));
        frag.setArguments(args);
        comFrag.setArguments(args);

        ArrayList<String> tabTitles = new ArrayList<>(Arrays.asList("Incomplete appointments", "complete appointments"));
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(frag, comFrag));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabTitles, fragments);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);



    }
}