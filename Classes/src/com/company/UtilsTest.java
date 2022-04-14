/**
 * @author Félix-Olivier Latulippe
 * @DA 2173242
 * @session HV2022
 * 
 * Ce fichier contient les tests des méthodes utilitaires 
 */

package com.company;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UtilsTest {
    int[][] tab = {
            { 1, 2, 3 },
            { 3, 4, 5 },
            { 1, 2, 4 }
    };

    @ParameterizedTest(name = "Moyenne colonne {0} est {1}")
    @CsvSource({
            "0, 1.6666666666666667",
            "1, 2.6666666666666665",
            "2, 4.00"
    })
    public void moyenneEval(int col, double expectedResult) {
        int[][] array = tab;

        double actualResult = Utils.moyenneEval(array, col);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "Plus grand élement de la colonne {0} est {1}")
    @CsvSource({
            "0, 3",
            "1, 4",
            "2, 5"
    })
    void maxEval(int col, int expectedResult) {
        int[][] array = tab;

        int actualResult = Utils.maxEval(array, col);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "Plus petit élement de la colonne {0} est {1}")
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 3"
    })
    void minEval(int col, int expectedResult) {
        int[][] array = tab;

        int actualResult = Utils.minEval(array, col);
        assertEquals(expectedResult, actualResult);
    }

    /*@ParameterizedTest(name = "{1} est présent dans la colonne {0}")
    @CsvSource({
            "0, 3",
            "1, 4",
            "2, 5"
    })
    void isPresentCol_True(int choixCol, int valeurRecherche) {
        int[] tabCol01 = new int[tab.length];
        int[] tabCol02 = new int[tab.length];
        int[] tabCol03 = new int[tab.length];

        for (int i = 0; i < tabCol01.length; i++) {
            tabCol01[i] = i;
        }

        Utils.quicksort(tab, tabCol01, 0);
        
        for (int i = 0; i < tabCol01.length; i++) {
            tabCol02[i] = i;
        }

        Utils.quicksort(tab, tabCol02, 1);
        
        for (int i = 0; i < tabCol01.length; i++) {
            tabCol03[i] = i;
        }

        Utils.quicksort(tab, tabCol03, 2);

        assertTrue(Utils.isPresentCol(tab, tabCol01, choixCol, valeurRecherche));
    }

    @ParameterizedTest(name = "{1} n'est pas présent dans la colonne {0}")
    @CsvSource({
            "0, 32",
            "1, 43",
            "2, 46"
    })
    void isPresentCol_False(int choixCol, int valeurRecherche) {
                int[] tabCol01 = new int[tab.length];
        int[] tabCol02 = new int[tab.length];
        int[] tabCol03 = new int[tab.length];

        for (int i = 0; i < tabCol01.length; i++) {
            tabCol01[i] = i;
        }

        Utils.quicksort(tab, tabCol01, 0);
        
        for (int i = 0; i < tabCol01.length; i++) {
            tabCol02[i] = i;
        }

        Utils.quicksort(tab, tabCol02, 1);
        
        for (int i = 0; i < tabCol01.length; i++) {
            tabCol03[i] = i;
        }

        Utils.quicksort(tab, tabCol03, 2);

        assertFalse(Utils.isPresentCol(tab, tabCol01, choixCol, valeurRecherche));
    }*/
}
