package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/4/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    List<Bean>list;
    LayoutInflater inflater;
    public MyAdapter(Context context, List<Bean> list){
        this.context=context;
        this.list=list;
       // inflater=context.
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getTitle());
        Glide.with(context).load("").placeholder(R.drawable.ic_empty).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.textView);
            iv=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
