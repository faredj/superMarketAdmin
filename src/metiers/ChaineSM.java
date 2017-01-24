package metiers;
import java.util.*;
import java.util.stream.Collectors;

import donnees.*;
/**
 * cette classe représente une chaine SuperMarchés
 * @author soria
 */
public class ChaineSM{
/**
 * L'idEnseigne de ChaineSM est modifiable
 * @see ChaineSM#getidEnseigne()
 * @see ChaineSM#setidEnseigne(Integer)
 */
	private Integer idEnseigne;
/**
 * Le nomEnseigne de ChaineSM est moifiable
 * @see ChaineSM#getNomEnsigne()
 * @see ChaineSM#setNomEnsigne(String)
 */
	private String nomEnsigne ;
/**
 * La liste des supermarchés de l'ensigne
 * @see SuperMarche
 */
	private List<SuperMarche> marchesEnsigne;
/**
 * Constructeur par défaut
 */	
	public ChaineSM(){
		this.idEnseigne = 0;
		this.nomEnsigne = "";
		this.marchesEnsigne = null;
	}
/**
 * Constructeur avec paramétres de ChaineSM	
 * @param idEnseigne l'Identificateur de l'ensigne
 * @param nomEnsigne le Nom de l'nsigne
 * @param marchesEnsigne la liste super Marché des chaine de super marché
 */	
	public ChaineSM(Integer idEnseigne, String nomEnsigne, List<SuperMarche> marchesEnsigne) {
		this.idEnseigne = idEnseigne;
		this.nomEnsigne = nomEnsigne;
		this.marchesEnsigne = marchesEnsigne;
	}
/**
 * Retourner L'idEnseigne de la ChaineSM
 * @return l'identificateur de l'enseigne sous forme d'un entier
 */
	public Integer getidEnseigne() {
		return idEnseigne;
	}
/**
 * Met à jour de l'Identificateur de l'ensigne
 * @param idEnseigne 
 */
	public void setidEnseigne(Integer idEnseigne) {
		this.idEnseigne = idEnseigne;
	}
/**
 * Retourner le NomEnsigne de la chaineSM
 * @return le NomEnsigne sous forme d'une chaine de caractére
 */
		public String getNomEnsigne() {
		return nomEnsigne;
	}
/**
 * Met à jour  du nomEnsigne de la chaineSM		
 * @param nomEnsigne
 */
	public void setNomEnsigne(String nomEnsigne) {
		this.nomEnsigne = nomEnsigne;
	}
/**
 * Retourner la liste des supermarchés
 * @return le marchesEnsigne sous la forme d'une liste
 */
	public List<SuperMarche> getMarchesEnsigne() {
		return marchesEnsigne;
	}
/**
 * Met à jour  de la liste des supermarchés
 * @param marchesEnsigne
 */
	public void setMarchesEnsigne(List<SuperMarche> marchesEnsigne) {
		this.marchesEnsigne = marchesEnsigne;
	}
/**
 * Méthode pour construire un supermarchés généraliste
 * @param adresseSM l'adressre du supermarché généraliste
 */
	public void construireGeneralisteSM(String adresseSM){//construction SM génétaliste + un stock + tout les rayons
		SupermacheDAO superMarcheDao = new SupermacheDAO();
		StockDAO stockDao = new StockDAO();
		RayonDAO rayonDao = new RayonDAO();
		VenteDAO venteDao = new VenteDAO();
		
		SuperMarcheGeneraliste sm = new SuperMarcheGeneraliste();
		sm.setAdresse(adresseSM);
		sm.setChaineSM(this);
		
		Integer idSm = superMarcheDao.insert(sm);//insertion du supermarché dans la base
		sm.setIdSM(idSm);
		
		Stock st = new Stock();
		st.setSuperMarche(sm);
		Integer idSt = stockDao.insert(st);
		st.setIdStock(idSt);
		st.setProduits(new ArrayList<Produit>());
		System.out.println("    ...Stock créé");
		sm.setStock(st);
		
		Vente vente = new Vente();
		vente.setSuperMarche(sm);
		Integer idVente = venteDao.insert(vente);
		vente.setIdVente(idVente);
		vente.setProduits(new ArrayList<Produit>());
		sm.setVente(vente);
		
		List<Rayon> rayons = new ArrayList<Rayon>();
		
		RayonAlimentaire rayonAl = new RayonAlimentaire();
		rayonAl.setSuperMarche(sm);
		Integer idRaAl = rayonDao.insert(rayonAl);
		rayonAl.setIdRayon(idRaAl);
		rayonAl.ajouterEtalages(3);
		rayons.add(rayonAl);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.ALIMENTAIRE);
		System.out.println("    ...Rayon Alimentaire créé");
		
