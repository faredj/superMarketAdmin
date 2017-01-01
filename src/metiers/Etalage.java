package metiers;
import java.util.*;

public abstract class Etalage {
	private String codeEtalage;
	private Rayon rayonEtalage;
	private List<Produit> produits;
	public Etalage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etalage(String codeEtalage, Rayon rayonEtalage, List<Produit> produits) {
		super();
		this.codeEtalage = codeEtalage;
		this.rayonEtalage = rayonEtalage;
		this.produits = produits;
	}
	public String getCodeEtalage() {
		return codeEtalage;
	}
	public void setCodeEtalage(String codeEtalage) {
		this.codeEtalage = codeEtalage;
	}
	public Rayon getRayonEtalage() {
		return rayonEtalage;
	}
	public void setRayonEtalage(Rayon rayonEtalage) {
		this.rayonEtalage = rayonEtalage;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
}
