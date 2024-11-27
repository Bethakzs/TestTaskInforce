package org.example.testtask.service;

import org.example.testtask.model.Book;

import java.util.List;

public interface ScrapingService {

	List<Book> scrapePages(String url, int pages);
}
