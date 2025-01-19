/**
 * Nom et prénom: Salifou Zeinab
 * 
 * Nom et prénom: Djama Omar Abdourhamane
 * 
 */

package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Comparator;

// classe de gestion des employes et des chalets
public class GestionChalets {

	// Map statique pour suivre le nombre d'occurrences de chaque identifiant généré
	private static Map<String, Integer> countMap = new HashMap<>();

	// création des listes d'employés et de chalets
	private static ArrayList<Employes> listeEmployes = new ArrayList<>();
	private static ArrayList<Chalets> listeChalets = new ArrayList<>();

	// Méthode Principale
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		// Message initial
		System.out.print("Entrez une commande (ou une ligne vide pour quitter) : ");

		// Boucle pour maintenir le programme en cours jusqu'à ce que l'utilisateur quitte
		while (true) {
		    String commande = scan.nextLine();

		    if (commande.trim().isEmpty()) {
		        break; // Sortir de la boucle si la ligne est vide
		    }

		    if (commande.trim().startsWith("//")) {
		        // Ligne de commentaire, afficher et passer à la suivante
		        System.out.println("Commentaire : " + commande);
		        continue;
		    }

		    // Vérifier le type de commande
		    String[] elements = commande.split(",");
		    String typeCommande = elements[0].toLowerCase();

		    if ("proprio".equals(typeCommande)) {
		        traiterCommandeProprio(elements);
		    } else if ("employe".equals(typeCommande)) {
		        traiterCommandeEmploye(elements);
		    } else {
		        System.out.println("Commande invalide : " + commande);
		    }

		    // Message pour la prochaine itération
		    System.out.print("Entrez une commande (ou une ligne vide pour quitter) : ");
		}

		
	}

	// Méthode pour traiter les commandes des employés
	private static void traiterCommandeEmploye(String[] elements) {

		

		String identifiantEmploye = elements[1].trim();

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);

		// Vérifier si l'employé a été trouvé
		if (employe == null) {
			System.out.println("Identifiant d'employé incorrect.");
			return;
		}

		// Vérifier la commande spécifique de l'employé
		String commande = elements[2].toLowerCase(); // commande en minuscules

		switch (commande) {
		case "afficher_dispo":
			employe.afficherDisponibilites();
			break;
		case "changer_dispo":
			if (elements.length >= 5) {
				String jour = elements[3].toLowerCase();
				boolean nouvelleDisponibilite = Boolean.parseBoolean(elements[4]);
				employe.setDisponibiliteJour(jour, nouvelleDisponibilite);
			} else {
				System.out.println("Commande invalide : la commande doit avoir au moins 5 éléments.");
			}
			break;
		case "chalets_associes":


			employe.afficherChaletsAssocies();
			break;
		case "afficher_salaire":

			System.out.println("Salaire de l'employé " + identifiantEmploye + ": " + employe.getSalaire());
			break;
		default:
			System.out.println("Commande d'employé non reconnue : " + commande);
		}
	}

	// Méthode pour traiter les commandes du proprio
	private static void traiterCommandeProprio(String[] elements) {

		String commandeLowerCase = elements[2].toLowerCase();

		
		switch (commandeLowerCase) {

		case "ajouter_employe":
			ajouterEmploye(elements);
			break;
		case "ajouter_chalet":
			ajouterChalet(elements);
			break;
		case "supprimer_employe":
			supprimerEmploye(elements);
			break;
		case "supprimer_chalet":
			supprimerChalet(elements);
			break;
		case "associer_chalet":
			associerChalet(elements);
			break;
		case "dissocier_chalet":
			dissocierChalet(elements);
			break;
		case "afficher_employe":
			afficherEmploye(elements);
			break;
		case "liste_employes":
			listeEmployes(elements);
			break;
		case "nombre_employes":
			nombreEmployes(elements);
			break;
		case "employes_mieux_payes":
			employesMieuxPayes(elements);
			break;
		case "afficher_chalet":
			afficherChalet(elements);
			break;
		case "liste_chalets":
			listeChalets(elements);
			break;
		case "nombre_chalets":
			nombreChalets(elements);
			break;
		case "salaire_moyen":
			salaireMoyen(elements);
			break;
		case "liste_chalets_associes":
			listeChaletsAssocies(elements);
			break;
		case "dispo_employe":
			dispoEmploye(elements);
			break;
		case "changer_salaire":
			changerSalaire(elements);
			break;
		case "modifier_dispo_chalet":
			modifierDispoChalet(elements);
			break;
		default:
			System.out.println("Commande invalide : " + String.join(", ", elements));
		}
	}

	// Méthodes spécifiques pour chaque commande du proprio

