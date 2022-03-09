package com.example.yousef;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateNewBook extends AppCompatActivity {



    Button btn_createBook;
    EditText et_bookName , et_bookAuthor , et_bookReleasYear, et_bookPages ;
    static Spinner sp_choos_category;
    String bookName, bookAuthor;
    int bookReleasYear, bookPages;
    String bookImagePath="";
    MyDatabase myDatabase ;
    int categId;
    int STORAGE_PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_book);


        btn_createBook = findViewById(R.id.btn_createBook);
        et_bookName = findViewById(R.id.et_book_name);
        et_bookAuthor = findViewById(R.id.et_author_name);
        et_bookReleasYear = findViewById(R.id.et_releas_year);
        et_bookPages = findViewById(R.id.et_page_number);
        sp_choos_category = findViewById(R.id.sp_choosCategory);




        myDatabase = new MyDatabase(this);


        ArrayList<String> allCategoriesName = myDatabase.getCategoriesName();

        ArrayAdapter<String> choosCategoryAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_dropdown_item,allCategoriesName);

        sp_choos_category.setAdapter(choosCategoryAdapter);

        sp_choos_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayList<Category> allCategory = myDatabase.getAllCategories();
                for (int y =0 ; y <allCategory.size();y++) {
                    if (y==i)
                        categId =y+1;
                        //cat = allCategory.get(y);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btn_createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(et_bookName.getText().toString())  &&
                        !TextUtils.isEmpty(et_bookAuthor.getText().toString())  &&
                        !TextUtils.isEmpty(et_bookReleasYear.getText().toString()) &&
                        !TextUtils.isEmpty(et_bookPages.getText().toString())) {
                            insertBook();
                        } else {

                    et_bookName.setError("can not be empty!");
                    et_bookAuthor.setError("can not be empty!");
                    et_bookPages.setError("can not be empty!");
                    et_bookReleasYear.setError("can not be empty!");
                    // Toast.makeText(getApplicationContext(), "You must fill in all fields above", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void insertBook() {

        bookName = et_bookName.getText().toString();
        bookAuthor = et_bookAuthor.getText().toString();
        bookReleasYear = Integer.parseInt(et_bookReleasYear.getText().toString());
        bookPages = Integer.parseInt(et_bookPages.getText().toString());

        long result = myDatabase.insertBook(bookName,bookAuthor,bookReleasYear,bookPages,categId);

        if (result != -1) {
            Toast.makeText(this, "Book is added successfully", Toast.LENGTH_LONG).show();
            et_bookName.getText().clear();
            et_bookAuthor.getText().clear();
            et_bookReleasYear.getText().clear();
            et_bookPages.getText().clear();
            onBackPressed();

        }else
            Toast.makeText(this, "Something error in DB, try again later", Toast.LENGTH_SHORT).show();

    }









}