package com.example.yousef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    RecyclerView RvCategories;
    //ArrayList<Category> categories  = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        RvCategories = findViewById(R.id.list_categories);

        MyDatabase myDatabase = new MyDatabase(this);
        ArrayList<Category> categories = myDatabase.getAllCategories();

        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categories,this);
        RecyclerView.LayoutManager llm = new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);
        RvCategories.setLayoutManager(llm);
        RvCategories.setAdapter(adapter);



    }
}