<?php
if($_SERVER['REQUEST_METHOD']=='POST'){


	include "conn.php";

	$firstname=$_POST["firstname"];
	$lastname=$_POST["lastname"];
	$specification=$_POST["specification"];
	$qualification=$_POST["qualification"];
	$grad_at=$_POST["grad_at"];
	$email=$_POST["email"];
	$password=$_POST["password"];
	$salt=$POST["salt"];

	$sqlCheckEmail = "SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$email'";
	$emailQuery = mysqli_fetch_array(mysqli_query($conn,$sqlCheckEmail));
	if(isset($emailQuery)){
		echo "Email already exits, try using another email.";
	}

	else{
		$sql_register = "INSERT INTO DOCTOR(DOCTOR_FNAME,DOCTOR_LNAME,DOCTOR_SPEC,DOCTOR_QLF,GRAD_AT,DOCTOR_EMAIL,DOCTOR_PASS,DOCTOR_SALT) VALUES('$fullname','$lastname','$specification','$qualification','$grad_at','$email','$password','$salt')";

		if(mysqli_query($conn,$sql_register)){
			echo "Successfully Registered";
		}
		else{
			echo "Failed To Register";
		}
		
	}

}

else{
	echo "Connection error";
}

?>