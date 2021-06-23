<?php
  include "conn.php";

	$mail=$_POST["email"];
        
	$pass=$_POST["password"];


        if (empty($mail) || empty($pass)) {
            echo "Fieldsareempty  ";
        }
	elseif (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
            echo "Invalidemail  ";
        }
	else {
            $doctorEmail ="SELECT * FROM PATIENT WHERE PATIENT_EMAIL LIKE '$mail'";
            $A ="SELECT * FROM DOCTOR WHERE DOCTOR_EMAIL LIKE '$mail'";


            $checkDoctorEmail =mysqli_query($conn, $doctorEmail);
            $SA = mysqli_query($conn, $A);

	    $resultDoc=mysqli_fetch_assoc($SA);

            if (mysqli_num_rows($checkDoctorEmail) > 0) {
               if (password_verify($_POST["password"],$result["PATIENT_PASS"])) {
			  echo"Patient       ";
                }
		else {
                    echo "Passwordisincorrect  ";
                }
            }
            elseif (mysqli_num_rows($SA) > 0) {
               if (password_verify($_POST["password"],$resultDoc["DOCTOR_PASS"])) {
		
                    echo "Doctor  ";
                }
		else {
                    echo "Passwordisincorrect  ";
                }
            }
	    else{
                echo "Emaildoesnotexistpleaseregister!  ";
            }
        }

mysqli_close($conn);
?>

