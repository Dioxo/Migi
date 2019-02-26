
package dioxo.migi.Objets.Objs;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Note implements Serializable
{

    @SerializedName("id_note")
    @Expose
    private String idNote;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Tags ")
    @Expose
    private List<Tags> tags = null;
    private final static long serialVersionUID = -1081055512385406330L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Note() {
    }

    /**
     * 
     * @param tags
     * @param title
     * @param description
     * @param idNote
     */
    public Note(String idNote, String title, String description, List<Tags> tags) {
        super();
        this.idNote = idNote;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public String getIdNote() {
        return idNote;
    }

    public void setIdNote(String idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        Log.i("JSON", "notes.toString");
        Log.i("JSON", idNote);
        Log.i("JSON", title);
        Log.i("JSON", description);
        return "Note{" +
                "idNote='" + idNote + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }

    public static Note fromJson(JSONObject jsonObject) {
        Note b = new Note();
        // Deserialize json into object fields
        try {
            b.idNote = jsonObject.getString("id_note");
            b.title = jsonObject.getString("title");
            b.description = jsonObject.getString("description");

            if(jsonObject.has("Tags")){
                b.tags = JsonTags(jsonObject.getJSONArray("Tags"));
            }else{
                b.tags = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

    private static ArrayList<Tags> JsonTags(JSONArray jsonObject) {
        JSONObject tagsJSON;
        ArrayList<Tags> tags = new ArrayList<>(jsonObject.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonObject.length(); i++) {
            try {
                tagsJSON = jsonObject.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tags tag = Tags.fromJson(tagsJSON);
            if (tag != null) {
                tags.add(tag);
            }
        }

        return tags;
    }
}
