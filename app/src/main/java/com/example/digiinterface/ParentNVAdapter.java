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

public class ParentNVAdapter extends RecyclerView.Adapter<ParentNVAdapter.ViewHolder>{

    ArrayList<ChildNVmodel> items = new ArrayList<>();
    List<ParentNVmodel> parentNVmodels;
    Context context;

    public ParentNVAdapter(List<ParentNVmodel> parentNVmodels, Context context) {
        this.parentNVmodels = parentNVmodels;
        this.context = context;
    }
    @NonNull
    @Override
    public ParentNVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rec,null, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ParentNVAdapter.ViewHolder holder, int position) {
        holder.text.setText(parentNVmodels.get(position).title);
        ChildNVAdapter childNVAdapter;
        childNVAdapter= new ChildNVAdapter(parentNVmodels.get(position).childNVmodels,context);
        holder.child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.child.setAdapter(childNVAdapter);
        childNVAdapter.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return parentNVmodels.size();
    }

    public void updateData(List<ChildNVmodel> updatePg){
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
