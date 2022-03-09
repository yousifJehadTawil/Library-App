package com.example.yousef;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView btn_creatCategory, btn_library , btn_createBook;
    int STORAGE_PERMISSION_REQUEST_CODE=1;
    int IMAGE_BOOK_REQUEST_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         btn_creatCategory = findViewById(R.id.btn_creatCategory);
         btn_library = findViewById(R.id.btn_library);
         btn_createBook = findViewById(R.id.brn_createBook);











        btn_creatCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateNewCategory.class);
                startActivity(intent);
            }
        });


        btn_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CategoriesActivity.class);
                startActivity(intent);
            }
        });


        btn_createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateNewBook.class);
                startActivity(intent);
            }
        });
    }
}