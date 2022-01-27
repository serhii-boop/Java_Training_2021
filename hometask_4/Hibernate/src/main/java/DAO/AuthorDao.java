package DAO;

import entities.Author;
import entities.Book;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {

    void addAuthor(Author author) throws SQLException;

    List<Author> getAllAuthor() throws SQLException;

    Author getAuthorByName(String name, String surname) throws SQLException;

    List<Author> getListAuthor(Date date,Date secondDate) throws SQLException;

    void updateAuthorName(String oldName, String newName);

    void removeAuthor(String name);

}
