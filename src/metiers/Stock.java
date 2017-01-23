package metiers;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import donnees.*;
/**
 * Cette classe représente le stock d'un supermarché
 * @author soria
 *
 */
public class Stock {
/**
 * L'IdStock du Stock est modifiable
 * @see Stock#getIdStock()
 * @see Stock#setIdStock(Integer)
 */
	private Integer idStock;
/**
 * Le supermarché du Sctock
 * @see Stock#getSuperMarche()
 * @see Stock#setSuperMarche(SuperMarche)
 */
	private SuperMarche superMarche;
/**
 * Liste des produits du Stock
 * @see Produit 
 */
	private List<Produit> produits;
/**
 * Constructeur par défaut	
 */
	public Stock() {
		super();
	}
/**
 * Constructeur d'un stock 
 * @param idStock L'identficateur du stock
 * @param superMarche Le supermarché du stock
 * @param produits Les produits du stock
 */
	public Stock(Integer idStock, SuperMarche superMarche, List<Produit> produits) {
		super();
		this.idStock = idStock;
		this.superMarche = superMarche;
		this.produits = produits;
	}

//Getters end Setters
/**
 * Retourne l'IdStock du Stock
 * @return L'identificateur du stock sous forme d'un entier
 */
    public Integer getIdStock() {
		return idStock;
	}
/**
 * Met à jour de l'identificateur du Stock
 * @param idStock
 */
	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}
/**
 * Retourne le SuperMarche du Stock
 * @return Le supermarché qui contient le stock sous forme d'une chaine de caractére
 */
	public SuperMarche getSuperMarche() {
		return superMarche;
	}
/**
 * Met à jour du supermarché du Stock
 * @param superMarche
 */
	public void setSuperMarche(SuperMarche superMarche) {
		this.superMarche = superMarche;
	}
/**
 * Retourne la liste des produits du Stock
 * @return Produit sous forme d'une liste
 */
	public List<Produit> getProduits() {
		return produits;
	}
