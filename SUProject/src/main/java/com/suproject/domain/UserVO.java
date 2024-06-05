package com.suproject.domain;

import java.util.Date;

public class UserVO {
	
	private String userid;
	private String userpw;
	private Date regdate;
	private String username;
	private String usergender;
	private String authority;
	private String callnum;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
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

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getCallnum() {
		return callnum;
	}

	public void setCallnum(String callnum) {
		this.callnum = callnum;
	}

	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpw=" + userpw + ", regdate=" + regdate + ", username=" + username
				+ ", usergender=" + usergender + ", authority=" + authority + ", callnum=" + callnum + "]";
	}

}
