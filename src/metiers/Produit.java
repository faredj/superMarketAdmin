package metiers;

import java.util.*;

import donnees.ProduitDAO;
import donnees.PromotionDAO;
/**
 * Cette classe représente les produits des supermarché
 * @author faredj
 *
 */
public abstract class Produit {
/**
 * L'IdProduit du produit est modifiable
 * @see Produit#getIdProduit()
 * @see Produit#setIdProduit(Integer)
 */
	private Integer idProduit;
/**
 * L'etakage du produit est modifiable
 * @see Produit#getEtalage()
 * @see Produit#setEtalage(Etalage)
 */
	private Etalage etalage;
/**
 * Le stock est modifiable
 * @see Produit#getStock()
 * @see Produit#setStock(Stock)
 */
	private Stock stock;
/**
 * La vente est modifiable
 * @see Produit#getVente()
 * @see Produit#setVente(Vente)
 */
	private Vente vente;
/**
 * La désignation du produit est modifiable
 * @see Produit#getDesignation()
 * @see Produit#setDesignation(String)
 */
	private String designation;
/**
 * Le type du produit est modifiable
 * @see Produit#getType()
 * @see Produit#setType(TypeGeneral)
 */
	private TypeGeneral type;
/**
 * Le prix du produit est modifiable
 * @see Produit#getPrix()
 * @see Produit#setPrix(Double)
 */
	private Double prix;
/**
 * La quantité du produit est modifiable
 * @see Produit#getQuantite()
 * @see Produit#setQuantite(Integer)
 */
	private Integer quantite;
/**
 * La date de peremption est modifiable
 * @see Produit#getDatePeremption()
 * @see Produit#setDatePeremption(String) 
 */
	private String datePeremption;
/**
 * La date d'entrée en stock est modifiable
 * @see Produit#getDateEntreStock()
 * @see Produit#setDateEntreStock(String)
 */
	private String dateEntreStock;
/**
 * La date de vente est modifiable
 * @see Produit#getDateDeVente()
 * @see Produit#setDateDeVente(String)
 */	
	private String dateDeVente;
/**
 * Constructeur par défaut	
 */
	public Produit() {
		super();
	}
/**
 * Constructeur des produits
 * @param idProduit L'Identifiacteur du produit
 * @param etalage L'étalage du produit
 * @param stock Le stock du produit
 * @param vente La vente du produit
 * @param designation La désignation du produit 
 * @param type Le type du produit
 * @param prix Le prix du produit
 * @param quantite La quantité du produit
 * @param datePeremption La date de peremption du produit
 * @param dateEntreStock La date d'entrée en stock du produit
 * @param dateDeVente La date de vente du produit
 */	
	public Produit(Integer idProduit, Etalage etalage, Stock stock, Vente vente, String designation, TypeGeneral type, Double prix, Integer quantite, String datePeremption, String dateEntreStock, String dateDeVente) {
		this.idProduit = idProduit;
		this.etalage = etalage;
		this.stock = stock;
		this.vente = vente;
		this.designation = designation;
		this.type = type;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremption = datePeremption;
		this.dateEntreStock = dateEntreStock;
		this.dateDeVente = dateDeVente;
	}


