package org.example.testtask.repository;

import org.example.testtask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	boolean existsByTitle(String title);
}