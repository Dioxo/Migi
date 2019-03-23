package dioxo.migi.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dioxo.migi.Authentication.AuthenticationActivity;
import dioxo.migi.Objets.Objs.User;
import dioxo.migi.R;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    @BindView(R.id.edTxtUser)
    EditText edTxtUser;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.coordinator)
    ConstraintLayout coordinator;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = new RegisterPresenterImpl(this);
    }

    @Override
    public void enableInputs() {
        edtEmail.setEnabled(true);
        edTxtPassword.setEnabled(true);
        edTxtUser.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        edtEmail.setEnabled(false);
        edTxtPassword.setEnabled(false);
        edTxtUser.setEnabled(false);
    }

    @Override
    public void goToNextPage() {
        startActivity(new Intent(this, AuthenticationActivity.class));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void registerError(String error) {
        Snackbar snackbar = Snackbar
                .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        //Si aucun EditText est vide
        if(TextUtils.isEmpty(edtEmail.getText())){
            edtEmail.setError("Erreur, champ vide");
        }

        if(TextUtils.isEmpty(edTxtPassword.getText())){
            edTxtPassword.setError("Erreur, champ vide");
        }

        if(TextUtils.isEmpty(edTxtUser.getText())){
            edTxtUser.setError("Erreur, champ vide");
        }

        if(!TextUtils.isEmpty(edtEmail.getText()) ||
                !TextUtils.isEmpty(edTxtUser.getText()) ||
                !TextUtils.isEmpty(edTxtPassword.getText())){
            presenter.registerUser(new User(edtEmail.getText().toString(),
                                    edTxtPassword.getText().toString(),
                                    edTxtUser.getText().toString()));
        }
    }
}
