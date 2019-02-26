package dioxo.migi.NavigationDrawer;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

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
    public void chercherNotes() {
        Log.i("JSON","entró");

        Response.Listener<String> responseSuccess = response1 -> {


            try {
                JSONArray jsonObject = new JSONArray(response1);
                ArrayList<Note> notes = fromJson(jsonObject);
                System.out.println(notes);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            Log.i("JSON", "Error");
        };


        NoteRequest noteRequest = new NoteRequest(responseSuccess,errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());

        request.add(noteRequest);
    }

    private ArrayList<Note> fromJson(JSONArray jsonObject) {
        JSONObject noteJSON;
        ArrayList<Note> notes = new ArrayList<Note>(jsonObject.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonObject.length(); i++) {
            try {
                noteJSON = jsonObject.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Note note = Note.fromJson(noteJSON);
            if (note != null) {
                notes.add(note);
            }
        }

        return notes;
    }


}