		RayonElectromenager rayonEl = new RayonElectromenager();
		rayonEl.setSuperMarche(sm);
		Integer idRaEl = rayonDao.insert(rayonEl);
		rayonEl.setIdRayon(idRaEl);
		rayonEl.ajouterEtalages(3);
		rayons.add(rayonEl);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.ELECTROMENAGER);
		System.out.println("    ...Rayon Electoménager créé");
		
		RayonEntretienMaisonLinge rayonEn = new RayonEntretienMaisonLinge();
		rayonEn.setSuperMarche(sm);
		Integer idRaEn = rayonDao.insert(rayonEn);
		rayonEn.setIdRayon(idRaEn);
		rayonEn.ajouterEtalages(3);
		rayons.add(rayonEn);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.ENTRETIENMAISONSLINGE);
		System.out.println("    ...Rayon Entretien Maison et Linge créé");
		
		RayonHygieneBeaute rayonH = new RayonHygieneBeaute();
		rayonH.setSuperMarche(sm);
		Integer idRaH = rayonDao.insert(rayonH);
		rayonH.setIdRayon(idRaH);
		rayonH.ajouterEtalages(3);
		rayons.add(rayonH);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.HYGIENEBEAUTE);
		System.out.println("    ...Rayon Hygiéne et Beauté créé");
		
		RayonMode rayonM = new RayonMode();
		rayonM.setSuperMarche(sm);
		Integer idRaM = rayonDao.insert(rayonM);
		rayonM.setIdRayon(idRaM);
		rayonM.ajouterEtalages(3);
		rayons.add(rayonM);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.MODE);
		System.out.println("    ...Rayon Mode créé");
		
		RayonMultimedia rayonMu = new RayonMultimedia();
		rayonMu.setSuperMarche(sm);
		Integer idRaMu = rayonDao.insert(rayonMu);
		rayonMu.setIdRayon(idRaMu);
		rayonMu.ajouterEtalages(3);
		rayons.add(rayonMu);
		sm.setRayons(rayons);
		st.initListProduit(TypeGeneral.MULTIMEDIA);
		rayons.add(rayonMu);
		System.out.println("    ...Rayon Multimédia créé");
		
		sm.setRayons(rayons);
		this.marchesEnsigne.add(sm);
		System.out.println("    >>SuperMarchés généraliste créé avec succé !");
		System.out.println();System.out.println();
	}
