package donnees;
import java.sql.*;
import java.util.*;

import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table Produit de la base de données
 * @author faredj
 *
 */
public class ProduitDAO implements DaoTemplate<Integer, Produit>{
/**
 * Méthode d'insertion à la base de donnée
 * @param produit le produit à insérer dans la base de donnée
 * @return l'identificateur du produit iséré
 */
	@Override
	public Integer insert(Produit produit) {
		Connection conn = Connexion.getConnection();
		Integer idPr = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `produit`(`idEtalage`, `idStock`, `idVente`, `designation`, `typeProduit`, `prix`, `quantite`, `datePeremption`, `dateEntreStock`, `dateDeVente`) VALUES (?,?,?,?,?,?,?,?,?,?)");
			if(produit.getEtalage()!=null){
				ps.setInt(1, produit.getEtalage().getIdEtalage());
				ps.setNull(2, Types.INTEGER);
				ps.setNull(3, Types.INTEGER);
			}else if(produit.getStock()!=null){
				ps.setNull(1, Types.INTEGER);
				ps.setInt(2, produit.getStock().getIdStock());
				ps.setNull(3, Types.INTEGER);
			}else{
				ps.setNull(1, Types.INTEGER);
				ps.setNull(2, Types.INTEGER);
				ps.setInt(3, produit.getVente().getIdVente());
			}
			ps.setString(4, produit.getDesignation());
			ps.setString(5, produit.getType().getTypeName());
			ps.setDouble(6, produit.getPrix());
			ps.setInt(7, produit.getQuantite());
			ps.setString(8, produit.getDatePeremption());
			ps.setString(9, produit.getDateEntreStock());
			ps.setString(10, produit.getDateDeVente());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idPr = keys.getInt(1);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idPr;
	}
/**
 * Méthode de modification d'un produit
 * @param produit Le produit à modifier
 * @return L'identificateur du produit aprés modification
 */
	@Override
	public Integer update(Produit produit) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `produit` SET `idEtalage`=?,`idStock`=?,`idVente`=?,`designation`=?,`typeProduit`=?,`prix`=?,`quantite`=?,`datePeremption`=?,`dateEntreStock`=?,`dateDeVente`=? WHERE `idProduit`=?");
			if(produit.getEtalage()!=null){
				ps.setInt(1, produit.getEtalage().getIdEtalage());
				ps.setNull(2, Types.INTEGER);
				ps.setNull(3, Types.INTEGER);
			}else if(produit.getStock()!=null){
				ps.setNull(1, Types.INTEGER);
				ps.setInt(2, produit.getStock().getIdStock());
				ps.setNull(3, Types.INTEGER);
			}else{
				ps.setNull(1, Types.INTEGER);
				ps.setNull(2, Types.INTEGER);
				ps.setInt(3, produit.getVente().getIdVente());
			}
			ps.setString(4, produit.getDesignation());
			ps.setString(5, produit.getType().getTypeName());
			ps.setDouble(6, produit.getPrix());
			ps.setInt(7, produit.getQuantite());
			ps.setString(8, produit.getDatePeremption().toString());
			ps.setString(9, produit.getDateEntreStock().toString());
			ps.setString(10, produit.getDateDeVente().toString());
			ps.setInt(11, produit.getIdProduit());
			ps.executeUpdate();
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produit.getIdProduit();
	}
/**
 * Méthode de suppression d'un produit
 * @param produit Le produit à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Produit produit) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `produit` WHERE `idProduit`= ?");
			ps.setInt(1, produit.getIdProduit());
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
 * Cette méthode retourne  La liste des produits du supermarché
 * @return renvoi la liste de tout les produits
 */
	@Override
	public List<Produit> findAll() {
		Connection conn = Connexion.getConnection();
		List<Produit> listeProduit = new ArrayList<Produit>();
		try {
			PreparedStatement selProduit = conn.prepareStatement("SELECT * FROM `produit`");
			ResultSet res = selProduit.executeQuery();
			while (res.next()) {
				Produit produit = null;
				switch (res.getString("typeProduit")) {
				case "Alimentaire":
					produit = new ProduitAlimentaire(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				case "Electroménager":
					produit = new ProduitElectromenager(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				case "Entretien maison et linge":
					produit = new ProduitEntretienMaison(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				case "Hygiéne et beauté":
					produit = new ProduitHygieneBeaute(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				case "Mode":
					produit = new ProduitMode(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				case "Multimédia":
					produit = new ProduitMultimedia(res.getInt("idProduit"), null, null, null, res.getString("designation"), TypeGeneral.ALIMENTAIRE, 
							res.getDouble("prix"), res.getInt("quantite"), res.getString("datePeremption"), res.getString("dateEntreStock"), res.getString("dateDeVente"));
					break;
				default:break;
				}
				Integer idEt = res.getObject("idEtalage") != null ? res.getInt("idEtalage") : null;
				Integer idSt = res.getObject("idStock") != null ? res.getInt("idStock") : null;
				
				if(idEt != null){
					PreparedStatement recupEt = conn.prepareStatement("SELECT * FROM `etalage` WHERE `idEtalage`= ? ");
					recupEt.setInt(1, res.getInt("idEtalage"));
					ResultSet etRes = recupEt.executeQuery();
					Etalage etTmp = null;
					while (etRes.next()) {
						etTmp = new Etalage(etRes.getInt("idEtalage"), null, null);
					}
					produit.setEtalage(etTmp);
					produit.setStock(null);
					produit.setVente(null);
					recupEt.close();
				}else if(idSt != null){
					PreparedStatement recupSt = conn.prepareStatement("SELECT * FROM `stock` WHERE `idStock`= ? ");
					recupSt.setInt(1, res.getInt("idStock"));
					ResultSet stRes = recupSt.executeQuery();
					Stock stTmp = null;
					while (stRes.next()) {
						stTmp = new Stock(stRes.getInt("idStock"), null, null);
					}
					produit.setEtalage(null);
					produit.setStock(stTmp);
					produit.setVente(null);
					recupSt.close();
				}else{
					PreparedStatement recupVe = conn.prepareStatement("SELECT * FROM `vente` WHERE `idVente`= ? ");
					recupVe.setInt(1, res.getInt("idVente"));
					ResultSet veRes = recupVe.executeQuery();
					Vente veTmp = null;
					while (veRes.next()) {
						veTmp = new Vente(veRes.getInt("idVente"), null, null);
					}
					produit.setEtalage(null);
					produit.setStock(null);
					produit.setVente(veTmp);
					recupVe.close();
				}
				listeProduit.add(produit);
			}
			selProduit.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeProduit;
	}

}
