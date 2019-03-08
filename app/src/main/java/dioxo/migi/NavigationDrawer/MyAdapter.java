package dioxo.migi.NavigationDrawer;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import dioxo.migi.Note.NoteActivity;
import dioxo.migi.Objets.Objs.CardNote;
import dioxo.migi.Objets.Objs.Note;
import dioxo.migi.R;
import dioxo.migi.libs.ApplicationContextProvider;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Note> notes;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView title,description,tag1,tag2,tag3;
        public Button archiver,reviser;
        public LinearLayout tagsContenier, tags;

        public MyViewHolder(View v) {
            super(v);
            cardView =  v.findViewById(R.id.my_card);
            title =  v.findViewById(R.id.txtTitle);
            description =  v.findViewById(R.id.txtDescription);
            //tag1 =  v.findViewById(R.id.txt_Tag1);
            //tag2 =  v.findViewById(R.id.txt_Tag2);
            //tag3 = v.findViewById(R.id.txt_Tag3);
            tagsContenier = v.findViewById(R.id.tags_contenier);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View cardView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);

        MyViewHolder vh = new MyViewHolder(cardView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        /*
        *         public CardView cardView;
        public TextView title,description,tag1,tag2,tag3;
        public Button archiver,reviser;
        * */
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());

        //COdigo para implementar los tags, por ahora sin implementación
        if(notes.get(position).getTags() != null) {

            for(int i = 0; i < notes.get(position).getTags().size(); i++)
            {
                holder.tags = (LinearLayout)LayoutInflater.from(ApplicationContextProvider.getContext()).inflate(R.layout.tags_contenier,null);
                TextView textTag = holder.tagsContenier.findViewById(R.id.txt_Tag);
                textTag.setText("LOL");

                holder.tagsContenier.addView(holder.tags);
            }


            System.out.println("---------------------TAGS -----------------------");
            System.out.println(notes.get(position).getTags().get(0).getTextTag());
            System.out.println(notes.get(position).getTags().get(1).getTextTag());
            System.out.println(notes.get(position).getTags().get(2).getTextTag());
            //holder.tag1.setText(notes.get(position).getTags().get(0).getTextTag());
            //holder.tag2.setText(notes.get(position).getTags().get(1).getTextTag());
            //holder.tag3.setText(notes.get(position).getTags().get(2).getTextTag());
        }else{
            //On n'a pas de Tag
            holder.tagsContenier.setVisibility(View.GONE);
        }

        /* holder.archiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Note","Archiver");
            }
        });


        holder.reviser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Note","Reviser");
            }
        });*/

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("Click", notes.get(position).getTitle());

                //If TITLE CLICKED
                /*
                * Send the note to next activity
                * */
                Intent intent = new Intent(ApplicationContextProvider.getContext(), NoteActivity.class);
                intent.putExtra("Note", notes.get(position));
                ApplicationContextProvider.getContext().startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notes.size();
    }
}