package metiers;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Cette classe présente les supermarchés généralistes
 * @author soria
 *
 */
public class SuperMarcheGeneraliste extends SuperMarche{
/**
 * La liste Rayon des supermarchés généraliste
 * @see Rayon
 */
	private List<Rayon> rayons;
/**
 * Constructeur par défaut
 */
	public SuperMarcheGeneraliste() {
		super();
	}
/**
 * Constructeur des supermarché généraliste
 * @param idSM L'Identificateur du supermarché
 * @param chaineSM La chaine des supermarchés
 * @param adresse L'adresse du supermarché
 * @param stock Le stock du supermarché
 * @param vente du produit du supermarché
 * @param rayon Les rayon des supermarchés
 */
	public SuperMarcheGeneraliste(Integer idSM, ChaineSM chaineSM, String adresse, Stock stock, Vente vente, List<Rayon> rayon) {
		super(idSM, chaineSM, adresse, stock, vente);
		this.rayons = rayon;
	}	

	@SuppressWarnings("unchecked")
/**
 *  Retourner les rayons du supermarché généraliste
* @return Rayon sous forme d'une liste
 */
	public List<Rayon> getRayons() {
		return this.rayons;
	}
/**
 *  Met à jour de la liste des rayons du supermarché généraliste
 * @param rayons
 */
	public void setRayons(List<Rayon> rayons) {
		this.rayons = rayons;
	}
/**
 * @param nom Le nom du produit du supermarché généraliste
 * @return La liste des produits dans le supermarché generaliste par nom
 */
	@Override
	public Integer nbProduitEnRayonParNom(String nom) {//chercher liste produit dans le SM par "NOM"
		return this.getRayons()
				.stream()
				.map(r->r.getEtalages())
				.flatMap(r2->r2.stream().map(e->e.getProduits()))
				.flatMap(p->p.stream().filter(p2->p2.getDesignation()==nom))
				.mapToInt(Produit::getQuantite)
				.sum();
	}


/**
 * @param rayon Le rayon du SupeMarchéGénéraliste
 * @param prixMin le prix minimum du produit du SupeMarchéGénéraliste
 * @return La liste des produits dans le supermarché generaliste par prix minimum et par rayon
 */	
	public Set<Produit> produitParRayonEtPrixMin(String rayon, Double prixMin){//chercher dans SM par "RAYON" et "PRIX MIN"
		return this.getRayons()
				.stream()
				.filter(r->r.getTypeRayon().name() == rayon)
				.map(r2->r2.getEtalages())
				.flatMap(r3->r3.stream().map(e->e.getProduits()))
				.flatMap(p->p.stream().filter(p2->p2.getPrix() <= prixMin))
				.collect(Collectors.toSet());
	}

}
