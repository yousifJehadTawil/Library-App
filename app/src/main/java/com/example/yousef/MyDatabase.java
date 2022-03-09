package com.example.yousef;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    public static final String db_name = "library_db";
    public static final int db_version = 5;
    public static final String category_tb_name = "categories";
    public static final String tb_col_categoryId = "categoryId";
    public static final String tb_col_categoryName = "categoryName";
    public static final String book_tb_name = "books";
    public static final String tb_col_bookId = "bookId";
    public static final String tb_col_bookName = "bookName";
    public static final String tb_col_bookAuthor = "bookAouther";
    public static final String tb_col_bookReYear = "bookReleaseYear";
    public static final String tb_col_bookPages = "bookPages";
    public static final String book_tb_col_categoryId = "categoryId_FK";


    public MyDatabase(Context context) {
        super(context, db_name, null, db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + category_tb_name + " (" +
                tb_col_categoryId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tb_col_categoryName + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + book_tb_name + " (" +
                tb_col_bookId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tb_col_bookName + " TEXT NOT NULL, " +
                tb_col_bookAuthor + " TEXT NOT NULL, " +
                tb_col_bookReYear + " INTEGER NOT NULL, " +
                tb_col_bookPages + " INTEGER NOT NULL, " +
                book_tb_col_categoryId + " INTEGER, " +
                " FOREIGN KEY  ( " + book_tb_col_categoryId + " ) REFERENCES " + category_tb_name + " (" + tb_col_categoryId + " ) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + category_tb_name);
        db.execSQL("DROP TABLE IF EXISTS " + book_tb_name);
        onCreate(db);
    }

    public long insertCategory(String categoryName) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(tb_col_categoryName, categoryName);
        return db.insert(category_tb_name, null, cv);
    }

    public long insertBook(String bookName, String bookAuthor, int bookYear, int bookPages, int categoryid_fk) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(tb_col_bookName, bookName);
        cv.put(tb_col_bookAuthor, bookAuthor);
        cv.put(tb_col_bookReYear, bookYear);
        cv.put(tb_col_bookPages, bookPages);
        cv.put(book_tb_col_categoryId, categoryid_fk);
        return db.insert(book_tb_name, null, cv);
    }

    public ArrayList<Category> getAllCategories() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Category> categories = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + category_tb_name, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int categoryId = cursor.getInt(0);
                String categoryName = cursor.getString(1);
                Category category = new Category(categoryId, categoryName);
                categories.add(category);
            } while (cursor.moveToNext());
        }
        return categories;
    }


    public ArrayList<String> getCategoriesName() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> categories = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + category_tb_name + " ", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String categoryName = cursor.getString(1);
                categories.add(categoryName);

            } while (cursor.moveToNext());
        }
        return categories;
    }

    public ArrayList<Book> getAllBooks(long categId) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Book> books = new ArrayList<>();

        String[] catId = {String.valueOf(categId)};
        Cursor cursor = db.rawQuery("SELECT * FROM " + book_tb_name + " WHERE " + book_tb_col_categoryId + " = ?", catId);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int bookId = cursor.getInt(0);
                String bookImagePath = cursor.getString(1);
                String bookName = cursor.getString(2);
                String bookAuthor = cursor.getString(3);
                int bookYear = cursor.getInt(4);
                int bookPages = cursor.getInt(5);
                int categoId_fk = cursor.getInt(6);

                Book book = new Book(bookId, bookName, bookAuthor, bookYear, bookPages, categoId_fk);
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }


    public Long updateBook(Book bookData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateData = new ContentValues();

        updateData.put(tb_col_bookName, bookData.getBookName());
        updateData.put(tb_col_bookAuthor, bookData.getAuthorName());
        updateData.put(tb_col_bookReYear, bookData.getBookReleaseYear());
        updateData.put(tb_col_bookPages, bookData.getBookPagesNum());
        updateData.put(tb_col_categoryId, bookData.getCategoryId_FK());
        String[] args = {bookData.getbId() + ""};
        long result = db.update(tb_col_bookId, updateData, tb_col_bookId + "=?", args);
        return result;
    }


    public int deletebook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {id + ""};
        int result = db.delete(book_tb_name, tb_col_bookId + "=?", args);
        return result;
    }

}
