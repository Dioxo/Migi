package dioxo.migi.Objets.Objs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import dioxo.migi.R;

public class TagDialog extends AppCompatDialogFragment {

    private EditText tagNom;

    private TagDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layour_ajouter_tag,null);

        builder.setView(view)
                .setTitle("Nom du tag")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = tagNom.getText().toString();
                        listener.ajouterTag(tag);
                    }
                });
        tagNom = (EditText)view.findViewById(R.id.edit_tag);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (TagDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + " Il faut implementer TagDialogListener");
        }
    }

    public interface TagDialogListener{
        void ajouterTag(String tagNom);
    }
}
