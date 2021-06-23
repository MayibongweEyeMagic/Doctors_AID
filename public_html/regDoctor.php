<?php
include "conn.php";


		$FNAME = $_POST["firstname"];
		$LNAME = $_POST["lastname"];
		$SPEC = $_POST["specialization"];
		$QUA = $_POST["qualification"];
		$PHONE = $_POST["phone"];
		$EMAIL = $_POST["email"];
		$PASSWORD = $_POST["password"];
		$GRAD = $_POST["grad_at"];
		$TOKENN = $_POST["token"];
		$SECURITY_QUESTION=$_POST["security_question"];
		$ANSWER_QUESTION=$_POST["answer_question"];

		if (empty($FNAME) || empty($LNAME) || empty($SPEC) || empty($QUA) || empty($PHONE) || empty($EMAIL) || empty($PASSWORD) || empty($GRAD) || empty($SECURITY_QUESTION) || empty($ANSWER_QUESTION)){
			echo "Some of the fields are empty";

		}
		elseif(strlen($PHONE) !=10){
			echo "Phone number must have 10 digits!";
		}
		
		else if(!filter_var($EMAIL, FILTER_VALIDATE_EMAIL)){
			echo "Invalid email";
		}
		else {
			$selectEmail ="SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$EMAIL'";
			$checkEmail  =mysqli_query($conn, $selectEmail);

			if(mysqli_num_rows($checkEmail) > 0){
				echo "Email already exist";
			}

			else {
				$pass = password_hash($PASSWORD,PASSWORD_DEFAULT);
				$sql = mysqli_query($conn, "INSERT INTO DOCTOR(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_SPEC, DOCTOR_QLF, DOCTOR_PHONE, DOCTOR_EMAIL, DOCTOR_PASS, GRAD_AT,TOKEN,SECURITY_QUESTION,ANSWER_QUESTION) VALUES('$FNAME','$LNAME','$SPEC','$QUA','$PHONE','$EMAIL','$pass','$GRAD','$TOKENN', '$SECURITY_QUESTION', '$ANSWER_QUESTION')");

				if($sql){
					echo "Successfully Registered";
				}

				else{
					echo "Failed To Register";
				}
			}

		}

mysqli_close($conn);


?>
