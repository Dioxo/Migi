package dioxo.migi.ListerTaches;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TachesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TachesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TachesFragment extends Fragment implements TachesView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TachesPresenter presenter;
    private ImageView imageView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public boolean ResearchByTaches = true;
    public TachesFragment() {
        // Required empty public constructor

        presenter = new TachesPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("TACHES" , "VALEUR DE BOOLEAN  onResume" + ResearchByTaches );

        if(ResearchByTaches){
            Log.i("TACHES" , "BUSCAR NOTAS");
            chercherNotes();
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TachesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TachesFragment newInstance(String param1, String param2) {
        TachesFragment fragment = new TachesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taches, container, false);

        imageView = (ImageView)view.findViewById(R.id.imageVide);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerNotes);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void chercherNotes() {
        Log.i("Notes", "chercherNotes");
        presenter.chercherNotes();
    }

    @Override
    public void afficherNotes(ArrayList<Note> notes) {
        if(notes.size() > 0){
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            //ArrayList<CardNote> notes = getTags();
            mAdapter = new MyAdapter(notes);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            afficherBackgroundVide();
        }
    }

    @Override
    public void afficherBackgroundVide() {
        imageView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void chercherNotesSelonTag(String tag){
        Log.i("TACHES", " DENTRO DE TACHES CON TAG "+ tag);
        //ResearchByTaches = true;
        //presenter = new TachesPresenterImpl(this);
        Log.i("TACHES" , "VALEUR DE BOOLEAN method" + ResearchByTaches );
        presenter.chercherNotes(tag);
    }

    public void chercherNotesSelonRevision(){
        presenter.chercherNotesRevision();
    }
}
