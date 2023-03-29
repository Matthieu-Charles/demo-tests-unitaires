package fr.diginamic.utils;

import fr.diginamic.exceptions.LevenshteinUnproperArgument;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testLevenshteinDistance() throws LevenshteinUnproperArgument {

        //Cas nominaux
        assertEquals(2, StringUtils.levenshteinDistance("Chine", "Chien"));
        assertEquals(1, StringUtils.levenshteinDistance("Vienne", "Sienne"));
        assertEquals(4, StringUtils.levenshteinDistance("chine", "ChIeN"));
        assertEquals(2, StringUtils.levenshteinDistance("miam", "maim"));
        assertEquals(1, StringUtils.levenshteinDistance("chiné", "chine"));
        assertEquals(1, StringUtils.levenshteinDistance("lance-pierre", "lance-pierres"));
        assertEquals(9, StringUtils.levenshteinDistance("+8èz&", "£dezvfdvdd"));

    }

    @Test
    public void testLevenshteinDistanceError() {

        assertThrows(LevenshteinUnproperArgument.class, () -> StringUtils.levenshteinDistance(null, "£dezvfdvdd"));
        assertThrows(LevenshteinUnproperArgument.class, () -> StringUtils.levenshteinDistance(null, null));

    }

}
