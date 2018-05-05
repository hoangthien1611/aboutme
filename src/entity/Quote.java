package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Quote {
	private int id_quote;
	@NotEmpty(message = "Vui lòng nhập vào tên tác giả!")
	private String author;
	@NotEmpty(message = "Vui lòng nhập vào nội dung!")
	private String content;
	private String picture;

	public int getId_quote() {
		return id_quote;
	}

	public void setId_quote(int id_quote) {
		this.id_quote = id_quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Quote() {
		super();
	}

}
