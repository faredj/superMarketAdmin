package metiers;
/**
 * Enumerateur des produits mode du supermarché
 * @author faredj
 *
 */
public enum ProduitsMode {
	
	Pantalon("Pantalon", new Double(23.50), new Integer(50), ""),
	TShirt("TShirt", new Double(26.50), new Integer(50), ""),
	Chemise("Chemise", new Double(21.50), new Integer(50), ""),
	Veste("Veste", new Double(28.50), new Integer(50), ""),
	Jean("Jean", new Double(20.50), new Integer(50), ""),
	Chaussure("Chaussure", new Double(19.50), new Integer(50), "");
/**
 * La désignation du produit mode du supermarché n'est pas modifiable
 * @see ProduitsMode#ProduitsMode(String, Double, Integer, String)
 * @see ProduitsMode#getDeclaringClass()	
 */
	private String Des;
/**
 * Le prix du produit mode du supermarché n'est pas modifiable
 * @see ProduitsMode#ProduitsMode(String, Double, Integer, String)
 * @see ProduitsMode#getPrix() 
 */
	private Double prix;
/**
 * La quantité du produit mode du supermarché est pas modifiable
 * @see ProduitsMode#ProduitsMode(String, Double, Integer, String)
 * @see ProduitsMode#getQuantite()
 */
	private Integer quantite;
/**
 * La date de peremtion du produit mode du supermarché n'est pas modifiable
 * @see ProduitsMode#ProduitsMode(String, Double, Integer, String)
 * @see ProduitsMode#getDatePeremption()
 */
	private String datePeremtion;
/**
 * Constructeur des produits mode du supermarché
 * @param des La désignation du produit mode du supermarché
 * @param prix Le prix du produit mode du supermarché
 * @param quantite La quantité du produit mode du supermarché
 * @param datePeremtion la date de peremtion du produit mode du supermarché
 */
	ProduitsMode(String des, Double prix, Integer quantite, String datePeremtion) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremtion = datePeremtion;
	}
/**
 * Retourner la Des du ProduitsMode
 * @return La désignation du produit mode du supermarché
 */
	public String getDes() {
		return Des;
	}
/**
 * Retourner le Prix du ProduitsMode
 * @return Le prix du produit mode du supermarché
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantite du ProduitsMode
 * @return La quantité du produit mode du supermarché
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner la DatePeremption du ProduitsMode
 * @return La date de peremption du produit mode du supermarché
 */
	public String getDatePeremption() {
		return datePeremtion;
	}
}
