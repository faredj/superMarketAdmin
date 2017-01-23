package metiers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Cette classe représente les supermarchés
 * @author soria
 *
 */
public abstract class SuperMarche{
/**
 * l'IdSM du supermarché 
 * @see SuperMarche#getIdSM()
 * @see SuperMarche#setIdSM(Integer)
 */
	private Integer idSM;
/**
 * La chaine supermarché 
 * @see SuperMarche#getChaineSM()
 * @see SuperMarche#setChaineSM(ChaineSM)
 */
	private ChaineSM chaineSM;
/**
 * L'adresse du supermarché 
 * @see SuperMarche#getAdresse()
 * @see SuperMarche#setAdresse(String)
 */
	private String adresse;
/**
 * Le stock du supermarché
 * @see SuperMarche#getStock()
 * @see SuperMarche#setStock(Stock)
 */
	private Stock stock;
/** La vente du supermarché 
 * @see SuperMarche#getVente()
 * @see SuperMarche#setVente(Vente)
 */
	private Vente vente;

	public SuperMarche() {
		super();
	}
/**
 * Constructeur de supermarché
 * @param idSM L'identificateur du supermarché
 * @param chaienSM La chaine des supermarchés
 * @param adresse L'adresse des supermarchés
 * @param stock Le stock des supermarchés
 * @param Vente La vente des produits des supermarchés
 */	
	public SuperMarche(Integer idSM, ChaineSM chaienSM, String adresse, Stock stock, Vente vente) {
		this.idSM = idSM;
		this.chaineSM = chaienSM;
		this.adresse = adresse;
		this.stock = stock;
		
	}
/**
 * Retourner l'IdSm du superMarche
 * @return l'identificateur dU supermarché sous forme d'un entier
 */
	public Integer getIdSM() {
		return idSM;
	}
/**
 * Met à jour de l'identificateur de supermarché
 * @param idSM
 */
	public void setIdSM(Integer idSM) {
		this.idSM = idSM;
	}
/**
 * Retourner la chaineSM du superMarche
 * @return La chaine supermarché sous forme d'une chaine de caractére
 */
	public ChaineSM getChaineSM() {
		return chaineSM;
	}
/**
 * Met à jour de la chaine supermarché
 * @param chaineSM
 */
	public void setChaineSM(ChaineSM chaineSM) {
		this.chaineSM = chaineSM;
	}
/**
 * REtourne l'Adresse du superMarche
 * @return L'adresse du supermarché sous forme d'une chaine de caractére
 */
	public String getAdresse() {
		return adresse;
	}
/**
 * Met à jour de l'adresse du supermarché
 * @param adresse
 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
/**
 * Retourne le Stock du superMarch
 * @return Le stock du supermarché sous forme d'une chaine de caractére
 */
	public Stock getStock() {
		return stock;
	}
/**
 * Met à jour du Stock du supermarché
 * @param Stock
 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
/**
 * @return la liste des rayons
 */
	public <T> T getRayons(){
		return null;
	}
/**
 * @param rayons
 */
	public <T> void setRayons(T rayons){
	}
/**
 * 	Retourne la vente du supermarché
 * @return la vente du supermarché sous forme d'une chaine de caractére
 */
	public Vente getVente() {
		return vente;
	}
/**
 * Met à jour de la vente du supermarché
 * @param vente
 */
	public void setVente(Vente vente) {
		this.vente = vente;
	}
/**
 * Chercher nb de produit dans les rayons par "NOM"
 * @param nom Le nom  du produit dans le rayon
 * @return Le nombre de produit recherché dans le rayon
 */
	public abstract Integer nbProduitEnRayonParNom(String nom);//chercher nb de produit dans les rayons par "NOM"
/**
 * 	Retourne nbProduitEnStockParNom du supermarché
 * @param nom Nom du produit 
 * @return Le nombre des produits par nom en stcock des supermarché
 */	
	public Integer nbProduitEnStockParNom(String nom){//chercher nb produit en stock par nom
		return this.getStock().getProduits()
				   .stream()
				   .filter(p->p.getDesignation() == nom)
				   .mapToInt(Produit::getQuantite)
				   .sum();
	}
	
/**
 *  Met à jour de la liste des produits périmes en Rayons
 * @return
 */
	public abstract Set<Produit> produitPerimesEnRayon();//chercher les produits perimes dans les rayons
/**
 * Retourne produitSelonDateEntreStock du supermarché
 * @param date Date d'entrée du produit en stock
 * @return les produits selon leurs date d'entrée en stock
 */
	public Set<Produit> produitSelonDateEntreStock(Date date){//retourner les produit selon leurs date d'entre en stock
		return this.getStock().getProduits()
				   .stream()
				   .filter(p->p.getDatePeremption().equals(date))
				   .collect(Collectors.toSet());
	}
/**
 * 	Retourne  le stock du supermarchés
 * @param ls Liste des Stock
 * @return Les stocks filtrés des supermarchés
 */
	public Stock retStockOfSuperMarche(List<Stock> ls){
		return ls.stream()
				 .filter(st->st.getSuperMarche().getIdSM() == this.getIdSM())
				 .findAny().get();
	}
/**
 * Retourne les ventes du supermarché
 * @param lv Liste de vente du supermarché
 * @return la liste des ventes du supermarché
 */
	public Vente retVenteOfSuperMarche(List<Vente> lv){
		return lv.stream()
				 .filter(v->v.getSuperMarche().getIdSM().compareTo(this.getIdSM()) == 0)
				 .findAny().get();
	}
/**
 * Retourne rayon du supermarché
 * @param lr Liste des rayons du supermarché
 * @return les rayons du supermarché spécialiste
 */
	public Rayon retRayonOfSuperMarche(List<Rayon> lr){
		return lr.stream()
				 .filter(r->r.getSuperMarche().getIdSM() == this.getIdSM())
				 .findAny().get();
	}
/**
 * Retourne rayons du supermarché 
 * @param lr liste des rayons du supermarché
 * @return la liste des rayons du supermarché généraliste
 */
	public List<Rayon> retRayonsOfSuperMarche(List<Rayon> lr){
		return lr.stream()
				 .filter(r->r.getSuperMarche().getIdSM() == this.getIdSM())
				 .collect(Collectors.toList());
	}

/**
 * Chercher la liste des produit dans le superMarché par "PRIX MAX"
 * @param prixMax le prix maximun des produits dans le rayon
 * @return Liste des produits qui ont le prix maximum dans le supermarché
 */
	public abstract Set<Produit> produitEnRayonParPrixMax(Double prixMax);//chercher liste produit dans le SM par "PRIX MAX"
/**
 * Chercher liste produit dans le SM par "PRIX MIN"
 * @param prixMin le prix minimum des produits dans le rayon
 * @return Liste des produits qui ont le prix minimum dans le supermarché
 */
	public abstract Set<Produit> produitEnRayonParPrixMin(Double prixMin);//chercher liste produit dans le SM par "PRIX MIN"
/**
 * Retourne promotions du supermarché
 * @param lr liste de promotions des produits du supermarché
 * @return la liste des produits en promotions des supermarchés généralistes
 */
	public List<Promotion> retPromoOfSuperMarche(List<Promotion> lr){
		return lr.stream()
				 .filter(p->p.getSuperMarche().getIdSM() == this.getIdSM())
				 .collect(Collectors.toList());
	}
/**
 * Retourne produits vendu du supermarché
 * @param lp Liste des produits
 * @param des Désignation du produit
 * @return La liste des produits vendu dans le supermarché par désignation
 */
	public List<Produit> retProduitVenduParDes(List<Produit> lp, String des){
		return lp.stream()
				 .filter(p->p.getDesignation().equals(des))
				 .collect(Collectors.toList());
	}
/**
 * Retourne les ventes par intervale de temps du supermarché
 * @param lp liste de toutes les ventes
 * @param date vente (debut)
 * @param date vente (fin)
 * @return Les produits par interval de temps
 * @throws ParseException
 */
	public List<Produit> retProduitParInterValTemp(List<Produit> lp, String dateDebut, String dateFin) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = df.parse(dateDebut);
        Date endDate = df.parse(dateFin);
        List<Produit> lpRet = new ArrayList<Produit>();
		for (Produit produit : lp) {
			Date dateOfp = df.parse(produit.getDateDeVente());
			if(dateOfp.after(startDate) && dateOfp.before(endDate))
				lpRet.add(produit);
		}
		return lpRet;
	}
}
