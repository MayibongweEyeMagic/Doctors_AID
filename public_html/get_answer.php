<?php 
require "conn.php";
$email = $_POST["email"];


if (!empty($email)) {
//check where email is dr or patient

    $patientEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$email'";
    $checkPatientEmail  =mysqli_query($conn, $patientEmail);

    $A ="SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$email'";
    $SA = mysqli_query($conn, $A);

    if(mysqli_num_rows($checkPatientEmail) > 0){ //if user is a patient
        
        $patient_select="SELECT SECURITY_QUESTION FROM PATIENT WHERE PATIENT_EMAIL='$email'";
        $p_query = mysqli_query($conn, $patient_select);
        
        if(mysqli_num_rows($p_query)==1){
            
            while($row = $p_query->fetch_assoc()){
                $output [] = $row;
            }
        }
           
        else {      
            die('Could not get data: ' . mysql_error());
        }
    
        echo json_encode($output);

    
    }

    else if(mysqli_num_rows($SA) > 0){

        $dr_select="SELECT SECURITY_QUESTION FROM DOCTOR WHERE DOCTOR_EMAIL='$email'";
        $d_query = mysqli_query($conn, $dr_select);
        
        if(mysqli_num_rows($d_query)==1){
            //echo "The answer to the security question is correct"
            while($row = $d_query->fetch_assoc()){
                $output [] = $row;
            }
        }
           
        else {      
            die('Could not get data: ' . mysql_error());
        }
    
        echo json_encode($output);
       
    } //end check of rows in doctor table

    else{
        echo "Email does not exist"; //email is incorrect
    }
}

else{/*(empty($email) && empty($pass) && empty($answer_question))*/
    echo "Input unavailable";
}

mysqli_close($conn);
?>
