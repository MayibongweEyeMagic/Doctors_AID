<?php
require "conn.php";
#retrieve all the patient's booking history
#for an individual file doctor'sname, doctor type, date of visit

$patient_userid = $_POST["patient_userid"];
$p_id = (int)$patient_userid;

if($conn){
    $patient_query = "SELECT * FROM BOOKINGS WHERE PATIENT_NO= '$p_id' AND STATUS='FULFILLED'";

    $sql_patientQ = mysqli_query($conn,$patient_query);
    $output = array();
    if($result=$sql_patientQ){
        while($row = $result->fetch_assoc()){
            $output [] = $row;
        }
    }
}
mysqli_close($conn);
echo json_encode($output);

?>