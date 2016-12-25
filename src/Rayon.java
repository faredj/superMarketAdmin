import java.util.*;

public abstract class Rayon {
	private String codeRayon;
	private String nomRayon;
	private List<Etalage> etalages;
	public Rayon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rayon(String codeRayon, String nomRayon, List<Etalage> etalages) {
		super();
		this.codeRayon = codeRayon;
		this.nomRayon = nomRayon;
		this.etalages = etalages;
	}
	
}