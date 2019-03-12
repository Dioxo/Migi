package dioxo.migi.NavigationDrawer;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Java_Request.NoteRequest;
import dioxo.migi.Objets.Objs.Note;
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

}
