<?php
class Tag{

    public function chercherTags($id_user, $id_note,$conn){

        require_once('./DB_Connect.php');
        //echo $_POST['email'];

        $sql = "select text_tag, color_tag from note_tag natural join tag where id_user = " .$id_user." and id_note = " . $id_note . " GROUP BY text_tag";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
            // output data of each row
            $Tag = array();
            $comptTag = 0;
            while($row = $result->fetch_assoc()) {
                $Tag[$comptTag++] = array(
                            "text_tag" => utf8_encode($row["text_tag"]),
                            "color_tag" => utf8_encode($row["color_tag"]),
                        );
            }
            return $Tag;
        }
    }

}



?>
