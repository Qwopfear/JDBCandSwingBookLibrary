package com.uPanamarou.Service;

public class BookModel {

    private static String[] book;


    public static String[] getBook() {
        return book;
    }

    public static void setBook(String[] book) {
        BookModel.book = book;
    }

    public static String getBookId(){
        return book[0];
    }
}
