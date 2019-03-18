package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.libs.ApplicationContextProvider;

public class TagsRequest extends StringRequest {

    private static final String route = Constantes.SELECT_TAG;
    private Map<String, String > parametres;

    public TagsRequest(Response.Listener<String> listener){
        super(Method.POST,route,listener, null);

        parametres = new HashMap<>();
        //parametres.put("password",password);
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);

        parametres.put(Constantes.ID_USER,id_user);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}
