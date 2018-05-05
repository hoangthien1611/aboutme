package entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Contact {
	private int id_contact;
	@NotEmpty(message = "Vui lòng nhập vào họ tên!")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập vào email!")
	private String email;
	@NotEmpty(message = "Vui lòng nhập vào số điện thoại!")
	private String phone;
	@NotEmpty(message = "Vui lòng nhập vào nội dung!")
	private String content;
	private Timestamp date_create;

	public int getId_contact() {
		return id_contact;
	}

	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Contact() {
		super();
	}

}
