<?php
include "conn.php";
		
		$FULLNAME =$_POST["fullname"];
		$LASTNAME =$_POST["lastname"];
		$ADDRESS =$_POST["address"];
		$PHONE =$_POST["phone"];
		$DOB =$_POST["dob"];
		$EMAIL =$_POST["email"];
		$PASS =$_POST["password"];
		$TOKEN = $_POST["token"];
		$SECURITY_QUESTION=$_POST["security_question"];
		$ANSWER_QUESTION=$_POST["answer_question"];


		if (empty($FULLNAME) || empty($LASTNAME) || empty($ADDRESS) || empty($PHONE) || empty($DOB) || empty($EMAIL) || empty($PASS) ||empty($SECURITY_QUESTION) ||empty($ANSWER_QUESTION)){
			echo "Can'tregisteroneofthefieldsareempty   ";
		}
		elseif(strlen($PHONE) !=10){
			echo "Phonenumbermusthave10digits!  ";
		}
		elseif(time() <strtotime("+18 years",strtotime($DOB))){
			echo "under18yearsofage.  ";
		}


		else if(!filter_var($EMAIL, FILTER_VALIDATE_EMAIL)){
				echo "Invalidemail ";
		}

		else {
			$checkEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$EMAIL'";
			$connectEmail =mysqli_query($conn, $checkEmail);

			if (mysqli_num_rows($connectEmail) > 0){
				echo "Emailalreadyexists  ";
			}

			else {
				$pass = password_hash($PASS,PASSWORD_DEFAULT);

				$sql =mysqli_query($conn, "INSERT INTO PATIENT(PATIENT_FNAME, PATIENT_LNAME,PATIENT_ADDRESS, PATIENT_PHONE, PATIENT_DOB, PATIENT_EMAIL, PATIENT_PASS,TOKEN,SECURITY_QUESTION,ANSWER_QUESTION) VALUES('$FULLNAME','$LASTNAME','$ADDRESS ','$PHONE','$DOB','$EMAIL','$pass','$TOKEN','$SECURITY_QUESTION','$ANSWER_QUESTION')");

				if($sql){
					echo "SuccessfullyRegistered  ";

				}
				else{
					echo "Somethingwentwrongpleasetryagainlater  ";
				}
			}
		}


?>
