package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class DemoApplication {



	@Autowired
	private static BookRepository bookRepository ;



	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(DemoApplication.class, args);

		AuthorRepository authorRepository =
				configurableApplicationContext.getBean(AuthorRepository.class);

		BookRepository bookRepository =
				configurableApplicationContext.getBean(BookRepository.class);

		Author author = new Author();
        author.setFirstName("Tarasko");
        author.setLastName("Shevchenkovych");

        LocalDate birthDate = LocalDate.of(1814, 3, 9);
        Date date = Date.valueOf(birthDate);

        author.setBirthDate(date);
        author.setGender("Male");

		Author author1 = new Author();
		author1.setFirstName("Tarasko");
		author1.setLastName("Shevchenkovych");

		LocalDate birthDate1 = LocalDate.of(1814, 3, 9);
		Date date2 = Date.valueOf(birthDate1);

		author1.setBirthDate(date2);
		author1.setGender("Male");

        Book book = new Book();
		book.setName("abobabiba");
		book.setPrice(211.2);
		LocalDate secondDate = LocalDate.of(2009, 9, 24);
		Date date1 = Date.valueOf(secondDate);
		book.setPublish_date(date1);
		book.setQuantity_in_stock(11111);

		Book book1 = new Book();
		book1.setName("abobabiba");
		book1.setPrice(211.2);
		LocalDate secondDate2 = LocalDate.of(2009, 9, 24);
		Date date9 = Date.valueOf(secondDate2);
		book1.setPublish_date(date9);
		book1.setQuantity_in_stock(11111);


		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		bookList.add(book1);
		author.setBooks(bookList);
		author1.setBooks(bookList);

		List<Author> authorList = new ArrayList<>(Arrays.asList(author,author1));
		authorRepository.saveAll(authorList);




//
//
//		List<Author> authors = new ArrayList<>();
//		authors.add(author);
//		authors.add(author1);
//		book.setAuthors(authors);
//        bookRepository.save(book);

        //authorRepository.save(author);
		//authorRepository.deleteById(9L);
//		LocalDate firstDate = LocalDate.of(1967, 9, 24);
//		Date startdate = Date.valueOf(firstDate);
//		LocalDate secondDate = LocalDate.of(2009, 9, 24);
//		Date endDate = Date.valueOf(secondDate);
//
//		List<Author> authorList = authorRepository.getAllBetweenDates(startdate,endDate);
//		for (Author i : authorList) {
//			System.out.println(i);
//		}

	}

//	@Bean
//	public CommandLineRunner run(BookRepository bookRepository) throws Exception {
//		return args -> {
//			bookRepository.updateBook(8,"ABOBA");
//		};
//	}
//
//	public Book addBook(){
//		Book book = new Book();
//		book.setName("aaa");
//		book.setPrice(211.2);
//		LocalDate secondDate = LocalDate.of(2009, 9, 24);
//		Date date1 = Date.valueOf(secondDate);
//		book.setPublish_date(date1);
//		book.setQuantity_in_stock(11111);
//
//		return book;
//
//	}



}
