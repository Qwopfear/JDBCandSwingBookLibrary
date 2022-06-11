package com.uPanamarou.UI;

import com.uPanamarou.Controllers.ButtonAddEventListener;
import com.uPanamarou.Controllers.ButtonDeleteEventListener;
import com.uPanamarou.Controllers.ButtonUpdateEventListener;
import com.uPanamarou.Controllers.TableRowClickListener;
import com.uPanamarou.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Library extends JFrame {
    private JTable booksTable;
    private BooksTableModel impl;
    private JPanel buttonsPanel;

    private int currentOperation = 0;
    public Library(){
        setTitle("MyLibrary");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        impl = new BooksTableModel();





        booksTable = new JTable(impl);
//        booksTable.setPreferredSize(new Dimension(200,300));
        booksTable.addMouseListener(new TableRowClickListener(booksTable));
        JScrollPane scrollPane = new JScrollPane(booksTable);
        booksTable.setVisible(true);

//        add(scrollPane);

        Box panel = Box.createVerticalBox();
        panel.setPreferredSize(new Dimension(600,200));
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(createButtons(), BorderLayout.SOUTH);
        add(panel);
        pack();
    }

    public void readDB() {
        Connection connection = new MySQLConnection().connectToDataBase();

        try {
            impl = new BooksTableModel();
            Statement s  = connection.createStatement();
            String[] book = new String[5];

            PreparedStatement psGet = connection.prepareStatement("SELECT * from books ");
            ResultSet resultSet = psGet.executeQuery();
            System.out.println(resultSet.toString());
            while (resultSet.next()){
                int index = 0;
                while (index < 5){
                    book[index] = resultSet.getString(index + 1);
                    System.out.println(book[index]);
                    index++;
                }
                impl.addDate(book);
                book = new String[5];
            }

                booksTable.setModel(impl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    JPanel createButtons(){
        JPanel buttonPanel = new JPanel();
        JButton add,delete,update;

        add = new JButton("Add");
        delete = new JButton("Delete");
        update = new JButton("Update");

        add.addMouseListener(new ButtonAddEventListener(this));
        delete.addMouseListener(new ButtonDeleteEventListener(this));
        update.addMouseListener(new ButtonUpdateEventListener(this));

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(update);
        return buttonPanel;
    }


    public void setCurrentOperation(int currentOperation) {
        this.currentOperation = currentOperation;
    }

    public int getCurrentOperation() {
        return currentOperation;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Library library = new Library();
                library.readDB();
            }
        });
    }
}
