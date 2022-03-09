package com.example.yousef;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<
        CategoryRecyclerViewAdapter.CategoryViewHolder> {

    ArrayList<Category> categories;
    Activity activity;
    Category currentCategory;
    int currPosition;


    public CategoryRecyclerViewAdapter(ArrayList<Category> categories, Activity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(activity)
                .inflate(R.layout.category_item,parent,false);
        CategoryViewHolder cvh = new CategoryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final int newPosition = position;
        currentCategory = categories.get(position);
        holder.categName.setText(currentCategory.getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currPosition = newPosition+1;
                Intent intent = new Intent(activity,BooksActivity.class);
                intent.putExtra("CategoryId",currPosition);
                activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categName= itemView.findViewById(R.id.tv_category);
        }
    }
}
