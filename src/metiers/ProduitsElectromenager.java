package metiers;
/**
 * Enumérateur des produits electromenager du supermarché
 * @author faredj
 *
 */
public enum ProduitsElectromenager {
	
	Television("Télévision", new Double(200.50), new Integer(50), ""),
	MicroOnde("Micro Onde", new Double(80.50), new Integer(50), ""),
	LaveLinge("Lave-Linge", new Double(120.50), new Integer(50), ""),
	LaveVaisselle("Lave Vaisselle", new Double(130.50), new Integer(50), ""),
	Refrigerateur("Réfrigérateur", new Double(140.50), new Integer(50), ""),
	Aspirateur("Aspirateur", new Double(34.50), new Integer(50), ""),
	Batteur("Batteur", new Double(.50), new Integer(50), ""),
	Cafetiere("Cafetiére", new Double(2.50), new Integer(50), ""),
	Four("Four", new Double(2.50), new Integer(50), ""),
	Cuisiniere("Cuisiniére", new Double(2.50), new Integer(50), "");
/**
 * la Designation du produit electromenager du supermarché n'est pas modifiable
 * @see ProduitsElectromenager#ProduitsElectomenager(string, Double, Integer, String)
 * @see ProduitsElectromenager#getDes() 	
 */
	private String Des;
/**
 * le prix du produit electromenager du supermarché n'est pas modifiable
 * @see ProduitElectromenager#ProduitElectromenager()
 * @see ProduisElectromenager#getPrix()
 */
	private Double prix;
/**
 * la quantité du produit electromenager du supermarché n'est pas modifiable
 * @see ProduitElectromenager#ProduitElectromenager()
 * @see ProduisElectromenager#getQuantite()
 */
	private Integer quantite;
/**
 * La date de peremtion du produit electromenager du supermarché est pas modifiable
 * @see ProduitElectromenager#ProduitElectromenager()
 * @see ProduisElectromenager#getDatePeremtion()
 */
	private String datePeremtion;
/**
 * Constructeur des produits electromenager du supermarché	
 * @param des La désignation du produit electromenager du supermarché	
 * @param prix Le prix du produit electromenager du supermarché	
 * @param quantite La quantité du produit electromenager du supermarché	
 * @param datePeremtion La date de peremtion du produit electromenager du supermarché	
 */
	ProduitsElectromenager(String des, Double prix, Integer quantite, String datePeremtion) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremtion = datePeremtion;
	}
/**
 * Retourner Des du ProduitsElectromenager
 * @return la désignation du produit electromenager sous forme d'une chaine de caractére
 */
	public String getDes() {
		return Des;
		}
/**
 * Retourner le Prix du ProduitsElectromenager
 * @return le prix  du produit electromenager  du super marché sous forme d'un double
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantite  du ProduitsElectromenager
 * @return la quantité  du produit  electromenager du super marché sous forme d'un entier
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner DatePeremption  du ProduitsElectromenager
 * @return la date de peremption  du produit  electromenager du super marché sous forme d'une chaine de caractére 
 */
	public String getDatePeremption() {
		return datePeremtion;
	}
}
