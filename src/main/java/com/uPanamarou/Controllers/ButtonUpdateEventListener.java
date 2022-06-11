package com.uPanamarou.Controllers;

import com.uPanamarou.UI.AddBookFrame;
import com.uPanamarou.UI.Library;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonUpdateEventListener extends MouseAdapter {

    private Library library;
    public ButtonUpdateEventListener(Library library){
        this.library = library;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                library.setCurrentOperation(1);
                AddBookFrame addBookFrame = new AddBookFrame(library);
            }
        });
    }
}
