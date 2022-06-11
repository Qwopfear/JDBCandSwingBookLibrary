package com.uPanamarou.Controllers;

import com.uPanamarou.Service.BookModel;
import com.uPanamarou.Service.BookService;
import com.uPanamarou.UI.Library;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ButtonDeleteEventListener extends MouseAdapter {

    private Library library;
    public ButtonDeleteEventListener(Library library){
        this.library = library;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Button delete clicked");
        BookService bookService = new BookService();
        try {
            if (bookService.deleteBook(BookModel.getBook())) {
                System.out.println("Book delete successfully");
                library.readDB(null);
            }else
                System.out.println("Some thing going wrong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
