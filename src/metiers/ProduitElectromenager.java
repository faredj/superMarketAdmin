package metiers;
/**
 * Cette classe représente les produits Electromenager du supermarché
 * @author faredj
 *
 */
public class ProduitElectromenager extends Produit{
/**
 * Constructeur par défaut
 */
	public ProduitElectromenager() {
		super();
		this.setType(TypeGeneral.ELECTROMENAGER);
	}
/**
 * Constructeur des produits electromnagers du supermarché
 * @param idProduit l'identificateur du produit Electromenger
 * @param etalage l'étalage du produit Electromenger
 * @param stock le stock du produit Electromenger
 * @param designation la désignation du produit Electromenger
 * @param vente la vente du produit Electromenger
 * @param type le type du produit Electromenger
 * @param prix le prix du produit Electromenger
 * @param quantite la quantité du produit Electromenger
 * @param datePeremption la date de peremption du produit Electromenger
 * @param dateEntreStock la date d'entrée en stock du produit Electromenger
 * @param dateDeVente La date du vente de produit electromenager
 */
	public ProduitElectromenager(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.ELECTROMENAGER);

	}

}
