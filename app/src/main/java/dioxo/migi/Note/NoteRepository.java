package dioxo.migi.Note;

import dioxo.migi.Objets.Objs.Note;

public interface NoteRepository {
    void updateNote(Note note);
    void insertNote(Note note);
    void deleteNote(String note);
}
