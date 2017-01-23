package metiers;

import java.util.List;
/**
 * Cette classe représente les rayons entretien de maison et du ligne
 * @author soria
 *
 */
public class RayonEntretienMaisonLinge extends Rayon{
/**
 * Constructeur par défaut
 */
	public RayonEntretienMaisonLinge() {
		super();
		this.setTypeRayon(TypeGeneral.ENTRETIENMAISONSLINGE);
	}
/**
 * Constructeur des rayons entretien de maison et du linge du supermarché
 * @param idRayon L'identificateur du rayon entretien de maison et du linge
 * @param superMarche Le supermarché du rayon entretien de maison et du linge
 * @param typeRayon Le type du rayon durayon entretien de maison et du linge
 * @param etalages Les étalages du rayon entretien de maison et du linge
 */
	public RayonEntretienMaisonLinge(Integer idRayon, SuperMarche superMarche, TypeGeneral typeRayon, List<Etalage> etalages) {
		super(idRayon, superMarche, typeRayon, etalages);
		this.setTypeRayon(TypeGeneral.ENTRETIENMAISONSLINGE);
	}
}
