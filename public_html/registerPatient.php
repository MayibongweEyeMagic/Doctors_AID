<?php
include "conn.php";

		$FULLNAME =$_POST["fullname"];
		$LASTNAME =$_POST["lastname"];
		$ADDRESS =$_POST["address"];
		$PHONE =$_POST["phone"];
		$DOB =$_POST["dob"];
		$EMAIL =$_POST["email"];
		$PASS =$_POST["password"];

		if (empty($FULLNAME) || empty($LASTNAME) || empty($ADDRESS) || empty($PHONE) || empty($DOB) || empty($EMAIL) || empty($PASS)){
			echo "Can't register one of the fields are empty";
		}

		else if(!filter_var($EMAIL, FILTER_VALIDATE_EMAIL)){
				echo "Invalid email";
		}

		else {
			$checkEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$EMAIL'";
			$connectEmail =mysqli_query($conn, $checkEmail);

			if (mysqli_num_rows($connectEmail) > 0){
				echo "Email already exists";
			}

			else {

				$sql =mysqli_query($conn, "INSERT INTO PATIENT(PATIENT_FNAME, PATIENT_LNAME,PATIENT_ADDRESS, PATIENT_PHONE, PATIENT_DOB, PATIENT_EMAIL, PATIENT_PASS) VALUES('$FULLNAME','$LASTNAME','$ADDRESS ','$PHONE','$DOB','$EMAIL','$PASS')");

				if($sql){
					echo "Successfully Registered";

				}
				else{
					echo "Something went wrong please try again later";
				}
			}
		}

mysqli_close($conn);

?>