/**
 * Met à jour de la liste des produits du stock
 * @param produits
 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
/**
 * Initialise les produits du stock puis sort quelques produits vers les etalages et à la fin effectue quelque vente
 * @param Type de produit 
 */	
	public void initListProduit(TypeGeneral type){
		switch (type) {
		case ALIMENTAIRE:
			for (ProduitsAlimentaire produit : ProduitsAlimentaire.values()) {
				Produit pEntrant = new ProduitAlimentaire(null, null, this, null, produit.getDes(), TypeGeneral.ALIMENTAIRE, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonAl = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonAl = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonAl = rayonsTmp.get(0);
				}
				Produit pSortant = new ProduitAlimentaire(null, null, this, null, produit.getDes(), TypeGeneral.ALIMENTAIRE, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonAl.getEtalages().get(0));
				
				Produit pVendu = new ProduitAlimentaire(null, null, this, null, produit.getDes(), TypeGeneral.ALIMENTAIRE, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonAl.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;
		case ELECTROMENAGER:
			for (ProduitsElectromenager produit : ProduitsElectromenager.values()) {
				Produit pEntrant = new ProduitElectromenager(null, null, this, null, produit.getDes(),  TypeGeneral.ELECTROMENAGER, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonEl = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonEl = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonEl = rayonsTmp.get(1);
				}
				Produit pSortant = new ProduitElectromenager(null, null, this, null, produit.getDes(),  TypeGeneral.ELECTROMENAGER, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonEl.getEtalages().get(0));
				
				Produit pVendu = new ProduitElectromenager(null, null, this, null, produit.getDes(), TypeGeneral.ELECTROMENAGER, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonEl.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;
		case ENTRETIENMAISONSLINGE:
			for (ProduitsEntretienMaisonLinge produit : ProduitsEntretienMaisonLinge.values()) {
				Produit pEntrant = new ProduitEntretienMaison(null, null, this, null, produit.getDes(), TypeGeneral.ENTRETIENMAISONSLINGE, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonEn = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonEn = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonEn = rayonsTmp.get(2);
				}
				Produit pSortant = new ProduitEntretienMaison(null, null, this, null, produit.getDes(), TypeGeneral.ENTRETIENMAISONSLINGE, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonEn.getEtalages().get(0));
				
				Produit pVendu = new ProduitEntretienMaison(null, null, this, null, produit.getDes(), TypeGeneral.ENTRETIENMAISONSLINGE, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonEn.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;
		case HYGIENEBEAUTE:
			for (ProduitsHygieneBeaute produit : ProduitsHygieneBeaute.values()) {
				Produit pEntrant = new ProduitHygieneBeaute(null, null, this, null, produit.getDes(), TypeGeneral.HYGIENEBEAUTE, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonH = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonH = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonH = rayonsTmp.get(3);
				}
				Produit pSortant = new ProduitHygieneBeaute(null, null, this, null, produit.getDes(), TypeGeneral.HYGIENEBEAUTE, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonH.getEtalages().get(0));
				
				Produit pVendu = new ProduitHygieneBeaute(null, null, this, null, produit.getDes(), TypeGeneral.HYGIENEBEAUTE, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonH.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;
		case MODE:
			for (ProduitsMode produit : ProduitsMode.values()) {
				Produit pEntrant = new ProduitMode(null, null, this, null, produit.getDes(), TypeGeneral.MODE, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonM = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonM = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonM = rayonsTmp.get(4);
				}
				Produit pSortant = new ProduitMode(null, null, this, null, produit.getDes(), TypeGeneral.MODE, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonM.getEtalages().get(0));
				
				Produit pVendu = new ProduitMode(null, null, this, null, produit.getDes(), TypeGeneral.MODE, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonM.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;
		case MULTIMEDIA:
			for (ProduitsMultemedia produit : ProduitsMultemedia.values()) {
				Produit pEntrant = new ProduitMultimedia(null, null, this, null, produit.getDes(), TypeGeneral.MULTIMEDIA, produit.getPrix(), produit.getQuantite(), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.entreVersStock(pEntrant);
				Rayon rayonMu = null;
				if(this.getSuperMarche() instanceof SuperMarcheSpecialiste){
					rayonMu = this.getSuperMarche().getRayons();
				}else{
					List<Rayon> rayonsTmp = this.getSuperMarche().getRayons();
					rayonMu = rayonsTmp.get(5);
				}
				Produit pSortant = new ProduitMultimedia(null, null, this, null, produit.getDes(), TypeGeneral.MULTIMEDIA, produit.getPrix(), new Integer(20), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "");
				this.sortirVerEtalage(pSortant, rayonMu.getEtalages().get(0));
				
				Produit pVendu = new ProduitMultimedia(null, null, this, null, produit.getDes(), TypeGeneral.MULTIMEDIA, produit.getPrix(), new Integer(5), produit.getDatePeremption(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				rayonMu.getEtalages().get(0).effectuerVente(pVendu);
			}
			break;

		default:break;
		}
	}
/**
 * Approvisionner le stock du supermarchés	
 * @param p : Le produit à entrer dans le stock
 */	
	public void entreVersStock(Produit p){
		ProduitDAO produitDao = new ProduitDAO();
		p.setDesignation(p.getDesignation().toLowerCase());
		boolean exist = false;
		for (Produit ps : this.getProduits()) {
			if(ps.getDesignation().equals(p.getDesignation())){
				exist = true;
				ps.setQuantite(p.getQuantite()+ps.getQuantite());
				produitDao.update(ps);
			}
		}
		if(!exist){
			Integer idPr = produitDao.insert(p);
			p.setIdProduit(idPr);
			List<Produit> lp = new ArrayList<Produit>();
			lp = this.getProduits();
			lp.add(p);
			this.setProduits(lp);
		}
	}
/**
 * Sortir des produits vers un etalage
 * @param p produit a sortir vers l'étalage
 * @param e Etalage où mettre le produit
 * @return true si le produit exist en stock et false sinon
 */	
	public boolean sortirVerEtalage(Produit p, Etalage e){//sortie produit vers etalage
		ProduitDAO produitDao = new ProduitDAO();
		String desP = p.getDesignation().toLowerCase();
		p.setDesignation(desP);
		boolean existEnStock = false;
		for (Produit ps : this.getProduits()) {
			if(p.getDesignation().equals(ps.getDesignation())){
				if(ps.getQuantite().compareTo(p.getQuantite()) > 0 || ps.getQuantite().compareTo(p.getQuantite()) == 0){
					existEnStock = true;
					Integer qt = p.getQuantite();
					ps.setQuantite(ps.getQuantite()-qt);
					produitDao.update(ps);
				}else{
					System.out.println("   >> Quantité insuffisante !");
				}
			}
		}
		if(existEnStock){
			boolean existEnEtalage = false;
			for (Produit pe : e.getProduits()) {
				if(p.getDesignation().equals(pe.getDesignation())){
					existEnEtalage = true;
					pe.setQuantite(pe.getQuantite()+p.getQuantite());
					produitDao.update(pe);
				}
			}
			if(!existEnEtalage){
				p.setStock(null);
				p.setEtalage(e);
				Integer idPr = produitDao.insert(p);
				p.setIdProduit(idPr);
				List<Produit> lp = new ArrayList<Produit>();
				lp = e.getProduits();
				lp.add(p);
				e.setProduits(lp);
			}
		}
		return existEnStock;
	}
/**
 * Chercher un produit dans le stock par désignation
 * @param des: Designation du produit
 * @return les produits recherchés dans le stock selon la désignation
 */	
	public Produit chercherProduitEnStockByDes(String des){
		Produit retPro = null;
		for (Produit produit : this.getProduits()) {
			if(produit.getDesignation().equals(des)){
				retPro = produit;
			}
		}
		return retPro;
	}
/**
 * Retourne retourne le nombre de produit en stock selon la désignation
 * @param s: désignation du produit
 * @return la quantité des produits dans le stock selon son désignation
 */	
	public Integer returnNbProduitByDes(String des){
		List<Produit> lp = this.getProduits();
		return lp.stream()
				 .filter(p->p.getDesignation().equals(des))
				 .mapToInt(p2->p2.getQuantite())
				 .sum();
	}
/**
 * @param s désignation du produit
 * @return true si le produit existe en stock et false sinon
 */
	public boolean ifProduitExist(String s){
		List<Produit> lp = this.getProduits();
		return lp.stream()
				 .map(p->p.getDesignation())
				 .anyMatch(s::equalsIgnoreCase);
	}
/**
 * Envoyer une cargaison vers un autre stock
 * @param p Produit a envoyer
 * @param s Stock où on envoi le produit
 */
	public void envoyerCargaison(Produit p, Stock s){
		ProduitDAO produitDao = new ProduitDAO();
		String desP = p.getDesignation().toLowerCase();
		p.setDesignation(desP);
		boolean existEnStock = false;
		for (Produit ps : this.getProduits()) {
			if(p.getDesignation().equals(ps.getDesignation())){
				if(ps.getQuantite().compareTo(p.getQuantite()) > 0 || ps.getQuantite().compareTo(p.getQuantite()) == 0){
					existEnStock = true;
					Integer qt = p.getQuantite();
					ps.setQuantite(ps.getQuantite()-qt);
					produitDao.update(ps);
				}else{
					System.out.println("   >> Quantité insuffisante !");
				}
			}
		}
		if(existEnStock){
			s.entreVersStock(p);
		}
	}
/**
 * Retourne liste des produit de ce Stock
 * @param lp Liste des produits
 * @return la liste des produits en stock
 */
	public List<Produit> retProduitOfStock(List<Produit> lp){
		return lp.stream()
				 .filter(p->p.getStock() != null)
				 .filter(p2->p2.getStock().getIdStock() == this.getIdStock())
				 .collect(Collectors.toList());
	}
}