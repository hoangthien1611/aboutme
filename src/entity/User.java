package entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private int id_user;
	@Size(min = 5, max = 32, message = "Nhập tên đăng nhập từ 5 đến 32 ký tự!")
	private String username;
	@Size(min = 5, max = 32, message = "Nhập mật khẩu từ 5 đến 32 ký tự!")
	private String password;
	@NotEmpty(message = "Vui lòng nhập vào họ tên!")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập vào họ tên!")
	private String email;
	private int id_role;
	private String name;

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		super();
	}

	public User(int id_user, String username, String password, String fullname, String email, int id_role,
			String name) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.id_role = id_role;
		this.name = name;
	}

}
