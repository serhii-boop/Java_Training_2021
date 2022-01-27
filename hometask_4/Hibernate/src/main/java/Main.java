import entities.Author;
import org.hibernate.Session;
import service.AuthorService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {



//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");


        LocalDate firstDate = LocalDate.of(1967, 9, 24);
        Date date = Date.valueOf(firstDate);
        LocalDate secondDate = LocalDate.of(2009, 9, 24);
        Date date1 = Date.valueOf(secondDate);

        List<Author> authorList = new AuthorService().getListAuthor(date,date1);
        for (Author i: authorList
             ) {
            System.out.println(i);
        }
        //List<Author> authorList = new AuthorService().getAllAuthor();

        //new AuthorService().updateAuthorName("Ivanov", "Myskohlid");
        //System.out.println(new AuthorService().getAuthorByName("Mykola","Myskohlid"));
//        for (Author i: authorList
//             ) {
//            System.out.println(i);
//        }
//        Author author = new Author();
//        author.setFirstName("Taras");
//        author.setLastName("Shevchenko");
//
//        LocalDate birthDate = LocalDate.of(1814, 3, 9);
//        Date date = Date.valueOf(birthDate);
//
//        author.setBirthDate(date);
//        author.setGender("Male");



//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(author);
//        entityManager.getTransaction().commit();
//        entityManager.close();




    }

}
