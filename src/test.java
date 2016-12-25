import java.util.*;

public class test {
	public static void main(String[] args) {
	ChaineSM chaine = new ChaineSM("LIDL", "ETS", "SM", new ArrayList<SuperMarche>());
	chaine.construireGeneralisteSM();
	chaine.construireGeneralisteSM();
//	chaine.ConstruireSpecialisteSM(TypeRayon.ALIMENTAIRE);
	System.out.println(chaine.getMarchesEnsigne().size());
	
	}
}
