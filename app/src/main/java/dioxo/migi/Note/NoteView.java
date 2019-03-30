package dioxo.migi.Note;

import dioxo.migi.Objets.Objs.Note;

public interface NoteView {
    void chargeNote(Note note);
    void chargeNoteVide();

    void updateNote();

    void insertNote();
    void deleteNote();

    void showProgressBar();
    void hideProgressBar();
    void enableInputs();
    void disableInputs();

    void update(boolean success);
    void insert(boolean success);
    void delete(boolean success);
    void reviserNote(boolean success);

    void ajouterTagAuView(String tagName);
}