/**
 * Construire un superMarché spécialiste
 * @param adresseSM : l'adresse du supermarché spécialiste
 * @param type :le type du Supermarché
 */	
	public void ConstruireSpecialisteSM(String adresseSM, TypeGeneral type){//construire SM spcialiste + stock + un seul rayon
		SupermacheDAO superMarcheDao = new SupermacheDAO();
		StockDAO stockDao = new StockDAO();
		RayonDAO rayonDao = new RayonDAO();
		VenteDAO venteDao = new VenteDAO();
		
		SuperMarcheSpecialiste sm = new SuperMarcheSpecialiste();
		sm.setAdresse(adresseSM);
		sm.setChaineSM(this);
		
		Integer idSm = superMarcheDao.insert(sm);
		sm.setIdSM(idSm);
		
		Stock st = new Stock();
		st.setSuperMarche(sm);
		sm.setStock(st);
		Integer idSt = stockDao.insert(st);
		st.setIdStock(idSt);
		st.setProduits(new ArrayList<Produit>());
		
		Vente vente = new Vente();
		vente.setSuperMarche(sm);
		Integer idVente = venteDao.insert(vente);
		vente.setIdVente(idVente);
		vente.setProduits(new ArrayList<Produit>());
		sm.setVente(vente);
		
		System.out.println("    ...Stock créé");
		
			switch (type) {
			case ALIMENTAIRE:
				RayonAlimentaire rayonAl = new RayonAlimentaire();
				rayonAl.setSuperMarche(sm);
				Integer idRaAl = rayonDao.insert(rayonAl);
				rayonAl.setIdRayon(idRaAl);
				rayonAl.ajouterEtalages(3);
				sm.setRayons(rayonAl);
				st.initListProduit(type);
				System.out.println("    ...Rrayon Alimentaire créé avec 3 étalages");
				break;

			case ELECTROMENAGER:
				RayonElectromenager rayonEl = new RayonElectromenager();
				rayonEl.setSuperMarche(sm);
				Integer idRaEl = rayonDao.insert(rayonEl);
				rayonEl.setIdRayon(idRaEl);
				rayonEl.ajouterEtalages(3);
				sm.setRayons(rayonEl);
				st.initListProduit(type);
				System.out.println("    ...Rayon Electoménager créé avec 3 étalages");
				break;
				
			case ENTRETIENMAISONSLINGE:
				RayonEntretienMaisonLinge rayonEn = new RayonEntretienMaisonLinge();
				rayonEn.setSuperMarche(sm);
				Integer idRaEn = rayonDao.insert(rayonEn);
				rayonEn.setIdRayon(idRaEn);
				rayonEn.ajouterEtalages(3);
				sm.setRayons(rayonEn);
				st.initListProduit(type);
				System.out.println("    ...Rayon Entretien Maison et Linge créé avec 3 étalages");
				break;
				
			case HYGIENEBEAUTE:
				RayonHygieneBeaute rayonH = new RayonHygieneBeaute();
				rayonH.setSuperMarche(sm);
				Integer idRaH = rayonDao.insert(rayonH);
				rayonH.setIdRayon(idRaH);
				rayonH.ajouterEtalages(3);
				sm.setRayons(rayonH);
				st.initListProduit(type);
				System.out.println("    ...Rayon Hygiéne et Beauté créé avec 3 étalages");
				break;
				
			case MODE:
				RayonMode rayonM = new RayonMode();
				rayonM.setSuperMarche(sm);
				Integer idRaM = rayonDao.insert(rayonM);
				rayonM.setIdRayon(idRaM);
				rayonM.ajouterEtalages(3);
				sm.setRayons(rayonM);
				st.initListProduit(type);
				System.out.println("    ...Rayon Mode créé avec 3 étalages");
				break;
				
			case MULTIMEDIA:
				RayonMultimedia rayonMu = new RayonMultimedia();
				rayonMu.setSuperMarche(sm);
				Integer idRaMu = rayonDao.insert(rayonMu);
				rayonMu.setIdRayon(idRaMu);
				rayonMu.ajouterEtalages(3);
				sm.setRayons(rayonMu);
				st.initListProduit(type);
				System.out.println("    ...Rayon Multimédia créé avec 10 étalages");
				break;
			default:break;
			}
		this.getMarchesEnsigne().add(sm);
		System.out.println("    >>SuperMarchés spécialité "+type+" créé avec succé !");
		System.out.println();System.out.println();
	}
/**
 *  Retourne nbProduitStockParNom de la chaineSM
 * @param nom le nom du produit 
 * @return nombre de produit dans tout les stock de l'enseigne par nom
 */	
	public Integer nbProduitStockParNom(String nom){
		return this.getMarchesEnsigne()
				   .stream()
				   .map(m->m.getStock().getProduits())
				   .flatMap(p->p.stream().filter(s->s.getDesignation()==nom))
				   .mapToInt(Produit::getQuantite)
				   .sum();
	}
/**
 * Retourne retSuperMarcheDeChaine de la chaineSM
 * @param listSM : liste des supermarché
 * @return la liste des supermarchés d'une chaine
 */
		
	public List<SuperMarche> retSuperMarcheDeChaine(List<SuperMarche> listSM){
		return listSM.stream()
					 .filter(sm->sm.getChaineSM().getidEnseigne() ==  this.getidEnseigne())
					 .collect(Collectors.toList());
	}
}
