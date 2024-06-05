package com.suproject.domain;

public class FeedbackDTO {

	private int bno;
	private String content;
	private String writer;
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

	public int getVideono() {
		return videono;
	}

	public void setVideono(int videono) {
		this.videono = videono;
	}

	@Override
	public String toString() {
		return "FeedbackDTO [bno=" + bno + ", content=" + content + ", writer=" + writer + ", videono=" + videono + "]";
	}

}
