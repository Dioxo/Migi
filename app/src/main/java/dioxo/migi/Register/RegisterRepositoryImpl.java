package dioxo.migi.Register;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dioxo.migi.Objets.Java_Request.RegisterUser;
import dioxo.migi.Objets.Objs.User;
import dioxo.migi.libs.ApplicationContextProvider;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class RegisterRepositoryImpl implements RegisterRepository {
    private EventBus eventBus;

    public RegisterRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void registerUser(User user) {
        Log.i("Register", "repository");
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.i("Register", response);

                RegisterEvent registerEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    registerEvent = new RegisterEvent(RegisterEvent.REGISTER_SUCCESS);
                }else{
                    registerEvent = new RegisterEvent(RegisterEvent.REGISTER_ERROR);
                }

                eventBus.post(registerEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        RegisterUser registerUser = new RegisterUser(success, user);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(registerUser);

    }
}
