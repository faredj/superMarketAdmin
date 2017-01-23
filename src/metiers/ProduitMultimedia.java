package metiers;
/**
 * Cette classe représente les produits multimedia du supermarché
 * @author faredj
 *
 */

public class ProduitMultimedia extends Produit{
/**
 * Constructeur par défaut
 */
	public ProduitMultimedia() {
		super();
		this.setType(TypeGeneral.MULTIMEDIA);
	}
/**
 * Constructeur des produits multimedia du supermarché
 * @param idProduit l'identificateur du produit multimedia du supermarché 
 * @param etalage l'étalage du produit multimedia du supermarché
 * @param stock le stock du produit multimedia du supermarché
 * @param vente la vente du produit multimedia du supermarché
 * @param designation la désignation du produit multimedia du supermarché
 * @param type le type u produit multimedia du supermarché
 * @param prix le prix du produit multimedia du supermarché
 * @param quantite la quantité du produit multimedia du supermarché
 * @param datePeremption la date de peremption du produit multimedia du supermarché
 * @param dateEntreStock la date d'entrée en stock du produit multimedia du supermarché
 * @param dateDeVente la date de vente du produit multimedia du supermarché
 */
	public ProduitMultimedia(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.MULTIMEDIA);
	}

}
