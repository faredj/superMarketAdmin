package donnees;

import java.sql.*;
import java.util.*;
import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table chaine de supermarché de la base de données
 * @author soria
 *
 */
public class ChaineSmDAO implements DaoTemplate<Integer, ChaineSM>{
/**
 * Méthode d'insertion à la base de données
 * @param chaineSM La chaine de supermarché à insérer
 * @return l'identificateur de la chaine insérée
 */
	@Override
	public Integer insert(ChaineSM chaineSM) {
		Integer idEns = null;
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement verifSM = conn.prepareStatement("SELECT * FROM `ChaineSM` WHERE `nomEnsigne`= ? ");
			verifSM.setString(1, chaineSM.getNomEnsigne());
			ResultSet res = verifSM.executeQuery();
			if(res.next()){
				System.out.println(">>> Ensigne déjà existante !");
			}else{
				PreparedStatement ps = conn.prepareStatement("INSERT INTO `ChaineSM`(`nomEnsigne`) VALUES (?)");
				ps.setString(1, chaineSM.getNomEnsigne());
				ps.executeUpdate();
				
				ResultSet keys = ps.getGeneratedKeys();
				if(keys.next()) {
					idEns = keys.getInt(1);
				}
				ps.close();
			}
			verifSM.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idEns;
	}
/**
 * Méthode de modification d'une chaine de supermarché
 * @param chaineSM La chaine supermarché à modifier
 * @return L'identificateur de la chaine aprés modification
 */
	@Override
	public Integer update(ChaineSM chaineSM) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `ChaineSM` SET `nomEnsigne`=? WHERE 1 `idEnsigne`=?");
			ps.setString(1, chaineSM.getNomEnsigne());
			ps.setInt(2, chaineSM.getidEnseigne());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chaineSM.getidEnseigne();
	}
/**
 * Méthode de suppression d'une chaine de supermaché
 * @param chaineSM La chaine supermarché à supprimer
 * @return 1 si l'éxcution est correcte, si non il retourne -1
 */
	@Override
	public Integer delete(ChaineSM chaineSM) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `ChaineSM` WHERE `idEnsigne` = ?");
			ps.setInt(1, chaineSM.getidEnseigne());
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
 * Cette méthode retourne  La liste des chaine de supermarché
 * @return toutes les chaine existantes
 */
	@Override
	public List<ChaineSM> findAll() {
		Connection conn = Connexion.getConnection();
		List<ChaineSM> listeChaine = new ArrayList<ChaineSM>();
		try {
			PreparedStatement selChaine = conn.prepareStatement("SELECT * FROM `ChaineSM`");
			ResultSet res = selChaine.executeQuery();
			while (res.next()) {
				ChaineSM chaine = new ChaineSM(res.getInt("idEnsigne"), res.getString("nomEnsigne"), new ArrayList<SuperMarche>());
				listeChaine.add(chaine);
			}
			selChaine.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeChaine;
	}

}