package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;

public class NoteRevision extends StringRequest {
    private static final String route = Constantes.NOTE_REVISION;
    private Map<String, String > parametres;

    public NoteRevision(Response.Listener<String> listener, Response.ErrorListener error){
        super(Request.Method.POST,route,listener,error);


        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        parametres = new HashMap<>();

        SharedPreferences noteSharedPreferences = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        String id_note = noteSharedPreferences.getString(Constantes.NOTE_ACTUAL, null);

        parametres.put(Constantes.ID_USER,id_user);
        parametres.put("idNote",id_note);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}
