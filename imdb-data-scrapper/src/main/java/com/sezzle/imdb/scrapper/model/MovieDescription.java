package com.sezzle.imdb.scrapper.model;

public class MovieDescription {
	
	private String title;
	private Integer movie_release_year;
	private Double imdb_rating;
	private String summary;
	private String duration;
	private String genre;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMovie_release_year() {
		return movie_release_year;
	}
	public void setMovie_release_year(Integer movie_release_year) {
		this.movie_release_year = movie_release_year;
	}
	public Double getImdb_rating() {
		return imdb_rating;
	}
	public void setImdb_rating(Double double1) {
		this.imdb_rating = double1;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	

}
