<?php
 include "conn.php";

$email=$_POST["email"];

//check if email exists in the database
if(!empty($email)){
    $dr="SELECT DOCTOR_EMAIL FROM DOCTOR WHERE DOCTOR_EMAIL='$email'";
    $patient="SELECT PATIENT_EMAIL FROM PATIENT WHERE PATIENT_EMAIL='$email'";
    
    $dr_query = mysqli_query($conn, $dr);
    $patient_query = mysqli_query($conn, $patient);
    
    if(mysqli_num_rows($dr_query)==1){
        echo "doctor's email";
    }
    elseif(mysqli_num_rows($patient_query)==1){
        echo "patient's email";
    }
    else{
        echo "Email does not exist";
    }
}
else{
    echo "No email input";
}


mysqli_close($conn);

?>
