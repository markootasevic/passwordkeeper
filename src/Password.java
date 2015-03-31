import java.io.Serializable;

public class Password implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;
	private String username;
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		if (account == null || account.isEmpty())
			throw new RuntimeException("You must enter account name.");
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null || username.isEmpty())
			throw new RuntimeException("You must enter username.");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == null || password.isEmpty())
			throw new RuntimeException("You must enter password.");
		this.password = password;
	}
	@Override
	public boolean equals(Object arg0) {
		Password pw = (Password) arg0;
		if(this.getAccount().equals(pw.getAccount()) && this.getPassword().equals(pw.getPassword()) && this.getUsername().equals(pw.getUsername()))
			return true;
		else 
			return false;
	}
}
