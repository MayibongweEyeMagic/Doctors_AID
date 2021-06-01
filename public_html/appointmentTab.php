
<?php
require "conn.php";
$doc_email = $_POST["email"];
if(!$conn){
        die("Connection failed: ".mysqli_connect_error());
}
if(!empty($doc_email)){

        $query="SELECT P.PATIENT_FNAME,P.PATIENT_LNAME,B.BOOKING_DATE,B.STATUS,B.REASON FROM DOCTOR D INNER JOIN BOOKINGS B ON D.DOCTOR_EMAIL=B.DOCTOR_EMAIL INNER JOIN PATIENT P ON B.PATIENT_EMAIL=P.PATIENT_EMAIL WHERE B.DOCTOR_EMAIL='$doc_email' AND STATUS='ACCEPTED' ORDER BY B.BOOKING_DATE;";       
        $query.="SELECT P.PATIENT_FNAME,P.PATIENT_LNAME,B.BOOKING_DATE,B.STATUS,B.REASON FROM DOCTOR D INNER JOIN BOOKINGS B ON D.DOCTOR_EMAIL=B.DOCTOR_EMAIL INNER JOIN PATIENT P ON B.PATIENT_EMAIL=P.PATIENT_EMAIL WHERE B.DOCTOR_EMAIL='$doc_email' AND STATUS='FULFILLED' ORDER BY B.BOOKING_DATE";

        if(mysqli_multi_query($conn,$query)){
                $output = array();
                do{
                        if($result = mysqli_store_result($conn)){
                                while($row = mysqli_fetch_assoc($result)){
                                        $output [] = $row;
                                }
                                mysqli_free_result($result);
                        }
                        if(mysqli_more_results($conn)){
                                echo "--------------\n"."<br>";
                        }
                }
                while(mysqli_next_result($conn));

        }
        else{
                echo " 0 results";
        }
        echo json_encode($output);
}else{
        echo "Doctor's email cannot be found";
        }
mysqli_close($conn);
?>