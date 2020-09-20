package in.godofcode.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class DetailActivity extends Activity {
    int index=-1;
    Movie movie=null;
    RecyclerView recyclerView1,recyclerView2;
    Adapter1 adapter1;
    Adapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=this.getIntent();
        index=intent.getIntExtra("index",-1);
        if(index>=0) {
            movie=MainActivity.movies.get(index);
        }
        else {
            Toast.makeText(this,"Something is wrong I can feel it"+index,Toast.LENGTH_SHORT).show();
            return;
        }
        recyclerView1=(RecyclerView)findViewById(R.id.recycle1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter1=new Adapter1(this,movie);
        recyclerView1.setAdapter(adapter1);
        recyclerView2=(RecyclerView)findViewById(R.id.recycle2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter2=new Adapter2(this,movie);
        recyclerView2.setAdapter(adapter2);
    }
}
