package fr.diginamic.utils;

import fr.diginamic.exceptions.LevenshteinUnproperArgument;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test(expected = LevenshteinUnproperArgument.class)
    public void testLevenshteinDistanceError() throws LevenshteinUnproperArgument {

        assertEquals(1, StringUtils.levenshteinDistance(null, "£dezvfdvdd"));
        assertEquals(1, StringUtils.levenshteinDistance(null, null));

    }

}
