package dioxo.migi.NavigationDrawer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dioxo.migi.Authentication.AuthenticationActivity;
import dioxo.migi.ListerTaches.TachesFragment;
import dioxo.migi.NavigationDrawer.InterfaceCommunicationWFragments.OnButtonPressListener;
import dioxo.migi.Note.NoteActivity;
import dioxo.migi.Objets.Objs.CardNote;
import dioxo.migi.Objets.Objs.ListTag;
import dioxo.migi.Objets.Objs.Node;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.Objets.Objs.Tag;
import dioxo.migi.R;
import dioxo.migi.listeTags.TagsFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TagsFragment.OnFragmentInteractionListener,
        TachesFragment.OnFragmentInteractionListener,
        OnButtonPressListener,
        ViewNavigation{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private NavigationPresenter presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), NoteActivity.class);
                intent.putExtra("Note",(Serializable) null);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Creation presenter
        presenter = new NavigationPresenterImpl(this);
        presenter.onCreate();
        ouvrirFragmentTachesParDefault();
    }

    private void ouvrirFragmentTachesParDefault() {
        Fragment fragment = new TachesFragment();
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, fragment)
                .commit();

        getSupportActionBar().setTitle("Liste de Taches");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deconnexion) {
            presenter.closeSession();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean transition = false;
        Fragment fragment = null;

        Log.i("Fragment", "ENTRE");

        if (id == R.id.nav_tags) {
            Log.i("Fragment", "CLICKED");
            transition = true;
            fragment = new TagsFragment();
            // Handle the camera action
        } else if (id == R.id.nav_liste_taches) {
            transition = true;
            fragment = new TachesFragment();
        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        if(transition){

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, fragment)
                    .commit();

            item.setChecked(true);

            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(getApplicationContext(), AuthenticationActivity.class));
    }

    @Override
    public void onButtonPressed(String msg) {

        TachesFragment fragment = new TachesFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, fragment)
                .commit();
        fragment.chercherNotesSelonTag(msg);

        getSupportActionBar().setTitle(msg);

    }
}
