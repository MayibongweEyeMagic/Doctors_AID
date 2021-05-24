<?php

        include "conn.php";

        $patient_email = $_POST["email"];

        if(!empty($patient_email)){

        $outcome = "SELECT STATUS,BOOKING_NO,BOOKING_DATE FROM BOOKINGS WHERE PATIENT_EMAIL LIKE '$patient_email' AND STATUS LIKE 'FULFILLED' AND STATUS LIKE 'PENDING' ";
        $QUERY_PATIENT = mysqli_query($conn,$outcome);
        $output = array();
        if($result=$QUERY_PATIENT ){

                while($row = $result->fetch_assoc()){
                        $output[] = $row;
                                }
                        }
        else{
                die('Could not get data: '.$mysqli->error);
                }
                echo json_encode($output);
        }
        else{
                echo "Patient email could not be found";
                }
        mysqli_close($conn);

?> 