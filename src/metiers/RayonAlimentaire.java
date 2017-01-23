package metiers;
import java.util.*;
/**
 * Cette classe représente les rayons alimentaire 
 * @author soria
 *
 */
public class RayonAlimentaire extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonAlimentaire() {
		super();
		this.setTypeRayon(TypeGeneral.ALIMENTAIRE);
	}
/**
 * Constructeur des rayons alimentaires du supermarché 
 * @param idRayon L'identificateur du rayon alimenataire 
 * @param superMarche Le supermarché du rayon alimentaire
 * @param typeRayon Le type du rayon durayon alimentaire 
 * @param etalages Les étalages du rayon alimenatire
 */
	public RayonAlimentaire(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.ALIMENTAIRE);
	}
}