// Méthode pour ajouter un employé
	private static void ajouterEmploye(String[] elements) {
		String nom = elements[3];
		String prenom = elements[4];
		int anneeNaissance = Integer.parseInt(elements[5]);
		double salaire = Double.parseDouble(elements[6]);

		String identifiant = genererIdentifiant(nom, prenom, anneeNaissance);
		int count = countMap.getOrDefault(identifiant, 0) + 1;
		countMap.put(identifiant, count);

		Employes nouvelEmploye = new Employes(nom, prenom, anneeNaissance, salaire, identifiant);
		listeEmployes.add(nouvelEmploye);

		
	}

	// Méthode pour ajouter un chalet
	private static void ajouterChalet(String[] elements) {
		String nomChalet = elements[3];
		String ville = elements[4];
		String adresse = elements[5];
		int nombreChambres = Integer.parseInt(elements[6]);
		int identifiantChalet = Integer.parseInt(elements[7]);

		Chalets nouveauChalet = new Chalets(nomChalet, ville, adresse, nombreChambres, identifiantChalet);
		listeChalets.add(nouveauChalet);

		
	}

	// Méthode pour supprimer un employé
	private static void supprimerEmploye(String[] elements) {
		String identifiant = elements[3];

		Employes employeASupprimer = trouverEmployeParIdentifiant(identifiant);
		if (employeASupprimer != null) {
			listeEmployes.remove(employeASupprimer);
			System.out.println("Employé supprimé : " + employeASupprimer);
		} else {
			System.out.println("Aucun employé trouvé avec l'identifiant : " + identifiant);
		}
	}

// Méthode pour supprimer un chalet
	private static void supprimerChalet(String[] elements) {
		int identifiantChalet = Integer.parseInt(elements[3]);

		Chalets chaletASupprimer = trouverChaletParIdentifiant(identifiantChalet);
		if (chaletASupprimer != null) {
			listeChalets.remove(chaletASupprimer);
			System.out.println("Chalet supprimé : " + chaletASupprimer);
		} else {
			System.out.println("Aucun chalet trouvé avec l'identifiant : " + identifiantChalet);
		}
	}

// Méthode pour associer un chalet a un employé
	private static void associerChalet(String[] elements) {
		int identifiantChalet = Integer.parseInt(elements[3]);
		String identifiantEmploye = elements[4];

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);
		Chalets chalet = trouverChaletParIdentifiant(identifiantChalet);

		if (employe != null && chalet != null) {
			employe.associerChalet(chalet);
			System.out.println("Chalet associé à l'employé : " + chalet + " -> " + employe);
		} else {
			System.out.println("Identifiants invalides. Employé ou chalet introuvable.");
		}
	}

// Méthode pour dissocier un chalet
	private static void dissocierChalet(String[] elements) {
		int identifiantChalet = Integer.parseInt(elements[3]);

		for (Employes employe : listeEmployes) {
			if (employe.getChalet() != null && employe.getChalet().getId() == identifiantChalet) {
				employe.dissocierChalet();
				System.out.println("Chalet dissocié de l'employé : " + identifiantChalet);
				return;
			}
		}

		System.out.println("Aucun employé associé avec le chalet d'identifiant : " + identifiantChalet);
	}

// Méthode pour afficher un employé
	private static void afficherEmploye(String[] elements) {
		String identifiantEmploye = elements[3];
		

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);
		if (employe != null) {
			System.out.println("Informations de l'employé : " + employe);
		} else {
			System.out.println("Aucun employé trouvé avec l'identifiant : " + identifiantEmploye);
		}
	}

// Méthode pour afficher la liste des employés
	private static void listeEmployes(String[] elements) {
		int nombreMaxAfficher = Integer.parseInt(elements[3]);

		System.out.println("Liste des employés :");
		for (int i = 0; i < Math.min(nombreMaxAfficher, listeEmployes.size()); i++) {
			System.out.println(listeEmployes.get(i));
		}
	}

// Méthode pour afficher le nombre d'employés
	private static void nombreEmployes(String[] elements) {
		System.out.println("Nombre total d'employés : " + listeEmployes.size());
	}

// Méthode pour la liste des employés le mieux payé
	private static void employesMieuxPayes(String[] elements) {
		int nombreMaxAfficher = Integer.parseInt(elements[3]);

		listeEmployes.sort(Comparator.comparingDouble(Employes::getSalaire).reversed());

		System.out.println("Liste des employés mieux payés :");
		for (int i = 0; i < Math.min(nombreMaxAfficher, listeEmployes.size()); i++) {
			System.out.println(listeEmployes.get(i));
		}
	}

// Méthode pour afficher un chalet
	private static void afficherChalet(String[] elements) {
		int identifiantChalet = Integer.parseInt(elements[3]);

		Chalets chalet = trouverChaletParIdentifiant(identifiantChalet);
		if (chalet != null) {
			System.out.println("Informations du chalet : " + chalet);
		} else {
			System.out.println("Aucun chalet trouvé avec l'identifiant : " + identifiantChalet);
		}
	}

