package dioxo.migi.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dioxo.migi.NavigationDrawer.NavigationDrawer;
import dioxo.migi.R;
import dioxo.migi.Register.RegisterActivity;
import dioxo.migi.Register.RegisterView;

public class AuthenticationActivity extends AppCompatActivity implements Authentication_View {

    @BindView(R.id.edTxtUser)
    EditText edTxtUser;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.register)
    TextView register;
    private Authentication_Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new Authentication_Presenter_Impl(this);
        presenter.onCreate();
        presenter.checkAlreadyConnected();
        hideProgressBar();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void enableInputs() {
        edTxtPassword.setEnabled(true);
        edTxtUser.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    public void disableInputs() {
        edTxtPassword.setEnabled(false);
        edTxtUser.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    public void goToNextPage() {
        Log.e("Login", "Success");
        /*SharedPreferences settings = getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        Log.e("Login", id_user + " Id user");*/


        Intent intent = new Intent(this, NavigationDrawer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
    public void loginError(String error) {
        effacerText();
        edTxtPassword.setError(error);
    }

    private void effacerText() {
        edTxtPassword.setText("");
        edTxtUser.setText("");
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        presenter.confirmerMDP(edTxtUser.getText().toString(), edTxtPassword.getText().toString());
        effacerText();
    }

    @OnClick(R.id.register)
    public void onRegisterClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
