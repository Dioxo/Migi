package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.libs.ApplicationContextProvider;

public class UpdateNote extends StringRequest {
    private static final String route = Constantes.UPDATE_ROUTE;
    private Map<String, String > parametres;

    public UpdateNote(Response.Listener<String> listener, Note note ){
        super(Request.Method.POST,route,listener,null);

        /*
        * Change params to send to request
        * */

        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        parametres = new HashMap<>();

        SharedPreferences noteSharedPreferences = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        String id_note = noteSharedPreferences.getString(Constantes.NOTE_ACTUAL, null);

        Log.i("Note", id_user + " " + note.getIdNote() + " " + note.getTitle() + " " + note.getDescription());
        parametres.put(Constantes.ID_USER,id_user);
        parametres.put("idNote",id_note);
        parametres.put("title",note.getTitle());
        parametres.put("description",note.getDescription());
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}

