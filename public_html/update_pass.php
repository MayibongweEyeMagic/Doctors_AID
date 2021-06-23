<?php 
require "conn.php";
$email =$_POST["email"];
$new_pass = $_POST["new_password"];
$answer_question=$_POST["answer_question"];
//update old password

if (!empty($email) && !empty($new_pass) && !empty($answer_question)) {
//check where email is dr or patient

    $patientEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$email'";
    $checkPatientEmail  =mysqli_query($conn, $patientEmail);

    $A ="SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$email'";
    $SA = mysqli_query($conn, $A);

    if(mysqli_num_rows($checkPatientEmail) > 0){ //if user is a patient
        
        $patient_select="SELECT ANSWER_QUESTION FROM PATIENT WHERE PATIENT_EMAIL='$email' AND ANSWER_QUESTION='$answer_question'";
        $p_query = mysqli_query($conn, $patient_select);

        if(mysqli_num_rows($p_query)==1){
            //echo "The answer to the security question is correct";
            $pass = password_hash($new_pass,PASSWORD_DEFAULT);

            $query = "UPDATE PATIENT SET PATIENT_PASS='$pass' WHERE PATIENT_EMAIL='$email' AND ANSWER_QUESTION='$answer_question'";
            $sql =mysqli_query($conn, $query);
            if($sql){
                echo "Password has been updated";
            }
            else {
                echo "Password did not change";
            }

        }
        else{
            echo "The answer to the security question is incorrect";
        }

    
    }

    else if(mysqli_num_rows($SA) > 0){
		 

        $dr_select="SELECT ANSWER_QUESTION FROM DOCTOR WHERE DOCTOR_EMAIL='$email' AND ANSWER_QUESTION='$answer_question'";
        $d_query = mysqli_query($conn, $dr_select);
        
        if(mysqli_num_rows($d_query)==1){
            //echo "The answer to the security question is correct";

            $pass = password_hash($new_pass,PASSWORD_DEFAULT);
            $query = "UPDATE DOCTOR SET DOCTOR_PASS='$pass' WHERE DOCTOR_EMAIL='$email' AND ANSWER_QUESTION ='$answer_question'";
            $sql =mysqli_query($conn, $query);
            if($sql){
                echo "Password has been updated";
            }
            else {
                echo "Password did not change";
            }

        }
        
        else{
            echo "The answer to the security question is incorrect";
        }
	
    } //end check of rows in doctor table

    
}



mysqli_close($conn);
?>
