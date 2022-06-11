package com.uPanamarou.Controllers;

import com.uPanamarou.UI.AddBookFrame;
import com.uPanamarou.UI.Library;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonAddEventListener extends MouseAdapter {

    private Library library;
    public ButtonAddEventListener(Library library) {
        this.library =  library;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Add button clicked");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                library.setCurrentOperation(0);
                AddBookFrame addBookFrame = new AddBookFrame(library);
            }
        });

    }
}
