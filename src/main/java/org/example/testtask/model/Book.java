package org.example.testtask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@Column(name = "price", nullable = false)
	private String price;

	@Column(name = "availability", nullable = false)
	private String availability;

	@Column(name = "rating")
	private String rating;
}

