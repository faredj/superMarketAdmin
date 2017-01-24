package donnees;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metiers.*;
/**
 * Cette classe représente les opérations effectuées sur la table stock de la base de données
 * @author soria
 *
 */
public class StockDAO implements DaoTemplate<Integer, Stock>{
/**
 * Méthode d'insertion d'une vente à la base de donnée
 * @param stock Le stock à insérer
 * @return l'identificateur du stock aprés insertion
 */
	@Override
	public Integer insert(Stock stock) {

		Connection conn = Connexion.getConnection();
		Integer idSt = null;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `stock`(`idSupermarche`) VALUES (?)");
				
			ps.setInt(1, stock.getSuperMarche().getIdSM());
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			if(keys.next()) {
				idSt = keys.getInt(1);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSt;
	}
/**
 * Méthode de modification d'un stock
 * @param stock le stock à modifier
 * @return L'identificateur du stock aprés modification
 */
	@Override
	public Integer update(Stock stock) {
		Connection conn = Connexion.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE `stock` SET `idSupermarche`=? WHERE  `idRayon` = ?");
			ps.setInt(1, stock.getSuperMarche().getIdSM());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stock.getIdStock();
	}
/**
 * Méthode de suppression d'un stock
 * @param stock Le stock à supprimer
 * @return 1 si l'éxcution est correcte si non il retourne -1
 */
	@Override
	public Integer delete(Stock stock) {
		Connection conn = Connexion.getConnection();
		Integer exec = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `stock` WHERE `idStock` = ?");
			ps.setInt(1, stock.getIdStock());
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
 * Cette méthode retourne  La liste des stocks du supermarché
 * @return renvoie tout les stocks 
 */
	@Override
	public List<Stock> findAll() {
		Connection conn = Connexion.getConnection();
		List<Stock> listeStock = new ArrayList<Stock>();
		try {
			PreparedStatement selStock = conn.prepareStatement("SELECT * FROM `stock`");
			ResultSet res = selStock.executeQuery();
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
				Stock stTmp = new Stock(res.getInt("idStock"), smTmp, null);
				listeStock.add(stTmp);
				recupSM.close();
			}
			selStock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeStock;
	}
}
