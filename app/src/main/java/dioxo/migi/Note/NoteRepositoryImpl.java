package dioxo.migi.Note;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dioxo.migi.Constantes;
import dioxo.migi.Objets.Java_Request.DeleteNote;
import dioxo.migi.Objets.Java_Request.InsertNote;
import dioxo.migi.Objets.Java_Request.InsertTag;
import dioxo.migi.Objets.Java_Request.ReviserNote;
import dioxo.migi.Objets.Java_Request.UpdateNote;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.libs.ApplicationContextProvider;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class NoteRepositoryImpl implements NoteRepository {
    EventBus eventBus;

    public NoteRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void updateNote(Note note) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new NoteEvent(NoteEvent.UPDATE_SUCCESS);
                }else{
                    noteEvent = new NoteEvent(NoteEvent.UPDATE_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        UpdateNote updateNote = new UpdateNote(success, note);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(updateNote);
    }

    @Override
    public void insertNote(Note note) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new NoteEvent(NoteEvent.INSERT_SUCCESS);
                    storeNoteId(jsonObject.getString("id_note"));
                }else{
                    noteEvent = new NoteEvent(NoteEvent.INSERT_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        InsertNote insertNote = new InsertNote(success, note);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(insertNote);

    }

    private void storeNoteId(String id_note) {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.NOTE_ACTUAL, id_note);

        // Commit the edits!
        editor.apply();
    }

    @Override
    public void deleteNote() {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    Log.i("Note", "result " + jsonObject.getBoolean("result") );
                    noteEvent = new NoteEvent(NoteEvent.DELETE_SUCCESS);
                }else{
                    Log.i("Note", "result " + jsonObject.getBoolean("result") );
                    noteEvent = new NoteEvent(NoteEvent.DELETE_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };


        DeleteNote updateNote = new DeleteNote(success);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(updateNote);
    }

    @Override
    public void ajouterTag(String tagNom) {

        Log.i("Tag","NOM "+ tagNom);

        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new NoteEvent(NoteEvent.TAG_INSERT_SUCCESS);
                    noteEvent.setMessage(tagNom);
                }else{
                    noteEvent = new NoteEvent(NoteEvent.TAG_INSERT_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        InsertTag insertTag = new InsertTag(success, tagNom);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(insertTag);

    }

    @Override
    public void reviserNote(String qualification) {


        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new NoteEvent(NoteEvent.REVISER_NOTE_SUCCESS);
                }else{
                    noteEvent = new NoteEvent(NoteEvent.REVISER_NOTE_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        ReviserNote reviserNote = new ReviserNote(success, qualification);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(reviserNote);

    }
}
