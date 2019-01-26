package dioxo.migi.Objets.Objs;

import android.graphics.Color;

public class Tag {
    private String text;
    private int color;

    public Tag(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }



}

