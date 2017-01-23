package metiers;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Cette classe représente les ventes des supermarchés
 * @author faredj
 *
 */
public class Vente {
	/**
	 * lidVente est modifiable
	 * @see Vente#getidVente()
	 * @see Vente#setidVente(Integer)
	 */
	private Integer idVente;
	/**
	 * Le supermarché est modifiable
	 * @see Vente#getSuperMarche()
	 * @see Vente#setSuperMarche(SuperMarche)
	 */
	private SuperMarche superMarche;
	/**
	 * La liste des produits vendus
	 * @see Vente#getProduits()
	 * @see Vente#setIdVente(Integer)
	 */
	private List<Produit> produits;
	/**
	 *Constructeur par défaut 
	 */
	public Vente() {
		super();
	}
	/**
	 * 	Constructeur de vente des produits
	 * @param idVente l'identificateur de la vente
	 * @param superMarche le supermarché de la vente
	 * @param produits les produits de vente
	 */
	public Vente(Integer idVente,SuperMarche superMarche, List<Produit> produits) {
		this.idVente = idVente;
		this.superMarche = superMarche;
		this.produits = produits;
	}
	/**
	 * Retourne l'IdVente de vente
	 * @return l'identificateur de vente sous forme d'un entier
	 */
	public Integer getIdVente() {
		return idVente;
	}
	/**
	 * Met à jour de l'IdVente
	 * @param idVente
	 */
	public void setIdVente(Integer idVente) {
		this.idVente = idVente;
	}
	/**
	 * Retourne SuperMarchede Vente
	 * @return le supermarché de la vente
	 */
	public SuperMarche getSuperMarche() {
		return superMarche;
	}
	/**
	 * Met à jour du SuperMarche
	 * @param superMarche
	 */
	public void setSuperMarche(SuperMarche superMarche) {
		this.superMarche = superMarche;
	}
	/**
	 * Retourne Produit de Vente
	 * @return La liste des produits de vente
	 */
	public List<Produit> getProduits() {
		return produits;
	}
	/**
	 * Met à jour des produits de vente
	 * @param produits
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	/**
	 * Retourne retProduitOfVente de Vente
	 * @param lp Liste de produit de vente
	 * @return La liste des produits de vente du supermarché
	 */
	public List<Produit> retProduitOfVente(List<Produit> lp){
		return lp.stream()
				 .filter(p->p.getVente() != null)
				 .filter(p1->p1.getVente().getIdVente().compareTo(this.getIdVente()) == 0)
				 .collect(Collectors.toList());
	}
}