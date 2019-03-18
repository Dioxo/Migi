
package dioxo.migi.Objets.Objs;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Tags implements Serializable
{

    @SerializedName("text_tag")
    @Expose
    private String textTag;
    @SerializedName("color_tag")
    @Expose
    private String colorTag;
    private final static long serialVersionUID = 3952317099627131610L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tags() {
    }

    /**
     * 
     * @param colorTag
     * @param textTag
     */
    public Tags(String textTag, String colorTag) {
        super();
        this.textTag = textTag;
        this.colorTag = colorTag;
    }

    public String getTextTag() {
        return textTag;
    }

    public void setTextTag(String textTag) {
        this.textTag = textTag;
    }

    public String getColorTag() {
        return colorTag;
    }

    public void setColorTag(String colorTag) {
        this.colorTag = colorTag;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "textTag='" + textTag + '\'' +
                ", colorTag='" + colorTag + '\'' +
                '}';
    }

    public static Tags fromJson(JSONObject jsonObject) {
        Tags tag = new Tags();
        // Deserialize json into object fields
        try {
            tag.colorTag = jsonObject.getString("color_tag");
            tag.textTag = jsonObject.getString("text_tag");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return tag;
    }
}
