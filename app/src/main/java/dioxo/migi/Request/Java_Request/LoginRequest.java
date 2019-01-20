package dioxo.migi.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class LoginRequest extends StringRequest {

    private static final String route = "";
    private Map<String, String > parametres;

    public LoginRequest(String email, String password, Response.Listener<String> listener){
        super(Method.POST,route,listener,null);

        parametres = new HashMap<>();
        parametres.put("email",email);
        parametres.put("password",password);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}
