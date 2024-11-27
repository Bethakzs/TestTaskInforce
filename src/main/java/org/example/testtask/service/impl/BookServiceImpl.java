package org.example.testtask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testtask.model.Book;
import org.example.testtask.repository.BookRepository;
import org.example.testtask.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public void createBook(Book bookEntity) {
		bookRepository.save(bookEntity);
		log.info("Book saved: {}", bookEntity);
	}

	@Override
	public List<Book> getAllBooks() {
		log.info("Getting all books");
		return bookRepository.findAll();
	}

	@Override
	public boolean bookExistsByTitle(String title) {
		return bookRepository.existsByTitle(title);
	}
}
