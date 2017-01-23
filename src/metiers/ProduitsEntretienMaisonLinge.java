package metiers;
/**
 * Enumerateur des produits entretien de maiaosn et du linge du supermarché
 * @author faredj
 *
 */
public enum ProduitsEntretienMaisonLinge {
	
	LessiveLiquide("Lessive Liquide", new Double(3.50), new Integer(50), ""),
	GelNettoyant("Gel Nettoyant", new Double(6.60), new Integer(50), ""),
	NettoyantVitre("Nettoyant Vitre", new Double(6.50), new Integer(50), ""),
	Balai("Balai", new Double(3.50), new Integer(50), ""),
	Lingette("Lingette", new Double(2.90), new Integer(50), ""),
	Chiffon("Chiffon", new Double(1.90), new Integer(50), ""),
	Frottoire("Frottoire", new Double(3.40), new Integer(50), ""),
	LiquideVaisselle("Liquide Vaisselle", new Double(4.30), new Integer(50), ""),
	LaveVitre("Lave Vitre", new Double(8.30), new Integer(50), ""), 
	EssuieMains("Essuie Mains", new Double(3.44), new Integer(50), "");
/**
 * la Designation du produit entretien de maison et du linge du supermarché n'est pas modifiable
 * @see ProduitsEntretienMaisonLinge#ProduitsEntretienMaisonLinge(string, Double, Integer, String)
 * @see ProduitsEntretienMaisonLinge#getDes() 		
 */
	private String Des;
/**
 * Le prix du produit entretien de maison et du linge du supermarché n'est pas modifiable
 * @see ProduitsEntretienMaisonLinge#ProduitsEntretienMaisonLinge(string, Double, Integer, String)
 * @see ProduitsEntretienMaisonLinge#getPrix() 	 
 */
	private Double prix;
/**
 * La quantité du produit entretien de maison et du linge du supermarché est pas modifiable
 * @see ProduitsEntretienMaisonLinge#ProduitsEntretienMaisonLinge(string, Double, Integer, String)
 * @see ProduitsEntretienMaisonLinge#getQuantite() 	
 */
	private Integer quantite;
/**
 * La Date de peremtion du produit entretien de maison et du linge du supermarché n'est pas modifiable
 * @see ProduitsEntretienMaisonLinge#ProduitsEntretienMaisonLinge(string, Double, Integer, String)
 * @see ProduitsEntretienMaisonLinge#getDatePeremption() 	
 */
	private String datePeremtion;
/**
 *Constructeur des produits entretien de maison et du linge du supermarché
 * @param des La désignation du produit entretien de maison et du linge du supermarché
 * @param prix  Le prix du produit entretien de maison et du linge du supermarché
 * @param quantite La quantité du produit entretien de maison et du linge du supermarché
 * @param datePeremtion La date de peremtion du produit entretien de maison et du linge du supermarché
 */
	ProduitsEntretienMaisonLinge(String des, Double prix, Integer quantite, String datePeremtion) {
		this.Des = des;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremtion = datePeremtion;
	}
/**
 * Retourner la Des du ProduitsEntretienMaisonLinge
 * @return la designation du produit entretien de maiaosn et du linge du supermarché sous forme d'une chaine de caractére
 */
	public String getDes() {
		return Des;
	}
/**
 * Retourner le Prix du ProduitsEntretienMaisonLinge
 * @return le prix du produit entretien de maiaosn et du linge du supermarché sous forme d'un double
 */
	public Double getPrix() {
		return prix;
	}
/**
 * Retourner la Quantite du ProduitsEntretienMaisonLinge 
 * @return la quantité du produit entretien de maiaosn et du linge du supermarché sous forme d'un entier
 */
	public Integer getQuantite() {
		return quantite;
	}
/**
 * Retourner la DatePeremption du ProduitsEntretienMaisonLinge 
 * @return la date de peremption du produit entretien de maiaosn et du linge du supermarché sous forme d'une chaine de caractére
 */
	public String getDatePeremption() {
		return datePeremtion;
	}
}