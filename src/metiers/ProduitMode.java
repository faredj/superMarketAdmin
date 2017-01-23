package metiers;
/**
 *Cette classe représente les produits mode du supermarché 
 * @author faredj
 *
 */

public class ProduitMode extends Produit{
/**
 * Constructeur par défaut
 */
	public ProduitMode() {
		super();
		this.setType(TypeGeneral.MODE);
	}
/**
 * Constructeur des produits Mode du supermarché
 * @param idProduit l'identificateur du produit Mode du supermarché
 * @param etalage l'étalage du produit Mode du supermarché
 * @param stock le stock du produit Mode du supermarché
 * @param vente la vente du produit Mode du supermarché
 * @param designation la désignation Mode du supermarché
 * @param type le type du produit Mode du supermarché
 * @param prix le prix du produit Mode du supermarché
 * @param quantite la quantité du produit Mode du supermarché
 * @param datePeremption la date de peremption Mode du supermarché
 * @param dateEntreStock la date d'entrée en stock Mode du supermarché
 * @param dateDeVente la date de vente du produit Mode du supermarché
 */
	public ProduitMode(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.MODE);
	}

}
