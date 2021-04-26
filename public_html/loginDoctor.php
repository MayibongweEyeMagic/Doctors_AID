<?php

require "conn.php";

$username=$_POST["username"];
$password=$_POST["password"];

if($conn){

	$sqlCheckLogin = "SELECT * FROM DOCTOR WHERE DOCTOR_USERNAME = '$username' AND DOCTOR_PASS = '$password'";
	$LoginQuery = mysqli_query($conn,$sqlCheckLogin);

	if(mysqli_num_rows($LoginQuery)>0){
		echo "Successfully Logged In";
	}
	else{
		echo "Incorrect Username or Password";
	}

}
else{

	echo "Connection Error";
}

?>