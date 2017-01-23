package metiers;

/**
 * Cette classe représente  les produits alimentaire du supermarché
 * @author faredj
 *
 */

public class ProduitAlimentaire extends Produit{
/**
 * Constructeur par défaut
 */
	public ProduitAlimentaire() {
		super();
		this.setType(TypeGeneral.ALIMENTAIRE);
	}
/**
 * Constructeur des produits alimentaire d'un supermarché
 * @param idProduit l'identificateur du produit alimentaire
 * @param etalage l'étalage du produit alimentaire
 * @param stock le stock du produit alimentaire
 * @param vente La vente du produit alimenatire
 * @param designation l désignation du produit alimentaire
 * @param type le type du produit alimentaire
 * @param prix le prix du produit alimentaire
 * @param quantite la quantité du produit alimentaire
 * @param datePeremption la date peremption du produit alimentaire
 * @param dateEntreStock la date d'entrée en stock du produit alimentaire
 * @param dateDeVente la date de vente du produit alimentaire
 */

	public ProduitAlimentaire(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.ALIMENTAIRE);
	}
	
}
