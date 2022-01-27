package DAO;

import entities.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    void addBook(Book book) throws SQLException;

    List<Book> getAllBook() throws SQLException;

    Book getBookByName(String name) throws SQLException;

    void updateBookName(String oldName, String newName);

    void removeBook(String name);



}
