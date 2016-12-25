
public class SuperMarcheSpecialiste extends SuperMarche{
	private Rayon rayon;

	public SuperMarcheSpecialiste() {
		super();
	}

	public SuperMarcheSpecialiste(String adresse, Stock stock, Rayon rayon) {
		super(adresse, stock);
		this.rayon=rayon;
	}

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
	
}
