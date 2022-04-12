package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;

import javax.sound.sampled.SourceDataLine;

public class Utils {
    public static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Permet de calculer la moyenne d'une colonne d'un tableau 2D
     * 
     * @param array    Le tableau 2D choisit
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
     * 
     * @param tab      Le tableau 2D choisit
     * @param choixCol La colonne choisit
     * @return Retourne l'élement le plus grand
     */
    public static int maxEval(int[][] tab, int choixCol) {
        int max = 0; // Valeur maximal
        max = tab[0][choixCol];
        for (int i = 0; i < tab.length; i++)
            if (tab[i][choixCol] > max)
                max = tab[i][choixCol];
        return max;
    }

    /**
     * Permet d'avoir le plus petit élement d'une colonne dans un tableau 2D
     * 
     * @param tab      Le tableau 2D choisit
     * @param choixCol La colonne choisit
     * @return Retourne l'élement le plus petit
     */
    public static int minEval(int[][] tab, int choixCol) {
        int min = 0; // Valeur minimal
        min = tab[0][choixCol];
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
        int[] valeurTemporaire = tab[valeurA]; // Valeur temporaire
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
        int pivot = tab[valeurDroite][choixCol]; // Entier pivot
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

    /**
     * Permet de faire une fouille dichotomique par indirection d'une colonne d'un
     * tableau 2D
     * 
     * @param tableau         Le tableau 2D
     * @param valeurRecherche La valeur rechercher
     * @param colonne         La colonne à rechercher
     * @return Retourne l'index si trouvé et -1 si il ne trouve rien
     */
    public static int fouilleDichoCol(int[][] tableau, int valeurRecherche, int colonne) {
        int debut = 0; // Valeur du debut
        int fin = tableau.length - 1; // Valeur de fin
        int milieu = 0; // Valeur du millieu
        boolean trouve = false; // Si valeur est trouvée

        while (debut <= fin && !trouve) {
            milieu = (debut + fin) / 2;

            if (valeurRecherche == tableau[milieu][colonne])
                trouve = true;
            else if (valeurRecherche < tableau[milieu][colonne])
                fin = milieu - 1;
            else
                debut = milieu + 1;
        }

        if (trouve)
            return milieu;
        else
            return -1;
    }

    /**
     * Permet de convertir un model vers un tableau d'entiers
     * 
     * @param model Le model a convertir
     * @return Retourne un tableau d'entiers
     */
    public static int[][] convertT2D(DefaultTableModel model) {
        int tabRow = model.getRowCount();
        int tabCol = model.getColumnCount();
        int[][] tableauEntiers = new int[tabRow][tabCol];

        for (int i = 0; i < tabRow; i++) {
            for (int j = 0; j < tabCol; j++) {
                tableauEntiers[i][j] = Integer.valueOf(String.valueOf(model.getValueAt(i, j)));
            }
        }
        return tableauEntiers;
    }

    // TODO Finir la fonction
    // public static void sauvergarde(String fileName, int[][] tab) throws
    // IOException {
    // BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
    // }

    public static void main(String[] args) throws IOException {
    }
}
