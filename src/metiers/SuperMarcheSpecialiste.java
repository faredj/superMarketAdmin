package metiers;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Cette classe représente les supermarchés spécialistes
 * @author soria
 *
 */
public class SuperMarcheSpecialiste extends SuperMarche{
/**
 * le rayon du supermarché spécialiste est changeable
 * @see Rayon
 * @see SuperMarcheSpecialiste#getRayon()
 * @see SuperMarcheSpecialiste#setRayon(Rayon)
 */
	private Rayon rayons;
/**
 * Constructeur par défaut
 */
	public SuperMarcheSpecialiste() {
		super();
	}
/**
 * Constructeur de supermarché spécialiste
 * @param idSM L'identifiacteur du supermarché spécialiste
 * @param chaineSM La chaine supermarché spécialiste
 * @param adresse L'adresse du supermarché spécialiste
 * @param stock Le stock du supermarché spécialiste
 * @param vente La vente du produit dans le supermarché spécialiste
 * @param rayon Le rayon du supermarché spécialiste
 */
	public SuperMarcheSpecialiste(Integer idSM, ChaineSM chaineSM, String adresse, Stock stock, Vente vente, Rayon rayon) {
		super(idSM, chaineSM, adresse, stock, vente);
		this.rayons = rayon;
	}	

/**
 * Constructeur par défaut
 */
	@SuppressWarnings("unchecked")
	public Rayon getRayons() {
		return rayons;
	}
/**
 * Constructeur des rayons des supermarchés spécailiste
 * @param rayons Les rayons des supermarchés spécialiste
 */
	public void setRayons(Rayon rayons) {
		this.rayons = rayons;
	}

	@Override
/**
 * Retourne nbProduitEnRayonParNom des supermarcheSpécialiste
 * @param nom Le nom du produit
 * @return La quantité des produits en stock des supermarchés Spécialiste selon leurs "nom"
 */
	public Integer nbProduitEnRayonParNom(String nom) {
		return this.getRayons().getEtalages()
				.stream().map(e->e.getProduits())
				.flatMap(p->p.stream().filter(p2->p2.getDesignation() == nom))
				.mapToInt(Produit::getQuantite)
				.sum();
	}
/**
 * * Retourne produitEnRayonParPrixMax des supermarcheSpécialiste
 * @param prixMax Le prix maximum du produit
 * @return Les produits en rayon des supermarchés Spécialiste selon leurs "prixMax"
 */
	@Override
	public Set<Produit> produitEnRayonParPrixMax(Double prixMax) {
		return this.getRayons().getEtalages()
				.stream()
				.map(e->e.getProduits())
				.flatMap(p->p.stream().filter(p2->p2.getPrix() <= prixMax))
				.collect(Collectors.toSet());
	}
/**
 *  Retourne produitEnRayonParPrixMin des supermarcheSpécialiste
 * @param prixMin Le prix minimum du produit
 * @return Les produits en rayon des supermarchés Spécialiste selon leurs "prixMin"
 */
	@Override
	public Set<Produit> produitEnRayonParPrixMin(Double prixMin) {
		return this.getRayons().getEtalages()
				.stream()
				.map(e->e.getProduits())
				.flatMap(p->p.stream().filter(p2->p2.getPrix() >= prixMin))
				.collect(Collectors.toSet());
	}
/**
 * Liste des produits du supermarché spécialiste
 * @return null
 */
	@Override
	public Set<Produit> produitPerimesEnRayon() {
		return null;
//		return this.getRayons().getEtalages()
//				.stream()
//				.map(e->e.getProduits())
//				.flatMap(p->p.stream().filter(p2->p2.getDatePeremption().after(new Date())))
//				.collect(Collectors.toSet());
	}
	
}
