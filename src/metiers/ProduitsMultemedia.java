package metiers;
/**
 * Enumerateur des produits multemedia du supermarché
 * @author faredj
 *
 */
public enum ProduitsMultemedia {
	
	PcPortable("Pc Portable", new Double(460.00), new Integer(50), ""),
	TelephonePortable("Telephone Portable", new Double(190.00), new Integer(50), ""),
	Tablette("Tablette", new Double(230.00), new Integer(50), ""),
	Camera("Camera", new Double(150.00), new Integer(50), ""),
	FlashDisk("Flash Disk", new Double(50.00), new Integer(50), ""),
	Souris("Souris", new Double(20.00), new Integer(50), "");
/**
 * La désignation du produit multemedia du supermarché n'est pas modifiable
 * @see ProduitsMultemedia#ProduitsMultemedia(String, Double, Integer, String)
 * @see ProduitsMultemedia#getDes()	
 */
	private String Des;
/**
 * Le prix du produit multemedia du supermarché n'est pas modifiable
 * @see ProduitsMultemedia#ProduitsMultemedia(String, Double, Integer, String)
 * @see ProduitsMultemedia#getPrix()
 */
	private Double prix;
/**
 * La quantité du produit multemedia dusuper marché est pas modifiable
 * @see ProduitsMultemedia#ProduitsMultemedia(String, Double, Integer, String)
 * @see ProduitsMultemedia#getQuantite()
 */
	private Integer quantite;
/**
 * La date de peremption du produit multemedia du supermarché est pas modifiable
 * @see ProduitsMultemedia#ProduitsMultemedia(String, Double, Integer, String)
 * @see ProduitsMultemedia#getDatePeremption()
 */
	private String datePeremption;
/**
 * Constructeur des produits multemedia	du supermarché 
 * @param des La désignation du produit multemedia du supermarché	
 * @param prix Le prix du produit multemedia du supermarché	
 * @param quantite La quantité du produit multemedia du supermarché	
 * @param datePeremtion La date de peremtion du produit multemedia du supermarché	
 */
	ProduitsMultemedia(String des, Double prix, Integer quantite, String datePeremption) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremption = datePeremption;
	}
/**
 * Retourner Des du ProduitsMultemedia 
 * @return La désignation du produit multemedia du supermarché
 */
	public String getDes() {
		return Des;
	}
/**
 * Retourner le Prix du ProduitsMultemedia
 * @return Le prix du produit multemedia du supermarché
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantite du ProduitsMultemedia
 * @return La quantité du produit multemedia du supermarché
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner la DatePeremption du ProduitsMultemedia
 * @return La date de peremption du produit multemedia du supermarché
 */
	public String getDatePeremption() {
		return datePeremption;
	}
}
