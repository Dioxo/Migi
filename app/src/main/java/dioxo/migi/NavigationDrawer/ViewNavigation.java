package dioxo.migi.NavigationDrawer;

import dioxo.migi.Objets.Objs.User;

public interface ViewNavigation {
    void goToLogin();
    void chercherInformationUser();

    void changerCredentiels(User user);
}
