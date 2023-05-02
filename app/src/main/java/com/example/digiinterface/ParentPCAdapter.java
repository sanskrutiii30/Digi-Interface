package com.example.digiinterface;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParentPCAdapter extends RecyclerView.Adapter<ParentPCAdapter.ViewHolder>{

    ArrayList<ChildPCmodel> items = new ArrayList<>();
    List<ParentPCmodel> parentNewVideomodels;
    Context context;

    public ParentPCAdapter(List<ParentPCmodel> parentNewVideomodels, Context context) {
        this.parentNewVideomodels = parentNewVideomodels;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentPCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rec,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentPCAdapter.ViewHolder holder, int position) {
        holder.text.setText(parentNewVideomodels.get(position).title);
        ChildPCAdapter childNewVideoAdapter;
        childNewVideoAdapter= new ChildPCAdapter(parentNewVideomodels.get(position).childNewVideomodels,context);
        holder.child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.child.setAdapter(childNewVideoAdapter);
        childNewVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentNewVideomodels.size();
    }

    public void updateData(List<ChildPCmodel> updatePg){
        items.clear();
        items.addAll(updatePg);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView child;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            child= itemView.findViewById(R.id.child);
            text= itemView.findViewById(R.id.title);
        }
    }
}
