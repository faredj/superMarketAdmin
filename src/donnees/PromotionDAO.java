package donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import metiers.*;
/**
 * Cette classe  représente les opérations effectuées sur la table promotion de la base de données
 * @author faredj
 *
 */
public class PromotionDAO implements DaoTemplate<Integer, Promotion>{
/**
 * Méthode d'insertion d'une promotion à la base de donnée
 * @param promo La promotion à insérer dans la base de donnée
 * @return l'identificateur de la promotion inséré
 */
	@Override
	public Integer insert(Promotion promo) {
		Connection conn = Connexion.getConnection();
		Integer idPromo = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `promotion`(`idSupermarche`, `desProduit`, `reduction`) VALUES (?,?,?)");
			
			ps.setInt(1, promo.getSuperMarche().getIdSM());
			ps.setString(2, promo.getDesProduit());
			ps.setInt(3, promo.getReduction());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idPromo = keys.getInt(1);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idPromo;
	}
/**
 * Méthode de modification d'une promotion
 * @param promo la promotion à modifier
 * @return L'identificateur de la promotion aprés modification
 */
	@Override
	public Integer update(Promotion promo) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `promotion` SET `idSupermarche`=?,`desProduit`=?,`reduction`=? WHERE `idPromotion` = ?");
			ps.setInt(1, promo.getSuperMarche().getIdSM());
			ps.setString(2, promo.getDesProduit());
			ps.setInt(3, promo.getReduction());
			ps.setInt(4, promo.getIdPromotion());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promo.getIdPromotion();
	}
/**
 * Méthode de suppression d'une promotion
 * @param promo La promotion à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Promotion promo) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `promotion` WHERE `idPromotion` = ?");
			ps.setInt(1, promo.getIdPromotion());
			ps.executeUpdate();
			ps.close();
			exec = 1;
		} catch (Exception e) {
			e.printStackTrace();
			exec = -1;
		}
		return exec;
	}
/**
 * Cette méthode retourne  La liste des promotions au supermarché
 * @return renvois toutes les promotions 
 */
	@Override
	public List<Promotion> findAll() {
		Connection conn = Connexion.getConnection();
		List<Promotion> listePromo = new ArrayList<Promotion>();
		try {
			PreparedStatement selPromo = conn.prepareStatement("SELECT * FROM `promotion`");
			ResultSet res = selPromo.executeQuery();
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
				Promotion pTmp = new Promotion(res.getInt("idPromotion"), smTmp, res.getString("desProduit"), res.getInt("reduction"));
				listePromo.add(pTmp);
				recupSM.close();
			}
			selPromo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listePromo;
	}
}
