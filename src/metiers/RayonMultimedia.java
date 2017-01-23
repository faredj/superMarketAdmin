package metiers;

import java.util.List;
/**
 *  Cette classe représente les rayons multimedia du supermarché
 * @author soria
 *
 */
public class RayonMultimedia extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonMultimedia() {
		super();
		this.setTypeRayon(TypeGeneral.MULTIMEDIA);
	}
/**
 * Constructeur des rayons multimedia du supermarché
 * @param idRayon L'identificateur du rayon multimedia
 * @param superMarche Le supermarché du rayon multimedia
 * @param typeRayon Le type du rayon du rayon multimedia
 * @param etalages Les étalages du rayon multimedia
 */
	public RayonMultimedia(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.MULTIMEDIA);
	}
}
