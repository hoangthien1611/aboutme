package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Advertisement {
	private int id_ad;
	@NotEmpty(message = "Vui lòng nhập vào tên quảng cáo!")
	private String name;
	@NotEmpty(message = "Vui lòng nhập vào link quảng cáo!")
	private String link;
	private String picture;

	public int getId_ad() {
		return id_ad;
	}

	public void setId_ad(int id_ad) {
		this.id_ad = id_ad;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Advertisement() {
		super();
	}

}
