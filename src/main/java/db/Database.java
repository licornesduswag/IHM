package db;

import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.ResultSet;
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
		String req = "SELECT username, password FROM \"User\" WHERE username='"+username+"';";
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
		String req = "INSERT INTO User VALUES('"+username+"','"+hashed+"');";
		Statement s = conn.createStatement();
		s.executeUpdate(req);
	}
	
	static public String[][] getResa() throws SQLException{
		int i = 0;
		String req = "SELECT nom, prenom, matricule, datedebut, datefin, prix FROM \"Resa\" NATURAL JOIN \"Client\"";
		Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.sql.ResultSet result = s.executeQuery(req);
		result.last();
		String[][] resa = new String[result.getRow()][5];
		result.beforeFirst();
		while(result.next()){
			resa[i][0] = result.getString(1).toUpperCase() + " " + result.getString(2);		
			resa[i][1] = result.getString(3);
			resa[i][2] = result.getDate(4).toString();
			resa[i][3] = result.getDate(5).toString();
			resa[i][4] = String.valueOf(result.getFloat(6));
			System.err.println(resa[i][0]);
			i++;
		}
		return resa;
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