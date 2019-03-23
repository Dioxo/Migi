package dioxo.migi.Register;

import dioxo.migi.Objets.Objs.User;

public interface RegisterPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(RegisterEvent registerEvent);

    void registerUser(User user);
}
