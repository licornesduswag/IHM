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
		String req = "SELECT idresa, nom, prenom, matricule, datedebut, datefin FROM \"Resa\" NATURAL JOIN \"Client\"";
		Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.sql.ResultSet result = s.executeQuery(req);
		result.last();
		String[][] resa = new String[result.getRow()][6];
		result.beforeFirst();
		while(result.next()){
			resa[i][0] = String.valueOf(result.getInt(1));
			resa[i][1] = result.getString(2);
			resa[i][2] = result.getString(3);
			resa[i][3] = result.getString(4);
			resa[i][4] = result.getDate(5).toString();
			resa[i][5] = result.getDate(6).toString();
			i++;
		}
		return resa;
	}
	
	//Renvoie la liste des plaques d'immatriculation
	static public String[] getMatricule() throws SQLException{
		int i = 0;
		String req = "SELECT matricule FROM \"Voiture\"";
		Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		java.sql.ResultSet result = s.executeQuery(req);
		result.last();
		String[] matricule = new String[result.getRow()];
		result.beforeFirst();
		while(result.next()){
			matricule[i] = result.getString(1);
			i++;
		}
		return matricule;
	}
	
	static public boolean addResa(String nom, String prenom, String matricule, java.sql.Date datedebut, java.sql.Date datefin) throws SQLException{
		String req1 = "SELECT numpermis FROM \"Client\" WHERE (prenom = '"+prenom+"' AND nom = '"+nom+"');";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(req1);
		if(!result.next()){
			return false;
		}
		String numpermis = result.getString(1);
		String req2 = "INSERT INTO \"Resa\" (numpermis, matricule, datedebut, datefin) VALUES ('"+numpermis+"','"+matricule+"','"+datedebut+"','"+datefin+"');";
		s.executeUpdate(req2);
		return true;
	}
	
	static public void editResa(int id, String nom, String prenom, String matricule, java.sql.Date datedebut, java.sql.Date datefin) throws SQLException{
		String req1 = "SELECT numpermis FROM \"Client\" WHERE (prenom = '"+prenom+"' AND nom = '"+nom+"');";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(req1);
		result.next();
		String numpermis = result.getString(1);
		String req2 = "UPDATE \"Resa\" SET numpermis = '"+numpermis+"',matricule = '"+matricule+"', datedebut = '"+datedebut+"', datefin = '"+datefin+"' WHERE idresa = '"+id+"';";
		s.executeUpdate(req2);
	}
	
	static public void removeResa(int id) throws SQLException{
		String req = "DELETE FROM \"Resa\" WHERE idresa = '"+id+"';";
		Statement s = conn.createStatement();
		s.executeUpdate(req);
	}
	
	static public boolean addClient(String nom, String prenom, java.sql.Date dateNaissance, String lieuNaissance,
			String numPermis, java.sql.Date datePermis, String lieuPermis, String villePermis ) throws SQLException{
		String req1 = "SELECT * FROM \"Client\" WHERE (prenom = '"+prenom+"' AND nom = '"+nom+"');";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(req1);
		if (result.next()){
			return false;
		}
		String req2 = "INSERT INTO \"Client\" VALUES ('"+nom+"','"+prenom+"','"+dateNaissance+"','"+lieuNaissance+"','"+numPermis+"','"+datePermis+"','"+lieuPermis+"','"+villePermis+"');";
		s.executeUpdate(req2);
		return true;
	}
}