package dioxo.migi.Register;

public interface RegisterView {
    void enableInputs();

    void disableInputs();

    void goToNextPage();

    void showProgressBar();
    void hideProgressBar();

    void registerError(String error);
}
