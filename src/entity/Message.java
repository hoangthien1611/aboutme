package entity;

public class Message {
	private int state;
	private String msg;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Message() {
		super();
	}

	public Message(int state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}

}
