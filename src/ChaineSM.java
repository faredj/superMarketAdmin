import java.util.*;

public class ChaineSM{
	private String nomEnsigne ;
	private String formeJuridique;
	private String TypeActivite;
	private List<SuperMarche> marchesEnsigne;
	
	public ChaineSM() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChaineSM(String nomEnsigne, String formeJuridique, String typeActivite, List<SuperMarche> marchesEnsigne) {
		super();
		this.nomEnsigne = nomEnsigne;
		this.formeJuridique = formeJuridique;
		TypeActivite = typeActivite;
		this.marchesEnsigne = marchesEnsigne;
	}

	public String getNomEnsigne() {
		return nomEnsigne;
	}

	public void setNomEnsigne(String nomEnsigne) {
		this.nomEnsigne = nomEnsigne;
	}

	public String getFormeJuridique() {
		return formeJuridique;
	}

	public void setFormeJuridique(String formeJuridique) {
		this.formeJuridique = formeJuridique;
	}

	public String getTypeActivite() {
		return TypeActivite;
	}

	public void setTypeActivite(String typeActivite) {
		TypeActivite = typeActivite;
	}

	public List<SuperMarche> getMarchesEnsigne() {
		return marchesEnsigne;
	}

	public void setMarchesEnsigne(List<SuperMarche> marchesEnsigne) {
		this.marchesEnsigne = marchesEnsigne;
	}
	public void construireGeneralisteSM(){
		SuperMarcheGeneraliste sm = new SuperMarcheGeneraliste();
		sm.setStock(new Stock());
		List<Rayon> rayons = new ArrayList<Rayon>();
		rayons.add(new RayonAlimentaire());
		rayons.add(new RayonElectromenager());
		rayons.add(new RayonEntretienMaisonLinge());
		rayons.add(new RayonHygieneBeaute());
		rayons.add(new RayonMode());
		rayons.add(new RayonMultimedia());
		sm.setRayons(rayons);
		this.marchesEnsigne.add(sm);
	}
	public void ConstruireSpecialisteSM(TypeRayon type){
		SuperMarcheSpecialiste sm = new SuperMarcheSpecialiste();
		sm.setStock(new Stock());
		switch (type) {
		case ALIMENTAIRE:sm.setRayon(new RayonAlimentaire());break;
		case ELECTROMENAGER:sm.setRayon(new RayonElectromenager());break;
		case ENTRETIENMAISONSLINGE:sm.setRayon(new RayonEntretienMaisonLinge());break;
		case HYGIENEBEAUTE:sm.setRayon(new RayonHygieneBeaute());break;
		case MODE:sm.setRayon(new RayonMode());break;
		case MULTIMEDIA:sm.setRayon(new RayonMultimedia());break;
		default:break;
		}
		this.getMarchesEnsigne().add(sm);
	}
	public enum TypeRayon{
		ALIMENTAIRE,
		ELECTROMENAGER,
		ENTRETIENMAISONSLINGE,
		HYGIENEBEAUTE,
		MODE,
		MULTIMEDIA
	}
}
