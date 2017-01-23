package metiers;
/**
 * Enumérateur des produits  alimentaires du supermarché
 * @author faredj
 *
 */
public enum ProduitsAlimentaire {
	
	Lait("Lait", new Double(2.50), new Integer(50), "05-01-2017"),
	Pain("Pain", new Double(3.60), new Integer(50), "23-05-2017"),
	Cafe("Cafe", new Double(7.80), new Integer(50), "16-08-2017"),
	Oeuf("Oeuf", new Double(1.50), new Integer(50), "04-01-2017"),
	Riz("Riz", new Double(4.80), new Integer(50), "09-06-2017"),
	Macaroni("Macaroni", new Double(4.44), new Integer(50), "07-11-2017"),
	Spaghetti("Spaghetti", new Double(3.50), new Integer(50), "18-01-2017"),
	Poulet("Poulet", new Double(8.50), new Integer(50), "09-01-2017"),
	Thon("Thon", new Double(2.60), new Integer(50), "24-09-2017"),
	Fromage("Fromage", new Double(3.50), new Integer(50), "07-01-2017");
	
/**
 * la Designation du produit alimentaires du supermarché n'est pas modifiable
 * @see ProduitsAlimenataire#ProduitsAlimentaire(string, Double, Integer, String)
 * @see Produitslimenatire#getDes()
  */
	private String Des;
/**
 * Le prix du produit alimentaires du supermarché n'est pas modifiable
 * @see ProduitsAlimentaire#ProduitsAlimentaire(String, Double, Integer, String)
 * @see ProduitsAlimentaire#getPrix()
 */
	private Double prix;
/**
 * La quantité des produits  alimentaires du supermarché est pas modifiable
 * @see ProduitsAlimentaire#ProduitsAlimentaire(String, Double, Integer, String)
 * @see ProduitsAlimenataire#getQuantité()
 */
   private Integer quantite;
/**
 * La date de peremtion des produits alimentaires du supermarché n'est pas modifiable
 * @see ProduitsAlimentaire#ProduitsAlimentaire(String, Double, Integer, String)
 * @see ProduitsAlimenataire#getDatePeremtion()
 */
	private String datePeremtion;
/**
 * Constructeur des produits alimentaire du supermarché	
 * @param des La désignation du produit alimentaire du supermarché
 * @param prix Le prix du produit  alimentaire du supermarché
 * @param quantite La quantité du rpoduit  alimentaire du supermarché
 * @param datePeremtion La date de peremption du produit alimentaire du supermarché
 */
	ProduitsAlimentaire(String des, Double prix, Integer quantite, String datePeremtion) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremtion = datePeremtion;
	}
/**
 * Retourner Des du ProduitsAlimentaire
 * @return la désignation du produit alimentaire sous forme d'une chaine de caractére
 */
	public String getDes() {
		return Des;
	}
/**
 * Retourner le Prix du ProduitsAlimentaire
 * @return le prix  du produit  alimentaire du super marché sous forme d'un double
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantite  du ProduitsAlimentaire
 * @return la quantité  du produit  alimentaire du super marché sous forme d'un entier
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner DatePeremption  du ProduitsAlimentaire
 * @return la date de peremption  du produit  alimentaire du super marché sous forme d'une chaine de caractére
 */
	public String getDatePeremption() {
		return datePeremtion;
	}
}