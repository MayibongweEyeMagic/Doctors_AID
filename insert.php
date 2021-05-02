<?php
require "conn.php";

$sql_register = "INSERT INTO DOCTOR(DOCTOR_FNAME,DOCTOR_LNAME,DOCTOR_SPEC,DOCTOR_QLF,DOCTOR_PHONE,DOCTOR_EMAIL,DOCTOR_PASS,DOCTOR_SALT) VALUES('fullname','lastname','specification','qualification','PHONE','email','password','SALT')";
if ($conn->query($sql_register) === TRUE) {
    echo "New record created successfully";
  } else {
    echo "Error: " . $sql_register . "<br>" . $conn->error;
  }

    $q= "SELECT * FROM DOCTOR";
    
    $sql = mysqli_query($conn,$q);
    $output = array();
    if($result=$sql){
        while($row = $result->fetch_assoc()){
            $output [] = $row;
        }
    }


echo json_encode($output);
mysqli_close($conn);


?>

<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 $Sql_Query = "select * from UserLoginTable where user_email = '$email' and user_password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }
 else{
  echo "Check Again";
  }
mysqli_close($con);

?>