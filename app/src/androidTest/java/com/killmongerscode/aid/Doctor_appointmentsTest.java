package com.killmongerscode.aid;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Doctor_appointmentsTest {

    @Rule
    public ActivityTestRule<Doctor_appointments> main = new ActivityTestRule<Doctor_appointments>(Doctor_appointments.class);


    @Test
    public void testShouldLaunchTheMainActivityAndFindItemsInTheList() throws Exception {
        RecyclerView recyclerView = (RecyclerView) main.getActivity().findViewById(R.id.list_appointments);
        assertThat(recyclerView.getScrollBarSize(), is(11));
    }

    @Before
    public void init() {
        main.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void testShouldTestTheItemNameInTheList() throws Exception {
        RecyclerView recyclerView = (RecyclerView) main.getActivity().findViewById(R.id.list_appointments);

        if (recyclerView.getChildCount() > 0) {
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                if (i == 1) {
                    onView(withId(R.id.list_appointments)).perform(RecyclerViewActions.actionOnItemAtPosition
                            (1, typeTextIntoFocusedView("Flag")));
                }
            }
        }
    }

}