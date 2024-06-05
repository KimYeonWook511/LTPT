package com.suproject.domain;

public class UploadDTO {

	private int bno;
	private String filename;
	private int videono;

	public UploadDTO() {
		// �⺻ ������
	}
	
	public UploadDTO(int bno, int videono) {
		this.bno = bno;
		this.videono = videono;
	}

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
		// URL ���ڵ� / replace �� replaceAll ���� -> CharSequence���� Stirng regex(���Խ�)��
		return filename.replace("[", "%5B").replace("]", "%5D");
	}
	
	@Override
	public String toString() {
		return "UploadDTO [bno=" + bno + ", filename=" + filename + ", videono=" + videono + "]";
	}
}
