package fr.diginamic.immobilier.entites;

import java.text.Collator;

/** Représente une maison avec toutes ses pièces
 * @author DIGINAMIC
 *
 */
public class Maison {
	
	/** pieces : tableau de pièces de la maison */
	private Piece[] pieces;
	
	/**
	 * Constructeur
	 */
	public Maison(){
		// Initialisation du tableau de pièces
		pieces = new Piece[0];
	}

	/** Ajoute une pièce à la maison
	 * @param nvPiece nouvelle pièce à ajouter
	 */
	public void ajouterPiece(Piece nvPiece) {

		if (nvPiece == null) {
			throw new NullPointerException("La pièce n'est pas initialisée (=null)");
		}
		
		// On est obligé d'agrandir le tableau initial de 1 à chaque ajout
		// d'une nouvelle pièce
		
		// On commence donc par créer un tableau temporaire appelé newTab
		// qui a une taille égale à la tableau du tableau pieces+1
		Piece[] newTab = new Piece[pieces.length+1];
		
		// On déverse toutes les pièces du tableau pieces dans newTab
		System.arraycopy(pieces, 0, newTab, 0, pieces.length);
		
		// On place en dernière position dans le nouveau tableau la nouvelle
		// pièce
		newTab[newTab.length-1]=nvPiece;
		
		// Enfin on affecte newTab à pieces
		this.pieces=newTab;
	}
	
	public int nbPieces() {
		return pieces.length-1;
	}

	/** Retourne la superficie d'un étage
	 * @param choixEtage choix de l'étage
	 * @return double
	 */
	public double superficieEtage(int choixEtage) {

		double superficieEtage = 0;
		boolean etageExiste = false;

		for (Piece piece : pieces) {
			if (choixEtage == piece.getNumEtage()) {
				superficieEtage = piece.getSuperficie();
				etageExiste = true;
			}
		}

		if(!etageExiste) {
			throw new RuntimeException("Il n'existe pas d'étage : " + choixEtage);
		}
		return superficieEtage;
	}
	
	/** Retourne la superficie totale pour un type de pièce donné
	 * @param typePiece type de pièce
	 * @return double
	 */
	public double superficieTypePiece(String typePiece) {
		double superficie = 0;
		boolean typePieceExists = false;

		if(typePiece == null || typePiece.isEmpty()){
			throw new RuntimeException("Le type demandé est une chaîne vide ou bien de valeur null");
		}

		final Collator instance = Collator.getInstance();
		instance.setStrength(Collator.PRIMARY);
		for (Piece piece : this.pieces) {
			if (instance.compare(typePiece, piece.getType())== 0) {
				superficie = superficie + piece.getSuperficie();
				typePieceExists = true;
			}
		}

		if(!typePieceExists) {
			throw new RuntimeException("Il n'y a aucune correspondance pour le type de pièce demandé : " + typePiece);
		}

		return superficie;
	}

	/** Retourne la surface totale
	 * @return double
	 */
	public double calculerSurface() {
		double superficieTot = 0;

		for (Piece piece : pieces) {
			superficieTot = superficieTot + piece.getSuperficie();
		}

		return superficieTot;
	}

	/** Getter pour l'attribut pieces
	 * @return the pieces
	 */
	public Piece[] getPieces() {
		return pieces;
	}

}