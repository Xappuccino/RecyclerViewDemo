package com.eebbk.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Integer> mData;
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    public MyRecyclerAdapter() {
        mData = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 2) {
                continue;
            }
            mData.add(i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType==TYPE_ONE){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_one,parent,false);
            viewHolder = new FirstViewHolder(v);
        }
        else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_two,parent,false);
            viewHolder = new FirstViewHolder(v);
        }
        Log.d("RECYCLE", "onCreate");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FirstViewHolder){
            ((FirstViewHolder) holder).textView.setText(mData.get(position).toString());
        }
        else if(holder instanceof SecondViewHolder){
            ((SecondViewHolder) holder).textView1.setText(mData.get(position).toString());
            ((SecondViewHolder) holder).textView2.setText((mData.get(position)+1)+"");
        }
        Log.d("RECYCLE", "onBind");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class FirstViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public FirstViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_one_tv);
        }
    }
    class SecondViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;

        public SecondViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.item_two_tv1);
            textView2 = itemView.findViewById(R.id.item_two_tv2);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(mData.get(position)%3==0){
            return TYPE_ONE;
        }
        else{
            return TYPE_TWO;
        }
    }
}
