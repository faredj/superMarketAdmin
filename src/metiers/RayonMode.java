package metiers;

import java.util.List;
/**
 * Cette classe représente les rayons mode du supermarché 
 * @author soria
 *
 */
public class RayonMode extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonMode() {
		super();
		this.setTypeRayon(TypeGeneral.MODE);
	}
/**
 * Constructeur des rayons mode du supermarché
 * @param idRayon L'identificateur du rayon mode
 * @param superMarche Le supermarché du rayon mode
 * @param typeRayon Le type du rayon du rayon mode
 * @param etalages Les étalages du rayon mode
 */
	public RayonMode(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.MODE);
	}
}