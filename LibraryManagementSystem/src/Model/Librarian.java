package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Librarian extends User{
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;
	
	public Librarian(String identitynumber, String password, String name, String user, String booksname, String booksauthor) {
		super(identitynumber,password, name, user, booksname, booksauthor);
	
}
	public Librarian() {}
	
	public ArrayList<User> getBookList() throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE user = 'book'");
			while(rs.next() ) {
				obj = new User(rs.getString("identitynumber"), rs.getString("password"), rs.getString("name"), rs.getString("user"), rs.getString("booksname"), rs.getString("booksauthor"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
		}
			return list;
		}
	
	
	public boolean addBook(String identitynumber, String booksname, String booksauthor) throws SQLException {
		
		String query = "INSERT INTO user" + "(identitynumber,booksname, booksauthor,user) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, identitynumber);
			preparedStatement.setString(2, booksname);
			preparedStatement.setString(3, booksauthor);
			preparedStatement.setString(4, "book");
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else	
		return false;
	}
	
	
public boolean deleteBook(String identitynumber) throws SQLException {
		
		String query = "DELETE FROM user WHERE identitynumber = ?"; 
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, identitynumber);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else	
		return false;
	}
public ArrayList<BookTypes> getList() {
	return null;
}
	}