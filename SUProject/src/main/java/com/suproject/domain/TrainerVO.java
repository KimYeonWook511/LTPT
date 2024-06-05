package com.suproject.domain;

import java.util.Date;

public class TrainerVO {

	private String trainer;
	private int ratingtotal;
	private int ratingcnt;
	private Date regdate;

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public int getRatingtotal() {
		return ratingtotal;
	}

	public void setRatingtotal(int ratingtotal) {
		this.ratingtotal = ratingtotal;
	}

	public int getRatingcnt() {
		return ratingcnt;
	}

	public void setRatingcnt(int ratingcnt) {
		this.ratingcnt = ratingcnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "TrainerVO [trainer=" + trainer + ", ratingtotal=" + ratingtotal + ", ratingcnt=" + ratingcnt + ", regdate="
				+ regdate + "]";
	}

}
