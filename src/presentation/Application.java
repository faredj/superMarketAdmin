package presentation;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import donnees.*;
import metiers.*;

public class Application {
@SuppressWarnings("resource")
public static void main(String[] args) throws Exception {
	ChaineSmDAO chaineDao = new ChaineSmDAO();
	SupermacheDAO supermacheDao = new SupermacheDAO();
	StockDAO stockDao = new StockDAO();
	RayonDAO rayonDao = new RayonDAO();
	EtalageDAO etalageDao = new EtalageDAO();
	ProduitDAO produitDao = new ProduitDAO();
	VenteDAO venteDao = new VenteDAO();
	PromotionDAO promoDao = new PromotionDAO();
	DecimalFormat df = new DecimalFormat("#.##");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	
	System.out.println("------------------------------------------------------------------------------");
	System.out.println("                       >>Gestion de Supermarchés v1.0<<");
	System.out.println("------------------------------------------------------------------------------");
	System.out.println("Menu principal :");
	System.out.println("    1 > Ajouter une nouvelle chaine de supermachés");
	System.out.println("    2 > Afficher et Gérer les chaines de super marchés existantes");
	System.out.println("    3 > Supprimer une chaine de supermarchés");
	System.out.println("    4 > Quitter");
	System.out.println("------------------------------------------------------------------------------");
	Scanner sc = new Scanner(System.in);
	String choix;
	do  {	
		System.out.print("Action : ");
		choix = sc.nextLine();
	}while((!(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4"))));
	
	while(choix.equals("1") || choix.equals("2") || choix.equals("3")){//Boucle du menu principal
		switch (choix) {
		case "1"://Ajout d'une nouvelle chaine 
			System.out.print("Entrer le nom de la chaine : ");
			String nom = sc.nextLine();
			nom = nom.toLowerCase();
			ChaineSM ch = new ChaineSM(null, nom, new ArrayList<SuperMarche>());
			Integer idEns = chaineDao.insert(ch);//insertion dans la base
			ch.setidEnseigne(idEns);
			if(!idEns.equals(null)){
				System.out.println("Type de supermarchés à construire : 1 > Généraliste | 2 > Spécialiste : ");
				String type ;
				do  {
					System.out.print("Votre choix : ");
					type = sc.nextLine();
				}while((!(type.equals("1") || type.equals("2"))));
				System.out.print("Adresse du SuperMarché : ");
				String adresseSM = sc.nextLine();
				adresseSM = adresseSM.toLowerCase();
				
				if(type.equals("2")){
					System.out.println("Spécialité du supermarchés : ");
					System.out.println("Alimentaire               > 1  | Electroménager   > 2 ");
					System.out.println("Entretien maison et linge > 3  | Hygiène & beauté > 4 ");
					System.out.println("Mode                      > 5  | Multimédia       > 6 ");
					String specialite;
					do  {
						System.out.print("Votre choix : ");
						specialite = sc.nextLine();
					}while((!(specialite.equals("1") || specialite.equals("2")|| specialite.equals("3")|| specialite.equals("4")|| specialite.equals("5")|| specialite.equals("6"))));
					
					switch (specialite) {
						case "1":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ALIMENTAIRE);break;
						case "2":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ELECTROMENAGER);break;
						case "3":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ENTRETIENMAISONSLINGE);break;
						case "4":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.HYGIENEBEAUTE);break;
						case "5":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.MODE);break;
						case "6":ch.ConstruireSpecialisteSM(adresseSM, TypeGeneral.MULTIMEDIA);break;
						default:break;
					}
				}else if (type.equals("1")){
					ch.construireGeneralisteSM(adresseSM);
				}
			}
		break;
		case "2"://AFFICHER ET GERER LES CHAINES EXISTANTES
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("  >>Liste de chaînes de supermarché");
			System.out.println("------------------------------------------------------------------------------");
			List<ChaineSM> listeChaine = chaineDao.findAll();
			if(listeChaine.isEmpty()){
				System.out.println("    >>> Aucune chaine trouvé !");
			}else{
				int i = 1;
				for (ChaineSM chaine : listeChaine) {
					System.out.println("   "+i+" | "+chaine.getNomEnsigne());
					i++;
				}
				System.out.println("------------------------------------------------------------------------------");
				String chaine ;
				boolean existInList = false;
				ChaineSM chaineSM = null;
				do{
					System.out.print("Choix de la chaine à gérer : ");
					chaine = sc.nextLine();
					chaine = chaine.toLowerCase();
					Iterator<ChaineSM> itCh = listeChaine.iterator();
					while(itCh.hasNext() && existInList == false){
						ChaineSM chTmp = itCh.next();
						if(chaine.equals(chTmp.getNomEnsigne())){
							existInList = true;
							chaineSM =  chTmp;
						}
					}
				}while(!existInList);
				List<SuperMarche> listeSuperMarche = chaineSM.retSuperMarcheDeChaine(supermacheDao.findAll());
				chaineSM.setMarchesEnsigne(listeSuperMarche);
				System.out.println();
				System.out.println("Nom de la Chaine : "+chaineSM.getNomEnsigne()+" | Nombre de supermarché : "+listeSuperMarche.size());
				if(listeSuperMarche.isEmpty()){
					System.out.println("   >> Aucun supermarché pour cette chaine !");
				}else{
					int j = 1;
					for (SuperMarche superMarche : listeSuperMarche) {
						String typeSM = superMarche.getClass().getSimpleName().equals("SuperMarcheSpecialiste") ? "Supermarché Spécialiste" : "Supermarché Généraliste";
						System.out.println("    "+j+" - Ref : '"+superMarche.getIdSM()+"' | Type de suppermarché : "+typeSM+" | Adresse : "+superMarche.getAdresse());
						j++;
					}
					System.out.println();
					System.out.println("    Opérations sur la chaine : "+chaineSM.getNomEnsigne());
					System.out.println("    ------------------------------------------------------------------------------");
					System.out.println("        1 > Ajouter un supermarché à la chaine ");
					System.out.println("        2 > Gérer un supermarché");
					System.out.println("        3 > Supprimer un supermarché");
					System.out.println("        4 > Retour");
					String actionSM;
					do  {
						System.out.print("    Action : ");
						actionSM = sc.nextLine();
					}while((!(actionSM.equals("1") || actionSM.equals("2") || actionSM.equals("3") || actionSM.equals("4"))));
					
					while ((actionSM.equals("1") || actionSM.equals("2") || actionSM.equals("3"))) {
						switch (actionSM) {
						case "1"://Ajouter un SuperMarché a la chaine courante
							System.out.println("Type de supermarchés à ajouter : 1 > Généraliste | 2 > Spécialiste : ");
							String type ;
							do  {
								System.out.print("Votre choix : ");
								type = sc.nextLine();
							}while((!(type.equals("1") || type.equals("2"))));
							System.out.print("Adresse du SuperMarché : ");
							String adresseSM = sc.nextLine();
							adresseSM = adresseSM.toLowerCase();
							
							if(type.equals("2")){
								System.out.println("Spécialité du supermarchés : ");
								System.out.println("Alimentaire               > 1  | Electroménager   > 2 ");
								System.out.println("Entretien maison et linge > 3  | Hygiène & beauté > 4 ");
								System.out.println("Mode                      > 5  | Multimédia       > 6 ");
								String specialite;
								do  {
									System.out.print("Votre choix : ");
									specialite = sc.nextLine();
								}while((!(specialite.equals("1") || specialite.equals("2")|| specialite.equals("3")|| specialite.equals("4")|| specialite.equals("5")|| specialite.equals("6"))));
								
								switch (specialite) {
									case "1":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ALIMENTAIRE);break;
									case "2":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ELECTROMENAGER);break;
									case "3":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.ENTRETIENMAISONSLINGE);break;
									case "4":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.HYGIENEBEAUTE);break;
									case "5":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.MODE);break;
									case "6":chaineSM.ConstruireSpecialisteSM(adresseSM, TypeGeneral.MULTIMEDIA);break;
									default:break;
								}
							}else if (type.equals("1")){
								chaineSM.construireGeneralisteSM(adresseSM);
							}
						break;
						case "2"://Gérer un SuperMarché de la chaine courante
							boolean SMInList = false;
							SuperMarche superMarcheToEdit = null;
							do{
								System.out.print("    Référence du supermarché à gérer : ");
								String choixSm = sc.nextLine();
								Integer chSm = choixSm.matches("^[0-9]+$") ? Integer.valueOf(choixSm) : 0;

								Iterator<SuperMarche> itSm = listeSuperMarche.iterator();
								while(itSm.hasNext() && SMInList == false){
									SuperMarche smTmp = itSm.next();
									if(chSm == smTmp.getIdSM()){
										SMInList = true;
										superMarcheToEdit = smTmp;
									}
								}
							}while(!SMInList);
							//Récupération stock et ses produits
							Stock stockOfSm = superMarcheToEdit.retStockOfSuperMarche(stockDao.findAll());
							superMarcheToEdit.setStock(stockOfSm);
							List<Produit> listeProEnStock = stockOfSm.retProduitOfStock(produitDao.findAll());
							for (Produit produit : listeProEnStock) {
								produit.setStock(stockOfSm);
							}
							stockOfSm.setProduits(listeProEnStock);
							stockOfSm.setSuperMarche(superMarcheToEdit);
							
							//récupération des ventes
							Vente venteOfSM = superMarcheToEdit.retVenteOfSuperMarche(venteDao.findAll());
							superMarcheToEdit.setVente(venteOfSM);
							List<Produit> listeProduitEnVente = venteOfSM.retProduitOfVente(produitDao.findAll());
							
