package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;

public class NoteRequest extends StringRequest {
    private static final String route = Constantes.NOTE_ROUTE;
    private Map<String, String > parametres;

    public NoteRequest(Response.Listener<String> listener, Response.ErrorListener error){
        super(Request.Method.POST,route,listener,error);


        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        parametres = new HashMap<>();
        parametres.put(Constantes.ID_USER,id_user);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}
