import java.util.*;

public class Stock {
	private String codeStock;
	private Map<Produit, Date> produits;
	public Stock() {
		super();
	}
	
	public Stock(String codeStock, Map<Produit, Date> produits) {
		super();
		this.codeStock = codeStock;
		this.produits = produits;
	}
	//Getters end Setters
	public String getCodeStock() {
		return codeStock;
	}
	public void setCodeStock(String codeStock) {
		this.codeStock = codeStock;
	}
	public Map<Produit, Date> getProduits() {
		return produits;
	}
	public void setProduits(Map<Produit, Date> produits) {
		this.produits = produits;
	}
	
	public void entreVersStock(Produit p){//approvisionnement (entré stock)
		boolean exist = false;
		for (Produit p1 : produits.keySet()) {
			if(p1.equals(p)){
				exist = true;
				p1.setQuantite(p1.getQuantite()+p.getQuantite());
			}
		}
		if(!exist)
			produits.put(p, new Date());
	}
	
	public void sortirVerEtalage(Produit p, Etalage e){//sortie produit vers etalage
		boolean existEnStock = false;
		for (Produit p1 : produits.keySet()) {
			if(p.equals(p1)){
				existEnStock = true;
				p1.setQuantite(p1.getQuantite()-p.getQuantite());
			}
		}
		if(existEnStock){//le produit sortira vers l'etalage e, s'il exist en stock
			boolean existEnEtalage = false;
			for (Produit p2 : e.getProduits()) {
				if(p2.equals(p)){
					existEnEtalage = true;
					p2.setQuantite(p2.getQuantite()+p.getQuantite());
				}
			}
			if(!existEnEtalage)
				e.getProduits().add(p);
		}
	}
	
	public Integer returnNbProduit(String s){//renvoyer la quantité de produit en stock selon son "nom"
		Set<Produit> lp = produits.keySet();
		return lp.stream()
				 .filter(p->p.getDesignation().equals(s))
				 .mapToInt(p2->p2.getQuantite())
				 .sum();
	}
	
	public void envoyerCargaison(Produit p, Stock s){//envoyer une cargaison vers un autres stock (autre super marché)
		boolean existEnStock1 = false;
		for (Produit p1 : produits.keySet()) {
			if(p.equals(p1)){
				existEnStock1 = true;
				p1.setQuantite(p1.getQuantite()-p.getQuantite());
			}
		}
		if(existEnStock1){//l'envoi de la cargaison se fera si le produit exist en stock emeteur
			boolean existEnStock2 = false;
			for (Produit p2 : s.getProduits().keySet()) {
				if(p2.equals(p)){
					existEnStock2 = true;
					p2.setQuantite(p2.getQuantite()+p.getQuantite());//si le produit exist dans le stock recepteur on va augmenter juste la quantité
				}
			}
			if(!existEnStock2)
				s.getProduits().put(p, new Date());//si le produit n'exite pas dans le stock recepteur on va l'ajouter a la map des produits
		}
	}
	
	
}