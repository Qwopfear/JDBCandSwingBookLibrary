package com.uPanamarou.UI;

import com.uPanamarou.Controllers.AddToDBEventListener;
import com.uPanamarou.Service.BookModel;
import com.uPanamarou.UI.Library;

import javax.swing.*;
import java.awt.*;

public class AddBookFrame extends JFrame {

    private String [] book;

    public AddBookFrame(Library library){
        setSize(new Dimension(300,300));
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        JButton button = new JButton("Add");
        if (library.getCurrentOperation() == 1) {
            book = BookModel.getBook();
            button.setText("Update");

        }
        else
            book = new String[4];

        try {
            JPanel mainPanel = new JPanel();
            Box HBoxBookName = Box.createHorizontalBox();
            HBoxBookName.setSize(new Dimension(40,40));
            JLabel titleLabel = new JLabel(" Book Title : ");
            JTextField titleArea = new JTextField(book[1]);
            titleArea.setPreferredSize(new Dimension(50,5));
            HBoxBookName.add(titleLabel);
            HBoxBookName.add(titleArea,"wrap");

            Box HBoxAuthorName = Box.createHorizontalBox();
            HBoxAuthorName.setSize(new Dimension(50,40));
            JLabel authorLabel = new JLabel(" Author name : ");
            JTextField authorArea = new JTextField(book[2]);
            authorArea.setPreferredSize(new Dimension(30,5));
            HBoxAuthorName.add(authorLabel);
            HBoxAuthorName.add(authorArea);

            Box HBoxBookSize = Box.createHorizontalBox();
            HBoxBookSize.setSize(new Dimension(50,40));
            JLabel sizeLabel = new JLabel(" pages  : ");
            JTextField sizeArea = new JTextField(book[3]);
            sizeArea.setPreferredSize(new Dimension(30,5));
            HBoxBookSize.add(sizeLabel);
            HBoxBookSize.add(sizeArea);

            Box HBoxBookGenre = Box.createHorizontalBox();
            HBoxBookSize.setSize(new Dimension(50,40));
            JLabel genreLabel = new JLabel(" Genre  : ");
            JTextField genreArea = new JTextField(book[4]);
            sizeArea.setPreferredSize(new Dimension(30,5));
            HBoxBookGenre.add(genreLabel);
            HBoxBookGenre.add(genreArea);

            mainPanel.add(HBoxBookName,"wrap");
            mainPanel.add(HBoxAuthorName,"wrap");
            mainPanel.add(HBoxBookSize,"wrap");
            mainPanel.add(HBoxBookGenre,"wrap");
            mainPanel.add(button);


            button.addMouseListener(new AddToDBEventListener(this,library));


            mainPanel.setLayout(new GridLayout(5,1,25,25));

            add(mainPanel);
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Before update choose book!!!");
            this.dispose();
        }

    }

}
