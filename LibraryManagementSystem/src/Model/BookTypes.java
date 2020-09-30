package Model;



	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	import Helper.DBConnection;
	
	
	public class BookTypes {
		
		private int id;
		private String name;
		
		DBConnection conn = new DBConnection();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		
		public BookTypes() {}
		
		public BookTypes(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		
		public ArrayList<BookTypes> getList() throws SQLException{
			ArrayList<BookTypes> list = new ArrayList<>();
			BookTypes obj;
			Connection con = conn.connDb();
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT * FROM type");
				while(rs.next()) {
					obj = new BookTypes();
					obj.setId(rs.getInt("id"));
					obj.setName(rs.getString("name"));
					list.add(obj);
				}
				} catch(SQLException e) {
					e.printStackTrace();
				}finally {
			st.close();
			rs.close();
			con.close();
				}
			return list;
				
			}
		public boolean addBookTypes(String name) throws SQLException {
			
			String query = "INSERT INTO type" + "(name) VALUES" + "(?)";
			boolean key = false;
			Connection con = conn.connDb();
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(key)
				return true;
			else	
			return false;
		}
		
		public boolean deleteBookTypes(String name) throws SQLException {
			
			String query = "DELETE FROM type WHERE name = ?"; 
			boolean key = false;
			Connection con = conn.connDb();
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(key)
				return true;
			else	
			return false;
		}
		
		public boolean updateBookTypes(String name, int id) throws SQLException {
			
			String query = "UPDATE type Set name=? WHERE id = ? "; 
			boolean key = false;
			Connection con = conn.connDb();
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(key)
				return true;
			else	
			return false;
		}
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		

	}


