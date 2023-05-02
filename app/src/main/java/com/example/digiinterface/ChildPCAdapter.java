package com.example.digiinterface;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ChildPCAdapter extends RecyclerView.Adapter<ChildPCAdapter.ViewHolder>{

    List<ChildPCmodel> childNewVideomodelList;
    Context context;

    public ChildPCAdapter(List<ChildPCmodel> childNewVideomodelList, Context context) {
        this.childNewVideomodelList = childNewVideomodelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildPCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rec,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildPCAdapter.ViewHolder holder, int position) {
//        String imageUrl = "https://lollypopy.in/iplustv_app/uploads/program_image/category/2016de91f96d996426e6475794963df9.jpeg";


        ChildPCmodel current =childNewVideomodelList.get(position);
        Glide.with(context)
                .load(current.getImage())
                .placeholder(R.drawable.img) // optional placeholder image
                .into(holder.iv_child_item);
//        holder.iv_child_item.setImageResource(childmodelslist.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return childNewVideomodelList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_child_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_item=itemView.findViewById(R.id.iv_child_item);
        }
    }
}
