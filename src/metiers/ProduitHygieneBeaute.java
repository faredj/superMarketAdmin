package metiers;
/**
 * Cette classe représente les produits hygiene beauté du supermarché
 * @author faredj
 *
 */

public class ProduitHygieneBeaute extends Produit{
/**
 * Constructeur par défaut
 */
	public ProduitHygieneBeaute() {
		super();
		this.setType(TypeGeneral.HYGIENEBEAUTE);
	}
/**
 * Constructeur des produits hygiene beauté du supermarché
 * @param idProduit l'identificateur du produit hygiéne de beauté
 * @param etalage l'étalage du produit hygiéne de beauté 
 * @param stock le stock  du produit hygiéne de beauté
 * @param vente la vente du produit hygiéne de beauté
 * @param designation la désignation du produit hygiéne de beauté
 * @param type le type du produit hygiéne de beauté
 * @param prix le prix du produit hygiéne de beauté
 * @param quantite la quantité du produit hygiéne de beauté
 * @param datePeremption la date de peremption du produit hygiéne de beauté
 * @param dateEntreStock la date d'entrée en stock du produit hygiéne de beauté
 * @param dateDeVente la date de vente du produit hygiéne de beauté
 */
	public ProduitHygieneBeaute(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.HYGIENEBEAUTE);
	}

}
