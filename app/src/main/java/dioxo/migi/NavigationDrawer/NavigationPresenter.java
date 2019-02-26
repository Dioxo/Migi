package dioxo.migi.NavigationDrawer;

public interface NavigationPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(NavigationEvent event);


    void chercherNotes();
    void afficherNotes();
}
