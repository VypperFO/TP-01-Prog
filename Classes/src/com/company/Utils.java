package com.company;

import java.text.DecimalFormat;

public class Utils {
    public static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Permet de calculer la moyenne d'une colonne d'un tableau 2D
     * @param array Le tableau 2D choisit
     * @param choixCol La colonne choisit
     * @return Retourne la moyenne avec deux decimales (.00)
     */
    public static double moyenneEval(int[][] array, int choixCol) {
        double somme = 0; // Somme de la colonne
        double moyenne = 0; // Moyenne de la colonne

        for (int i = 0; i < array.length; i++) {
            somme += array[i][choixCol];
        }

        moyenne = somme / array.length;
        moyenne = Double.valueOf(df.format(moyenne));

        return moyenne;
    }

    /**
     * Permet d'avoir le plus grand élement d'une colonne dans un tableau 2D
     * @param tab Le tableau 2D choisit
     * @param choixCol La colonne choisit
     * @return Retourne l'élement le plus grand
     */
    public static int maxEval(int[][] tab, int choixCol) {
        int max = tab[0][choixCol];
        for (int i = 0; i < tab.length; i++)
            if (tab[i][choixCol] > max)
                max = tab[i][choixCol];
        return max;
    }

    /**
     * Permet d'avoir le plus petit élement d'une colonne dans un tableau 2D
     * @param tab Le tableau 2D choisit
     * @param choixCol La colonne choisit
     * @return Retourne l'élement le plus petit
     */
    public static int minEval(int[][] tab, int choixCol) {
        int min = tab[0][choixCol];
        for (int i = 0; i < tab.length; i++)
            if (tab[i][choixCol] < min)
                min = tab[i][choixCol];
        return min;
    }

    public static void main(String[] args) {
        int[][] tab = {
                { 1, 2, 3, 4 },
                { 3, 4, 5, 4 },
                { 1, 2, 4, 5, 2 }
        };

        System.out.println(maxEval(tab, 2));
    }
}
