package com.suproject.domain;

public class ApplyPTDTO {

	private String userid;
	private String username;
	private String usergender;
	private String callnum;
	private String trainerid;
	private String trainername;
	private int totalcnt;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getCallnum() {
		return callnum;
	}

	public void setCallnum(String callnum) {
		this.callnum = callnum;
	}

	public String getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(String trainerid) {
		this.trainerid = trainerid;
	}

	public String getTrainername() {
		return trainername;
	}

	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}

	public int getTotalcnt() {
		return totalcnt;
	}

	public void setTotalcnt(int totalcnt) {
		this.totalcnt = totalcnt;
	}

	@Override
	public String toString() {
		return "ApplyPTDTO [userid=" + userid + ", username=" + username + ", usergender=" + usergender + ", callnum="
				+ callnum + ", trainerid=" + trainerid + ", trainername=" + trainername + ", totalcnt=" + totalcnt
				+ "]";
	}

}
