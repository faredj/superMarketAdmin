package metiers;
import java.util.Date;

public class Produit {
	private String codeProduit;
	private String designation;
	private Double prix;
	private Integer quantite;
	private Date datePeremption;
	
	public Produit() {
		super();
	}
	public Produit(String codeProduit, String designation, Double prix, Integer quantite, Date datePeremption) {
		super();
		this.codeProduit = codeProduit;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.datePeremption = datePeremption;
	}
	
	//Getters et Setters 
	public String getCodeProduit() {
		return codeProduit;
	}
	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public Date getDatePeremption() {
		return datePeremption;
	}
	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (codeProduit == null) {
			if (other.codeProduit != null)
				return false;
		} else if (!codeProduit.equals(other.codeProduit))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (prix == null) {
			if (other.prix != null)
				return false;
		} else if (!prix.equals(other.prix))
			return false;
		return true;
	}
	
}
