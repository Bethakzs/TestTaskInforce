package org.example.testtask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testtask.exception.ScrapingException;
import org.example.testtask.mapper.BookMapper;
import org.example.testtask.model.Book;
import org.example.testtask.service.BookService;
import org.example.testtask.service.ScrapingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapingServiceImpl implements ScrapingService {

	private static final String BASE_URL = "https://books.toscrape.com/";
	private static final String PRODUCT_SELECTOR = ".product_pod";

	private final BookService bookService;

	public List<Book> scrapePages(String baseUrl, int maxPages) {
		String url = baseUrl;
		int currentPage = 0;

		while (url != null && (maxPages <= 0 || currentPage < maxPages)) {
			try {
				log.info("Scraping page: {}", url);
				scrapeAndSaveBooks(url);

				url = getNextPageUrl(url);
				currentPage++;
			} catch (IOException e) {
				log.error("Error while scraping page {}: {}", url, e.getMessage(), e);
				throw new ScrapingException(HttpStatus.BAD_REQUEST.value(), "Error while scraping books");
			}
		}

		return bookService.getAllBooks();
	}

	private void scrapeAndSaveBooks(String url) throws IOException {
		Document document = Jsoup.connect(url).get();

		Elements books = document.select(PRODUCT_SELECTOR);
		for (Element book : books) {
			Book bookEntity = BookMapper.mapToBookEntity(book);

			if (!bookService.bookExistsByTitle(bookEntity.getTitle())) {
				bookService.createBook(bookEntity);
			} else {
				log.info("Book with title '{}' already exists, skipping insert.", bookEntity.getTitle());
			}
		}
	}

	private String getNextPageUrl(String currentUrl) throws IOException {
		Document document = Jsoup.connect(currentUrl).get();

		Element nextPage = document.select("li.next a").first();

		if (nextPage != null) {
			String nextPageUrl = nextPage.attr("href");
			log.info("Next page URL found: {}", nextPageUrl);

			if (nextPageUrl.contains("catalogue")) {
				return BASE_URL + nextPageUrl;
			} else {
				return BASE_URL + "/catalogue/" + nextPageUrl;
			}
		}

		log.info("No next page found, ending scraping.");
		return null;
	}
}
