package db;

import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	static java.sql.Connection conn = null;
	static public java.sql.Connection db_connect(){
		try{
			//Connexion
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/LocationVoiture";
			String user = "postgres";
			String password = "postgres";
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	static public boolean connectUser(String username, String password) throws SQLException{
		String req = "SELECT username, password FROM \"users\" WHERE username='"+username+"';";
		Statement s = conn.createStatement();
		java.sql.ResultSet result = s.executeQuery(req);
		result.next();
		if(result.getString(1).equals(username)){
			if (BCrypt.checkpw(password, result.getString(2))){
				return true;
			}
		}
		return false;
	}
	
	static public void addUser(String username, String password) throws SQLException{
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		String req = "INSERT INTO users VALUES('"+username+"','"+hashed+"');";
		Statement s = conn.createStatement();
		s.executeUpdate(req);
	}
	
	public static void main(String[] args){
		db_connect();
		try {
			System.out.println(connectUser("maximusk", "supersafepassword"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}