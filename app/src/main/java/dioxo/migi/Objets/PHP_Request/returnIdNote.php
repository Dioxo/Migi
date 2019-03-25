<?php
class IdNote{

  //retourne l'idNote de la table note
  public function returnIdNote($id_user, $title, $description , $conn){
    //echo $_POST['email'];
    $sql = "SELECT id_note  FROM note where title = '" . $title . "' and description = '".$description."' and id_user = ".$id_user;
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
            //AJouter l'id utilisateur
            return $row["id_note"];
        }
    }
  }
  public function returnIdTag($id_user, $text_tag , $conn){
    //echo $_POST['email'];
    $sql = " SELECT id_tag from tag where id_user = ".$id_user." and text_tag = '".$text_tag."'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
            //AJouter l'id utilisateur
            return $row["id_tag"];
        }
    }
  }



}
?>
