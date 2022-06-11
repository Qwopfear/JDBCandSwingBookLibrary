package com.uPanamarou.Controllers;

import com.uPanamarou.Service.BookService;
import com.uPanamarou.UI.Library;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class SelectedByAreaEventListener implements KeyListener {
    private String selectRequest = new String();
    private Library library;
    private JComboBox selectedBy;
    private BookService bookService;
    public SelectedByAreaEventListener(Library library, JComboBox selectedBy) {
        this.library = library;
        this.selectedBy = selectedBy;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        bookService = new BookService();

        selectRequest += String.valueOf(e.getKeyChar());
        System.out.println(selectRequest);
        try {
            library.readDB(
                    bookService.selectBy(
                            selectedBy.getSelectedItem().toString(),selectRequest));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
