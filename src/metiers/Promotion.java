package metiers;

import java.util.List;

import donnees.PromotionDAO;
/**
 * Cette classe représente les promotions des produits des supermarché
 * @author faredj
 *
 */
public class Promotion {
	/**
	 * L'idPromotion est modifiable
	 * @see Promotion#getIdPromotion()
	 * @see Promotion#setIdPromotion(Integer)
	 */
	private Integer idPromotion;
	/**
	 * le SuperMarche est modifiable
	 * @see Promotion#getSuperMarche()
	 * @see Promotion#setSuperMarche(SuperMarche)
	 */
	private SuperMarche superMarche;
	/**
	 * La desProduit est modifiable
	 * @see Promotion#getDesProduit()
	 * @see Promotion#setDesProduit(String)
	 */
	private String desProduit;
	/**
	 * La reduction est modifiable
	 * @see Promotion#getReduction()
	 * @see Promotion#setReduction(Integer)
	 */
	private Integer reduction;
	/**
	 * Constructeur par défaut	
	 */
	public Promotion() {
		super();
	}
	/**
	 * Constructeur de la promotion 	
	 * @param idPromotion L'identificateur de la promotion
	 * @param superMarche le supermarché de la promotion
	 * @param desProduit la désignation du produit de la promotion
	 * @param reduction la reduction de la promotion
	 */
	public Promotion(Integer idPromotion, SuperMarche superMarche, String desProduit, Integer reduction) {
		this.idPromotion = idPromotion;
		this.superMarche = superMarche;
		this.desProduit = desProduit;
		this.reduction = reduction;
	}
	
	/**
	 * Retourne SuperMarche de la Promotion
	 * @return le supermarché de la promotion
	 */
	public SuperMarche getSuperMarche() {
		return superMarche;
	}
	/**
	 * Met à jour de supermarché de la promotion
	 * @param superMarche
	 */
	public void setSuperMarche(SuperMarche superMarche) {
		this.superMarche = superMarche;
	}
	/**
	 * Retourne IdPromotion de la promotion
	 * @return l'identifiacteur de la promotion
	 */
	public Integer getIdPromotion() {
		return idPromotion;
	}
	/**
	 * Met à jour de l'identificateur de la promotion
	 * @param idPromotion
	 */
	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}
	/**
	 * Retourne la DesPromotion de Promotion	
	 * @return la désignation de la promotion
	 */
	public String getDesProduit() {
		return desProduit;
	}
	/**
	 * Met à jour de la désignation de la promotion
	 * @param desProduit
	 */
	public void setDesProduit(String desProduit) {
		this.desProduit = desProduit;
	}
	/**
	 * Retourne Reduction de la Promotion
	 * @return la réduction de la promotion
	 */
	public Integer getReduction() {
		return reduction;
	}
	/**
	 * Met à jour de la réduction de la promotion
	 * @param reduction
	 */
	public void setReduction(Integer reduction) {
		this.reduction = reduction;
	}
	/**
	 * 	
	 * @param des designation de la protion
	 * @return la promotion par désignation
	 */
	public Promotion retPromotionByDes(String des){
		PromotionDAO promoDao = new PromotionDAO();
		List<Promotion> listPromo = promoDao.findAll();
		return listPromo.stream().filter(p->p.getDesProduit().equals(des))
				        .findAny().get();
	}
	
}
	    