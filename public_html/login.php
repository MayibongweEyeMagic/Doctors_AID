<?php
  include "conn.php";

    $mail =$_POST["email"];
    $pass =$_POST["password"];


    if (empty($mail) || empty($pass)) {
    	echo "Fields are empty";
	} 
	elseif (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
        echo "Invalid email";
    }

    else {
        $patientEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$mail'";
        $patientPass ="SELECT * FROM PATIENT WHERE PATIENT_PASS LIKE '$pass'";

        $A ="SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$mail'";
        $B  ="SELECT * FROM DOCTOR WHERE DOCTOR_PASS LIKE '$pass'";

        $checkPatientEmail  =mysqli_query($conn, $patientEmail);
        $checkPatientPass  =mysqli_query($conn, $patientPass);
        $SA = mysqli_query($conn, $A);
        $SB  = mysqli_query($conn, $BHASH);

        if(mysqli_num_rows($checkPatientEmail) > 0){
            if (mysqli_num_rows($checkPatientPass) > 0) {
                echo "Patient";
            }
            else {
                echo "Password is incorrect";
            }
        }

    	else if(mysqli_num_rows($SA) > 0){
            if (mysqli_num_rows($SB) > 0) {
                echo "Doctor";
            }
			else{
				echo "Password is incorrect"; //if password is invalid, that means
			}
        }

        else {
            echo "Email does not exist please register!";
        }
	}

mysqli_close($conn);


?>
