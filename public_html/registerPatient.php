<?php
require "conn.php";

$firstname=$_POST["firstname"];
$lastname=$_POST["lastname"];
$address=$_POST["address"];
$dob=$_POST["dob"];
$username=$_POST["username"];
$email=$_POST["email"];
$password=$_POST["password"];
$salt=$POST["salt"];

if($conn){

	if($email != "Email Unavailable! "){

		$isValidEmail = filter_var($email,FILTER_VALIDATE_EMAIL);

		if($isValidEmail == false){
			echo "Email is not valid! ";
		}
		else{
				$sqlCheckEmail = "SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$email'";
				$emailQuery = mysqli_query($conn,$sqlCheckEmail);
				if(mysqli_num_rows($emailQuery)>0){
				
				echo "Email is already registered, use another email! ";
				}
		}

	}

	#check if username exists in database
	$sqlCheckUsername = "SELECT * FROM PATIENT WHERE PATIENT_USERNAME LIKE '$username'";
	$usernameQuery = mysqli_query($conn,$sqlCheckUsername);


	if(mysqli_num_rows($usernameQuery)>0){
		echo "Username is already used, type another one! ";
	}       
	else{
		#I changed patient_id -> patient dob i.e date of birth
		#date has format 'yyyy-mm-dd' e.g '2000-01-01'
		$sql_register = "INSERT INTO PATIENT(PATIENT_FNAME,PATIENT_LNAME,PATIENT_ADDRESS,PATIENT_DOB,PATIENT_USERNAME,PATIENT_EMAIL,PATIENT_PASS,PATIENT_SALT) VALUES('$firstname','$lastname','$address','$dob','$username','$email','$password','$salt')";

		if(mysqli_query($conn,$sql_register)){
			echo "Successfully Registered! ";
		}
		else{
			echo "Failed To Register! ";
		}
	}
}


else{
	echo "Connection error! ";
}


?>
