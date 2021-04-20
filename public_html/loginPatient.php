<?php

require "conn.php";

$username=$_POST["username"];
$password=$_POST["password"];

if($conn){
	#check username and password separately, aii no it's fine
	$sqlCheckLogin = "SELECT * FROM PATIENT WHERE PATIENT_USERNAME = '$username' AND PATIENT_PASS = '$password'";
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
