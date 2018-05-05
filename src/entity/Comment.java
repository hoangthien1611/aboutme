package entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Comment {
	private int id_comment;
	private int id_news;
	private String name;
	@NotEmpty(message = "Vui lòng nhập vào họ tên!")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập vào email!")
	private String email;
	@NotEmpty(message = "Vui lòng nhập vào nội dung!")
	private String content;
	private Timestamp date_create;
	private int status;
	private int id_parent;

	public int getId_comment() {
		return id_comment;
	}

	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}

	public int getId_news() {
		return id_news;
	}

	public void setId_news(int id_news) {
		this.id_news = id_news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public Comment() {
		super();
	}

}
