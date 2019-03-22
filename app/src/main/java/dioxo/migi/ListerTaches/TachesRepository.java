package dioxo.migi.ListerTaches;

public interface TachesRepository {
    void chercherNotes();
    void chercherNotes(String tag);

    void closeSession();

    void chercherNotesRevision();
}
