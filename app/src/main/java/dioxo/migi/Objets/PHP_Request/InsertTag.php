<?php
require_once('./DB_Connect.php');
require_once('./returnIdNote.php');

$id_user = $_POST['id_user'];
$text_tag = $_POST['text_tag'];
$id_note = $_POST['id_note'];
$sql = "INSERT INTO tag (id_user, text_tag) VALUES (".$id_user.",'".
                                   $text_tag."')";
$confirmation = false;

if ($conn->query($sql) === TRUE) {
    //echo "Record updated successfully"
    $id_tag = IdNote::returnIdTag($id_user,$text_tag ,$conn);
    $confirmation = true;
} else {
    $search = 'Duplicate entry';
    if(preg_match("/{$search}/i", $conn->error)) {
        $confirmation = true;
        $id_tag = IdNote::returnIdTag($id_user,$text_tag ,$conn);
    }else{
    $res = array("result" => false,
                "cause" => $conn->error);
    }
}

if($confirmation){
  
  $sql = "INSERT INTO note_tag VALUES ('".$id_user."','".$id_note."','".$id_tag."')";
  if ($conn->query($sql) === TRUE) {
      //echo "Record updated successfully"
      $res = array("result" => true);
  } else {
      $res = array("result" => false);
  }

}

echo json_encode($res);

$conn->close();

?>
