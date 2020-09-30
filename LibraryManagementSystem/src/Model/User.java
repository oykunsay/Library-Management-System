package Model;

import Helper.DBConnection;

public class User {
	String identitynumber, password, name, user, booksname, booksauthor;
	DBConnection conn = new DBConnection();
	
	public User( String identitynumber, String password, String name, String user, String booksname,
			String booksauthor) {
		this.identitynumber = identitynumber;
		this.password = password;
		this.name = name;
		this.user = user;
		this.booksname = booksname;
		this.booksauthor = booksauthor;
	}
	public User() {}

	public String getIdentitynumber() {
		return identitynumber;
	}
	public void setIdentitynumber(String identitynumber) {
		this.identitynumber = identitynumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBooksname() {
		return booksname;
	}
	public void setBooksname(String booksname) {
		this.booksname = booksname;
	}
	public String getBooksauthor() {
		return booksauthor;
	}
	public void setBooksauthor(String booksauthor) {
		this.booksauthor = booksauthor;
	}
	
	
}
