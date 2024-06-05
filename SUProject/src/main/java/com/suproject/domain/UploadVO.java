package com.suproject.domain;

public class UploadVO {

	private int bno;
	private String filename;
	private int videono;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public int getVideono() {
		return videono;
	}

	public void setVideono(int videono) {
		this.videono = videono;
	}

	public String getFilenameURL() {
		// URL 인코딩 / replace 와 replaceAll 차이 -> CharSequence형과 Stirng regex(정규식)형
		return filename.replace("[", "%5B").replace("]", "%5D");
	}
	
	@Override
	public String toString() {
		return "UploadVO [bno=" + bno + ", filename=" + filename + ", videono=" + videono + "]";
	}
}
