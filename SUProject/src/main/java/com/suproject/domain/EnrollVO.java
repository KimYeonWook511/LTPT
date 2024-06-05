package com.suproject.domain;

import java.util.Date;

public class EnrollVO {

	private String member;
	private String trainer;
	private int totalcnt;
	private int completecnt;
	private Date regdate;
	private String membername;
	private String trainername;

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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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
		return "EnrollVO [member=" + member + ", trainer=" + trainer + ", totalcnt=" + totalcnt + ", completecnt="
				+ completecnt + ", regdate=" + regdate + ", membername=" + membername + ", trainername=" + trainername
				+ "]";
	}

}
