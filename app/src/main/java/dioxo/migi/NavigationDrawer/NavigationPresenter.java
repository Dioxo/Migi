package dioxo.migi.NavigationDrawer;

import java.util.ArrayList;

import dioxo.migi.Objets.Objs.Note;

public interface NavigationPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(NavigationEvent event);


    void closeSession();

    void chercherInformationUser();
}
