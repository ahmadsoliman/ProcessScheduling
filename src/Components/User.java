package Components;

public class User {
	private int index;
	private String userName;
	private int priority;

	public User(String userName, int priority) {
		super();
		this.userName = userName;
		this.priority = priority;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean equals(Object obj) {
		return (obj instanceof User && ((User) obj).getUserName().equals(
				getUserName()));
	}
}
