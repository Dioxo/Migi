<?php
require_once('./DB_Connect.php');
$id_user = $_POST['id_user'];
$id_note = $_POST['id_note'];
$q = $_POST['q'];

$sql = "CALL agregar_revision(".$id_user.",".$id_note.",".$q.")";

if ($conn->query($sql) === TRUE) {
    $res = array("result" => true);
} else {
    $res = array("result" => false);
}
//  print_r($Notes_id);
echo json_encode($res);
//echo (json_encode($Notes_id));
$conn->close();

?>
