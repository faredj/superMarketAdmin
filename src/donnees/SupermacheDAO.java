package donnees;
import java.sql.*;
import java.util.*;
import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table Supermarché de la base de données
 * @author soria
 *
 */
public class SupermacheDAO implements DaoTemplate<Integer, SuperMarche>{
/**
 * Méthode d'insertion d'un supermarché dans la base de donnée
 * @param superMarche Le supermarché à insérer
 * @return l'identificateur de supermarché aprés insertion
 */
	@Override
	public Integer insert(SuperMarche superMarche) {
		Connection conn = Connexion.getConnection();
		Integer idSm = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `supermarche`(`idEnsigne`, `typeSM`, `adresseSM`) VALUES (?,?,?)");
			
			ps.setInt(1, superMarche.getChaineSM().getidEnseigne());
			ps.setString(2, superMarche.getClass().getSimpleName());
			ps.setString(3, superMarche.getAdresse());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idSm = keys.getInt(1);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSm;
	}
/**
 * Méthode de modification d'un supermarché
 * @param sm Le supermarché à modifier
 * @return L'identificateur du supermarché aprés modification
 */
	@Override
	public Integer update(SuperMarche sm) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `supermarche` SET `idEnsigne`=?,`typeSM`=?,`adresseSM`=? WHERE `idSupermarche` = ?");
			ps.setInt(1, sm.getChaineSM().getidEnseigne());
			ps.setString(2, sm.getClass().getSimpleName());
			ps.setString(3, sm.getAdresse());
			ps.setInt(4, sm.getIdSM());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sm.getIdSM();
	}
/**
 * Méthode de suppression d'un supermarché
 * @param sm Le supermarché à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(SuperMarche sm) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `supermarche` WHERE `idSupermarche` = ?");
			ps.setInt(1, sm.getIdSM());
			ps.executeUpdate();
			ps.close();
			conn.close();
			exec = 1;
		} catch (Exception e) {
			e.printStackTrace();
			exec = -1;
		}
		return exec;
	}
/**
 * Cette méthode retourne  La liste des supermarchés
 * @return renvoie tout les supermarché 
 */
	@Override
	public List<SuperMarche> findAll() {
		Connection conn = Connexion.getConnection();
		List<SuperMarche> listeSuperMarche = new ArrayList<SuperMarche>();
		try {
			PreparedStatement selSuperM = conn.prepareStatement("SELECT * FROM `supermarche`");
			ResultSet res = selSuperM.executeQuery();
			while (res.next()) {
				if(res.getString("typeSM").equals("SuperMarcheGeneraliste")){
					PreparedStatement recupCh = conn.prepareStatement("SELECT * FROM `ChaineSM` WHERE `idEnsigne`= ? ");
					recupCh.setInt(1, res.getInt("idEnsigne"));
					ResultSet chRes = recupCh.executeQuery();
					ChaineSM chaine = null;
					while (chRes.next()) {
						chaine = new ChaineSM(chRes.getInt("idEnsigne"), chRes.getString("nomEnsigne"), new ArrayList<SuperMarche>());
					}
					SuperMarcheGeneraliste superMarcheGn = new SuperMarcheGeneraliste(res.getInt("idSupermarche"), chaine, res.getString("adresseSM"), null, null, null);
					listeSuperMarche.add(superMarcheGn);
				}else{
					PreparedStatement recupCh = conn.prepareStatement("SELECT * FROM `ChaineSM` WHERE `idEnsigne`= ? ");
					recupCh.setInt(1, res.getInt("idEnsigne"));
					ResultSet chRes = recupCh.executeQuery();
					ChaineSM chaine = null;
					while (chRes.next()) {
						chaine = new ChaineSM(chRes.getInt("idEnsigne"), chRes.getString("nomEnsigne"), new ArrayList<SuperMarche>());
					}
					SuperMarcheSpecialiste superMarcheSp = new SuperMarcheSpecialiste(res.getInt("idSupermarche"), chaine, res.getString("adresseSM"), null, null, null);
					listeSuperMarche.add(superMarcheSp);
				}
			}
			selSuperM.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSuperMarche;
	}
	
}
