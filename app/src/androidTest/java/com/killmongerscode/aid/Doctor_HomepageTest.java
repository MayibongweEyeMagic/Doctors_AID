package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class Doctor_HomepageTest {

    @Rule
    public ActivityTestRule<Doctor_Homepage> activityTestRule = new ActivityTestRule<>(Doctor_Homepage.class);


    @Test
    public void onCreate() {


        try {
            runOnUiThread(() -> {
                activityTestRule.getActivity().onCreate(new Bundle());
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }


    /*@Test
    public void Item0() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.pro1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @Test
    public void Item1() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.list1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @Test
    public void Item2() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.chat1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @Test
    public void Item3() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.pend1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void Item4() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.conf1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void Item5() {
        try {
            runOnUiThread(() -> {
                PopupMenu p = new PopupMenu(activityTestRule.getActivity(), null);
                p.getMenuInflater().inflate(R.id.doctor_home, p.getMenu());
                MenuItem dummyMenuItem1 = p.getMenu().findItem(R.id.info1);
                activityTestRule.getActivity().onOptionsItemSelected(dummyMenuItem1);
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }*/




}
