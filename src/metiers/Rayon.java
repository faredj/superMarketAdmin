package metiers;
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
	//Getters et Setters
	public String getCodeRayon() {
		return codeRayon;
	}
	public void setCodeRayon(String codeRayon) {
		this.codeRayon = codeRayon;
	}
	public String getNomRayon() {
		return nomRayon;
	}
	public void setNomRayon(String nomRayon) {
		this.nomRayon = nomRayon;
	}
	public List<Etalage> getEtalages() {
		return etalages;
	}
	public void setEtalages(List<Etalage> etalages) {
		this.etalages = etalages;
	}
	
}