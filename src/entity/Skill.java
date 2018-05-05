package entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Skill {
	private int id_skill;
	@NotEmpty(message = "Nhập tên kĩ năng!")
	private String name;
	@NotNull(message = "Nhập giá trị phần trăm!")
	private int percent;

	public int getId_skill() {
		return id_skill;
	}

	public void setId_skill(int id_skill) {
		this.id_skill = id_skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public Skill() {
		super();
	}

}
