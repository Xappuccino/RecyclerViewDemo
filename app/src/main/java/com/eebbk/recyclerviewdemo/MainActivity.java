package com.eebbk.recyclerviewdemo;

import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerAdapter myRecyclerAdapter;
    private RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecyView);
//        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        itemDecoration = new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL);
//        layoutManager = new GridLayoutManager(this,2);
//        layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        myRecyclerAdapter = new MyRecyclerAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.addItemDecoration(itemDecoration);
    }

}
