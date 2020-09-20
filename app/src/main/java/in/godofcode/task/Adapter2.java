package in.godofcode.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    Movie movie;
    Context context;
    public int id;
    private Set<String> favs=null;


    public Adapter2(Context context, Movie movie) {
        this.movie = movie;
        this.context = context;
        favs=MainActivity.favorites.getStringSet("ids",null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.details,viewGroup,false);
        return new Adapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        id=movie.getId();
        viewHolder.t0.setText(movie.getOriginal_title());
        viewHolder.t1.setText(movie.getTitle());
        viewHolder.t2.setText(movie.getVote_average()+"");
        viewHolder.t3.setText(movie.isAdult()+"");
        viewHolder.t4.setText(movie.getOriginal_language());
        viewHolder.t5.setText(movie.getOverview());
        String temp="";
        for(Integer t:movie.getGenre_ids())
            temp+=t+" | ";
        viewHolder.t6.setText(temp);
        viewHolder.t7.setText(movie.getRelease_date());
        viewHolder.t9.setText(movie.getPopularity()+"");
        try {
            viewHolder.tv.setText(movie.getVote_count()+"");
            if(favs==null)
                return;
            if(favs.contains(Integer.toString(movie.getId()))) {
                viewHolder.fi.setImageResource(R.drawable.star);
            }
            else {
                viewHolder.fi.setImageResource(R.drawable.nostar);
            }
        }
        catch (Exception ex) {
            System.out.println(viewHolder.tv.getText());
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t0,t1,t2,t3,t4,t5,t6,t7,t9;
        public TextView tv;
        public ImageView fi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t0=itemView.findViewById(R.id.original);
            t1=itemView.findViewById(R.id.tit);
            t2=itemView.findViewById(R.id.votav);
            t3=itemView.findViewById(R.id.adul);
            t4=itemView.findViewById(R.id.lang);
            t5=itemView.findViewById(R.id.over);
            t6=itemView.findViewById(R.id.genr);
            t7=itemView.findViewById(R.id.relda);
            tv=itemView.findViewById(R.id.teV);
            t9=itemView.findViewById(R.id.pop);
            fi=itemView.findViewById(R.id.star);
            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(id);
                    if(favs==null)
                        favs=new HashSet<String>();
                    if(favs.contains(Integer.toString(id))) {
                        favs.remove(Integer.toString(id));
                        fi.setImageResource(R.drawable.nostar);
                        SharedPreferences.Editor editor=MainActivity.favorites.edit();
                        editor.putStringSet("ids",favs);
                        editor.apply();
                        editor.commit();
                    }
                    else {
                        favs.add(Integer.toString(id));
                        fi.setImageResource(R.drawable.star);
                        SharedPreferences.Editor editor=MainActivity.favorites.edit();
                        editor.putStringSet("ids",favs);
                        editor.apply();
                        editor.commit();
                    }
                }
            });
        }
    }
}
