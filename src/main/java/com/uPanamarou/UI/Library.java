package com.uPanamarou.UI;

import com.uPanamarou.Controllers.*;
import com.uPanamarou.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Library extends JFrame {
    private JTable booksTable;
    private BooksTableModel impl;
    private JComboBox selectedBy;
    private JLabel selectedByLabel;
    private JTextField selectedByArea;
    private int currentOperation = 0;
    public Library(){
        setTitle("MyLibrary");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        impl = new BooksTableModel();


        String [] comboBoxItems = {
                "Id",
                "Author",
                "Book Title",
                "Pages",
                "Genre"
        };

        selectedBy = new JComboBox(comboBoxItems);
        selectedByLabel = new JLabel("selectedBy: ");
        selectedByArea = new JTextField();
        selectedByArea.setPreferredSize(new Dimension(100,20));
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

    public void readDB(PreparedStatement preparedStatement) {
        Connection connection = new MySQLConnection().connectToDataBase();
        PreparedStatement psGet = preparedStatement;
        try {
            impl = new BooksTableModel();
            Statement s  = connection.createStatement();
            String[] book = new String[5];
            if (preparedStatement==null)
                psGet = connection.prepareStatement("SELECT * from books ");
            ResultSet resultSet = psGet.executeQuery();
            while (resultSet.next()){
                int index = 0;
                while (index < 5){
                    book[index] = resultSet.getString(index + 1);
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

        buttonPanel.add(selectedByLabel);
        buttonPanel.add(selectedBy);
        buttonPanel.add(selectedByArea);

        selectedByArea.addKeyListener(new SelectedByAreaEventListener(this,selectedBy));

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
                library.readDB(null);
            }
        });
    }
}
