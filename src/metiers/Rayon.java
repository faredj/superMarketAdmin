package metiers;

import java.util.*;
import java.util.stream.Collectors;

import donnees.EtalageDAO;
/**
 * Cette calsse représente les rayons du supermarché
 * @author soria
 *
 */
public abstract class Rayon {
/**
 * L'Identificateur du Rayon est modifiable
 * @see Rayon#getIdRayon()
 * @see Rayon#setIdRayon(Integer)
 */
private Integer idRayon;
/**
 * Le superMarché du Rayon est modifiable
 * @see SuperMarche
 * @see Rayon#getSuperMarche()
 * @see Rayon#setSuperMarche(SuperMarche)
 */
	private SuperMarche superMarche;
/**
 *Le type du Rayon est modifiable
 *@see TypeGenarl
 *@see Rayon#getTypeRayon()
 *@see Rayon#setTypeRayon(TypeGeneral) 
 */
	private TypeGeneral typeRayon;
/**
 * La liste des etalages du Rayon
 * @see Rayon#getEtalages()
 * @see Rayon#setEtalages(List)
 */
   private List<Etalage> etalages;
  /**
   * Constructeur par défaut
   */
    	public Rayon() {
		super();
	}
 /**
  * 	Constructeur du Rayon du supermarché
  * @param idRayon L'identificateur rayon
  * @param superMarche Le supermarché
  * @param typeRayon Le type du rayon
  * @param etalages Les étalages
  */
	public Rayon(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super();
		this.idRayon = idRayon;
		this.superMarche = superMarche;
		this.typeRayon = typeRayon;
		this.etalages = etalages;
	}
	//Getters et Setters
/**
 * Retourner l'IdRayon du Rayon
 * @return L'identificateur du rayon sous forme d'un entier
 */
	public Integer getIdRayon() {
		return idRayon;
	}
/**
 * Met à jour de lIdRayon du rayon
 * @param idRayon
 */
	public void setIdRayon(Integer idRayon) {
		this.idRayon = idRayon;
	}
/**
 * Retourner le SuperMarche du rayon
 * @return Le super marché du rayon sous forme d'une chaine de caractére
 */
	public SuperMarche getSuperMarche() {
		return superMarche;
	}
/**
 * Met à jour du SuperMarche du rayon
 * @param superMarche
 */
	public void setSuperMarche(SuperMarche superMarche) {
		this.superMarche = superMarche;
	}
/**
 * Retourner le TypeRayon du rayon
 * @return le type du rayonsous forme d'une chaine de caractére
 */
	public TypeGeneral getTypeRayon() {
		return typeRayon;
	}
/**
 * Met à jour du TypeRayon du rayon
 * @param typeRayon
 */
	public void setTypeRayon(TypeGeneral typeRayon) {
		this.typeRayon = typeRayon;
	}
/**
 * Retourne les Etalages du rayon
 * @return les étalages du rayon sous forme d'une liste
 */
	public List<Etalage> getEtalages() {
		return etalages;
	}
/**
 * Met à jour des Etalages du rayon
 * @param etalages
 */
	public void setEtalages(List<Etalage> etalages) {
		this.etalages = etalages;
	}
/**
 * Retourne retProduitRayonByDes du Rayon
 * @param des Désignation des produits du rayon
 * @return les produits du rayon par désignation
 */
	public List<Produit> retProduitRayonByDes(String des){
		return this.getEtalages().stream()
				   .filter(e->e.getRayon().getIdRayon().compareTo(this.getIdRayon()) == 0)
				   .map(e->e.getProduits())
				   .flatMap(p->p.stream().filter(p1->p1.getDesignation().equals(des)))
				   .collect(Collectors.toList());
	}
/**
 * Retourne retQuantiteProduitRayonByDes du Rayon
 * @param des Designation des produits du rayon
 * @return La quantité des produits dans le rayon par désignation
 */
	public Integer retQuantiteProduitRayonByDes(String des){
		return this.getEtalages().stream()
				   .filter(e->e.getRayon().getIdRayon().compareTo(this.getIdRayon()) == 0)
				   .map(e->e.getProduits())
				   .flatMap(p->p.stream().filter(p1->p1.getDesignation().equals(des)))
				   .mapToInt(p2->p2.getQuantite())
				   .sum();
	}
/**
 * Ajout des étalages
 * @param nb nombre d'étalage
 */
	public void ajouterEtalages(Integer nb) {
		EtalageDAO etalageDao = new EtalageDAO();
		List<Etalage> liE = new ArrayList<Etalage>();
		for (Integer i = 0; i < nb; i++){
			Etalage e = new Etalage();
			e.setRayon(this);
			e.setProduits(new ArrayList<Produit>());
			Integer idEt = etalageDao.insert(e);
			e.setIdEtalage(idEt);
			liE.add(e);
		}
		this.setEtalages(liE);
	}
/**
 * 	Retourne retEtalagesOfRayon du rayon
 * @param le liste des etalages
 * @return les  étalages du rayon
 */
	public List<Etalage> retEtalagesOfRayon(List<Etalage> le){
		return le.stream()
				 .filter(e->e.getRayon().getIdRayon().compareTo(this.getIdRayon()) == 0)
				 .collect(Collectors.toList());
	}
}