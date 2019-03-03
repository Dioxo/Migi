package dioxo.migi.Note;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.R;

public class NoteActivity extends AppCompatActivity implements NoteView {

    @BindView(R.id.txtTitle)
    EditText txtTitle;
    @BindView(R.id.txtDescription)
    EditText txtDescription;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    private NotePresenter presenter;
    private boolean noteAlreadyExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new NotePresenterImpl(this);
        presenter.onCreate();


        hideProgressBar();


        //Confirmer si existe une note qui est passé comme argument
        if (getIntent().getExtras().getSerializable("Note") != null) {
            noteAlreadyExist = true;
            presenter.chechIfNoteClicked(getIntent().getExtras().getSerializable("Note"));
        } else {
            presenter.chechIfNoteClicked(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_options, menu);

        if (noteAlreadyExist) {
            Log.i("Note", menu.getItem(0).getTitle() + " ");
            Log.i("Note", menu.getItem(1).getTitle() + " ");
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(false);

        } else {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(true);

        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.confirmer:
                updateNote();
                break;

            case R.id.effacer:
                deleteNote();
                break;

            case R.id.addNote:
                insertNote();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void chargeNote(Note note) {

        //L'idNote doit etre toujours not null
        /*
         *
         * Sauvegarder la note actuel pour savoir à quelle note je dois
         * Envoyer les modifications
         * */
        storeActualNoteId(note.getIdNote());

        txtTitle.setText(note.getTitle());
        txtDescription.setText(note.getDescription());


    }

    private void storeActualNoteId(String idNote) {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.NOTE_ACTUAL, idNote);

        // Commit the edits!
        editor.apply();

    }

    @Override
    public void chargeNoteVide() {
        txtDescription.setText("");
        txtTitle.setText("");
    }

    @Override
    public void updateNote() {

        if (noteAlreadyExist) {
            Note note = new Note();
            note.setTitle(txtTitle.getText().toString());
            note.setDescription(txtDescription.getText().toString());
            presenter.updateNote(note);
        } else {
            Log.i("Note", "Error, note not exist in BDD");
        }
    }


    @Override
    public void insertNote() {
        if (!noteAlreadyExist) {
            Note note = new Note();
            note.setTitle(txtTitle.getText().toString());
            note.setDescription(txtDescription.getText().toString());
            presenter.insertNote(note);
        } else {
            Log.i("Note", "Error, note already exist");
        }

    }

    @Override
    public void deleteNote() {
        //Obtenier l'idNote qui est sauvegardé et l'effacer

        if (noteAlreadyExist) {
            presenter.deleteNote();
        } else {
            Log.i("Note", "Error, note not exist in BDD");
        }
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
    public void update(boolean success) {
        hideProgressBar();

        if(success){

            Snackbar snackbar = Snackbar
                    .make(coordinator, "Les actualisations ont bien été enregistrés", Snackbar.LENGTH_LONG);
            snackbar.show();

        }else{
            Snackbar snackbar = Snackbar
                .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void insert(boolean success) {
        hideProgressBar();

        if(success){

            Snackbar snackbar = Snackbar
                    .make(coordinator, "Les actualisations ont bien été enregistrés", Snackbar.LENGTH_LONG);
            snackbar.show();

            noteAlreadyExist = true;
            supportInvalidateOptionsMenu();
        }else{
            Snackbar snackbar = Snackbar
                    .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void delete(boolean success) {
        hideProgressBar();

        if(success){

            finish();

        }else{
            Snackbar snackbar = Snackbar
                    .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
