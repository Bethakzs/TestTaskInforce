package org.example.testtask.mapper;

import org.example.testtask.model.Book;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

	public static Book mapToBookEntity(Element book) {
		String title = book.select("h3 a").attr("title");
		String price = book.select(".price_color").text();
		String availability = book.select(".availability").text().trim();
		String ratingClass = book.select(".star-rating").attr("class").split(" ")[1];

		Book bookEntity = new Book();
		bookEntity.setTitle(title);
		bookEntity.setPrice(price);
		bookEntity.setAvailability(availability);
		bookEntity.setRating(ratingClass);

		return bookEntity;
	}
}
