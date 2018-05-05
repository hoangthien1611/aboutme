package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Work {
	private int id_work;
	@NotEmpty(message = "Nhập tên công việc!")
	private String name;
	@NotEmpty(message = "Nhập mô tả!")
	private String description;
	@NotEmpty(message = "Nhập khoảng thời gian!")
	private String period;

	public int getId_work() {
		return id_work;
	}

	public void setId_work(int id_work) {
		this.id_work = id_work;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Work() {
		super();
	}

}
