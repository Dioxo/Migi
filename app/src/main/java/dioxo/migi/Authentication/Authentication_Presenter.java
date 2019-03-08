package dioxo.migi.Authentication;


public interface Authentication_Presenter {
    void confirmerMDP(String email, String mdp);

    void goToNextPage();

    void onCreate();

    void onDestroy();

    void onEventMainThread(Authentication_Event authenticationEvent);

    void checkAlreadyConnected();
}
