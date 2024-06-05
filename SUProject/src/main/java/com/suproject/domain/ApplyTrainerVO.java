package com.suproject.domain;

import java.util.Date;

public class ApplyTrainerVO {

	private String userid;
	private String callnum;
	private String content;
	private Date regdate;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReplContent() {
		return content.replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br>").replace(" ", "&nbsp;");
	}
	
	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	@Override
	public String toString() {
		return "ApplyTrainerVO [userid=" + userid + ", callnum=" + callnum + ", content=" + content + ", regdate="
				+ regdate + ", username=" + username + ", usergender=" + usergender + "]";
	}

}
