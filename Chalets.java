/**
 * Nom et prénom: Salifou Zeinab
 * 
 * Nom et prénom: Djama Omar Abdourhamane
 * 
 */
package src;

public class Chalets {

	// ------Declaration des variables*******

	private String nom;
	private int id;
	private String ville;
	private String adresse;
	private boolean estLoue;
	private Employes estAssocie;
	private int nbChambre;

	// ------Constructeur***
	public Chalets(String nom, String ville, String adresse, int nbChambre, int id) {

		// ------Initialisation des variables***
		this.nom = nom;
		this.id = id;
		this.nbChambre = nbChambre;
		this.adresse = adresse;
		this.ville = ville;
	}

//-----------Getters***
	public String getNomDeChalet() {
		return this.nom;
	}

	public int getId() {
		return this.id;
	}

	public String getVille() {
		return this.ville;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public int getNbChambre() {
		return this.nbChambre;
	}

	public Employes getEstAssocie() {
		return estAssocie;

	}

//Methode chalet est loué ou non
	public boolean estLoue() {
		return estLoue;
	}

	// Méthode pour vérifier si le chalet n'est pas déjà associé à un autre employé
	public boolean estLibre() {

		return estAssocie == null;
	}

	// -----Setters***
	public void setestLoue(boolean value) {
		estLoue = value;
	}

	public void setEstAssocie(Employes estAssocie) {
		this.estAssocie = estAssocie;
	}

	// Methode qui decrit un chalet
	public String toString() {

		String result = nom + "," + ville + "," + adresse + "," + nbChambre + "," + id;
		return result;
	}
}
