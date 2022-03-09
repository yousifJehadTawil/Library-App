package com.example.yousef;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookViewHolder> {

    ArrayList<Book> books;
    Activity activity;

    public BookRecyclerViewAdapter(ArrayList<Book> books, Activity activity) {
        this.books = books;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.book_item,parent,false);

        BookViewHolder holder = new BookViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book  book = books.get(position);

            Toast.makeText(activity, "book path not found", Toast.LENGTH_LONG).show();
        holder.bookName.setText(book.getBookName());
        holder.bookYear.setText(book.getBookReleaseYear()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,DisplayBookActivity.class);
                intent.putExtra("book",book);
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder{
        ImageView iv ;
        TextView bookName , bookYear;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.tv_rv_book_name);
            bookYear = itemView.findViewById(R.id.tv_rv_book_year);
        }
    }
}
