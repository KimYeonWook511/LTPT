package com.suproject.domain;

public class TrainerDTO {

	private String trainer;
	private int rating;

	public TrainerDTO() {
		// 기본 생성자
	}
	
	public TrainerDTO(String trainer, int rating) {
		this.trainer = trainer;
		this.rating = rating;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "TrainerDTO [trainer=" + trainer + ", rating=" + rating + "]";
	}

}
