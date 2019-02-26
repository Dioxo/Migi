package dioxo.migi.Objets.Objs;

import android.graphics.Color;

import java.lang.annotation.Annotation;

public class Tag implements Deprecated{
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


    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

