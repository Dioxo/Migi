package dioxo.migi.Authentication;



import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Java_Request.LoginRequest;
import dioxo.migi.libs.ApplicationContextProvider;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

public class Authentication_Repository_Impl implements Authentication_Repository {
    private Authentication_Event event;
    private EventBus eventBus;

    public Authentication_Repository_Impl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void confirmerMDP(String email, String mdp) {
        /*event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OKAY);

        eventBus.post(event);*/

        Log.i("Login" , "Email : " + email);
        Log.i("Login" , "Password : " + mdp);

        Response.Listener<String> response = response1 -> {
            try {

                JSONObject jsonObject = new JSONObject(response1);
                Log.i("Login","Response : " + response1);

                Log.i("Login","JSON mdp : " + jsonObject);
                String password = jsonObject.getString("password");
                if(password.equals(mdp)){
                    storeUser_id(jsonObject.getString("id_user"));
                    Log.i("Login","ok");
                    event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OKAY);
                }else{
                    Log.i("Login","error");
                    event = new  Authentication_Event(Authentication_Event.AUTHENTICATION_ERROR,
                            Authentication_Event.AUTHENTICATION_ERROR_MESSAGE );
                }
                Log.i("Login" , "Event " +  event.toString());
                eventBus.post(event);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Login","Error : " + error);
                event = new  Authentication_Event(Authentication_Event.AUTHENTICATION_ERROR,
                        Authentication_Event.AUTHENTICATION_ERROR_MESSAGE);
                eventBus.post(event);

        }
        };

        LoginRequest loginRequest = new LoginRequest(email,response,errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());

        request.add(loginRequest);
    }

    private void storeUser_id(String id_user) {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.ID_USER, id_user);

        // Commit the edits!
        editor.apply();
    }

}
