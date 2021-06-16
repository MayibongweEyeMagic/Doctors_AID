package com.killmongerscode.aid;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

@RunWith(AndroidJUnit4.class)
public class Registration_DoctorTest {

  @Rule
  public ActivityTestRule<Registration_Doctor> activityTestRule =
      new ActivityTestRule<>(Registration_Doctor.class);

  @Test
  public void patiententeringdetailstologin() {
    try {
      runOnUiThread(
          () -> {
            activityTestRule.getActivity().findViewById(R.id.first_name_doctor).performClick();
            activityTestRule.getActivity().findViewById(R.id.last_name_doctor).performClick();
            activityTestRule.getActivity().findViewById(R.id.qualification).performClick();
            activityTestRule.getActivity().findViewById(R.id.qual_obtained_where).performClick();
            activityTestRule.getActivity().findViewById(R.id.doctor_email_address).performClick();
            activityTestRule.getActivity().findViewById(R.id.doctor_password).performClick();
            activityTestRule.getActivity().findViewById(R.id.doctor_pass_confirm).performClick();
            activityTestRule.getActivity().findViewById(R.id.phone_number).performClick();
            activityTestRule.getActivity().findViewById(R.id.doctor_create_account).performClick();
          });
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
