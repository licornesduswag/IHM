package db;

import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {
	static java.sql.Connection conn = null;
	
	//Connexion à la BDD
	static public void db_connect(){
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/LocationVoiture";
			String user = "postgres";
			String password = "postgres";
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Ajout d'un utilisateur à la BDD, le mot de passe est chiffré via BCrypt
	static public void addUser(String username, String password) throws SQLException{
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		String req = "INSERT INTO User VALUES('"+username+"','"+hashed+"');";
		Statement s = conn.createStatement();
		s.executeUpdate(req);
	}
	
	//Vérification de la présence d'un utilisateur ainsi que de son mot de passe dans la BDD
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
	
	//Renvoie la liste des réservations pour les afficher	
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
			i++;
		}
		return resa;
	}
	
	//Renvoie la liste des plaques d'immatriculation
	static public String[] getMatricule() throws SQLException{
		int i = 0;
		String req = "SELECT matricule, marque FROM \"Voiture\"";
		Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.sql.ResultSet result = s.executeQuery(req);
		result.last();
		String[] matricule = new String[result.getRow()];
		result.beforeFirst();
		while(result.next()){
			matricule[i] = result.getString(2) + " - " + result.getString(1);
			i++;
		}
		return matricule;
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