package dioxo.migi.Objets.Objs;

import android.util.Log;

public class ListTag extends LList<String>{

    public ListTag() {
    }

    public ListTag(String elem, String color) {
        super(elem, color);
    }

    public ListTag(String head, String color, LList<String> tail) {
        super(head, color, tail);
    }

    @Override
    public void add(String elem, String color) {
        Node<String> p = getHead();

        //état initial, où on pense que il n'y a pas de tags repetés
        boolean flag = true;
        while(p != null && flag){

            //le tag existe déjà
            if(p.getText().equals(elem)){
                flag = false;
            }


            p = p.getNext();
        }

        // si le tag n'existe pas
        if(flag){
            super.add(elem, color);
        }else{
            Log.i(" Tag Error", "Le tag existe déjà");
        }

    }
}
