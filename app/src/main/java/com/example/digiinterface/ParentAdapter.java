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

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder>{

    ArrayList<Childmodel> items = new ArrayList<>();
    List<Parentmodel> parentmodels;
    Context context;

    public ParentAdapter(List<Parentmodel> parentmodels, Context context) {
        this.parentmodels = parentmodels;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rec,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.ViewHolder holder, int position) {
        holder.text.setText(parentmodels.get(position).title);
        ChildAdapter childAdapter;
        childAdapter= new ChildAdapter(parentmodels.get(position).childmodels,context);
        holder.child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.child.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentmodels.size();
    }

    public void updateData(List<Childmodel> updatePg){
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
