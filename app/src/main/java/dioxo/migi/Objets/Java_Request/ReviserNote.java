package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;

public class ReviserNote extends StringRequest {
    private static final String route = Constantes.INSERTAR_REVISION;
    private Map<String, String> parametres;

    public ReviserNote(Response.Listener<String> listener, String qualification) {
        super(Request.Method.POST, route, listener, null);

        /*
         * Change params to send to request
         * */

        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER, null);

        SharedPreferences noteSharedPreferences = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        String idNote = noteSharedPreferences.getString(Constantes.NOTE_ACTUAL, null);

        parametres = new HashMap<>();
        parametres.put(Constantes.ID_USER, id_user);

        //double coef_revision =EF-0.8+0.28*q-0.02*q*q;

        parametres.put("q", qualification);
        parametres.put("id_note", idNote);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}