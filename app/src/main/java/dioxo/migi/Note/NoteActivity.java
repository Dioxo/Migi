package dioxo.migi.Note;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.R;
import dioxo.migi.libs.ApplicationContextProvider;

public class NoteActivity extends AppCompatActivity implements NoteView{

    @BindView(R.id.txtTitle)
    EditText txtTitle;
    @BindView(R.id.txtDescription)
    EditText txtDescription;

    private NotePresenter presenter;
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

        //Confirmer si existe une note qui est passé comme argument
        if(getIntent().getExtras().getSerializable("Note") != null){
            presenter.chechIfNoteClicked(getIntent().getExtras().getSerializable("Note"));
        }else {
            presenter.chechIfNoteClicked(null);
        }
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
        SharedPreferences settings =getApplicationContext().getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
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
        Note note = new Note();
        note.setTitle(txtTitle.getText().toString());
        note.setDescription(txtDescription.getText().toString());
        presenter.updateNote(note);
    }


    @Override
    public void insertNote() {
        Note note = new Note();
        note.setTitle(txtTitle.getText().toString());
        note.setDescription(txtDescription.getText().toString());
        presenter.insertNote(note);
    }

    @Override
    public void deleteNote() {
        //Obtenier l'idNote qui est sauvegardé et l'effacer
        SharedPreferences settings = getSharedPreferences(Constantes.NOTE_ACTUAL, 0);
        String idNote = settings.getString(Constantes.NOTE_ACTUAL,null);
        presenter.deleteNote(idNote);
    }
}
