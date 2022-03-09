package com.example.yousef;

import java.io.Serializable;

public class Book implements Serializable {
    private int bId;
    private String bookName;
    private String authorName;
    private int bookReleaseYear;
    private int bookPagesNum;
    private long categoryId_FK;
    public Book(){

    }

    public Book(int bId, String bookName, String authorName, int bookReleaseYear, int bookPagesNum, long categoryId_FK) {
        this.bId = bId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookReleaseYear = bookReleaseYear;
        this.bookPagesNum = bookPagesNum;
        this.categoryId_FK = categoryId_FK;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getBookReleaseYear() {
        return bookReleaseYear;
    }

    public void setBookReleaseYear(int bookReleaseYear) {
        this.bookReleaseYear = bookReleaseYear;
    }

    public int getBookPagesNum() {
        return bookPagesNum;
    }

    public void setBookPagesNum(int bookPagesNum) {
        this.bookPagesNum = bookPagesNum;
    }

    public long getCategoryId_FK() {
        return categoryId_FK;
    }

    public void setCategoryId_FK(long categoryId_FK) {
        this.categoryId_FK = categoryId_FK;
    }
}