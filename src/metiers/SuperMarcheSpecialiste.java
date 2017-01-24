package metiers;

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

/**
 * Retourne nbProduitEnRayonParNom des supermarcheSpécialiste
 * @param nom Le nom du produit
 * @return La quantité des produits en stock des supermarchés Spécialiste selon leurs "nom"
 */
	@Override
	public Integer nbProduitEnRayonParNom(String nom) {
		return this.getRayons().getEtalages()
				.stream().map(e->e.getProduits())
				.flatMap(p->p.stream().filter(p2->p2.getDesignation() == nom))
				.mapToInt(Produit::getQuantite)
				.sum();
	}

}
