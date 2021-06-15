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

public class SelectByProfessionTest {

    @Rule
    public ActivityTestRule<SelectByProfession> activityTestRule = new ActivityTestRule<>(SelectByProfession.class);

    @Test
    public void testinggetDocname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getDocName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetDocsurname(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getDoSurname());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetdocemail(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getDocEmail());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}