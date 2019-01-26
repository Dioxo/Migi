package dioxo.migi.Objets.Objs;

import java.util.ArrayList;

public class CardNote {
    private String title,description;
    private ArrayList<Tag> tags;

    public CardNote(String title, String description, ArrayList<Tag> tags) {
        this.title = title;
        this.description = description;
        this.tags = tags;
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

}
