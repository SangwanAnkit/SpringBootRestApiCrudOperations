package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="category", nullable = false)
	private String category;
	
	@Column(name="starRating", nullable = false)
	private int starRating;
	
	public Movie() {
		
	}
	
	public Movie( String title, String category, int starRating) {
		super();
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}	
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}	
	
	
}
