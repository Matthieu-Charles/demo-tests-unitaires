import fr.diginamic.enumerations.Saison;
import org.junit.Test;

import static org.junit.Assert.*;

/**Classe de test de l'énumération Saison
 * @author Matthieu CHARLES
 */
public class SaisonTest {

    @Test
    public void testValueOfLibelle(){

        //Cas nominaux
        assertEquals(Saison.PRINTEMPS, Saison.valueOfLibelle("Printemps"));
        assertEquals(Saison.ETE, Saison.valueOfLibelle("Eté"));
        assertEquals(Saison.AUTOMNE, Saison.valueOfLibelle("Automne"));
        assertEquals(Saison.HIVER, Saison.valueOfLibelle("Hiver"));

        //Cas aux limites
        assertEquals(Saison.PRINTEMPS, Saison.valueOfLibelle("printemps"));
        assertEquals(Saison.ETE, Saison.valueOfLibelle("ETE"));
        assertEquals(Saison.AUTOMNE, Saison.valueOfLibelle("automne"));
        assertEquals(Saison.HIVER, Saison.valueOfLibelle("hiver"));

        //Cas aux limites qui ne doivent pas être accepté
        assertNull(Saison.valueOfLibelle("Printemp"));
        assertNull(Saison.valueOfLibelle("autonne"));
        assertNull(Saison.valueOfLibelle("Noel"));

        //Cas où on passe une chaîne de caractères vide
        assertNull(Saison.valueOfLibelle(""));

        //Prise en compte d'un libellé de valeur null
        assertNull(Saison.valueOfLibelle(null));

    }
}
