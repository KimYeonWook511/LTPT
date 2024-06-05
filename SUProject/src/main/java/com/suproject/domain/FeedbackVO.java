package com.suproject.domain;

import java.util.Date;

public class FeedbackVO {

	private int bno;
	private String content;
	private String writer;
	private Date regdate;
	private int videono;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getVideono() {
		return videono;
	}

	public void setVideono(int videono) {
		this.videono = videono;
	}
	
	public String getReplContent() {
		return content.replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br>").replace(" ", "&nbsp;");
	}
	
	@Override
	public String toString() {
		return "FeedbackVO [bno=" + bno + ", content=" + content + ", writer=" + writer + ", regdate=" + regdate
				+ ", videono=" + videono + "]";
	}

}
