package com.killmongerscode.aid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import androidx.fragment.app.FragmentActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Appointment_detailTest {

    @Rule
    public ActivityTestRule<Appointment_detail> activityTestRule = new ActivityTestRule<>(Appointment_detail.class);

    @Test
    public void testingdetailsofvisit(){
        try {
            runOnUiThread(()->{
                activityTestRule.getActivity().findViewById(R.id.date_visit).performClick();
                activityTestRule.getActivity().findViewById(R.id.add_time).performClick();
                activityTestRule.getActivity().findViewById(R.id.add_diagnosis).performClick();
                activityTestRule.getActivity().findViewById(R.id.add_treatment).performClick();
                activityTestRule.getActivity().findViewById(R.id.doctor_complete_appointment).performClick();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingselectdate(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().selectDate());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingselectTime(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().selectTime());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingform(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().form());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}