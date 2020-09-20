package in.godofcode.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Set;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Movie> movieList;
    private static String ImagePath="https://image.tmdb.org/t/p/w500";
    private Set<String> favs=null;
    public RecyclerViewAdapter(Context context,ArrayList<Movie> movieList) {
        this.context=context;
        this.movieList=movieList;
        favs=MainActivity.favorites.getStringSet("ids",null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.card,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie cMovie=movieList.get(i);
        viewHolder.name.setText(cMovie.getTitle());
        String temp=ImagePath+cMovie.getPoster_path();
        Picasso.get().load(temp).fit().centerInside().into(viewHolder.icon);
        viewHolder.popular.setText(Double.toString(cMovie.getPopularity()));
        viewHolder.index=i;
        viewHolder.id=cMovie.getId();
        if(favs==null)
            return;
        if(favs.contains(Integer.toString(cMovie.getId()))) {
            viewHolder.fav.setImageResource(R.drawable.star);
        }
        else {
            viewHolder.fav.setImageResource(R.drawable.nostar);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView icon;
        public TextView name;
        public TextView popular;
        public ImageView fav;
        public int index=-1;
        public int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon0);
            name=itemView.findViewById(R.id.name0);
            popular=itemView.findViewById(R.id.popular0);
            fav=itemView.findViewById(R.id.fav0);
            itemView.setOnClickListener(this);
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(id);
                    if(favs==null)
                        return;
                    if(favs.contains(Integer.toString(id))) {
                        favs.remove(Integer.toString(id));
                        fav.setImageResource(R.drawable.nostar);
                        SharedPreferences.Editor editor=MainActivity.favorites.edit();
                        editor.putStringSet("ids",favs);
                        editor.commit();
                    }
                    else {
                        favs.add(Integer.toString(id));
                        fav.setImageResource(R.drawable.star);
                        SharedPreferences.Editor editor=MainActivity.favorites.edit();
                        editor.putStringSet("ids",favs);
                        editor.commit();
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(index!=-1) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("index",index);
                context.startActivity(intent);
            }
        }
    }
}
