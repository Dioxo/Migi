package dioxo.migi.Note;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dioxo.migi.Constantes;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.Objets.Objs.TagDialog;
import dioxo.migi.Objets.Objs.TagDialog.TagDialogListener;
import dioxo.migi.R;
import dioxo.migi.libs.ApplicationContextProvider;

public class NoteActivity extends AppCompatActivity implements NoteView, TagDialogListener {

    @BindView(R.id.txtTitle)
    EditText txtTitle;
    @BindView(R.id.txtDescription)
    EditText txtDescription;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.tags_contenier)
    LinearLayout tagsContenier;
    @BindView(R.id.fab_conver_tache)
    FloatingActionButton fabConverTache;
    @BindView(R.id.fab_reviser)
    FloatingActionButton fabReviser;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.ajouter_tag)
    FloatingActionButton ajouterTag;

    private NotePresenter presenter;
    private boolean noteAlreadyExist = false;
    private Animation fabOpen, fabClose, fabRotateClockwise, fabRotateAntiClockwise;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabRotateClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabRotateAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anti_clockwise);

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

        if (note.getTags() != null) {

            for (int i = 0; i < note.getTags().size(); i++) {
                LinearLayout tags_corp = (LinearLayout) LayoutInflater.from(ApplicationContextProvider.getContext()).inflate(R.layout.tags_contenier, null);
                //holder.tags = (LinearLayout)LayoutInflater.from(ApplicationContextProvider.getContext()).inflate(R.layout.tags_contenier,null);
                TextView textTag = tags_corp.findViewById(R.id.txt_Tag);
                textTag.setText(note.getTags().get(i).getTextTag());

                tagsContenier.addView(tags_corp);
            }

        } else {
            //On n'a pas de Tags
            tagsContenier.setVisibility(View.GONE);
        }

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
    public void enableInputs() {
        txtTitle.setEnabled(true);
        txtDescription.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        txtTitle.setEnabled(false);
        txtDescription.setEnabled(false);
    }

    @Override
    public void update(boolean success) {
        hideProgressBar();
        enableInputs();

        if (success) {

            Snackbar snackbar = Snackbar
                    .make(coordinator, "Les actualisations ont bien été enregistrés", Snackbar.LENGTH_LONG);
            snackbar.show();

        } else {
            Snackbar snackbar = Snackbar
                    .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void insert(boolean success) {
        hideProgressBar();
        enableInputs();

        if (success) {

            Snackbar snackbar = Snackbar
                    .make(coordinator, "Les actualisations ont bien été enregistrés", Snackbar.LENGTH_LONG);
            snackbar.show();

            noteAlreadyExist = true;
            supportInvalidateOptionsMenu();
        } else {
            Snackbar snackbar = Snackbar
                    .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void delete(boolean success) {
        hideProgressBar();
        enableInputs();
        if (success) {

            finish();

        } else {
            Snackbar snackbar = Snackbar
                    .make(coordinator, "Erreur: impossible d'enregistrer les modifications", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @OnClick(R.id.fab_conver_tache)
    public void onFabConverTacheClicked() {
    }

    @OnClick(R.id.fab_reviser)
    public void onFabReviserClicked() {
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {

        if (isOpen) {

            fabReviser.startAnimation(fabClose);
            fabConverTache.startAnimation(fabClose);
            ajouterTag.startAnimation(fabClose);
            fab.startAnimation(fabRotateAntiClockwise);

            fabReviser.setClickable(false);
            fabConverTache.setClickable(false);
            ajouterTag.setClickable(false);
            isOpen = false;

        } else {
            fabReviser.startAnimation(fabOpen);
            fabConverTache.startAnimation(fabOpen);
            ajouterTag.startAnimation(fabOpen);
            fab.startAnimation(fabRotateClockwise);

            fabReviser.setClickable(true);
            fabConverTache.setClickable(true);
            ajouterTag.setClickable(true);
            isOpen = true;
        }

    }

    @OnClick(R.id.ajouter_tag)
    public void onViewClicked() {
        TagDialog tagDialog = new TagDialog();
        tagDialog.show(getSupportFragmentManager(), "Tag Dialog");
    }

    @Override
    public void applyText(String tagNom) {
        if(noteAlreadyExist) {
            presenter.ajouterTag(tagNom);
        }
    }
}
