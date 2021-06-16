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
public class pending_bookingsTest {

    @Rule
    public ActivityTestRule<pending_bookings> activityTestRule = new ActivityTestRule<>(pending_bookings.class);

    @Test
    public void testingRemoveItem(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().RemoveItem(0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingSetAccept(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().RemoveItem(0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingOnItemDelete(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().RemoveItem(0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testingBuildRecyclerView(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().BuildRecyclerView());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetbookingno(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().getBooking_no(0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testinggetToken(){
        try {
            runOnUiThread(() -> activityTestRule.getActivity().RemoveItem(0));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}