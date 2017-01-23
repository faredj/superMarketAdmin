package metiers;
/**
 * Cette classe représente les produits entretiens Maison du supermarché
 * @author faredj
 *
 */
public class ProduitEntretienMaison extends Produit{
/**
 * Constructeur par défaut
 */

	public ProduitEntretienMaison() {
		super();
		this.setType(TypeGeneral.ENTRETIENMAISONSLINGE);
	}
/**
 * Constructeur des produits entretien maison du supermarché
 * @param idProduit l'identicateur du produit entretien maison
 * @param etalage l'étalage du produit entretien maison
 * @param stock le stock du produit entretien maison
 * @param vente la vente du produit entretien maison
 * @param designation la désignation du produit entretien maison
 * @param type le type du produit entretien maison
 * @param prix le prix du produit entretien maison
 * @param quantite la quantité du produit entretien maison
 * @param datePeremption la date peremption du produit entretien maison
 * @param dateEntreStock la date d'entrée en stock du produit entretien maison
 * @param dateDeVente la date de vente du produit
 */
	public ProduitEntretienMaison(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		super(idProduit, etalage, stock, vente, designation, type, prix, quantite, datePeremption, dateEntreStock, dateDeVente);
		this.setType(TypeGeneral.ENTRETIENMAISONSLINGE);
	}

}
