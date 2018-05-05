package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Project {
	private int id_project;
	@NotEmpty(message = "Vui lòng nhập vào tên dự án!")
	private String name;
	private String picture;
	@NotEmpty(message = "Vui lòng nhập vào mô tả!")
	private String preview_text;
	@NotEmpty(message = "Vui lòng nhập vào link!")
	private String link;

	public int getId_project() {
		return id_project;
	}

	public void setId_project(int id_project) {
		this.id_project = id_project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPreview_text() {
		return preview_text;
	}

	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Project() {
		super();
	}

}
