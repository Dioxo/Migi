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

public class ReviserDialog extends AppCompatDialogFragment {

    private EditText note_revision;

    private ReviserDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_reviser,null);

        builder.setView(view)
                .setTitle("Note de la revision")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = note_revision.getText().toString();
                        listener.reviserNote(tag);
                    }
                });
        note_revision = (EditText)view.findViewById(R.id.note_revision);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ReviserDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + " Il faut implementer TagDialogListener");
        }
    }

    public interface ReviserDialogListener{
        void reviserNote(String qualification);
    }
}
