package com.example.yousef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateNewCategory extends AppCompatActivity {

    EditText et_create_category;
    Button btn_create_category;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_category);

        et_create_category = findViewById(R.id.et_createCategory);
        btn_create_category = findViewById(R.id.btn_createCategory);


        btn_create_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryName = et_create_category.getText().toString();
                if (categoryName.isEmpty()) {
                    et_create_category.setError("Can not be empty");
                } else {
                    insertCategory();
                }
            }
        });
    }

    private void insertCategory() {

        MyDatabase myDatabase = new MyDatabase(this);

        long result = myDatabase.insertCategory(categoryName);
        if (result != -1) {
            Toast.makeText(this, "Category is added successfully", Toast.LENGTH_LONG).show();
            et_create_category.getText().clear();
            onBackPressed();

        }else
            Toast.makeText(this, "Something error in DB, try again later", Toast.LENGTH_SHORT).show();

    }

   /* private void showCategories(){
        Intent intent = new Intent(this,CategoriesActivity.class);
        startActivity(intent);
    }*/
}