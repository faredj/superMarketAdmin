package metiers;
/**
 * Enumérateur des type general des supermarchés
 * @author faredj
 *
 */
public enum TypeGeneral{
	
	ALIMENTAIRE("Alimentaire"),
	ELECTROMENAGER("Electroménager"),
	ENTRETIENMAISONSLINGE("Entretien maison et linge"),
	HYGIENEBEAUTE("Hygiéne et beauté"),
	MODE("Mode"),
	MULTIMEDIA("Multimédia");
/**
 * Le nom du type du supermarché n'est pas modifiable
 * @see TypeGeneral#typeName
 * @see TypeGeneral#getTypeName()	
 */	
	private String typeName;
/**
 * Constructeur des type general des supermarchés
 * @param typeName Le nom du type du supermarché
 */
	TypeGeneral(String typeName){
		this.typeName = typeName;
	}
/**
 * Retourne le TypeName du TypeGeneral
 * @return le nom du type de TypeGeneral du supermarché
 */
	public String getTypeName() {
		return typeName;
	}
}