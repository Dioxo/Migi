<?php
require_once('./DB_Connect.php');
require_once('./returnIdNote.php');

$id_user = $_POST['id_user'];
$title = $_POST['title'];
$description = $_POST['description'];

$sql = "INSERT INTO note (id_user,title,description) VALUES (".$id_user.",'".
                                   $title."','".
                                    $description."')";

if ($conn->query($sql) === TRUE) {
    //echo "Record updated successfully"
    $id_note = IdNote::returnIdNote($id_user,$title, $description ,$conn);
    $res = array("result" => true,
                "id_note" => $id_note);
} else {
    $res = array("result" => false);
}

//  print_r($Notes_id);
echo json_encode($res);
//echo (json_encode($Notes_id));
$conn->close();

?>
