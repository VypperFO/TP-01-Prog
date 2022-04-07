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

    /**
     * Permet de faire une permutation entre deux élements dans un tableau 2D
     * 
     * @param tab     Le tableau 2D choisit
     * @param valeurA Le premier entier
     * @param valeurB Le deuxième entier
     */
    public static void permutation(int[][] tab, int valeurA, int valeurB) {
        int[] valeurTemporaire = tab[valeurA];
        tab[valeurA] = tab[valeurB];
        tab[valeurB] = valeurTemporaire;
    }

    /**
     * Permet de positionner un élement dans sa colonne à sa position finale dans un
     * tableau 2D
     * 
     * @param tab          Le tableau 2D choisit
     * @param valeurGauche Entier comparer à gauche
     * @param valeurDroite Entier comparer à droite
     * @param choixCol     Colonne choisit
     * @return Retourne l'élement à sa position finale
     */
    public static int partition(int[][] tab, int valeurGauche, int valeurDroite, int choixCol) {
        int pivot = tab[valeurDroite][choixCol];
        for (int i = valeurGauche; i < valeurDroite; i++) {
            if (tab[i][choixCol] < pivot) {
                permutation(tab, valeurGauche, i);
                valeurGauche++;
            }
        }
        permutation(tab, valeurGauche, valeurDroite);

        return valeurGauche;
    }

    /**
     * Permet de faire un quicksort d'une colonne dans un tableau 2D
     * 
     * @param tab      Le tableau 2D choisit
     * @param gauche   Entier de comparaison de gauche
     * @param droite   Entier pivot de droite
     * @param choixCol Colonne du tableau 2D choisit
     */
    public static void quicksortRaw(int[][] tab, int gauche, int droite, int choixCol) {
        if (gauche < droite) {
            int index = partition(tab, gauche, droite, choixCol);
            quicksortRaw(tab, gauche, index - 1, choixCol);
            quicksortRaw(tab, index + 1, droite, choixCol);
        }
    }

    /**
     * Permet de simplifier et organiser l'écriture de la méthode quicksortRaw
     * 
     * @param tab      Le tableau 2D choisit
     * @param choixCol Colonne du tableau 2D choisit
     */
    public static void quicksort(int[][] tab, int choixCol) {
        try {
            quicksortRaw(tab, 0, tab.length - 1, choixCol);
        } catch (NullPointerException e) {
            System.out.println("Votre tableau est vide");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue");
        }
    }

    public static void main(String[] args) {
        int[][] tab = {
                { 1, 2, 3, 4 },
                { 3, 4, 5, 4 },
                { 1, 2, 4, 5 }
        };

        int[][] tab02 = null;

        quicksort(tab02, 1);
    }
}
