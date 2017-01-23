package metiers;
/**
 * Enumerateur des produits hygiéne et beauté
 * @author faredj
 *
 */
public enum ProduitsHygieneBeaute {
	
	Savon("Savon", new Double(1.30), new Integer(50), "2017-08-16"),
	GelDouche("Gel Douche", new Double(2.50), new Integer(50), "2017-05-10"),
	BrosseADent("Brosse à Dents", new Double(3.80), new Integer(50), "2017-04-26"),
	CremeLavante("Créme Lavante", new Double(2.60), new Integer(50), "2017-05-17"),
	MousseARaser("Mousse à Raser", new Double(6.50), new Integer(50), "2017-06-19"),
	Deodorant("Deodorant", new Double(5.60), new Integer(50), "2017-19-16"),
	Parfum("Parfum", new Double(3.50), new Integer(50), "2017-12-19"),
	Shampoing("Shampoing", new Double(8.80), new Integer(50), "2017-11-18"),
	Maquillage("Maquillage", new Double(3.30), new Integer(50), "2017-05-23"),
	EauDeToilette("Eau de Toilette", new Double(4.70), new Integer(50), "2017-08-16");
/**
 * La désignation  du produit hygiéne et beauté du supermarché n'est pas modifiable
 * @see ProduitsHygieneBeaute#ProduitsHygieneBeaute(String, Double, Integer, String)
 * @see ProduitsHygieneBeaute#getDes()
 */
	private String Des;
/**
 * Le prix du produit hygiéne et beauté du supermarché n'est pas modifiable
 * @see ProduitsHygieneBeaute#ProduitsHygieneBeaute(String, Double, Integer, String)
 * @see ProduitsHygieneBeaute#getPrix()
 */
	private Double prix;
/**
 * La quantité  du produit hygiéne et beauté du supermarché est pas modifiable
 * @see ProduitsHygieneBeaute#ProduitsHygieneBeaute(String, Double, Integer, String)
 * @see ProduitsHygieneBeaute#getQuantite()
 */
	private Integer quantite;
/**
 * La date de peremtion du produit hygiéne et beauté du supermarché n'est pas modifiable
 * @see ProduitsHygieneBeaute#ProduitsHygieneBeaute(String, Double, Integer, String)
 * @see ProduitsHygieneBeaute#getDatePeremption()
 */
	private String datePeremtion;
/**
 * Constructeur des produits hygiéne de beauté du supermarché
 * @param des La designation du produit hygiéne de beauté du supermarché
 * @param prix Le prix du produit hygiéne de beauté du supermarché 
 * @param quantite La quantité du produit hygiéne de beauté du supermarché
 * @param datePeremtion La date de peremtion  du produit hygiéne de beauté du supermarché
 */
	
	ProduitsHygieneBeaute(String des, Double prix, Integer quantite, String datePeremtion) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremtion = datePeremtion;
	}
/**
 * Retourner la Des du ProduitsHygieneBeaute
 * @return La designation du produit hygiéne et de beauté du supermarché sous forme d'une chaine de caractére
 */
	public String getDes() {
		return Des;
	}
/**
 * Retourner le Prix du ProduitsHygieneBeaute
 * @return  le prix du produit hygiéne et de beauté du supermarché sous forme d'un Double
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantité du ProduitsHygieneBeaute
 * @return la quantité du produit hygiéne et de beauté du supermarché sous forme d'un entier
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner la DatePeremption du ProduitsHygieneBeaute
 * @return la date de peremption du produit hygiéne et de beauté du supermarché sous forme d'une chaine de caractére
 */
	public String getDatePeremption() {
		return datePeremtion;
	}
}
