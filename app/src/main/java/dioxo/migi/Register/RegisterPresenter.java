package dioxo.migi.Register;

import dioxo.migi.Objets.Objs.User;

public interface RegisterPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(RegisterEvent authenticationEvent);

    void registerUser(User user);
}
