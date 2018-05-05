package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Interest {
	private int id_interest;
	@NotEmpty(message = "Nhập vào tên!")
	private String name;
	@NotEmpty(message = "Nhập mô tả!")
	private String description;

	public int getId_interest() {
		return id_interest;
	}

	public void setId_interest(int id_interest) {
		this.id_interest = id_interest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Interest() {
		super();
	}

}
