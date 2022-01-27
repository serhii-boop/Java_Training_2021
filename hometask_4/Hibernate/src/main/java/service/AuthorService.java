package service;

import DAO.AuthorDao;
import Hib.SessionUtil;
import entities.Author;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AuthorService extends SessionUtil implements AuthorDao {
    @Override
    public void addAuthor(Author author) throws SQLException {
        openTransactionSession();

        Session session =getSession();
        session.save(author);

        closeTransactionSession();
    }

    @Override
    public List<Author> getAllAuthor() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM author";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Author.class);
        List<Author> authorList = query.list();
        closeTransactionSession();

        return authorList;
    }

    @Override
    public Author getAuthorByName(String name, String surname) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM author where firstName = ? AND lastName = ?";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Author.class);
        query.setParameter(1,name);
        query.setParameter(2, surname);

        Author author = (Author) query.getSingleResult();

        closeTransactionSession();

        return author;
    }

    @Override
    public List<Author> getListAuthor(Date date, Date secondDate) throws SQLException{
        openTransactionSession();

        String sql = "SELECT * FROM author where birthDate BETWEEN ? and ?";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Author.class);
        query.setParameter(1,date);
        query.setParameter(2, secondDate);

        List<Author> authors = query.list();

        closeTransactionSession();

        return authors;

    }

    @Override
    public void updateAuthorName(String oldName, String newName) {
        openTransactionSession();

        String sql = "update author set lastName=? where lastName=?";
        Session session = getSession();
        Query query = session.createSQLQuery(sql).addEntity(Author.class);
        query.setParameter(1,newName);
        query.setParameter(2,oldName);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void removeAuthor(String name) {
        openTransactionSession();

        String sql = "delete from author where id=?";
        Session session = getSession();
        Query query = session.createSQLQuery(sql).addEntity(Author.class);
        query.setParameter(1,name);

        query.executeUpdate();

        closeTransactionSession();
    }
}
