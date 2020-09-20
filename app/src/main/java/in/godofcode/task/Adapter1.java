package in.godofcode.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    Movie movie;
    Context context;
    private static String ImagePath="https://image.tmdb.org/t/p/w500";
    public Adapter1(Context context,Movie movie) {
        this.movie=movie;
        this.context=context;
    }
    @NonNull
    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.trailer_card,viewGroup,false);
        return new Adapter1.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder viewHolder, int i) {
        if(i==0) {
            String temp=ImagePath+movie.getPoster_path();
            Picasso.get().load(temp).fit().centerInside().into(viewHolder.im1);
        }
        else if(i==1) {
            String temp=ImagePath+movie.getBackdrop_path();
            Picasso.get().load(temp).fit().centerInside().into(viewHolder.im1);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            im1=itemView.findViewById(R.id.image1);
        }
    }
}
