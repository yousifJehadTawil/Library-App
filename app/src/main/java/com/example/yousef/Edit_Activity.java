package com.example.yousef;
import static com.example.yousef.CreateNewBook.sp_choos_category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;


public class Edit_Activity extends AppCompatActivity {
    private TextView bookNameET_Edit;
    private TextView authorNameET_Edit;
    private TextView releaseYearET_Edit;
    private TextView pageNumberET_Edit;
    private TextView categoryIdNumberEt_Edit;

    byte[] image_Edit;
    private Button UpdateBTN_Edit;
  ;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MyDatabase database = new MyDatabase(this);
        Book bookDB = new Book();

        bookNameET_Edit = findViewById(R.id.bookNameET_Edit);
        authorNameET_Edit = findViewById(R.id.authorNameET_Edit);
        releaseYearET_Edit = findViewById(R.id.releaseYearET_Edit);
        pageNumberET_Edit = findViewById(R.id.pageNumberEt_Edit);
        UpdateBTN_Edit = findViewById(R.id.UpdateBTN);
        categoryIdNumberEt_Edit = findViewById(R.id.categoryIdNumberEt_Edit);



        Intent intent = getIntent();
        Book bookdata = (Book) intent.getSerializableExtra("BookUpdate");
        int bookid = bookdata.getbId();
        categoryIdNumberEt_Edit.setText(bookdata.getCategoryId_FK() + "");
        bookNameET_Edit.setText(bookdata.getBookName());
        authorNameET_Edit.setText(bookdata.getAuthorName());
        releaseYearET_Edit.setText(bookdata.getBookReleaseYear()+"");
        pageNumberET_Edit.setText(bookdata.getBookPagesNum()+"");



        getSupportActionBar().setTitle(bookdata.getBookName());


        UpdateBTN_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookCategoryId = categoryIdNumberEt_Edit.getText().toString().trim();
                String bookname = bookNameET_Edit.getText().toString().trim();
                String author = authorNameET_Edit.getText().toString().trim();
                String date = releaseYearET_Edit.getText().toString().trim();
                String page = pageNumberET_Edit.getText().toString().trim();


                Book bookData = new Book();
//                sp_choos_category.setSelection((int) bookData.getCategoryId_FK());
                bookData.setbId(bookid);
                bookData.setBookName(bookname);
                bookData.setAuthorName(author);
                bookData.setBookReleaseYear(Integer.parseInt(date));
                bookData.setBookPagesNum(Integer.parseInt(page));
                bookData.setCategoryId_FK(Integer.parseInt(bookCategoryId));

                database.updateBook(bookData);

                Intent intent1 = new Intent(Edit_Activity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }





}