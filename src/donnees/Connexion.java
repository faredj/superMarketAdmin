package donnees;

import java.sql.*;
/**
 * Cette classe représente La connexion à la base de donnée
 * @author soria
 *
 */
public class Connexion {
/**
 * @return La connexion à la base de donnée
 */
	public static Connection getConnection(){
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex) {
		   System.out.println("Error: unable to load driver class!");
		   System.exit(1);
		}
		Connection conn = null;
	try {
	    String url = "jdbc:mysql://localhost/superMarches";
	    String user = "root";
	    String passwd = "&(à(&ç_ç";
	    conn = DriverManager.getConnection(url, user, passwd);
	} catch (SQLException ex) {
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
		return conn;
	}

}
