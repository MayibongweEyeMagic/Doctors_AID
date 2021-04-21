<?php
#dBookingHist 
#for an item, have patient's name, date of visit and reason for visit
require "conn.php";

$doctor_userid = $_POST["doctor_userid"];
$d_id = (int)$doctor_userid;

if($conn){
    $doctor_query = "SELECT * FROM BOOKINGS WHERE DOCTOR_NO= '$d_id' AND STATUS='FULFILLED'";

    $sql_doctorQ = mysqli_query($conn,$doctor_query);
    $output = array();
    if($result=$sql_doctorQ){
        while($row = $result->fetch_assoc()){
            $output [] = $row;
        }
    }
}
mysqli_close($conn);
echo json_encode($output);


?>