package dioxo.migi.Objets.Java_Request;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.User;

public class RegisterUser extends StringRequest {
    private static final String route = Constantes.REGISTER_USER;
    private Map<String, String> parametres;

    public RegisterUser(Response.Listener<String> listener, User user) {
        super(Request.Method.POST, route, listener, null);

        /*
         * Change params to send to request
         * */
        parametres = new HashMap<>();
        parametres.put("email", user.getEmail());
        parametres.put("nickname", user.getNickName());
        parametres.put("password", user.getPassword());
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}