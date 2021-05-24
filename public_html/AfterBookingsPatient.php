

<?php
        //to display the data of the confirmed booking and  the related doctor's i$        require "conn.php";

        $patient_email = $_POST["email"];
        $d_fname = $_POST["docFname"];
        $d_lname = $_POST["docLname"];
        $d_email = $_POST["docEmail"];
        if(!empty($patient_email)){
            $get = "SELECT PATIENT_FNAME,PATIENT_LNAME,DOCTOR_FNAME,DOCTOR_LNAME,DOCTOR_SPEC,BOOKING_NO,STATUS,BOOKING_DATE FROM PATIENT INNER JOIN BOOKINGS INNER DOCTOR  WHERE PATIENT_EMAIL LIKE '$patient_email' AND DOCTOR_FNAME LIKE 'd_fname' AND DOCTOR_LNAME LIKE 'd_lname' AND DOCTOR_EMAIL LIKE 'd_email' AND STATUS LIKE $PENDING'" ;
        $query = mysqli_query($conn,$get);
        $output = array();
                if($result=$query){
                        while($row = $result->fetch_assoc()){
                        $output []=$row;
                        }
                }
                else{
                die('Could not get data: '.mysql_error());
                }
                echo json_encode($output);
                }
                
        else{
                echo "Patients email could not be found";
        }
        mysqli_close($conn);     
     ?>  