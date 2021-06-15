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

public class patient_appointments_adapterTest {

    private static FragmentManager da = Mockito.mock(FragmentManager.class);
    private static patient_appointments_adapter patient_appointments_adapter;


    @Test
    public void testing(){
        Fragment f = new Fragment();
        Fragment ff = patient_appointments_adapter.getDoctorname();
        int c = patient_appointments_adapter.getItemCount();

        boolean test1 = f == ff;
        boolean test2 = c == 1;

        assertTrue(test1 && test2);
    }

}