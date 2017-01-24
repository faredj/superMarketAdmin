package donnees;

import java.sql.*;

/**
 * Cette classe représente La connexion à la base de donnée
 * @author soria
 *
 */
public class Connexion {
	/**
	 * l'URL de connexion
	 */
	private String url = "jdbc:mysql://localhost/superMarches";
	/**
	 * Nom de l'utilisateur 
	 */
	private String user = "root";
	/**
	 * Mot de passe de l'utilisateur
	 */
	private String passwd = "&(à(&ç_ç";
	/**
	 * l'Objet Connection
	 */
	private static Connection connect;
	/**
	 * Constructeur privé de la connexion
	 */
	private Connexion(){
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	/**
	 * Méthode qui va retourner la connexion et la créer si elle n'existe pas
	 */
	public static Connection getConnection(){
		if(connect == null){
			new Connexion();
		}
		return connect;   
	}
}
