package com.example.yousef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    RecyclerView rv_books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        rv_books = findViewById(R.id.list);

        Bundle extras = getIntent().getExtras();
        int category_id = extras.getInt("CategoryId",0);

        Log.d("Tag","Category id: "+category_id);
        MyDatabase myDatabase = new MyDatabase(this);
        ArrayList<Book> book = myDatabase.getAllBooks(category_id);

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(book,this);
        RecyclerView.LayoutManager llm = new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);
        rv_books.setLayoutManager(llm);
        rv_books.setAdapter(adapter);


    }
}