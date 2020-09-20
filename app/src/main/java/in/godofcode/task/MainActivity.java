package in.godofcode.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private static final String KEY="4e44d9029b1270a757cddc766a1bcb63";
    private static final String BASE_URL="https://api.themoviedb.org/3/";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    public static ArrayList<Movie> movies;
    public static SharedPreferences favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        DBApi dbApi=retrofit.create(DBApi.class);
        Call<DBResponse> call=dbApi.getMovies(KEY);
        recyclerView=(RecyclerView)findViewById(R.id.recycle0);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favorites=getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=favorites.edit();
        //editor.putStringSet("ids",new )
        call.enqueue(new Callback<DBResponse>() {
            @Override
            public void onResponse(Call<DBResponse> call, Response<DBResponse> response) {
                if(response.isSuccessful()) {
                    DBResponse dbResponse=response.body();
                    assert dbResponse!=null;
                    movies=dbResponse.getResults();
                    recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,movies);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
                else {
                    Toast.makeText(MainActivity.this,"Something is wrong I can feel it"+response.code(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DBResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
