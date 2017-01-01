package metiers;

public abstract class SuperMarche{
	private static int count=1;
	private Integer codeSM;
	private String adresse;
	private Stock stock;
	
	public SuperMarche() {
		super();
	}
	public SuperMarche(String adresse, Stock stock) {
		super();
		this.codeSM = count;
		this.adresse = adresse;
		this.stock = stock;
		count++;
	}
	public Integer getCodeSM() {
		return codeSM;
	}
	public void setCodeSM(Integer codeSM) {
		this.codeSM = codeSM;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
