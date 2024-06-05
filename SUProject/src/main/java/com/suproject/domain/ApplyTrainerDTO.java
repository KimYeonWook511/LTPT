package com.suproject.domain;

public class ApplyTrainerDTO {

	private String userid;
	private String callnum;
	private String content;
	private String username;
	private String usergender;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCallnum() {
		return callnum;
	}

	public void setCallnum(String callnum) {
		this.callnum = callnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toString() {
		return "ApplyTrainerDTO [userid=" + userid + ", callnum=" + callnum + ", content=" + content + ", username="
				+ username + ", usergender=" + usergender + "]";
	}

}
