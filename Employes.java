/**
 * Nom et prénom: Salifou Zeinab
 * 
 * Nom et prénom: Djama Omar Abdourhamane
 * 
 */
package src;

import java.util.ArrayList;

public class Employes {

//------Declaration des variables*******

	private String nom;
	private String prenom;
	private int anneeNaissance;
	public String identifiant;
	private double salaire;
	private boolean[] disponibilites = new boolean[7];
	private Chalets[] chaletsEntretenus = new Chalets[4];

	// ------Constructeur***
	public Employes(String nom, String prenom, int anneeNaissance, double salaire, String identifiant) {

		// ------Initialisation des variables***

		this.nom = nom;
		this.prenom = prenom;
		this.salaire = salaire;
		this.anneeNaissance = anneeNaissance;
		this.identifiant = identifiant;
		this.disponibilites = new boolean[7];
		this.chaletsEntretenus = new Chalets[4];
	}

	// -----------Getters***
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public double getSalaire() {
		return salaire;
	}

	public int getAnneeNaissance() {
		return anneeNaissance;
	}

	public String getIdentifiant() {
		return identifiant;
	}

//-----Autres méthodes***
	public Chalets[] getChaletsEntretenus() {
		return chaletsEntretenus;
	}

	public boolean[] getDisponibilites() {
		return disponibilites;
	}

	// -----Setters***
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	// Méthode pour associer un chalet a un employé
	public void associerChalet(Chalets chalet) {

		for (int i = 0; i < chaletsEntretenus.length; i++) {
			if (chaletsEntretenus[i] == null) {
				chaletsEntretenus[i] = chalet;
				System.out.println("Chalet associé à l'employé : " + chalet + " -> " + this);
				return;
			}
		}
		System.out.println("Impossible d'associer plus de chalets à l'employé.");
	}

	public Chalets getChalet() {

		for (Chalets chalet : chaletsEntretenus) {
			if (chalet != null) {
				return chalet;
			}
		}
		return null; // Si aucun chalet n'est associé à cet employé
	}

	// Méthode pour dissocier un chalet
	public void dissocierChalet() {

		for (int i = 0; i < chaletsEntretenus.length; i++) {
			chaletsEntretenus[i] = null;
		}

	}

	// Méthode pour afficher les disponibilités d'un employé
	public void afficherDisponibilites() {
		System.out.println("Disponibilités de l'employé " + identifiant + ":");
		String[] joursSemaine = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };

		for (int i = 0; i < disponibilites.length; i++) {
			System.out.println(joursSemaine[i] + ": " + (disponibilites[i] ? "Disponible" : "Non disponible"));
		}
	}

	// Méthode pour affiher les chalets associés a un employé

	public void afficherChaletsAssocies() {
		System.out.println("Chalets associés à l'employé " + identifiant + ":");
		for (Chalets chalet : chaletsEntretenus) {
			if (chalet != null) {
				System.out.println(chalet);
			}
		}
	}

	public void setDisponibilites(boolean[] disponibilites) {
		this.disponibilites = disponibilites;
	}

	// Méthode pour changer les jours de disponibilité d'un employé
	public void setDisponibiliteJour(String jour, boolean nouvelleDisponibilite) {

		if (disponibilites != null && disponibilites.length == 7) {
			switch (jour.toLowerCase()) {
			case "lundi":
				disponibilites[0] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le lundi: " + nouvelleDisponibilite);
				break;
			case "mardi":
				disponibilites[1] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le mardi:" + nouvelleDisponibilite);
				break;
			case "mercredi":
				disponibilites[2] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le mercredi:" + nouvelleDisponibilite);
				break;
			case "jeudi":
				disponibilites[3] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le jeudi:" + nouvelleDisponibilite);
				break;
			case "vendredi":
				disponibilites[4] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le vendredi:" + nouvelleDisponibilite);
				break;
			case "samedi":
				disponibilites[5] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le samedi:" + nouvelleDisponibilite);
				break;
			case "dimanche":
				disponibilites[6] = nouvelleDisponibilite;
				System.out.println("Disponibilité de l'employe changee pour le dimanche: " + nouvelleDisponibilite);
				break;
			default:
				System.out.println("Jour invalide.");
			}
		} else {
			System.out.println("Tableau de disponibilités non initialisé ou de taille incorrecte.");
		}
	}

	// La methode compareTo implementer de java.lang()

	public int compareTo(Employes employe) {

		if (this.salaire < employe.salaire) {
			return -1;
		}
		if (this.salaire > employe.salaire) {
			return 1;
		} else {
			return 0;
		}
	}

	// Methode qui decrit un employe
	public String toString() {

		String result = nom + "," + prenom + "," + anneeNaissance + "," + salaire + "," + identifiant;
		return result;
	}

	// Méthode qui utilise des parties des attributs de l'employé pour générer
	// l'identifiant
	public void genererIdentifiant(ArrayList<Employes> listeEmployes) {

		String identifiantPartiel = nom.substring(0, 3) + prenom.substring(0, 2);
		int index = 1;
		String identifiant = identifiantPartiel + index;

		// Vérifier si l'identifiant est déjà présent dans la liste des employés
		while (estIdentifiantPresent(listeEmployes, identifiant)) {
			index++;
			identifiant = identifiantPartiel + index;
		}

		// Utilisez le setter pour assigner l'identifiant généré à l'employé
		setIdentifiant(identifiant);
	}

	private boolean estIdentifiantPresent(ArrayList<Employes> listeEmployes, String identifiant) {
		// Vérifier si l'identifiant est présent dans la liste des employés
		return listeEmployes.stream().anyMatch(e -> e.getIdentifiant().equalsIgnoreCase(identifiant));

	}
}
