package donnees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table vente de la base de données
 * @author faredj
 *
 */
public class VenteDAO implements DaoTemplate<Integer, Vente>{
/**
 * Méthode d'insertion d'une vente à la base de donnée
 * @param vente La vente à insérer dans la base de donnée
 * @return l'identificateur de vente aprés insertion 
 */
	@Override
	public Integer insert(Vente vente) {
		Connection conn = Connexion.getConnection();
		Integer idVe = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `vente`(`idSupermarche`) VALUES (?)");
				
			ps.setInt(1, vente.getSuperMarche().getIdSM());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idVe = keys.getInt(1);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idVe;
	}
/**
 * Méthode de modification d'une vente
 * @param vente La vente à modifier
 * @return L'identificateur de vente aprés modification
 */
	@Override
	public Integer update(Vente vente) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `vente` SET `idSupermarche`=? WHERE `idVente` = ?");
			ps.setInt(1, vente.getSuperMarche().getIdSM());
			ps.setInt(2, vente.getIdVente());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vente.getIdVente();
	}
/**
 * Méthode de suppression d'une vente de la base de donnée
 * @param vente La vente à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Vente vente) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `vente` WHERE `idVente` = ?");
			ps.setInt(1, vente.getIdVente());
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
 * Cette méthode retourne  La liste des ventes du supermarché
 * @return renvoie toutes les ventes 
 */
	@Override
	public List<Vente> findAll() {
		Connection conn = Connexion.getConnection();
		List<Vente> listeVente = new ArrayList<Vente>();
		try {
			PreparedStatement selVente = conn.prepareStatement("SELECT * FROM `vente`");
			ResultSet res = selVente.executeQuery();
			while (res.next()) {
				PreparedStatement recupSM = conn.prepareStatement("SELECT * FROM `supermarche` WHERE `idSupermarche`= ? ");
				recupSM.setInt(1, res.getInt("idSupermarche"));
				ResultSet smRes = recupSM.executeQuery();
				SuperMarche smTmp = null;
				while (smRes.next()) {
					if(smRes.getString("typeSM").equals("SuperMarcheGeneraliste")){
						smTmp = new SuperMarcheGeneraliste(smRes.getInt("idSupermarche"), null, smRes.getString("adresseSM"), null, null, null);
					}else{
						smTmp = new SuperMarcheSpecialiste(smRes.getInt("idSupermarche"), null, smRes.getString("adresseSM"), null, null, null);
					}
				}
				Vente vTmp = new Vente(res.getInt("idVente"), smTmp, null);
				listeVente.add(vTmp);
				recupSM.close();
			}
			selVente.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeVente;
	}

}
