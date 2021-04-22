<?php

require "conn.php";

$email=$_POST["email"];
$password=$_POST["password"];

if($conn){

	$sqlCheckLogin = "SELECT * FROM DOCTOR WHERE DOCTOR_Email = '$email' AND DOCTOR_PASS = '$password'";
	$LoginQuery = mysqli_query($conn,$sqlCheckLogin);

	if(mysqli_num_rows($LoginQuery)>0){
		echo "Successfully Logged In";
	}
	else{
		echo "Incorrect Email or Password";
	}

}
else{

	echo "Connection Error";
}

?>