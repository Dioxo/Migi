package dioxo.migi.NavigationDrawer;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Java_Request.UserRequest;
import dioxo.migi.Objets.Objs.User;
import dioxo.migi.libs.ApplicationContextProvider;
import dioxo.migi.libs.EventBus;

import dioxo.migi.libs.GreenRobotEventBus;

class NavigationRepositoryImpl implements NavigationRepository {
    private EventBus eventBus;

    public NavigationRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void closeSession() {
        SharedPreferences preferences =ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        preferences.edit().remove(Constantes.ID_USER).apply();
        eventBus.post(new NavigationEvent(NavigationEvent.SESSION_CLOSE_SUCCESS));
    }

    @Override
    public void chercherInformationUser() {
        Response.Listener<String> response = response1 -> {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            User user = gson.fromJson(response1, User.class);

            eventBus.post(new NavigationEvent(
                                        NavigationEvent.USER_SUCCESS,user
            ));
        };

        Response.ErrorListener errorListener = error -> {
            eventBus.post(new NavigationEvent(NavigationEvent.USER_ERROR));
        };
        UserRequest userRequest = new UserRequest(response,errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(userRequest);
    }

}
