package com.example.yousef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayBookActivity extends AppCompatActivity {
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_book);

        TextView NameDisplay = findViewById(R.id.NameDisplay);
        TextView AuthorDisplay = findViewById(R.id.AuthorDisplay);
        TextView YearDisplay = findViewById(R.id.YearDisplay);
        TextView PageDisplay = findViewById(R.id.PageDisplay);
        TextView CategoryDisplay = findViewById(R.id.CategoryDisplay);
        Button editBTN = findViewById(R.id.editBTN);
        Button deleteBTN = findViewById(R.id.deleteBTN);


        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("book");


        NameDisplay.setText(book.getBookName());
        AuthorDisplay.setText(book.getAuthorName());
        YearDisplay.setText(String.valueOf(book.getBookReleaseYear()));
        PageDisplay.setText(String.valueOf(book.getBookReleaseYear()));
        //CategoryDisplay.setText((int) book.getCategoryId_FK());

        editBTN.getContext();
        deleteBTN.getContext();

        editBTN.setOnClickListener(view -> {
            Intent editIntent = new Intent(DisplayBookActivity.this, Edit_Activity.class);
            editIntent.putExtra("BookUpdate",book);
            startActivity(editIntent);
        });
        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase bookDB = new MyDatabase(DisplayBookActivity.this);
                bookDB.deletebook(book.getbId());
                Intent intent1 = new Intent(DisplayBookActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }


}