							for (Produit produit : listeProduitEnVente) {
								produit.setVente(venteOfSM);
							}
							venteOfSM.setProduits(listeProduitEnVente);
							venteOfSM.setSuperMarche(superMarcheToEdit);
							
							String typeSM = superMarcheToEdit.getClass().getSimpleName().equals("SuperMarcheSpecialiste") ? "Supermarché Spécialiste" : "Supermarché Généraliste";
							//Récupération rayon et ses etalages et produits
							if(superMarcheToEdit.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
								List<Rayon> listeRayonOfSm = superMarcheToEdit.retRayonsOfSuperMarche(rayonDao.findAll());
								superMarcheToEdit.setRayons(listeRayonOfSm);
								Integer nbProduitEnRayon = 0;
								for (Rayon rayon : listeRayonOfSm) {
									List<Etalage> listeEtalageEnRayon = rayon.retEtalagesOfRayon(etalageDao.findAll());
									for (Etalage etalage : listeEtalageEnRayon) {
										List<Produit> listeProduitEnEtalage = etalage.retProduitsOfEtalage(produitDao.findAll());
										for (Produit produit : listeProduitEnEtalage) {
											produit.setEtalage(etalage);
										}
										etalage.setRayon(rayon);
										etalage.setProduits(listeProduitEnEtalage);
										nbProduitEnRayon += listeProduitEnEtalage.size();
									}
									rayon.setEtalages(listeEtalageEnRayon);
									rayon.setSuperMarche(superMarcheToEdit);
								}
								superMarcheToEdit.setRayons(listeRayonOfSm);
								System.out.println();
								System.out.println("    ------------------------------------------------------------------------------");
								System.out.println("    Type du supermarché               : "+typeSM);
								System.out.println("    Adresse du supermarché            : "+superMarcheToEdit.getAdresse());
								System.out.println("    Nombre de produit en stock        : "+listeProEnStock.size());
								System.out.println("    Nombre de produits dans les rayon : "+nbProduitEnRayon);
								System.out.println("    Nombre de produits vendu : "+listeProduitEnVente.size());

								
								System.out.println();
								System.out.println("        Opérations sur le supermarché : ");
								System.out.println("        ------------------------------------------------------------------------------");
								System.out.println("            1 > Afficher la liste des produits en stock");
								System.out.println("            2 > Afficher la liste des prouits dans les étalages");
								System.out.println("            3 > Gérer les ventes");
								System.out.println("            4 > Gérer les promotions");
								System.out.println("            5 > Aprovisionner le stock");
								System.out.println("            6 > Sortie de produit du stock > étalage");
								System.out.println("            7 > Envoyer cargaison vers un autre stock");
								System.out.println("            8 > Modifier un produit");
								System.out.println("           10 > Rechercher des produits");
								System.out.println("           11 > Retour");
								String smOperation;
								System.out.print("        Action : ");
								do  {
									smOperation = sc.nextLine();
								}while((!(smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10") || smOperation.equals("11"))));  
								while ((smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10"))) {
									//////////////////////////OPERATION SM GENERALISTE
									switch (smOperation) {
									case "1":
										System.out.println("   - Liste des produits en stock : ");
										for (Produit produit : listeProEnStock) {
											System.out.println("  - Dés: "+produit.getDesignation()+" | Prix: "+produit.getPrix()+" | Qt: "+produit.getQuantite());
										}
										break;
									case "2":
										System.out.println("   - Liste des produits en rayon : ");
										for (Rayon rayon : listeRayonOfSm) {
											System.out.println(" Rayon : "+rayon.getTypeRayon());
											int e = 1;
											for (Etalage etalage : rayon.getEtalages()) {
												System.out.println("   Etalage "+e+" :");
												for (Produit produit : etalage.getProduits()) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Prix: "+produit.getPrix()+" | Qt: "+produit.getQuantite());
												}
												e++;
											}
										}
										break;
									case "3"://gestion des ventes
										System.out.println(" - Gestion des ventes : ");
										System.out.println("            1 > Ajouter une nouvelle vente");
										System.out.println("            2 > Afficher toutes les ventes");
										System.out.println("            3 > Chercher les vente par date");
										System.out.println("            4 > Chercher les ventes par produit");
										System.out.println("            5 > Retour");
										String opVente;
										System.out.print("        Action : ");
										do  {
											opVente = sc.nextLine();
										}while((!(opVente.equals("1") || opVente.equals("2") || opVente.equals("3")|| opVente.equals("4")|| opVente.equals("5"))));
										while ((opVente.equals("1") || opVente.equals("2") || opVente.equals("3") || opVente.equals("4"))){
											
											switch (opVente) {
											case "1":
												System.out.println(" - Ajouter une nouvelle vente : ");
												String desPrAvendre = "";
												Produit produitVendu = null;
												boolean existEnEtalage = false;
												Integer qt = 0;
												List<Produit> produitsAvendre = new ArrayList<Produit>();
												while (!existEnEtalage) {
													System.out.print("    Désignation du produit :");
													desPrAvendre = sc.nextLine();
													desPrAvendre = desPrAvendre.toLowerCase();
													for (Rayon rayon : listeRayonOfSm) {
														qt += rayon.retQuantiteProduitRayonByDes(desPrAvendre);
														produitsAvendre.addAll(rayon.retProduitRayonByDes(desPrAvendre));
													}
													if(qt > 0){
														existEnEtalage = true;
														Integer QtAvendre = 0;
														while (true) {
														    System.out.print("    Quantité à vendre : ");
														    try {
														    	QtAvendre = Integer.parseInt(sc.next());
																if(qt.compareTo(QtAvendre) > 0  || qt.compareTo(QtAvendre) == 0){
																	Iterator<Produit> iterator = produitsAvendre.iterator();
																	while (iterator.hasNext() && QtAvendre.compareTo(0) > 0) {
																		Produit produitAvendre = iterator.next();
																		if(produitAvendre.getQuantite().compareTo(0) > 0){
																			if(QtAvendre.compareTo(produitAvendre.getQuantite()) < 0){
																				produitVendu = produitAvendre.cloneObject();
																				produitVendu.setQuantite(QtAvendre);
																				Double newPrix=0.0;
																				System.out.println("1 1 > "+produitAvendre.getQuantite());
																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}
																				System.out.println("1 2 > "+produitAvendre.getQuantite());

																				produitAvendre.getEtalage().effectuerVente(produitVendu);
																				QtAvendre = 0;
																			}else if(QtAvendre.compareTo(produitAvendre.getQuantite()) == 0){
																				produitVendu = produitAvendre.cloneObject();
																				Double newPrix=0.0;
																				System.out.println("2 1 > "+produitAvendre.getQuantite());

																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}
																				System.out.println("2 2 > "+produitAvendre.getQuantite());

																				produitAvendre.getEtalage().effectuerVente(produitVendu);
																				QtAvendre = 0;
																			}else{
																				Integer qtPavenre = produitAvendre.getQuantite();
																				produitVendu = produitAvendre.cloneObject();
																				Double newPrix=0.0;
																				System.out.println("3 1 > "+produitAvendre.getQuantite());

																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}
																				System.out.println("3 2 > "+produitAvendre.getQuantite());
																				produitAvendre.getEtalage().effectuerVente(produitVendu);

																				QtAvendre = QtAvendre - qtPavenre;
																			}
																		}
																		
																	}
															        break; 
																}else{
																	System.out.println("    Quantité insuffisante !");
																}
														    } catch (NumberFormatException ignore) {
														    }
														}
													}else{
														System.out.println("    Produit inexistant !");
													}
												}

												break;
											case "2":
												System.out.println(" - Liste de toutes les ventes : ");
												for (Produit produit : listeProduitEnVente) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											case "3":
												System.out.println(" - Rechercher les ventes par dates : ");
												boolean validDateDebut = false;
												String dateDebut = "";
												while (!validDateDebut) {
												    System.out.print("    Date debut : ");
													dateDebut = sc.nextLine();
													validDateDebut = isValidDate(dateDebut);
												}
												boolean validDateFin = false;
												String dateFin="";
												while (!validDateFin) {
												    System.out.print("    Date fin : ");
												    dateFin = sc.nextLine();
												    validDateFin = isValidDate(dateFin);
												}
												List<Produit> lpVenduParDate = superMarcheToEdit.retProduitParInterValTemp(listeProduitEnVente, dateDebut, dateFin);
												for (Produit produit : lpVenduParDate) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											case "4":
												System.out.println(" - Rechercher les ventes par désignation du produit : ");
												System.out.print("    Désignation du produit :");
												String desPrAchercher = sc.nextLine();
												List<Produit> lpVenduParDes = superMarcheToEdit.retProduitVenduParDes(listeProduitEnVente, desPrAchercher);
												for (Produit produit : lpVenduParDes) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											default:
												break;
											}
											System.out.println(" - Gestion des ventes : ");
											System.out.println("            1 > Ajouter une nouvelle vente");
											System.out.println("            2 > Afficher toutes les ventes");
											System.out.println("            3 > Chercher les vente par date");
											System.out.println("            4 > Chercher les ventes par produit");
											System.out.println("            5 > Retour");
											System.out.print("        Action : ");
											do  {
												opVente = sc.nextLine();
											}while((!(opVente.equals("1") || opVente.equals("2") || opVente.equals("3")|| opVente.equals("4")|| opVente.equals("5"))));
										}
										break;
									case "4"://gestion des promotions
										System.out.println(" - Gestion des promotions : ");
										System.out.println("            1 > Ajouter une nouvelle promotion");
										System.out.println("            2 > Afficher tous les produit en promotion");
										System.out.println("            3 > Supprimer une promotion");
										System.out.println("            4 > Retour");
										String opPromo;
										System.out.print("        Action : ");
										do  {
											opPromo = sc.nextLine();
										}while((!(opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3")|| opPromo.equals("4"))));
										
										while((opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3"))){
											switch (opPromo) {
											case "1":
												System.out.println(" - Ajout d'une nouvelle promotion : ");
												String desPrPromo = "";
												Integer nbProduit = 0;
												boolean existEnEtalage = false;
												boolean existEnPromo = false;
												List<Produit> produitsPromo = new ArrayList<Produit>();
												while (!existEnEtalage) {
													System.out.print("    Désignation du produit à mettre en promotion :");
													desPrPromo = sc.nextLine();
													desPrPromo = desPrPromo.toLowerCase();
													for (Rayon rayon : listeRayonOfSm) {
														nbProduit += rayon.retProduitRayonByDes(desPrPromo).size();
														produitsPromo.addAll(rayon.retProduitRayonByDes(desPrPromo));
													}
													if(nbProduit.compareTo(0) > 0){
														existEnEtalage = true;
														List<Promotion> listePromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
														for (Promotion promotion : listePromo) {
															if(promotion.getDesProduit().equals(desPrPromo))
																existEnPromo = true;
														}
														if(!existEnPromo){
															Integer pourcentageRedu = 0;
															while (true) {
															    System.out.print("    Reduction en % : ");
															    try {
															    	pourcentageRedu = Integer.parseInt(sc.next());
															    	if(pourcentageRedu.compareTo(0) > 0 && pourcentageRedu.compareTo(100) < 0){
															    		
															    		Promotion promo = new Promotion(null, superMarcheToEdit, desPrPromo, pourcentageRedu);
															    		Integer idPromo = promoDao.insert(promo);
															    		promo.setIdPromotion(idPromo);
															    		break;
															    	}
															    } catch (NumberFormatException ignore) {
															    }
															}
														}else{
															System.out.println("    Ce produit existe déjà en promotion !");
														}
													}else{
														System.out.println("    Produit inexistant !");
													}
												}
												break;
											case "2":
												System.out.println(" - Liste de toutes les prmotions : ");
												List<Promotion> listePromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
												for (Promotion promotion : listePromo) {
													System.out.println("    Produit : "+promotion.getDesProduit()+"  |  Reduction : "+promotion.getReduction()+"%");
												}
												break;
											case "3":
												System.out.println(" - Supprimer une promotion : ");
												boolean existEnPromo2 = false;
												List<Promotion> listPromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
												while(!existEnPromo2){
													System.out.print("    Désignation du produit en promotion :");
													desPrPromo = sc.nextLine();
													desPrPromo = desPrPromo.toLowerCase();
													Iterator<Promotion> itP = listPromo.iterator();
													while (itP.hasNext() && !existEnPromo2) {
														Promotion promotion = itP.next();
														if(promotion.getDesProduit().equals(desPrPromo)){
															existEnPromo2 = true;
															itP.remove();
															promoDao.delete(promotion);
														}
													}
													if(!existEnPromo2)
														System.out.println("    Ce produit n'existe pas en promotion !");
												}
												break;
											default:
												break;
											}
											System.out.println(" - Gestion des promotions : ");
											System.out.println("            1 > Ajouter une nouvelle promotion");
											System.out.println("            2 > Afficher tous les produit en promotion");
											System.out.println("            3 > Supprimer une promotion");
											System.out.println("            4 > Retour");
											System.out.print("        Action : ");
											do  {
												opPromo = sc.nextLine();
											}while((!(opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3")|| opPromo.equals("4")|| opPromo.equals("5"))));
										}
										break;
									case "5":
										System.out.println(" - Approvisionnement stock : ");
										System.out.print("    Désignation  : ");
										String designation = sc.nextLine();
										designation = designation.toLowerCase();
										Integer quantite = 0;
										while (true) {
										    System.out.print("    Quantité : ");
										    try {
										    	quantite = Integer.parseInt(sc.next());
										        break;
										    } catch (NumberFormatException ignore) {
										    }
										}
										
										if(!(stockOfSm.ifProduitExist(designation))){//si le produit à approvisionner n'exist  en stock
											Double prix = 0.0;
											while (true) {
											    System.out.print("    Prix : ");
											    try {
											    	prix = Double.parseDouble(sc.next());
											        break;
											    } catch (NumberFormatException ignore) {
											    }
											}
											System.out.println("         Alimentaire               > 1  | Electroménager   > 2 ");
											System.out.println("         Entretien maison et linge > 3  | Hygiène & beauté > 4 ");
											System.out.println("         Mode                      > 5  | Multimédia       > 6 ");
											String typeProduit = "";
											do  {
												System.out.print("    Choisir le type : ");
												typeProduit = sc.nextLine();
											}while((!(typeProduit.equals("1") || typeProduit.equals("2")|| typeProduit.equals("3")|| typeProduit.equals("4")|| typeProduit.equals("5")|| typeProduit.equals("6"))));
											
											Produit produit = null;
											boolean entrerDatePer = false;
											switch (typeProduit) {
											case "1":
												produit = new ProduitAlimentaire(null, null, stockOfSm, null, designation, TypeGeneral.ALIMENTAIRE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												entrerDatePer = true;
												break;
											case "2":
												produit = new ProduitElectromenager(null, null, stockOfSm, null, designation, TypeGeneral.ELECTROMENAGER, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "3":
												produit = new ProduitEntretienMaison(null, null, stockOfSm, null, designation, TypeGeneral.ENTRETIENMAISONSLINGE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "4":
												produit = new ProduitHygieneBeaute(null, null, stockOfSm, null, designation, TypeGeneral.HYGIENEBEAUTE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												entrerDatePer = true;
												break;
											case "5":
												produit = new ProduitMode(null, null, stockOfSm, null, designation, TypeGeneral.MODE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "6":
												produit = new ProduitMultimedia(null, null, stockOfSm, null, designation, TypeGeneral.MULTIMEDIA, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											default:break;
											}
											String datePer = "";
											if(entrerDatePer){
												boolean validDatePer = false;
												while (!validDatePer) {
												    System.out.print("    Date péremption : ");
													datePer = sc.nextLine();
													validDatePer = isValidDate(datePer);
												}
												produit.setDatePeremption(datePer);
											}
											stockOfSm.entreVersStock(produit);
										}else{//si le produit a approvisionner exist en stock
											Produit pTmp2 = stockOfSm.chercherProduitEnStockByDes(designation);
											Produit produit = null;
											switch (pTmp2.getType()) {
											case ALIMENTAIRE:
												produit = new ProduitAlimentaire(null, null, stockOfSm, null, designation, TypeGeneral.ALIMENTAIRE, 
														null, quantite, "", null, null);
												break;
											case ELECTROMENAGER:
												produit = new ProduitElectromenager(null, null, stockOfSm, null, designation, TypeGeneral.ELECTROMENAGER, 
														null, quantite, "", null, null);												
												break;												
											case ENTRETIENMAISONSLINGE:
												produit = new ProduitEntretienMaison(null, null, stockOfSm, null, designation, TypeGeneral.ENTRETIENMAISONSLINGE, 
														null, quantite, "", null, null);													
												break;
											case HYGIENEBEAUTE:
												produit = new ProduitHygieneBeaute(null, null, stockOfSm, null, designation, TypeGeneral.HYGIENEBEAUTE, 
														null, quantite, "", null, null);													
												break;
											case MODE:
												produit = new ProduitMode(null, null, stockOfSm, null, designation, TypeGeneral.MODE, 
														null, quantite, "", null, null);													
												break;
											case MULTIMEDIA:
												produit = new ProduitMultimedia(null, null, stockOfSm, null, designation, TypeGeneral.MULTIMEDIA, 
														null, quantite, "", null, null);
												break;
											default:
												break;
											}
											stockOfSm.entreVersStock(produit);
										}

										break;
										
									case "6":
										System.out.println(" - Sortie produit du stock > étalage :");
										String desPr = "";
										Produit PSor = null;
										boolean existEnSt = false;
										boolean sortieOk = false;
										while (!sortieOk) {
											if(desPr.equals("") || !existEnSt){
												System.out.print("    Désignation du produit :");
												desPr = sc.nextLine();
												desPr = desPr.toLowerCase();
												existEnSt = stockOfSm.ifProduitExist(desPr);
												if(existEnSt)
													PSor = stockOfSm.chercherProduitEnStockByDes(desPr);
											}
											Etalage etalageRec = null;
											if(PSor != null){
												Integer QtDeSortie = 0;
												while (true) {
												    System.out.print("    Quantité à sortir : ");
												    try {
												    	QtDeSortie = Integer.parseInt(sc.next());
												        break; 
												    } catch (NumberFormatException ignore) {
												    }
												}
												Produit produitTmp = null;
												switch (PSor.getType()) {
												case ALIMENTAIRE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(0).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);
													break;
												case ELECTROMENAGER:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(1).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;												
												case ENTRETIENMAISONSLINGE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(2).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);														
													break;
												case HYGIENEBEAUTE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(3).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												case MODE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(4).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												case MULTIMEDIA:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(listeRayonOfSm.get(5).getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												default:
													break;
												}
											}
										}
										break;
									case "7":
										System.out.println(" - Envoyer une cargaison :");
										boolean SMInList2 = false;
										SuperMarche superMarcheToRecep = null;
										if(listeSuperMarche.size() > 1){
											List<SuperMarche> listTmp = new ArrayList<SuperMarche>();
											for (SuperMarche superMarche : listeSuperMarche) {
												if(!superMarcheToEdit.equals(superMarche)){
													listTmp.add(superMarche);
													System.out.println("    Réf : "+superMarche.getIdSM()+" | "+superMarche.getClass().getSimpleName()+" Adresse : "+superMarche.getAdresse());
												}
											}
											do{
												System.out.print("    Référence du supermarché qui reçoi la cargaison : ");
												String choixSm = sc.nextLine();
												Integer chSm = choixSm.matches("^[0-9]+$") ? Integer.valueOf(choixSm) : 0;

												Iterator<SuperMarche> itSm = listTmp.iterator();
												while(itSm.hasNext() && SMInList2 == false){
													SuperMarche smTmp = itSm.next();
													if(chSm == smTmp.getIdSM()){
														SMInList2 = true;
														superMarcheToRecep = smTmp;
													}
												}
											}while(!SMInList2);
											boolean envoiOK = false;
											String desProAEnvoyer = "";
											Produit PEnvoyer = null;
											boolean existEnSt2 = false;
											while (!envoiOK) {
												if(desProAEnvoyer.equals("") || !existEnSt2){
													System.out.print("    Désignation du produit :");
													desProAEnvoyer = sc.nextLine();
													desProAEnvoyer = desProAEnvoyer.toLowerCase();
													existEnSt2 = stockOfSm.ifProduitExist(desProAEnvoyer);
													if(existEnSt2)
														PEnvoyer = stockOfSm.chercherProduitEnStockByDes(desProAEnvoyer);
												}
												if(PEnvoyer != null){
													Integer QtDeSortie = 0;
													while (true) {
													    System.out.print("    Quantité à envoyer : ");
													    try {
													    	QtDeSortie = Integer.parseInt(sc.next());
													        break; 
													    } catch (NumberFormatException ignore) {
													    }
													}
													Produit produitTmp = null;
													switch (PEnvoyer.getType()) {
													case ALIMENTAIRE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ALIMENTAIRE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case ELECTROMENAGER:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ELECTROMENAGER)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;												
													case ENTRETIENMAISONSLINGE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ENTRETIENMAISONSLINGE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case HYGIENEBEAUTE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.HYGIENEBEAUTE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case MODE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.MODE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case MULTIMEDIA:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.MULTIMEDIA)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
															envoiOK = true;
														}
														break;
													default:
														break;
													}
												}
											}
											
										}else{
											System.out.println("  Envoi impossible, cette chaine possède un seul supermarché !");
										}
								
										break;
									case "8":
										System.out.println(" - Modification du prix d'un produit :");
										String desPrToEdit = "";
										Produit pEdit = null;
										boolean existStocks = false;
										while (!existStocks) {
											System.out.print("    Désignation du produit :");
											desPrToEdit = sc.nextLine();
											desPrToEdit = desPrToEdit.toLowerCase();
											if(stockOfSm.ifProduitExist(desPrToEdit)){
												pEdit = stockOfSm.chercherProduitEnStockByDes(desPrToEdit);
												existStocks = true;
											}
										}
										Double newPrix = 0.0;
										while (true) {
										    System.out.print("    Prix : ");
										    try {
										    	newPrix = Double.parseDouble(sc.next());
										        break;
										    } catch (NumberFormatException ignore) {
										    }
										}
										pEdit.setPrix(newPrix);
										produitDao.update(pEdit);
										switch (pEdit.getType()) {
										case ALIMENTAIRE:
											pEdit.editListProduit(listeRayonOfSm.get(0).retProduitRayonByDes(pEdit.getDesignation()));
											break;
										case ELECTROMENAGER:
											pEdit.editListProduit(listeRayonOfSm.get(1).retProduitRayonByDes(pEdit.getDesignation()));
											break;												
										case ENTRETIENMAISONSLINGE:
											pEdit.editListProduit(listeRayonOfSm.get(2).retProduitRayonByDes(pEdit.getDesignation()));
											break;
										case HYGIENEBEAUTE:
											pEdit.editListProduit(listeRayonOfSm.get(3).retProduitRayonByDes(pEdit.getDesignation()));
											break;
										case MODE:
											pEdit.editListProduit(listeRayonOfSm.get(4).retProduitRayonByDes(pEdit.getDesignation()));
											break;
										case MULTIMEDIA:
											pEdit.editListProduit(listeRayonOfSm.get(5).retProduitRayonByDes(pEdit.getDesignation()));
											break;
										default:
											break;
										}

										break;
									case "9":
										System.out.println(" - Suppression d'un produit :");
										String desPrToDel = "";
										Produit pDell = null;
										boolean existStocks2 = false;
										while (!existStocks2) {
											System.out.print("    Désignation du produit :");
											desPrToDel = sc.nextLine();
											desPrToDel = desPrToDel.toLowerCase();
											if(stockOfSm.ifProduitExist(desPrToDel)){
												pDell = stockOfSm.chercherProduitEnStockByDes(desPrToDel);
												existStocks2 = true;
											}else
												System.out.println("    >>Produit inexistant !");
										}
										if(pDell.getQuantite().compareTo(0) == 0){//produit n'existe pas en stock
											Integer qtProduitRayon = 0;
											List<Produit> lpTodelete = null;
											switch (pDell.getType()) {
											case ALIMENTAIRE:
												lpTodelete = listeRayonOfSm.get(0).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(0).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;
											case ELECTROMENAGER:
												lpTodelete = listeRayonOfSm.get(1).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(1).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;												
											case ENTRETIENMAISONSLINGE:
												lpTodelete = listeRayonOfSm.get(2).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(2).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;
											case HYGIENEBEAUTE:
												lpTodelete = listeRayonOfSm.get(3).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(3).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;
											case MODE:
												lpTodelete = listeRayonOfSm.get(4).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(4).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;
											case MULTIMEDIA:
												lpTodelete = listeRayonOfSm.get(5).retProduitRayonByDes(pDell.getDesignation());
												qtProduitRayon = listeRayonOfSm.get(5).retQuantiteProduitRayonByDes(pDell.getDesignation());
												break;
											default:
												break;
											}
											if(!(qtProduitRayon.compareTo(0) == 0)){//qt est > 0
												System.out.println("    >>Suppression impossible! quantité dans les etalage > 0");
											}else{
												pDell.deleteListProduit(lpTodelete);
												produitDao.delete(pDell);
												stockOfSm.getProduits().remove(pDell);
											}
										}else{//produit exist en stock
											System.out.println("    >>Suppression impossible! quantité en stock > 0");
										}
										break;
									case "10":
										String opProduit;
										System.out.println("   - Rechercher des produits :");
										System.out.println("            1 > Produits périmés");
										System.out.println("            2 > Produits par prix > prix min");
										System.out.println("            3 > Produits par prix < prix max");
										System.out.println("            4 > Produits par désignation");
										System.out.println("            5 > Produits selon date d'entré en stock");
										System.out.println("            6 > Retour");
										System.out.print("        Action : ");
										do  {
											opProduit = sc.nextLine();
										}while((!(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3") || opProduit.equals("4") || opProduit.equals("5") || opProduit.equals("6"))));
										while(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3")|| opProduit.equals("4") || opProduit.equals("5")){
											
											switch (opProduit) {
											case "1":
												System.out.println("    - Produits périmés : ");
												if(superMarcheToEdit.produitPerimes().size() > 0){
													for (Produit produit : superMarcheToEdit.produitPerimes()) {
														System.out.println("  - Dés : "+produit.getDesignation()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite()+" | Date de péremption : "+produit.getDatePeremption());
													}
												}else{
													System.out.println("    Aucun produit n'est périmés !");
												}
												break;
											case "2":
												System.out.println("    - Produits par prix > prix min : ");
												Double prixMin = 0.0;
												while (true) {
												    System.out.print("    Prix min : ");
												    try {
												    	prixMin = Double.parseDouble(sc.next());
												        break;
												    } catch (NumberFormatException ignore) {
												    }
												}
												List<Produit> listeProduitParPrixMin = superMarcheToEdit.produitEnRayonsParPrixMin(listeRayonOfSm, prixMin);
												if(listeProduitParPrixMin.size() > 0){
													for (Produit produit : listeProduitParPrixMin) {
														System.out.println("  - Dés : "+produit.getDesignation()+"  |  Prix : "+produit.getPrix()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a un prix supérieur à "+prixMin+" !");
												}
												break;
											case "3":
												System.out.println("    - Produits par prix < prix max : ");
												Double prixMax = 0.0;
												while (true) {
												    System.out.print("    Prix max : ");
												    try {
												    	prixMax = Double.parseDouble(sc.next());
												        break;
												    } catch (NumberFormatException ignore) {
												    }
												}
												List<Produit> listeProduitParPrixMax = superMarcheToEdit.produitEnRayonsParPrixMax(listeRayonOfSm, prixMax);
												if(listeProduitParPrixMax.size() > 0){
													for (Produit produit : listeProduitParPrixMax) {
														System.out.println("  - Dés : "+produit.getDesignation()+"  |  Prix : "+produit.getPrix()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a un prix inférieur à "+prixMax+" !");
												}
												break;
											case "4":
												System.out.println("    - Produits par désignation : ");
												String desPrToSer = "";
												Produit pSer = null;
													System.out.print("    Désignation du produit :");
													desPrToSer = sc.nextLine();
													desPrToSer = desPrToSer.toLowerCase();
													if(stockOfSm.ifProduitExist(desPrToSer)){
														pSer = stockOfSm.chercherProduitEnStockByDes(desPrToSer);
														System.out.println("      Prix : "+pSer.getPrix()+"  Quantité : "+pSer.getQuantite()+"  Type : "+pSer.getType().getTypeName()+"  Date peremption : "+pSer.getDatePeremption());
													}else
														System.out.println("    >>Produit inexistant !");
													
												break;
											case "5":
												System.out.println("    - Produits selon date d'entré en stock : ");
												boolean validDatePer = false;
												String dateEntreStock = "";
												while (!validDatePer) {
												    System.out.print("   Saisissez une date : ");
												    dateEntreStock = sc.nextLine();
													validDatePer = isValidDate(dateEntreStock);
												}
										        String dateEntre = new SimpleDateFormat("dd-MM-yyyy").format(dateFormat.parse(dateEntreStock));
												List<Produit> listeProduitParDate = superMarcheToEdit.produitSelonDateEntreStock(dateEntre);
												if(listeProduitParDate.size() > 0){
													for (Produit produit : listeProduitParDate) {
														System.out.println("  - Dés : "+produit.getDesignation()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a entré en stock dans cette date !");
												}
												break;
											default:
												break;
											}
											System.out.println("   - Rechercher des produits :");
											System.out.println("            1 > Produits périmés");
											System.out.println("            2 > Produits par prix > prix min");
											System.out.println("            3 > Produits par prix < prix max");
											System.out.println("            4 > Produits par désignation");
											System.out.println("            5 > Produits selon date d'entré en stock");
											System.out.println("            6 > Retour");
											System.out.print("        Action : ");
											do  {
												opProduit = sc.nextLine();
											}while((!(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3") || opProduit.equals("4") || opProduit.equals("5") || opProduit.equals("6"))));
										}
										break;
									default:break;
									}
									System.out.println();
									System.out.println("        Opérations sur le supermarché : ");
									System.out.println("        ------------------------------------------------------------------------------");
									System.out.println("            1 > Afficher la liste des produits en stock");
									System.out.println("            2 > Afficher la liste des prouits dans les étalages");
									System.out.println("            3 > Gérer les ventes");
									System.out.println("            4 > Gérer les promotions");
									System.out.println("            5 > Aprovisionner le stock");
									System.out.println("            6 > Sortie de produit du stock > étalage");
									System.out.println("            7 > Envoyer cargaison vers un autre stock");
									System.out.println("            8 > Modifier un produit");
									System.out.println("            9 > Supprimer un produit");
									System.out.println("           10 > Rechercher des produits");
									System.out.println("           11 > Retour");
									System.out.print("        Action : ");
									do  {
										smOperation = sc.nextLine();
									}while((!(smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10") || smOperation.equals("11"))));
								}
							}else{
							
								Rayon rayonOfSm = superMarcheToEdit.retRayonOfSuperMarche(rayonDao.findAll());
								superMarcheToEdit.setRayons(rayonOfSm);
								
								List<Etalage> listeEtalageEnRayon = rayonOfSm.retEtalagesOfRayon(etalageDao.findAll());
								Integer nbProduitEnRayon = 0;
								for (Etalage etalage : listeEtalageEnRayon) {
									List<Produit> listeProduitEnEtalage = etalage.retProduitsOfEtalage(produitDao.findAll());
									for (Produit produit : listeProduitEnEtalage) {
										produit.setEtalage(etalage);
									}
									etalage.setRayon(rayonOfSm);
									etalage.setProduits(listeProduitEnEtalage);
									nbProduitEnRayon += listeProduitEnEtalage.size();
								}
								rayonOfSm.setEtalages(listeEtalageEnRayon);
								rayonOfSm.setSuperMarche(superMarcheToEdit);
								superMarcheToEdit.setRayons(rayonOfSm);
								System.out.println();
								System.out.println("    ------------------------------------------------------------------------------");
								System.out.println("    Type du supermarché               : "+typeSM);
								System.out.println("    Adresse du supermarché            : "+superMarcheToEdit.getAdresse());
								System.out.println("    Nombre de produit en stock        : "+listeProEnStock.size());
								System.out.println("    Nombre de produits dans les rayon : "+nbProduitEnRayon);
								System.out.println("    Nombre de produits vendu : "+listeProduitEnVente.size());

								
								System.out.println();
								System.out.println("        Opérations sur le supermarché : ");
								System.out.println("        ------------------------------------------------------------------------------");
								System.out.println("            1 > Afficher la liste des produits en stock");
								System.out.println("            2 > Afficher la liste des prouits dans les étalages");
								System.out.println("            3 > Gérer les ventes");
								System.out.println("            4 > Gérer les promotions");
								System.out.println("            5 > Aprovisionner le stock");
								System.out.println("            6 > Sortie de produit du stock > étalage");
								System.out.println("            7 > Envoyer cargaison vers un autre stock");
								System.out.println("            8 > Modifier un produit");
								System.out.println("           10 > Rechercher des produits");
								System.out.println("           11 > Retour");
								String smOperation;
								System.out.print("        Action : ");
								do  {
									smOperation = sc.nextLine();
								}while((!(smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10") || smOperation.equals("11"))));
								while ((smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10"))) {
									//////////////////////////OPERATION SM SPECIALISTE
									switch (smOperation) {
									case "1":
										System.out.println("   - Liste des produits en stock : ");
										for (Produit produit : listeProEnStock) {
											System.out.println("  - Dés: "+produit.getDesignation()+" | Prix: "+produit.getPrix()+" | Qt: "+produit.getQuantite());
										}
										break;
									case "2":
										System.out.println("   - Liste des produits en rayon : ");
											int e = 1;
											for (Etalage etalage : rayonOfSm.getEtalages()) {
												System.out.println("   Etalage "+e+" :");
												for (Produit produit : etalage.getProduits()) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Prix: "+produit.getPrix()+" | Qt: "+produit.getQuantite());
												}
												e++;
											}
										break;
									case "3"://gestion des ventes
										System.out.println(" - Gestion des ventes : ");
										System.out.println("            1 > Ajouter une nouvelle vente");
										System.out.println("            2 > Afficher toutes les ventes");
										System.out.println("            3 > Chercher les vente par date");
										System.out.println("            4 > Chercher les ventes par produit");
										System.out.println("            5 > Retour");
										String opVente;
										System.out.print("        Action : ");
										do  {
											opVente = sc.nextLine();
										}while((!(opVente.equals("1") || opVente.equals("2") || opVente.equals("3")|| opVente.equals("4")|| opVente.equals("5"))));
										while ((opVente.equals("1") || opVente.equals("2") || opVente.equals("3") || opVente.equals("4"))){
											
											switch (opVente) {
											case "1":
												System.out.println(" - Ajouter une nouvelle vente : ");
												String desPrAvendre = "";
												Produit produitVendu = null;
												boolean existEnEtalage = false;
												Integer qt = 0;
												List<Produit> produitsAvendre = new ArrayList<Produit>();
												while (!existEnEtalage) {
													System.out.print("    Désignation du produit :");
													desPrAvendre = sc.nextLine();
													desPrAvendre = desPrAvendre.toLowerCase();
														qt += rayonOfSm.retQuantiteProduitRayonByDes(desPrAvendre);
														produitsAvendre.addAll(rayonOfSm.retProduitRayonByDes(desPrAvendre));
													if(qt > 0){
														existEnEtalage = true;
														Integer QtAvendre = 0;
														while (true) {
														    System.out.print("    Quantité à vendre : ");
														    try {
														    	QtAvendre = Integer.parseInt(sc.next());
																if(qt.compareTo(QtAvendre) > 0  || qt.compareTo(QtAvendre) == 0){
																	Iterator<Produit> iterator = produitsAvendre.iterator();
																	while (iterator.hasNext() && QtAvendre.compareTo(0) > 0) {
																		Produit produitAvendre = iterator.next();
																		if(produitAvendre.getQuantite().compareTo(0) > 0){
																			if(QtAvendre.compareTo(produitAvendre.getQuantite()) < 0){
																				produitVendu = produitAvendre.cloneObject();
																				produitVendu.setQuantite(QtAvendre);
																				Double newPrix=0.0;
																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}

																				produitAvendre.getEtalage().effectuerVente(produitVendu);
																				QtAvendre = 0;
																			}else if(QtAvendre.compareTo(produitAvendre.getQuantite()) == 0){
																				produitVendu = produitAvendre.cloneObject();
																				Double newPrix=0.0;

																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}

																				produitAvendre.getEtalage().effectuerVente(produitVendu);
																				QtAvendre = 0;
																			}else{
																				Integer qtPavenre = produitAvendre.getQuantite();
																				produitVendu = produitAvendre.cloneObject();
																				Double newPrix=0.0;

																				if(produitVendu.estEnPromo()){
																					newPrix = prixAfterReduc(produitVendu.getPrix(), produitVendu.retReduction());
																					produitVendu.setPrix(newPrix);
																				}
																				produitAvendre.getEtalage().effectuerVente(produitVendu);

																				QtAvendre = QtAvendre - qtPavenre;
																			}
																		}
																		
																	}
															        break; 
																}else{
																	System.out.println("    Quantité insuffisante !");
																}
														    } catch (NumberFormatException ignore) {
														    }
														}
													}else{
														System.out.println("    Produit inexistant !");
													}
												}

												break;
											case "2":
												System.out.println(" - Liste de toutes les ventes : ");
												for (Produit produit : listeProduitEnVente) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											case "3":
												System.out.println(" - Rechercher les ventes par dates : ");
												boolean validDateDebut = false;
												String dateDebut = "";
												while (!validDateDebut) {
												    System.out.print("    Date debut : ");
													dateDebut = sc.nextLine();
													validDateDebut = isValidDate(dateDebut);
												}
												boolean validDateFin = false;
												String dateFin="";
												while (!validDateFin) {
												    System.out.print("    Date fin : ");
												    dateFin = sc.nextLine();
												    validDateFin = isValidDate(dateFin);
												}
												List<Produit> lpVenduParDate = superMarcheToEdit.retProduitParInterValTemp(listeProduitEnVente, dateDebut, dateFin);
												for (Produit produit : lpVenduParDate) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											case "4":
												System.out.println(" - Rechercher les ventes par désignation du produit : ");
												System.out.print("    Désignation du produit :");
												String desPrAchercher = sc.nextLine();
												List<Produit> lpVenduParDes = superMarcheToEdit.retProduitVenduParDes(listeProduitEnVente, desPrAchercher);
												for (Produit produit : lpVenduParDes) {
													System.out.println("  - Dés: "+produit.getDesignation()+" | Qt vendu : "+produit.getQuantite()+" | Prix total : "+df.format((produit.getQuantite()*produit.getPrix()))+" | Date de vente : "+produit.getDateDeVente());
												}
												break;
											default:
												break;
											}
											System.out.println(" - Gestion des ventes : ");
											System.out.println("            1 > Ajouter une nouvelle vente");
											System.out.println("            2 > Afficher toutes les ventes");
											System.out.println("            3 > Chercher les vente par date");
											System.out.println("            4 > Chercher les ventes par produit");
											System.out.println("            5 > Retour");
											System.out.print("        Action : ");
											do  {
												opVente = sc.nextLine();
											}while((!(opVente.equals("1") || opVente.equals("2") || opVente.equals("3")|| opVente.equals("4")|| opVente.equals("5"))));
										}
										break;
									case "4"://gestion des promotions
										System.out.println(" - Gestion des promotions : ");
										System.out.println("            1 > Ajouter une nouvelle promotion");
										System.out.println("            2 > Afficher tous les produit en promotion");
										System.out.println("            3 > Supprimer une promotion");
										System.out.println("            4 > Retour");
										String opPromo;
										System.out.print("        Action : ");
										do  {
											opPromo = sc.nextLine();
										}while((!(opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3")|| opPromo.equals("4"))));
										
										while((opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3"))){
											switch (opPromo) {
											case "1":
												System.out.println(" - Ajout d'une nouvelle promotion : ");
												String desPrPromo = "";
												Integer nbProduit = 0;
												boolean existEnEtalage = false;
												boolean existEnPromo = false;
												List<Produit> produitsPromo = new ArrayList<Produit>();
												while (!existEnEtalage) {
													System.out.print("    Désignation du produit à mettre en promotion :");
													desPrPromo = sc.nextLine();
													desPrPromo = desPrPromo.toLowerCase();
														nbProduit += rayonOfSm.retProduitRayonByDes(desPrPromo).size();
														produitsPromo.addAll(rayonOfSm.retProduitRayonByDes(desPrPromo));
													if(nbProduit.compareTo(0) > 0){
														existEnEtalage = true;
														List<Promotion> listePromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
														for (Promotion promotion : listePromo) {
															if(promotion.getDesProduit().equals(desPrPromo))
																existEnPromo = true;
														}
														if(!existEnPromo){
															Integer pourcentageRedu = 0;
															while (true) {
															    System.out.print("    Reduction en % : ");
															    try {
															    	pourcentageRedu = Integer.parseInt(sc.next());
															    	if(pourcentageRedu.compareTo(0) > 0 && pourcentageRedu.compareTo(100) < 0){
															    		
															    		Promotion promo = new Promotion(null, superMarcheToEdit, desPrPromo, pourcentageRedu);
															    		Integer idPromo = promoDao.insert(promo);
															    		promo.setIdPromotion(idPromo);
															    		break;
															    	}
															    } catch (NumberFormatException ignore) {
															    }
															}
														}else{
															System.out.println("    Ce produit existe déjà en promotion !");
														}
													}else{
														System.out.println("    Produit inexistant !");
													}
												}
												break;
											case "2":
												System.out.println(" - Liste de toutes les prmotions : ");
												List<Promotion> listePromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
												for (Promotion promotion : listePromo) {
													System.out.println("    Produit : "+promotion.getDesProduit()+"  |  Reduction : "+promotion.getReduction()+"%");
												}
												break;
											case "3":
												System.out.println(" - Supprimer une promotion : ");
												boolean existEnPromo2 = false;
												List<Promotion> listPromo = superMarcheToEdit.retPromoOfSuperMarche(promoDao.findAll());
												while(!existEnPromo2){
													System.out.print("    Désignation du produit en promotion :");
													desPrPromo = sc.nextLine();
													desPrPromo = desPrPromo.toLowerCase();
													Iterator<Promotion> itP = listPromo.iterator();
													while (itP.hasNext() && !existEnPromo2) {
														Promotion promotion = itP.next();
														if(promotion.getDesProduit().equals(desPrPromo)){
															existEnPromo2 = true;
															itP.remove();
															promoDao.delete(promotion);
														}
													}
													if(!existEnPromo2)
														System.out.println("    Ce produit n'existe pas en promotion !");
												}
												break;
											default:
												break;
											}
											System.out.println(" - Gestion des promotions : ");
											System.out.println("            1 > Ajouter une nouvelle promotion");
											System.out.println("            2 > Afficher tous les produit en promotion");
											System.out.println("            3 > Supprimer une promotion");
											System.out.println("            4 > Retour");
											System.out.print("        Action : ");
											do  {
												opPromo = sc.nextLine();
											}while((!(opPromo.equals("1") || opPromo.equals("2") || opPromo.equals("3")|| opPromo.equals("4")|| opPromo.equals("5"))));
										}
										break;
									case "5":
										System.out.println(" - Approvisionnement stock : ");
										System.out.print("    Désignation  : ");
										String designation = sc.nextLine();
										designation = designation.toLowerCase();
										Integer quantite = 0;
										while (true) {
										    System.out.print("    Quantité : ");
										    try {
										    	quantite = Integer.parseInt(sc.next());
										        break;
										    } catch (NumberFormatException ignore) {
										    }
										}
										
										if(!(stockOfSm.ifProduitExist(designation))){//si le produit à approvisionner n'exist  en stock
											Double prix = 0.0;
											while (true) {
											    System.out.print("    Prix : ");
											    try {
											    	prix = Double.parseDouble(sc.next());
											        break;
											    } catch (NumberFormatException ignore) {
											    }
											}
											System.out.println("         Alimentaire               > 1  | Electroménager   > 2 ");
											System.out.println("         Entretien maison et linge > 3  | Hygiène & beauté > 4 ");
											System.out.println("         Mode                      > 5  | Multimédia       > 6 ");
											String typeProduit = "";
											do  {
												System.out.print("    Choisir le type : ");
												typeProduit = sc.nextLine();
											}while((!(typeProduit.equals("1") || typeProduit.equals("2")|| typeProduit.equals("3")|| typeProduit.equals("4")|| typeProduit.equals("5")|| typeProduit.equals("6"))));
											
											Produit produit = null;
											boolean entrerDatePer = false;
											switch (typeProduit) {
											case "1":
												produit = new ProduitAlimentaire(null, null, stockOfSm, null, designation, TypeGeneral.ALIMENTAIRE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												entrerDatePer = true;
												break;
											case "2":
												produit = new ProduitElectromenager(null, null, stockOfSm, null, designation, TypeGeneral.ELECTROMENAGER, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "3":
												produit = new ProduitEntretienMaison(null, null, stockOfSm, null, designation, TypeGeneral.ENTRETIENMAISONSLINGE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "4":
												produit = new ProduitHygieneBeaute(null, null, stockOfSm, null, designation, TypeGeneral.HYGIENEBEAUTE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												entrerDatePer = true;
												break;
											case "5":
												produit = new ProduitMode(null, null, stockOfSm, null, designation, TypeGeneral.MODE, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											case "6":
												produit = new ProduitMultimedia(null, null, stockOfSm, null, designation, TypeGeneral.MULTIMEDIA, 
														prix, quantite, "", new SimpleDateFormat("dd-MM-yyyy").format(new Date()), null);
												break;
											default:break;
											}
											String datePer = "";
											if(entrerDatePer){
												boolean validDatePer = false;
												while (!validDatePer) {
												    System.out.print("    Date péremption : ");
													datePer = sc.nextLine();
													validDatePer = isValidDate(datePer);
												}
												produit.setDatePeremption(datePer);
											}
											stockOfSm.entreVersStock(produit);
										}else{//si le produit a approvisionner exist en stock
											Produit pTmp2 = stockOfSm.chercherProduitEnStockByDes(designation);
											Produit produit = null;
											switch (pTmp2.getType()) {
											case ALIMENTAIRE:
												produit = new ProduitAlimentaire(null, null, stockOfSm, null, designation, TypeGeneral.ALIMENTAIRE, 
														null, quantite, "", null, null);
												break;
											case ELECTROMENAGER:
												produit = new ProduitElectromenager(null, null, stockOfSm, null, designation, TypeGeneral.ELECTROMENAGER, 
														null, quantite, "", null, null);												
												break;												
											case ENTRETIENMAISONSLINGE:
												produit = new ProduitEntretienMaison(null, null, stockOfSm, null, designation, TypeGeneral.ENTRETIENMAISONSLINGE, 
														null, quantite, "", null, null);													
												break;
											case HYGIENEBEAUTE:
												produit = new ProduitHygieneBeaute(null, null, stockOfSm, null, designation, TypeGeneral.HYGIENEBEAUTE, 
														null, quantite, "", null, null);													
												break;
											case MODE:
												produit = new ProduitMode(null, null, stockOfSm, null, designation, TypeGeneral.MODE, 
														null, quantite, "", null, null);													
												break;
											case MULTIMEDIA:
												produit = new ProduitMultimedia(null, null, stockOfSm, null, designation, TypeGeneral.MULTIMEDIA, 
														null, quantite, "", null, null);
												break;
											default:
												break;
											}
											stockOfSm.entreVersStock(produit);
										}

										break;
										
									case "6":
										System.out.println(" - Sortie produit du stock > étalage :");
										String desPr = "";
										Produit PSor = null;
										boolean existEnSt = false;
										boolean sortieOk = false;
										while (!sortieOk) {
											if(desPr.equals("") || !existEnSt){
												System.out.print("    Désignation du produit :");
												desPr = sc.nextLine();
												desPr = desPr.toLowerCase();
												existEnSt = stockOfSm.ifProduitExist(desPr);
												if(existEnSt)
													PSor = stockOfSm.chercherProduitEnStockByDes(desPr);
											}
											
											Etalage etalageRec = null;
											if(PSor != null){
												Integer QtDeSortie = 0;
												while (true) {
												    System.out.print("    Quantité à sortir : ");
												    try {
												    	QtDeSortie = Integer.parseInt(sc.next());
												        break; 
												    } catch (NumberFormatException ignore) {
												    }
												}
												Produit produitTmp = null;
												switch (PSor.getType()) {
												case ALIMENTAIRE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);
													break;
												case ELECTROMENAGER:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;												
												case ENTRETIENMAISONSLINGE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);														
													break;
												case HYGIENEBEAUTE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												case MODE:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												case MULTIMEDIA:
													produitTmp = new ProduitAlimentaire(PSor.getIdProduit(), PSor.getEtalage(), PSor.getStock(), PSor.getVente(), PSor.getDesignation(), PSor.getType(), PSor.getPrix(), QtDeSortie, PSor.getDatePeremption(), PSor.getDateEntreStock(), PSor.getDateDeVente());
													etalageRec = getRandonEtalage(rayonOfSm.getEtalages());
													sortieOk = stockOfSm.sortirVerEtalage(produitTmp, etalageRec);													
													break;
												default:
													break;
												}
											}
										}
										break;
									case "7":
										System.out.println(" - Envoyer une cargaison :");
										boolean SMInList2 = false;
										SuperMarche superMarcheToRecep = null;
										if(listeSuperMarche.size() > 1){
											List<SuperMarche> listTmp = new ArrayList<SuperMarche>();
											for (SuperMarche superMarche : listeSuperMarche) {
												if(!superMarcheToEdit.equals(superMarche)){
													listTmp.add(superMarche);
													System.out.println("    Réf : "+superMarche.getIdSM()+" | "+superMarche.getClass().getSimpleName()+" Adresse : "+superMarche.getAdresse());
												}
											}
											do{
												System.out.print("    Référence du supermarché qui reçoi la cargaison : ");
												String choixSm = sc.nextLine();
												Integer chSm = choixSm.matches("^[0-9]+$") ? Integer.valueOf(choixSm) : 0;

												Iterator<SuperMarche> itSm = listTmp.iterator();
												while(itSm.hasNext() && SMInList2 == false){
													SuperMarche smTmp = itSm.next();
													if(chSm == smTmp.getIdSM()){
														SMInList2 = true;
														superMarcheToRecep = smTmp;
													}
												}
											}while(!SMInList2);
											boolean envoiOK = false;
											String desProAEnvoyer = "";
											Produit PEnvoyer = null;
											boolean existEnSt2 = false;
											while (!envoiOK) {
												if(desProAEnvoyer.equals("") || !existEnSt2){
													System.out.print("    Désignation du produit :");
													desProAEnvoyer = sc.nextLine();
													desProAEnvoyer = desProAEnvoyer.toLowerCase();
													existEnSt2 = stockOfSm.ifProduitExist(desProAEnvoyer);
													if(existEnSt2)
														PEnvoyer = stockOfSm.chercherProduitEnStockByDes(desProAEnvoyer);
												}
												if(PEnvoyer != null){
													Integer QtDeSortie = 0;
													while (true) {
													    System.out.print("    Quantité à envoyer : ");
													    try {
													    	QtDeSortie = Integer.parseInt(sc.next());
													        break; 
													    } catch (NumberFormatException ignore) {
													    }
													}
													Produit produitTmp = null;
													switch (PEnvoyer.getType()) {
													case ALIMENTAIRE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ALIMENTAIRE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case ELECTROMENAGER:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ELECTROMENAGER)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;												
													case ENTRETIENMAISONSLINGE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.ENTRETIENMAISONSLINGE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case HYGIENEBEAUTE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.HYGIENEBEAUTE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case MODE:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.MODE)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
														}
														break;
													case MULTIMEDIA:
														produitTmp = new ProduitAlimentaire(PEnvoyer.getIdProduit(), PEnvoyer.getEtalage(), PEnvoyer.getStock(), PEnvoyer.getVente(), PEnvoyer.getDesignation(), PEnvoyer.getType(), PEnvoyer.getPrix(), QtDeSortie, PEnvoyer.getDatePeremption(), PEnvoyer.getDateEntreStock(), PEnvoyer.getDateDeVente());
														if(superMarcheToRecep.getClass().getSimpleName().equals("SuperMarcheGeneraliste")){
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else if(superMarcheToRecep.retRayonOfSuperMarche(rayonDao.findAll()).getTypeRayon().equals(TypeGeneral.MULTIMEDIA)){
															//Récupération stock et ses produits
															Stock stockRec = superMarcheToRecep.retStockOfSuperMarche(stockDao.findAll());
															superMarcheToRecep.setStock(stockRec);
															List<Produit> listeProEnStockRec = stockRec.retProduitOfStock(produitDao.findAll());
															for (Produit produit : listeProEnStockRec) {
																produit.setStock(stockRec);
															}
															stockRec.setProduits(listeProEnStockRec);
															stockRec.setSuperMarche(superMarcheToRecep);
															stockOfSm.envoyerCargaison(produitTmp, stockRec);
															envoiOK = true;
														}else{
															System.out.println("    Impossible d'envoyer ce type de produit !");
															envoiOK = true;
														}
														break;
													default:
														break;
													}
												}
											}
											
										}else{
											System.out.println("  Envoi impossible, cette chaine possède un seul supermarché !");
										}
								
										break;
									case "8":
										System.out.println(" - Modification du prix d'un produit :");
										String desPrToEdit = "";
										Produit pEdit = null;
										boolean existStocks = false;
										while (!existStocks) {
											System.out.print("    Désignation du produit :");
											desPrToEdit = sc.nextLine();
											desPrToEdit = desPrToEdit.toLowerCase();
											if(stockOfSm.ifProduitExist(desPrToEdit)){
												pEdit = stockOfSm.chercherProduitEnStockByDes(desPrToEdit);
												existStocks = true;
											}
										}
										Double newPrix = 0.0;
										while (true) {
										    System.out.print("    Prix : ");
										    try {
										    	newPrix = Double.parseDouble(sc.next());
										        break;
										    } catch (NumberFormatException ignore) {
										    }
										}
										pEdit.setPrix(newPrix);
										produitDao.update(pEdit);
										pEdit.editListProduit(rayonOfSm.retProduitRayonByDes(pEdit.getDesignation()));
										break;
									case "9":
										System.out.println(" - Suppression d'un produit :");
										String desPrToDel = "";
										Produit pDell = null;
										boolean existStocks2 = false;
										while (!existStocks2) {
											System.out.print("    Désignation du produit :");
											desPrToDel = sc.nextLine();
											desPrToDel = desPrToDel.toLowerCase();
											if(stockOfSm.ifProduitExist(desPrToDel)){
												pDell = stockOfSm.chercherProduitEnStockByDes(desPrToDel);
												existStocks2 = true;
											}else
												System.out.println("    >>Produit inexistant !");
										}
										if(pDell.getQuantite().compareTo(0) == 0){//produit n'existe pas en stock
											Integer qtProduitRayon = 0;
											List<Produit> lpTodelete = null;
											lpTodelete = rayonOfSm.retProduitRayonByDes(pDell.getDesignation());
											qtProduitRayon = rayonOfSm.retQuantiteProduitRayonByDes(pDell.getDesignation());
											if(!(qtProduitRayon.compareTo(0) == 0)){//qt est > 0
												System.out.println("    >>Suppression impossible! quantité dans les etalage > 0");
											}else{
												pDell.deleteListProduit(lpTodelete);
												produitDao.delete(pDell);
												stockOfSm.getProduits().remove(pDell);
											}
										}else{//produit exist en stock
											System.out.println("    >>Suppression impossible! quantité en stock > 0");
										}
										break;
									case "10":
										String opProduit;
										System.out.println("   - Rechercher des produits :");
										System.out.println("            1 > Produits périmés");
										System.out.println("            2 > Produits par prix > prix min");
										System.out.println("            3 > Produits par prix < prix max");
										System.out.println("            4 > Produits par désignation");
										System.out.println("            5 > Produits selon date d'entré en stock");
										System.out.println("            6 > Retour");
										System.out.print("        Action : ");
										do  {
											opProduit = sc.nextLine();
										}while((!(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3") || opProduit.equals("4") || opProduit.equals("5") || opProduit.equals("6"))));
										while(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3")|| opProduit.equals("4") || opProduit.equals("5")){
											
											switch (opProduit) {
											case "1":
												System.out.println("    - Produits périmés : ");
												if(superMarcheToEdit.produitPerimes().size() > 0){
													for (Produit produit : superMarcheToEdit.produitPerimes()) {
														System.out.println("  - Dés : "+produit.getDesignation()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite()+" | Date de péremption : "+produit.getDatePeremption());
													}
												}else{
													System.out.println("    Aucun produit n'est périmés !");
												}
												break;
											case "2":
												System.out.println("    - Produits par prix > prix min : ");
												Double prixMin = 0.0;
												while (true) {
												    System.out.print("    Prix min : ");
												    try {
												    	prixMin = Double.parseDouble(sc.next());
												        break;
												    } catch (NumberFormatException ignore) {
												    }
												}
												List<Produit> listeProduitParPrixMin = superMarcheToEdit.produitEnRayonParPrixMin(rayonOfSm, prixMin);
												if(listeProduitParPrixMin.size() > 0){
													for (Produit produit : listeProduitParPrixMin) {
														System.out.println("  - Dés : "+produit.getDesignation()+"  |  Prix : "+produit.getPrix()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a un prix supérieur à "+prixMin+" !");
												}
												break;
											case "3":
												System.out.println("    - Produits par prix < prix max : ");
												Double prixMax = 0.0;
												while (true) {
												    System.out.print("    Prix max : ");
												    try {
												    	prixMax = Double.parseDouble(sc.next());
												        break;
												    } catch (NumberFormatException ignore) {
												    }
												}
												List<Produit> listeProduitParPrixMax = superMarcheToEdit.produitEnRayonParPrixMax(rayonOfSm, prixMax);
												if(listeProduitParPrixMax.size() > 0){
													for (Produit produit : listeProduitParPrixMax) {
														System.out.println("  - Dés : "+produit.getDesignation()+"  |  Prix : "+produit.getPrix()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a un prix inférieur à "+prixMax+" !");
												}
												break;
											case "4":
												System.out.println("    - Produits par désignation : ");
												String desPrToSer = "";
												Produit pSer = null;
													System.out.print("    Désignation du produit :");
													desPrToSer = sc.nextLine();
													desPrToSer = desPrToSer.toLowerCase();
													if(stockOfSm.ifProduitExist(desPrToSer)){
														pSer = stockOfSm.chercherProduitEnStockByDes(desPrToSer);
														System.out.println("      Prix : "+pSer.getPrix()+"  Quantité : "+pSer.getQuantite()+"  Type : "+pSer.getType().getTypeName()+"  Date peremption : "+pSer.getDatePeremption());
													}else
														System.out.println("    >>Produit inexistant !");
													
												break;
											case "5":
												System.out.println("    - Produits selon date d'entré en stock : ");
												boolean validDatePer = false;
												String dateEntreStock = "";
												while (!validDatePer) {
												    System.out.print("   Saisissez une date : ");
												    dateEntreStock = sc.nextLine();
													validDatePer = isValidDate(dateEntreStock);
												}
										        String dateEntre = new SimpleDateFormat("dd-MM-yyyy").format(dateFormat.parse(dateEntreStock));
												List<Produit> listeProduitParDate = superMarcheToEdit.produitSelonDateEntreStock(dateEntre);
												if(listeProduitParDate.size() > 0){
													for (Produit produit : listeProduitParDate) {
														System.out.println("  - Dés : "+produit.getDesignation()+" | Type : "+produit.getType().getTypeName()+" | Qt : "+produit.getQuantite());
													}
												}else{
													System.out.println("    Aucun produit n'a entré en stock dans cette date !");
												}
												break;
											default:
												break;
											}
											System.out.println("   - Rechercher des produits :");
											System.out.println("            1 > Produits périmés");
											System.out.println("            2 > Produits par prix > prix min");
											System.out.println("            3 > Produits par prix < prix max");
											System.out.println("            4 > Produits par désignation");
											System.out.println("            5 > Produits selon date d'entré en stock");
											System.out.println("            6 > Retour");
											System.out.print("        Action : ");
											do  {
												opProduit = sc.nextLine();
											}while((!(opProduit.equals("1") || opProduit.equals("2") || opProduit.equals("3") || opProduit.equals("4") || opProduit.equals("5") || opProduit.equals("6"))));
										}
										break;
									default:break;
									}
									System.out.println();
									System.out.println("    ------------------------------------------------------------------------------");
									System.out.println("    Type du supermarché               : "+typeSM);
									System.out.println("    Adresse du supermarché            : "+superMarcheToEdit.getAdresse());
									System.out.println("    Nombre de produit en stock        : "+listeProEnStock.size());
									System.out.println("    Nombre de produits dans les rayon : "+nbProduitEnRayon);
									System.out.println("    Nombre de produits vendu : "+listeProduitEnVente.size());

									
									System.out.println();
									System.out.println("        Opérations sur le supermarché : ");
									System.out.println("        ------------------------------------------------------------------------------");
									System.out.println("            1 > Afficher la liste des produits en stock");
									System.out.println("            2 > Afficher la liste des prouits dans les étalages");
									System.out.println("            3 > Gérer les ventes");
									System.out.println("            4 > Gérer les promotions");
									System.out.println("            5 > Aprovisionner le stock");
									System.out.println("            6 > Sortie de produit du stock > étalage");
									System.out.println("            7 > Envoyer cargaison vers un autre stock");
									System.out.println("            8 > Modifier un produit");
									System.out.println("           10 > Rechercher des produits");
									System.out.println("           11 > Retour");
									System.out.print("        Action : ");
									do  {
										smOperation = sc.nextLine();
									}while((!(smOperation.equals("1") || smOperation.equals("2") || smOperation.equals("3") || smOperation.equals("4") || smOperation.equals("5") || smOperation.equals("6") || smOperation.equals("7") || smOperation.equals("8") || smOperation.equals("9") || smOperation.equals("10") || smOperation.equals("11"))));
								}
							}
							
							break;
						case "3"://Supprimer un SuperMarché de la chaine courante
							boolean SMInListToDel = false;
							SuperMarche superMarcheToDel = null;
							do{
								System.out.print("    Référence du supermarché à supprimer : ");
								String choixSm = sc.nextLine();
								Integer chSm = choixSm.matches("^[0-9]+$") ? Integer.valueOf(choixSm) : 0;
								
								Iterator<SuperMarche> itSm = listeSuperMarche.iterator();
								while(itSm.hasNext() && SMInListToDel == false){
									SuperMarche smTmp = itSm.next();
									if(chSm == smTmp.getIdSM()){
										SMInListToDel = true;
										superMarcheToDel = smTmp;
									}
								}
							}while(!SMInListToDel);
							supermacheDao.delete(superMarcheToDel);
							listeSuperMarche.remove(superMarcheToDel);
							break;
						default:break;
						}
						int k = 1;
						for (SuperMarche superMarche : listeSuperMarche) {
							String typeSM = superMarche.getClass().getSimpleName().equals("SuperMarcheSpecialiste") ? "Supermarché Spécialiste" : "Supermarché Généraliste";
							System.out.println("    "+k+" - Ref : '"+superMarche.getIdSM()+"' | Type de suppermarché : "+typeSM+" | Adresse : "+superMarche.getAdresse());
							k++;
						}
						System.out.println();
						System.out.println("    Opérations sur la chaine: "+chaineSM.getNomEnsigne());
						System.out.println("    ------------------------------------------------------------------------------");
						System.out.println("        1 > Ajouter un supermarché à la chaine ");
						System.out.println("        2 > Gérer un supermarché");
						System.out.println("        3 > Supprimer un supermarché");
						System.out.println("        4 > Retour");
						do  {
							System.out.print("    Action : ");
							actionSM = sc.nextLine();
						}while((!(actionSM.equals("1") || actionSM.equals("2") || actionSM.equals("3") || actionSM.equals("4"))));						
					}
				}

			}
			break;
		case "3":
			System.out.println("  - Supprimer une chaine de supermaché : ");
			List<ChaineSM> listeChaineToDel = chaineDao.findAll();
			if(listeChaineToDel.isEmpty()){
				System.out.println("    >>> Aucune chaine trouvé !");
			}else{
				int i = 1;
				for (ChaineSM chaine : listeChaineToDel) {
					System.out.println("   "+i+" | "+chaine.getNomEnsigne());
					i++;
				}
				System.out.println("------------------------------------------------------------------------------");
				String chaine ;
				boolean existInList = false;
				ChaineSM chaineSM = null;
				do{
					System.out.print("Choix de la chaine à gérer : ");
					chaine = sc.nextLine();
					chaine = chaine.toLowerCase();
					Iterator<ChaineSM> itCh = listeChaineToDel.iterator();
					while(itCh.hasNext() && existInList == false){
						ChaineSM chTmp = itCh.next();
						if(chaine.equals(chTmp.getNomEnsigne())){
							existInList = true;
							chaineSM =  chTmp;
						}
					}
				}while(!existInList);
				chaineDao.delete(chaineSM);
				listeChaineToDel.remove(chaineSM);
			}
			break;
		default:break;
		}
		//Menu principal
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("                       >>Gestion de Supermarchés v1.0<<");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Menu principal :");
		System.out.println("    1 > Ajouter une nouvelle chaine de supermachés");
		System.out.println("    2 > Afficher et Gérer les chaines de super marchés existantes");
		System.out.println("    3 > Supprimer une chaine de supermarchés");
		System.out.println("    4 > Quitter");
		System.out.println("------------------------------------------------------------------------------");
		do  {
			System.out.print("Action : ");
			choix = sc.nextLine();
		}while((!(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4"))));
	}
	System.out.println();
	System.out.println("   >>Au revoir !");
}
	/*
	 * Teste une date si il est valide
	 * @param input date a tester
	 * @return true si date valide false sinon
	 */
	public static boolean isValidDate(String input) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false);
	    try {
	         format.parse(input);
	         return true;
	    }
	    catch(ParseException e){
	         return false;
	    }
	}
	/*
	 * Choisi aléatoirement un étalage dans une liste
	 * @param le liste des étalage
	 * @return étalage choisi
	 */
	public static Etalage getRandonEtalage(List<Etalage> le){
		Random rand = new Random();
		Integer ran = rand.nextInt(le.size());
		return le.get(ran);
	}
	/*
	 * Applique le pourcentage de réduction
	 * @param oldPrix l'ancien prix
	 * @param reduc la réduction en pourcentage
	 * @return nouveau prix après reduction
	 */
	public static Double prixAfterReduc(Double oldPrix, Integer reduc){
		return (oldPrix * reduc)/100;
	}
}
