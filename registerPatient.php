<?php
require "conn.php";

$firstname=$_POST["firstname"];
$lastname=$_POST["lastname"];
$address=$_POST["address"];
$dob=$_POST["dob"];
$email=$_POST["email"];
$password=$_POST["password"];
$salt=$POST["salt"];

if($conn){

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
		else{
			#removed patient_username
			$sql_register = "INSERT INTO PATIENT(PATIENT_FNAME,PATIENT_LNAME,PATIENT_ADDRESS,PATIENT_DOB,PATIENT_EMAIL,PATIENT_PASS,PATIENT_SALT) VALUES('$firstname','$lastname','$address','$dob','$email','$password','$salt')";

			if(mysqli_query($conn,$sql_register)){
				echo "Successfully Registered! ";
			}
			else{
				echo "Failed To Register! ";
			}
		}
	}
}


else{
	echo "Connection error! ";
}


?>
