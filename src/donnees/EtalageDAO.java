package donnees;
import java.sql.*;
import java.util.*;
import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table Etalages de la base de données 
 * @author faredj
 *
 */
public class EtalageDAO implements DaoTemplate<Integer, Etalage>{
/**
 * Méthode d'insertion à la base de données
 * @param etalage l'étalage à insérer
 * @return l'identificateur de l'etalage inséré
 */
	@Override
	public Integer insert(Etalage etalage) {
		Connection conn = Connexion.getConnection();
		Integer idEt = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `etalage`(`idRayon`) VALUES (?)");
			
			ps.setInt(1, etalage.getRayon().getIdRayon());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idEt = keys.getInt(1);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idEt;
	}
/**
 * Méthode de modification d'un étalage
 * @param etalage l'étalage à modifier
 * @return L'identificateur de l'étalage aprés modification
 */
	@Override
	public Integer update(Etalage etalage) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `etalage` SET `idRayon`=? WHERE `idEtalage`=?");
			ps.setInt(1, etalage.getRayon().getIdRayon());
			ps.setInt(2, etalage.getIdEtalage());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etalage.getIdEtalage();
	}
/**
 * Méthode de suppression d'un étalage de la base de donnée
 * @param etalage l'étalage à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Etalage etalage) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `etalage` WHERE `idEtalage` = ?");
			ps.setInt(1, etalage.getIdEtalage());
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
 * Cette méthode retourne  La liste des etalages du supermarché
 * @return la liste de tout les étalages
 */
	@Override
	public List<Etalage> findAll() {
		Connection conn = Connexion.getConnection();
		List<Etalage> listeEtalage = new ArrayList<Etalage>();
		try {
			PreparedStatement selEtalage = conn.prepareStatement("SELECT * FROM `etalage`");
			ResultSet res = selEtalage.executeQuery();
			while (res.next()) {
				PreparedStatement recupR = conn.prepareStatement("SELECT * FROM `rayon` WHERE `idRayon`= ? ");
				recupR.setInt(1, res.getInt("idRayon"));
				ResultSet rRes = recupR.executeQuery();
				Rayon rTmp = null;
				while (rRes.next()) {
					switch (rRes.getString("nomRayon")) {
					case "ALIMENTAIRE":
						rTmp = new RayonAlimentaire(rRes.getInt("idRayon"), null, TypeGeneral.ALIMENTAIRE, null);
						break;
					case "ELECTROMENAGER":
						rTmp = new RayonElectromenager(rRes.getInt("idRayon"), null, TypeGeneral.ELECTROMENAGER, null);
						break;
					case "ENTRETIENMAISONSLINGE":
						rTmp = new RayonEntretienMaisonLinge(rRes.getInt("idRayon"), null, TypeGeneral.ENTRETIENMAISONSLINGE, null);
						break;
					case "HYGIENEBEAUTE":
						rTmp = new RayonHygieneBeaute(rRes.getInt("idRayon"), null, TypeGeneral.HYGIENEBEAUTE, null);
						break;
					case "MODE":
						rTmp = new RayonMode(rRes.getInt("idRayon"), null, TypeGeneral.MODE, null);
						break;
					case "MULTIMEDIA":
						rTmp = new RayonMultimedia(rRes.getInt("idRayon"), null, TypeGeneral.MULTIMEDIA, null);
						break;

					default:break;
					}
				}
				Etalage etalage = new Etalage(res.getInt("idEtalage"), rTmp, null);
				listeEtalage.add(etalage);
				recupR.close();
			}
			selEtalage.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEtalage;
	}

}
