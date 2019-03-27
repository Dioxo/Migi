package dioxo.migi.listeTags;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import dioxo.migi.Objets.Java_Request.TagsRequest;
import dioxo.migi.libs.ApplicationContextProvider;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class TagsRepositoryImpl implements TagsRepository {

    EventBus eventBus;

    public TagsRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void chercherTags() {
        Log.i("Tag", "chercher tags Repository");

        Response.Listener<String>  success = response1 -> {
            try {
                JSONArray array = new JSONArray(response1);
                Log.i("Tag", "response Success " + response1);

                ArrayList<String> tags = fromJson(array);

                TagsEvent event = new TagsEvent(TagsEvent.SUCESS, tags);
                eventBus.post(event);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        TagsRequest tagsRequest = new TagsRequest(success);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());

        request.add(tagsRequest);
    }

    private ArrayList<String> fromJson(JSONArray jsonObject) {
        ArrayList<String> tags = new ArrayList<>(jsonObject.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonObject.length(); i++) {
            try {
                tags.add(jsonObject.getString(i));
                System.out.println("Tags " + tags.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }

        return tags;
    }
}
