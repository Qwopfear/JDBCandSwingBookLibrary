package com.uPanamarou.Controllers;

import com.uPanamarou.Service.BookModel;
import com.uPanamarou.Service.BookService;
import com.uPanamarou.UI.Library;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class AddToDBEventListener extends MouseAdapter {
    private JFrame targetFrame;
    private Library library;
    public AddToDBEventListener(JFrame frame,Library library){
        targetFrame = frame;
        this.library = library;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        boolean bookAdded = false;
        JTextField genre = (JTextField) targetFrame.
                        getContentPane().
                        getComponents()[0].
                        getComponentAt(200,200).getComponentAt(100,10);
        JTextField title = (JTextField) targetFrame.
                getContentPane().
                getComponents()[0].
                getComponentAt(200,10).getComponentAt(100,10);
        JTextField size = (JTextField) targetFrame.
                getContentPane().
                getComponents()[0].
                getComponentAt(200,145).getComponentAt(100,10);
        JTextField author = (JTextField) targetFrame.
                getContentPane().
                getComponents()[0].
                getComponentAt(200,85).getComponentAt(100,10);
        String [] book = new String[4];
        book[0] = title.getText();
        book[1] = author.getText();
        book[2] = size.getText();
        book[3] = genre.getText();

        BookService bookService = new BookService();
        try {
            if (library.getCurrentOperation() == 0)
            bookAdded =  bookService.addBookToDatabase(book);
            else if (library.getCurrentOperation() == 1)
              bookAdded = bookService.updateBook(book, Integer.parseInt(BookModel.getBookId()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (bookAdded){
            System.out.println("book successfully added to database");
            targetFrame.dispose();
            library.readDB();
        }



    }
}