	//Getters et Setters
/**
 * Retourne l'IdProduit du Produit
 * @return L'identificateur du produit sous forme d'un entier
 */
	public Integer getIdProduit() {
		return idProduit;
	}
/**
 * Met à jour de l'identificateur du produit
 * @param idProduit
 */
	public void setIdProduit(Integer idProduit) {
     	this.idProduit = idProduit;
	}
/**
 * Retourne l'Etalage du Produit
 * @return L'etalage du produit sous forme d'une chaine de caractére
 */
	public Etalage getEtalage() {
		return etalage;
	}
/**
 * Met à jour de l'etalage de produit
 * @param etalage
 */
	public void setEtalage(Etalage etalage) {
		this.etalage = etalage;
	}
 /**
 * Retourne le Stock du Produit
 * @return Le stock du produit sous forme d'une chaine de caractére
 */
	public Stock getStock() {
		return stock;
	}
/**
 * Met à jour du stock de produit
 * @param stock
 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
/**
 * Retourne la Vente du Poduit
 * @return La vente du produit sous forme d'une chaine de caractére
 */
	public Vente getVente() {
		return vente;
	}
/**
 * Met à jour de la Vente du Produit
 * @param vente
 */
	public void setVente(Vente vente) {
		this.vente = vente;
	}
/**
 * Retourne la Designation du Produit
 * @return La désignation du produit sous forme d'une chaine de caractére
 */
	public String getDesignation() {
		return designation;
	}
/**
 * Met à jour du désignation de produit
 * @param designation
 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
/**
 * Retourne le TypeGenral du Produit
 * @return Le type general du produit sous forme d'une chaine de caractére
 */
	public TypeGeneral getType() {
		return type;
	}
	/**
 * Met à jour du type general du produit
 * @param type
 */
	public void setType(TypeGeneral type) {
		this.type = type;
	}
/**
 * Retourne le Prix du Produit
 * @return Le prix du produit sous forme de dOUBLE
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Met à jour du prix de produit
 * @param prix
 */
	public void setPrix(Double prix) {
    	this.prix = prix;
	}
/**
 * Retourne la Quantité du Produit
 * @return L a quantité du produit sous forme d'un entier
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Met à jour de la quantité du produit
 * @param quantite
 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
/**
 * Retourne la DatePeremption du Produit
 * @return la date de peremption du produit sous forme d'une cahine de caractére
 */
	public String getDatePeremption() {
		return datePeremption;
	}
/**
 * Met à jour de la date de peremption du produit
 * @param datePeremption
 */
	public void setDatePeremption(String datePeremption) {
		this.datePeremption = datePeremption;
	}
/**
 * Retourne la DateEntreStck du produit
 * @return la date d'entrée en stcok du produit sous forme d'une chaine de caractére
 */
	public String getDateEntreStock() {
		return dateEntreStock;
	}
/**
 * Met à jour de la date d'entrée en stock
 * @param dateEntreStock
 */
	public void setDateEntreStock(String dateEntreStock) {
		this.dateEntreStock = dateEntreStock;
	}
/**
 * Retourne la DateDeVente du Produit
 * @return La date de vente du produit
 */
	public String getDateDeVente() {
		return dateDeVente;
	}
/**
 * Met à jour de la date de vente du produit
 * @param dateDeVente
 */
	public void setDateDeVente(String dateDeVente) {
		this.dateDeVente = dateDeVente;
	}
/**
 * Redéfinition de la méthode equals
 * @param obj
 * @return si ce roduit est équvalent a celui en paramétre renvoi true sinon renvoi false
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		return true;
	}
	/**
	 * Editer une liste de produit
	 * @param lp liste de produit à éditer
	 */
	public void editListProduit(List<Produit> lp){
		ProduitDAO produitDao = new ProduitDAO();
		for (Produit produit : lp) {
			produit.setPrix(this.getPrix());
			produitDao.update(produit);
		}
	}
	/**
	 * Supprimer une liste de produit
	 * @param lp liste de produit à supprimer
	 */
	public void deleteListProduit(List<Produit> lp){
		ProduitDAO produitDao = new ProduitDAO();
		for (Iterator<Produit> iterator = lp.iterator(); iterator.hasNext();) {
			Produit produit = iterator.next();
			if(produit.getEtalage() != null){
				produitDao.delete(produit);
				Etalage etalage = produit.getEtalage();
				etalage.getProduits().remove(produit);
			}
			iterator.remove();
		}
	}
	/**
	 * Dupliquer un objet produit
	 * @return retourne le clone de ce produit
	 */
	public Produit cloneObject(){
		Produit clone = null;
		switch (this.getType()) {
		case ALIMENTAIRE:
			clone = new ProduitAlimentaire(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.ALIMENTAIRE, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		case ELECTROMENAGER:
			clone = new ProduitElectromenager(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.ELECTROMENAGER, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		case ENTRETIENMAISONSLINGE:
			clone = new ProduitEntretienMaison(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.ENTRETIENMAISONSLINGE, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		case HYGIENEBEAUTE:
			clone = new ProduitHygieneBeaute(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.HYGIENEBEAUTE, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		case MODE:
			clone = new ProduitMode(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.MODE, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		case MULTIMEDIA:
			clone = new ProduitMultimedia(this.getIdProduit(), this.getEtalage(), this.getStock(), this.getVente(), this.getDesignation(), TypeGeneral.MULTIMEDIA, 
					this.getPrix(), this.getQuantite(), this.getDatePeremption(), this.getDateEntreStock(), this.getDateDeVente());
			break;
		default:break;
		}
		return clone;
    }
	/**
	 * Vérifie si ce produit est en promotion
	 * @return retourne true si le produit est en promo et false sinon
	 */
	public boolean estEnPromo(){
		PromotionDAO promoDao = new PromotionDAO();
		List<Promotion> listPromo = promoDao.findAll();
		return listPromo.stream().filter(p->p.getSuperMarche() != this.getEtalage().getRayon().getSuperMarche())
								 .map(p1->p1.getDesProduit())
								 .anyMatch(this.getDesignation()::equalsIgnoreCase);
	}
	/**
	 * Retourne la réduction de ce produit
	 * @return retourne -1 si le produit n'est pas en promotion et la reduction s'il est en promotion
	 */
	public Integer retReduction(){
		Integer reduc = -1;
		if(this.estEnPromo()){
			PromotionDAO promoDao = new PromotionDAO();
			List<Promotion> listPromo = this.getEtalage().getRayon().getSuperMarche().retPromoOfSuperMarche(promoDao.findAll());
			Iterator<Promotion> itP = listPromo.iterator();
			while (itP.hasNext()) {
				Promotion promotion = itP.next();
				if(promotion.getDesProduit().equals(this.getDesignation())){
					reduc = promotion.getReduction();
				}
			}
		}
		return reduc;
	}
}
