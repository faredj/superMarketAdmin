package donnees;
import java.sql.*;
import java.util.*;
import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table rayon de la base de données
 * @author soria
 *
 */

public class RayonDAO implements DaoTemplate<Integer, Rayon>{
/**
 * Méthode d'insertion d'un rayon à la base de donnée
 * @param rayon Le rayon à insérer
 * @return l'identificateur du rayon aprés insertion
 */
	@Override
	public Integer insert(Rayon rayon) {
		Connection conn = Connexion.getConnection();
		Integer key = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `rayon`(`idSupermarche`, `nomRayon`) VALUES (?,?)");

			ps.setInt(1, rayon.getSuperMarche().getIdSM());
			ps.setString(2, rayon.getTypeRayon().name());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			
			if(keys.next()){
				key = keys.getInt(1);
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
/**
 * Méthode de modification d'un rayon
 * @param rayon Le rayon à modifier
 * @return L'identificateur du rayon aprés modification
 */
	@Override
	public Integer update(Rayon rayon) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `rayon` SET `idSupermarche`=?,`nomRayon`=? WHERE `idRayon` = ?");
			ps.setInt(1, rayon.getSuperMarche().getIdSM());
			ps.setString(2, rayon.getTypeRayon().name());
			ps.setInt(3, rayon.getIdRayon());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rayon.getIdRayon();
	}
/**
 * Méthode de suppression d'un rayon de la base de donnée
 * @param rayon Le rayon à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Rayon rayon) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `rayon` WHERE `idRayon` = ?");
			ps.setInt(1, rayon.getIdRayon());
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
 * Cette méthode retourne  La liste des rayons du supermarché
 * @return renvoie tout les rayons
 */
	@Override
	public List<Rayon> findAll() {
		Connection conn = Connexion.getConnection();
		List<Rayon> listeRayon = new ArrayList<Rayon>();
		try {
			PreparedStatement selRayon = conn.prepareStatement("SELECT * FROM `rayon`");
			ResultSet res = selRayon.executeQuery();
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
				switch (res.getString("nomRayon")) {
				case "ALIMENTAIRE":
					RayonAlimentaire rayonAl = new RayonAlimentaire(res.getInt("idRayon"), smTmp, TypeGeneral.ALIMENTAIRE, null);
					listeRayon.add(rayonAl);
					break;
				case "ELECTROMENAGER":
					RayonElectromenager rayonEl = new RayonElectromenager(res.getInt("idRayon"), smTmp, TypeGeneral.ELECTROMENAGER, null);
					listeRayon.add(rayonEl);
					break;
				case "ENTRETIENMAISONSLINGE":
					RayonEntretienMaisonLinge rayonEn = new RayonEntretienMaisonLinge(res.getInt("idRayon"), smTmp, TypeGeneral.ENTRETIENMAISONSLINGE, null);
					listeRayon.add(rayonEn);
					break;
				case "HYGIENEBEAUTE":
					RayonHygieneBeaute rayonH = new RayonHygieneBeaute(res.getInt("idRayon"), smTmp, TypeGeneral.HYGIENEBEAUTE, null);
					listeRayon.add(rayonH);
					break;
				case "MODE":
					RayonMode rayonM = new RayonMode(res.getInt("idRayon"), smTmp, TypeGeneral.MODE, null);
					listeRayon.add(rayonM);
					break;
				case "MULTIMEDIA":
					RayonMultimedia rayonMu = new RayonMultimedia(res.getInt("idRayon"), smTmp, TypeGeneral.MULTIMEDIA, null);
					listeRayon.add(rayonMu);
					break;

				default:break;
				}
				recupSM.close();
			}
			selRayon.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeRayon;
	}

}