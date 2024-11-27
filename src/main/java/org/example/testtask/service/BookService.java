package org.example.testtask.service;

import org.example.testtask.model.Book;

import java.util.List;

public interface BookService {

	void createBook(Book bookEntity);

	List<Book> getAllBooks();

	boolean bookExistsByTitle(String title);
}
