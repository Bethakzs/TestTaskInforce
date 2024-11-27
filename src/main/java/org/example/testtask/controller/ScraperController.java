package org.example.testtask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testtask.model.Book;
import org.example.testtask.service.ScrapingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/scraper")
@RequiredArgsConstructor
public class ScraperController {

	private final ScrapingService scrapingService;

	@GetMapping("/scrape")
	public ResponseEntity<List<Book>> scrape(
			@RequestParam String url,
			@RequestParam(required = false, defaultValue = "0") int pages) {
		List<Book> books = scrapingService.scrapePages(url, pages);
		return ResponseEntity.ok(books);
	}
}


