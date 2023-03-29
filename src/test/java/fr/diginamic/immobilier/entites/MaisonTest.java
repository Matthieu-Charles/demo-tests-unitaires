package fr.diginamic.immobilier.entites;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MaisonTest {

    private static Maison maison1;
    private static Chambre chambre0;
    private static Chambre chambre1;
    private static Chambre chambre2;
    private static Chambre chambre3;
    private static Cuisine cuisine1;
    private static Cuisine cuisine2;
    private static SalleDeBain salleDeBain1;
    private static SalleDeBain salleDeBain2;
    private static Salon salon1;
    private static Salon salon2;
    private static WC wc1;
    private static WC wc2;

    @BeforeAll
    public static void init(){

        maison1 = new Maison();

        chambre1 = new Chambre(1, 10.5);
        chambre2 = new Chambre(2, 20.5);
        chambre3 = new Chambre(3, 13.3);

        cuisine1 = new Cuisine(0, 15.3);
        cuisine2 = new Cuisine(1, 20.3);

        salleDeBain1 = new SalleDeBain(1, 24.6);
        salleDeBain2 = new SalleDeBain(2, 16.2);

        salon1 = new Salon(0, 21.3);
        salon2 = new Salon(0, 26.7);

        wc1 = new WC(0, 8.6);
        wc2 = new WC(1, 6.5);

    }

    @AfterEach
    public void viderMaison(){
        maison1 = new Maison();
    }

    @Test
    public void ajouterPieceTest(){

        maison1.ajouterPiece(chambre1);
        assertEquals(maison1.getPieces().length, 1);

        maison1.ajouterPiece(chambre2);
        maison1.ajouterPiece(cuisine1);
        assertEquals(maison1.getPieces().length, 3);

        maison1.ajouterPiece(salleDeBain1);
        maison1.ajouterPiece(salon1);
        maison1.ajouterPiece(wc1);
        assertEquals(maison1.getPieces().length, 6);

        //Erreur personnalisée levée lors l'ajout d'une pièce non initialisée
        Exception exception = assertThrows(NullPointerException.class, () -> maison1.ajouterPiece(chambre0));

        String expectedMessage = "La pièce n'est pas initialisée (=null)";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void superficieEtageTest(){

        maison1.ajouterPiece(chambre1);
        maison1.ajouterPiece(chambre2);
        maison1.ajouterPiece(chambre3);
        maison1.ajouterPiece(wc1);

        System.out.println(Arrays.toString(maison1.getPieces()));

        assertEquals(8.6, maison1.superficieEtage(0));
        assertEquals(10.5, maison1.superficieEtage(1));
        assertEquals(20.5, maison1.superficieEtage(2));
        assertEquals(13.3, maison1.superficieEtage(3));

        maison1.ajouterPiece(cuisine1);
        maison1.ajouterPiece(cuisine2);

        assertEquals(15.3, maison1.superficieEtage(0));
        assertEquals(20.3, maison1.superficieEtage(1));
        assertEquals(20.5, maison1.superficieEtage(2));
        assertEquals(13.3, maison1.superficieEtage(3));

        //Erreur personnalisée levée lors de la demande pour un étage non-existant
        Exception exception = assertThrows(RuntimeException.class, () -> maison1.superficieEtage(4));

        String expectedMessage = "Il n'existe pas d'étage : ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void superficieTypePieceTest() {

        maison1.ajouterPiece(chambre1);
        maison1.ajouterPiece(chambre2);
        maison1.ajouterPiece(chambre3);
        assertEquals(44.3, maison1.superficieTypePiece("Chambre"));
        assertEquals(44.3, maison1.superficieTypePiece("chambre"));

        maison1.ajouterPiece(wc1);
        maison1.ajouterPiece(wc2);
        assertEquals(15.1, maison1.superficieTypePiece("WC"));
        assertEquals(15.1, maison1.superficieTypePiece("wc"));

        maison1.ajouterPiece(salleDeBain1);
        maison1.ajouterPiece(salleDeBain2);
        assertEquals( 40.8, maison1.superficieTypePiece("Salle de bain"));
        assertEquals( 40.8, maison1.superficieTypePiece("Salle de Bain"));

        maison1.ajouterPiece(cuisine1);
        maison1.ajouterPiece(cuisine2);
        assertEquals(maison1.superficieTypePiece("Cuisine"), 35.6);
        assertEquals(maison1.superficieTypePiece("cuisine"), 35.6);

        //Erreur personnalisée levée lors de la demande pour un type non-existant ou mal orthographié
        Exception exception = assertThrows(RuntimeException.class, () -> maison1.superficieTypePiece(""));

        String expectedMessage = "Le type demandé est une chaîne vide ou bien de valeur null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //Erreur personnalisée levée lors de la demande pour un type non-existant ou mal orthographié
        exception = assertThrows(RuntimeException.class, () -> maison1.superficieTypePiece(null));

        expectedMessage = "Le type demandé est une chaîne vide ou bien de valeur null";
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        //Erreur personnalisée levée lors de la demande pour un type non-existant ou mal orthographié
        exception = assertThrows(RuntimeException.class, () -> maison1.superficieTypePiece("balcon"));

        expectedMessage = "Il n'y a aucune correspondance pour le type de pièce demandé : ";
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void calculerSurfaceTest() {

        maison1.ajouterPiece(chambre1);
        maison1.ajouterPiece(chambre2);
        maison1.ajouterPiece(cuisine1);
        maison1.ajouterPiece(salon1);
        maison1.ajouterPiece(wc2);
        assertEquals(74.1, maison1.calculerSurface());

        maison1 = new Maison();
        maison1.ajouterPiece(chambre1);
        maison1.ajouterPiece(salon1);
        maison1.ajouterPiece(salon2);
        maison1.ajouterPiece(salleDeBain2);
        assertEquals(74.7, maison1.calculerSurface());

    }

}
