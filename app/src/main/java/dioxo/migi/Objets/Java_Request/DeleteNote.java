package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.libs.ApplicationContextProvider;

public class DeleteNote extends StringRequest {
    private static final String route = Constantes.DELETE_NOTE;
    private Map<String, String > parametres;

    public DeleteNote(Response.Listener<String> listener, String idNote ){
        super(Request.Method.POST,route,listener,null);

        /*
         * Change params to send to request
         * */

        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        parametres = new HashMap<>();
        parametres.put(Constantes.ID_USER,id_user);
        parametres.put("idNote",idNote);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}
