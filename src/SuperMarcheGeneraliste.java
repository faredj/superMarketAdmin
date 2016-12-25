import java.util.List;

public class SuperMarcheGeneraliste extends SuperMarche{
	private List<Rayon> rayons;

	public SuperMarcheGeneraliste() {
		super();
	}
	public SuperMarcheGeneraliste(String adresse, Stock stock, List<Rayon> rayons) {
		super(adresse, stock);
		this.rayons=rayons;
	}
	public List<Rayon> getRayons() {
		return rayons;
	}

	public void setRayons(List<Rayon> rayons) {
		this.rayons = rayons;
	}
}
