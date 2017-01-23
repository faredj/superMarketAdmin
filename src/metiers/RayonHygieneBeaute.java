package metiers;

import java.util.List;
/**
 * Cette classe représente les rayons hygiéne et beauté du supermarché
 * @author soria
 *
 */
public class RayonHygieneBeaute extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonHygieneBeaute() {
		super();
		this.setTypeRayon(TypeGeneral.HYGIENEBEAUTE);
	}
/**
 * Constructeur des rayons d'hygiéne et beauté du supermarché 
 * @param idRayon L'identificateur du rayon d'hygiéne et beauté
 * @param superMarche Le supermarché du rayon d'hygiéne et beauté
 * @param typeRayon Le type du rayon durayon d'hygiéne et beauté
 * @param etalages Les étalages du rayon d'hygiéne et beauté
 */
	public RayonHygieneBeaute(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.HYGIENEBEAUTE);
	}
}
