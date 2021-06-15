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
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DoctorTest {
    String NAME = "name";
    String SURNAME = "surname";

    @Rule
    public ActivityTestRule<Doctor> activityTestRule = new ActivityTestRule<>(Doctor.class);











    @Test
    public void testingsetName(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setName("Phindulo"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetSurname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getSurname());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsetSurname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setSurname("Makhado"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetTel(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getTel());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsetTel(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setTel("0794166035"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetemail(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getEmail());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsetEmail(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setEmail("1832463@students.wits.ac.za"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetSpecialite(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getSpecialite());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsetSpeccialite(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setSpecialite("Data Scientist"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetuniversity(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getUniversity());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingsetUniversity(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().setUniversity("Wits"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetQualification(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}