package dioxo.migi.Note;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import dioxo.migi.Objets.Java_Request.DeleteNote;
import dioxo.migi.Objets.Java_Request.InsertNote;
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

    @Override
    public void deleteNote(String note) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                NoteEvent noteEvent;

                // si l'actualisation est effectuée
                if(jsonObject.getBoolean("result")){
                    noteEvent = new NoteEvent(NoteEvent.DELETE_SUCCESS);
                }else{
                    noteEvent = new NoteEvent(NoteEvent.DELETE_ERROR);
                }

                eventBus.post(noteEvent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        DeleteNote updateNote = new DeleteNote(success, note);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(updateNote);
    }
}
