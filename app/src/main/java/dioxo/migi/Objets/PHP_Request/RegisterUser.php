<?php
require_once('./DB_Connect.php');

$email = $_POST['email'];
$password = $_POST['password'];
$nickname = $_POST['nickname'];


$sql = "INSERT INTO user(email, password, nickname) VALUES ('".$email."', '".$password."','".$nickname."')";

if ($conn->query($sql) === TRUE) {
    //echo "Record updated successfully"
    $res = array("result" => true);
} else {
    $res = array("result" => false);
}

//  print_r($Notes_id);
echo json_encode($res);
//echo (json_encode($Notes_id));
$conn->close();

?>
