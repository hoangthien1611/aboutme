package entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class News {
	private int id_news;
	@NotEmpty(message = "Vui lòng nhập vào tên tin!")
	private String name;
	@NotEmpty(message = "Vui lòng nhập vào mô tả tin!")
	private String preview_text;
	@NotEmpty(message = "Vui lòng nhập vào chi tiết tin!")
	private String detail_text;
	private int id_cat;
	private String nameCat;
	private int id_parent;
	private String picture;
	private int count_views;
	private Timestamp date_create;
	private int active;
	private int id_user;
	private String username;
	
	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCat() {
		return nameCat;
	}

	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId_news() {
		return id_news;
	}

	public void setId_news(int id_news) {
		this.id_news = id_news;
	}

	public String getPreview_text() {
		return preview_text;
	}

	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}

	public String getDetail_text() {
		return detail_text;
	}

	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getCount_views() {
		return count_views;
	}

	public void setCount_views(int count_views) {
		this.count_views = count_views;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public News() {
		super();
	}

}
