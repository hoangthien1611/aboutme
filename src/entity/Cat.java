package entity;

import javax.validation.constraints.Size;

public class Cat {
	private int id_cat;
	@Size(min=5, max=35, message="Nhập tên danh mục từ 5 đến 35 ký tự!")
	private String name;
	private int count_news;

	public Cat() {
		super();
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount_news() {
		return count_news;
	}

	public void setCount_news(int count_news) {
		this.count_news = count_news;
	}

}
