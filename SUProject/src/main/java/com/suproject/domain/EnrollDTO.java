package com.suproject.domain;

public class EnrollDTO {

	private String member;
	private String trainer;
	private int totalcnt;
	private int completecnt;
	private String membername;
	private String trainername;
	
	public EnrollDTO() {
		// 기본 생성자
	}
	
	public EnrollDTO(String member, String trainer) {
		this.member = member;
		this.trainer = trainer;
	}
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	
	public int getTotalcnt() {
		return totalcnt;
	}

	public void setTotalcnt(int totalcnt) {
		this.totalcnt = totalcnt;
	}

	public int getCompletecnt() {
		return completecnt;
	}

	public void setCompletecnt(int completecnt) {
		this.completecnt = completecnt;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getTrainername() {
		return trainername;
	}

	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}

	@Override
	public String toString() {
		return "EnrollDTO [member=" + member + ", trainer=" + trainer + ", totalcnt=" + totalcnt + ", completecnt="
				+ completecnt + ", membername=" + membername + ", trainername=" + trainername + "]";
	}
	
}
