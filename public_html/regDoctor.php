<?php
include "conn.php";

//	if($conn){

		$FNAME = $_POST["firstname"];
		$LNAME = $_POST["lastname"];
		$SPEC = $_POST["specialization"];
		$QUA = $_POST["qualification"];
		$PHONE = $_POST["phone"];
		$EMAIL = $_POST["email"];
		$PASSWORD = $_POST["password"];
		$GRAD = $_POST["grad_at"];

		if (empty($FNAME) || empty($LNAME) || empty($SPEC) || empty($QUA) || empty($PHONE) || empty($EMAIL) || empty($PASSWORD) || empty($GRAD)){
			echo "Some of the fields are empty";
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

				$sql = mysqli_query($conn, "INSERT INTO DOCTOR(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_SPEC, DOCTOR_QLF, DOCTOR_PHONE, DOCTOR_EMAIL, DOCTOR_PASS, GRAD_AT) VALUES('$FNAME','$LNAME','$SPEC','$QUA','$PHONE','$EMAIL','$PASSWORD','$GRAD')");

				if($sql){
					echo "Successfully Registered";
				}

				else{
					echo "Failed To Register! ";
				}
			}

		}

mysqli_close($conn);


?>
