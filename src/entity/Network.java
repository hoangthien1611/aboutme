package entity;

import org.hibernate.validator.constraints.NotEmpty;

public class Network {
	private int id_net;
	@NotEmpty(message = "Nhập vào tên mạng xã hội!")
	private String name;
	@NotEmpty(message = "Nhập vào link!")
	private String link;
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getId_net() {
		return id_net;
	}

	public void setId_net(int id_net) {
		this.id_net = id_net;
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

	public Network() {
		super();
	}

}
