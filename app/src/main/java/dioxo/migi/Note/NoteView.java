package dioxo.migi.Note;

import dioxo.migi.Objets.Objs.Note;

public interface NoteView {
    void chargeNote(Note note);
    void chargeNoteVide();

    void updateNote();

    void insertNote();
    void deleteNote();
}
