package metiers;

import java.util.List;
/**
 * Cette classe représente les rayons electromenagers du supermarché
 * @author soria
 *
 */
public class RayonElectromenager extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonElectromenager() {
		super();
		this.setTypeRayon(TypeGeneral.ELECTROMENAGER);
	}
/**
 * Constructeur des rayons electromenagers du supermarché 
 * @param idRayon L'identificateur du rayon electromenager
 * @param superMarche Le supermarché du rayon electromenager
 * @param typeRayon Le type du rayon durayon electromenager 
 * @param etalages Les étalages du rayon electromenager
 */
	public RayonElectromenager(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.ELECTROMENAGER);
	}
}
