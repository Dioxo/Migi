package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;

public class DeleteTag extends StringRequest {
    private static final String route = Constantes.DELETE_TAG_NOTE;
    private Map<String, String > parametres;

    public DeleteTag(Response.Listener<String> listener, String textTag ){
        super(Request.Method.POST,route,listener,null);

        /*
         * Change params to send to request
         * */

        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);

        SharedPreferences noteSharedPreferences = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        String idNote = noteSharedPreferences.getString(Constantes.NOTE_ACTUAL,null);

        parametres = new HashMap<>();
        parametres.put(Constantes.ID_USER,id_user);
        parametres.put("id_note",idNote);
        parametres.put("text_tag",textTag);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}