// Méthode pour afficher la liste des chalets
	private static void listeChalets(String[] elements) {
		int nombreMaxAfficher = Integer.parseInt(elements[3]);

		System.out.println("Liste des chalets :");
		for (int i = 0; i < Math.min(nombreMaxAfficher, listeChalets.size()); i++) {
			System.out.println(listeChalets.get(i));
		}
	}

// Méthode pour afficher le nombre de chalets
	private static void nombreChalets(String[] elements) {
		System.out.println("Nombre total de chalets : " + listeChalets.size());
	}

// Méthode pour afficher le salaire moyen 
	private static void salaireMoyen(String[] elements) {
		double sommeSalaires = 0;

		for (Employes employe : listeEmployes) {
			sommeSalaires += employe.getSalaire();
		}

		double salaireMoyen = listeEmployes.isEmpty() ? 0 : sommeSalaires / listeEmployes.size();
		System.out.println("Salaire moyen des employés : " + salaireMoyen);
	}

// Méthode pour afficher la liste de chalets associés a un employé
	private static void listeChaletsAssocies(String[] elements) {
		String identifiantEmploye = elements[3];

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);
		if (employe != null) {
			System.out.println("Liste des chalets associés à l'employé " + identifiantEmploye + " :");
			if (employe.getChalet() != null) {
				System.out.println(employe.getChalet());
			} else {
				System.out.println("Aucun chalet associé.");
			}
		} else {
			System.out.println("Aucun employé trouvé avec l'identifiant : " + identifiantEmploye);
		}
	}

// Méthode pour afficher les disponibilites d'un employé
	private static void dispoEmploye(String[] elements) {
		String identifiantEmploye = elements[3];

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);
		if (employe != null) {
			System.out.println("Disponibilités de l'employé " + identifiantEmploye + " :");
			System.out.println(employe.getDisponibilites());
		} else {
			System.out.println("Aucun employé trouvé avec l'identifiant : " + identifiantEmploye);
		}
	}

// Méthode pour changer le salaire d'un employé
	private static void changerSalaire(String[] elements) {
		String identifiantEmploye = elements[3];
		double nouveauSalaire = Double.parseDouble(elements[4]);

		Employes employe = trouverEmployeParIdentifiant(identifiantEmploye);
		if (employe != null) {
			employe.setSalaire(nouveauSalaire);
			System.out.println("Salaire de l'employé " + identifiantEmploye + " modifié : " + employe);
		} else {
			System.out.println("Aucun employé trouvé avec l'identifiant : " + identifiantEmploye);
		}
	}

// Méthode pour changer les disponibilites d'un employé
	private static void modifierDispoChalet(String[] elements) {
		int identifiantChalet = Integer.parseInt(elements[3]);
		String etatLocation = elements[4].toLowerCase();

		Chalets chalet = trouverChaletParIdentifiant(identifiantChalet);
		if (chalet != null) {
			if ("reserve".equals(etatLocation)) {
				chalet.setestLoue(true);
			} else if ("libre".equals(etatLocation)) {
				chalet.setestLoue(false);
			} else {
				System.out.println("État de location invalide : " + etatLocation);
				return;
			}

			System.out.println("État de location du chalet " + identifiantChalet + " modifié : " + chalet);
		} else {
			System.out.println("Aucun chalet trouvé avec l'identifiant : " + identifiantChalet);
		}
	}

	// Méthode pour générer l'identifiant d'un employé
	private static String genererIdentifiant(String nom, String prenom, int anneeNaissance) {
		String troisPremieresLettresNom = nom.substring(0, Math.min(3, nom.length())).toLowerCase();
		String deuxDerniersChiffresAnneeNaissance = String.format("%02d", anneeNaissance % 100);
		String deuxPremieresLettresPrenom = prenom.substring(0, Math.min(2, prenom.length())).toLowerCase();

		String identifiantBase = troisPremieresLettresNom + deuxDerniersChiffresAnneeNaissance
				+ deuxPremieresLettresPrenom;

		int index = countMap.getOrDefault(identifiantBase, 0) + 1;
		countMap.put(identifiantBase, index);

		return identifiantBase + index;
	}

	// Méthode pour trouver un employé par son identifiant
	private static Employes trouverEmployeParIdentifiant(String identifiant) {
		for (Employes employe : listeEmployes) {
			if (employe.getIdentifiant().equalsIgnoreCase(identifiant)) {
				return employe;
			}
		}
		return null;
	}

	// Méthode pour trouver un chalet par son identifiant
	private static Chalets trouverChaletParIdentifiant(int identifiant) {
		for (Chalets chalet : listeChalets) {
			if (chalet.getId() == identifiant) {
				return chalet;
			}
		}
		return null;
	}
}
