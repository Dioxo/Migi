package dioxo.migi.Authentication;


public interface Authentication_View {
    void enableInputs();

    void disableInputs();

    void goToNextPage();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);

}
