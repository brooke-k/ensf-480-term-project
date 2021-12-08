package ensf480.group14.users;

public abstract class User {
	protected String type;

	protected void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
};
