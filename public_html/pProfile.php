<?php
require "conn.php";

$patient_userid = $_POST["patient_userid"];
$p_id = (int)$patient_userid;

if($conn){
    $patient_query = "SELECT * FROM PATIENT WHERE PATIENT_NO= '$p_id'";

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