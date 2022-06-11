package com.uPanamarou.Service;

import com.uPanamarou.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookService {
    private String bookTitle;
    private String authorName;
    private String pages;
    private String genre;

    private Connection connection = new MySQLConnection().connectToDataBase();


    public boolean addBookToDatabase(String[] book) throws SQLException {
        this.bookTitle = book[0];
        this.authorName = book[1];
        this.pages = book[2];
        this.genre = book[3];
        PreparedStatement ps =
                    connection.
                    prepareStatement("INSERT INTO books (bName,aName,pages,genre) value (?,?,?,?)");
        ps.setString(1,bookTitle);
        ps.setString(2,authorName);
        ps.setString(3,pages);
        ps.setString(4,genre);
        ps.executeUpdate();

        return true;
    }


    public boolean updateBook(String[] book,int bookId) throws SQLException {
        if (book==null)
            return false;
        this.bookTitle = book[0];
        this.authorName = book[1];
        this.pages = book[2];
        this.genre = book[3];
        PreparedStatement ps =
                connection
               .prepareStatement("UPDATE books SET bName = ?,aName = ?,pages = ?, genre = ? WHERE id = ?");
        ps.setString(1,bookTitle);
        ps.setString(2,authorName);
        ps.setString(3,pages);
        ps.setString(4,genre);
        ps.setString(5, String.valueOf(bookId));

        ps.executeUpdate();

        return true;
    }

    public boolean deleteBook(String[] book) throws SQLException {
        if (book==null)
            return false;
        PreparedStatement ps =
                connection.prepareStatement("DELETE FROM Books WHERE id = ?");
        ps.setString(1,book[0]);
        ps.executeUpdate();
        return true;
    }

}
