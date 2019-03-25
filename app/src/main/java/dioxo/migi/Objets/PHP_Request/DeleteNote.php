<?php
require_once('./DB_Connect.php');

$id_user = $_POST['id_user'];
$idNote = $_POST['idNote'];

$sql = "DELETE FROM  note WHERE id_note=".$idNote." AND id_user=".$id_user;

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
