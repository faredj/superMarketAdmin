package metiers;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import donnees.ProduitDAO;
/**
 * Cette classe représente les étalage des supermarché
 * @author faredj
 *
 */
public class Etalage 
{
/**
 * l'idEnsigne de l'étalage est modifiable
 * @see Etalage#getIdEtalage()
 * @see Etalage#setIdEtalage(Integer)
 */
	private Integer idEtalage;
/**
 * le rayon de l'étalage est changeable
 * @see Rayon
 * @see Etalage#getRayon()
 * @see Etalage#setRayon(Rayon)
 */
	private Rayon rayon;
/**
 * la liste produit de l'étalage
 * @see Etalage#getProduits()
 * @see Etalage#setProduits(List)
 */
	private List<Produit> produits;
/**
 * Constructeur par défaut
 */
	public Etalage() {
		super();
	}
/**
 * Constructeur d'un étalage
 * @param idEtalage l'identificateur de l'etalage
 * @param rayon le rayon de l'etalage
 * @param produits la liste des produits de l'étalage
 */
	public Etalage(Integer idEtalage,Rayon rayon, List<Produit> produits) {
		this.idEtalage = idEtalage;
		this.rayon = rayon;
		this.produits = produits;
	}
/**
 * retourner l'IdEtalage de l'étalage
 * @return l'identificateur de l'étalage sous forme d'un entier
 */
	public Integer getIdEtalage() {
		return idEtalage;
	}
/**
 * Met à jour de l'IdEtalage de l'talage
 * @param idEtalage
 */
	public void setIdEtalage(Integer idEtalage) {
		this.idEtalage = idEtalage;
	}
/**
 * Retourner le rayon de l'étalage
 * @return rayon  sous forme d'une chaine de caractére 
 */
	public Rayon getRayon() {
		return rayon;
	}
/**
 * Met à jour  du rayon de l'étalage
 * @param rayon
 */
	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
/**
 * retourner les produits de l'étalage
 * @return Produit sous forme d'une liste
 */
	public List<Produit> getProduits() {
		return produits;
	}
/**
 * Met à jour de la liste des produits
 * @param produits
 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
/**
 * Retourne retProduitsOfEtalage de l' Etalage
 * @param lp liste des produits
 * @return la liste des produits de l'étalage
 */	

	public List<Produit> retProduitsOfEtalage(List<Produit> lp){
		return lp.stream()
				 .filter(p->p.getEtalage() != null)
				 .filter(p2->p2.getEtalage().getIdEtalage().compareTo(this.getIdEtalage()) == 0)
				 .collect(Collectors.toList());
		}
/**
 * 	Efectuer une vente
 * @param produit Le produit de l'étalage
 */
	public void effectuerVente(Produit produit){
		ProduitDAO produitDao = new ProduitDAO();
		String desP = produit.getDesignation().toLowerCase();
		produit.setDesignation(desP);
		boolean existEnEtalage = false;
		for (Produit ps : this.getProduits()) {
			if(produit.getDesignation().equals(ps.getDesignation())){
				if(ps.getQuantite().compareTo(produit.getQuantite()) > 0 || ps.getQuantite().compareTo(produit.getQuantite()) == 0){
					existEnEtalage = true;
					Integer qt = produit.getQuantite();
					ps.setQuantite(ps.getQuantite()-qt);
					produitDao.update(ps);
				}else{
					System.out.println("   >> Quantité insuffisante !");
				}
			}
		}
		if(existEnEtalage){
			produit.setStock(null);
			produit.setEtalage(null);
			produit.setVente(this.getRayon().getSuperMarche().getVente());
			produit.setDateDeVente(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			produitDao.insert(produit);
			List<Produit> lp = new ArrayList<Produit>();
			lp = this.getRayon().getSuperMarche().getVente().getProduits();
			lp.add(produit);
			this.getRayon().getSuperMarche().getVente().setProduits(lp);;
		}
	}
}