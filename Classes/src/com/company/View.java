/**
 * @author Félix-Olivier Latulippe
 * @DA 2173242
 * @session HV2022
 * 
 * Ce fichier contient le frame, les actions listener ainsi que les méthodes maisons 
 */

package com.company;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
public class View extends JFrame {
    JLabel labDA, labExam01, labExam02, labTP01, labTP02;
    JButton btnAjout, btnModif, btnSup, btnQuit;
    JTextField txfDA, txfExam01, txfExam02, txfTP01, txfTP02;
    JTable tabNotes, tabStats;
    DefaultTableModel modelNotes, modelStats;

    Dimension dimTxf = new Dimension(125, 25);
    Dimension dimBtn = new Dimension(125, 25);
    String[] colNames = { "DA", "Examen 1", "Examen 2", "TP 1", "TP 2", "Total %" };
    String[] rowNames = { "Moyenne", "Note minimum", "Note maximum", "Nombre d'eleves" };

    JPanel panCenter, panEst, panLabTxf, panBtn, panBtnQuit;
    JFrame frame = new JFrame("Felix-Olivier Latulippe; 2173242");

    public static final DecimalFormat df = new DecimalFormat("0.00");
    public final String file = "Classes/src/com/company/notes.txt"; // Le fichier choisit 

    public View() throws IOException {
        // @@@@@@@@@@@@@
        // @@@ Frame @@@
        // @@@@@@@@@@@@@
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1050, 350));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        // @@@@@@@@@@@@@@@@
        // @@@ Tableaux @@@
        // @@@@@@@@@@@@@@@@
        /** Tableau donnee */

        nbNoms = countLinesFile(file);
        tabNoms = new String[nbNoms][6];
        modelNotes = new DefaultTableModel(readFileTab(file), colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabNotes = new JTable(modelNotes);
        tabNotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabNotes.getTableHeader().setReorderingAllowed(false);


        JScrollPane scroll = new JScrollPane(tabNotes);
        scroll.setPreferredSize(new Dimension(300, 200));
        tabNotes.setRowSelectionInterval(0, 0);

        tabNotes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() || tabNotes.getSelectedRow() > -1) {
                    txfDA.setText(String.valueOf(modelNotes.getValueAt(tabNotes.getSelectedRow(), 0)));
                    txfExam01.setText(String.valueOf(modelNotes.getValueAt(tabNotes.getSelectedRow(), 1)));
                    txfExam02.setText(String.valueOf(modelNotes.getValueAt(tabNotes.getSelectedRow(), 2)));
                    txfTP01.setText(String.valueOf(modelNotes.getValueAt(tabNotes.getSelectedRow(), 3)));
                    txfTP02.setText(String.valueOf(modelNotes.getValueAt(tabNotes.getSelectedRow(), 4)));
                }
            }
        });

        /** Stats */
        modelStats = new DefaultTableModel(4, 6) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabStats = new JTable(modelStats);
        tabStats.setCellSelectionEnabled(false);

        for (int i = 0; i < rowNames.length; i++) {
            modelStats.setValueAt(rowNames[i], i, 0);
        }

        ajouterStats();

        // @@@@@@@@@@@@@@@@@@@@@@@@@@
        // @@@ Label et TextField @@@
        // @@@@@@@@@@@@@@@@@@@@@@@@@@
        labDA = new JLabel("DA");
        labExam01 = new JLabel("Examen 1");
        labExam02 = new JLabel("Examen 2");
        labTP01 = new JLabel("TP 1");
        labTP02 = new JLabel("TP 2");

        txfDA = new JTextField("");
        txfDA.setPreferredSize(dimTxf);

        txfExam01 = new JTextField("");
        txfExam01.setPreferredSize(dimTxf);

        txfExam02 = new JTextField("");
        txfExam02.setPreferredSize(dimTxf);

        txfTP01 = new JTextField("");
        txfTP01.setPreferredSize(dimTxf);

        txfTP02 = new JTextField("");
        txfTP02.setPreferredSize(dimTxf);

        // @@@@@@@@@@@@@@
        // @@@ Button @@@
        // @@@@@@@@@@@@@@
        btnAjout = new JButton("Ajouter");
        btnAjout.setPreferredSize(dimBtn);
        btnAjout.addActionListener(e -> btnAjoutAction());

        btnModif = new JButton("Modifier");
        btnModif.setPreferredSize(dimBtn);
        btnModif.addActionListener(e -> btnModifAction());

        btnSup = new JButton("Supprimer");
        btnSup.setPreferredSize(dimBtn);
        btnSup.addActionListener(e -> btnSupAction());

        btnQuit = new JButton("Quitter");
        btnQuit.setPreferredSize(dimBtn);
        btnQuit.addActionListener(e -> btnQuitAction());

        // @@@@@@@@@@@@@
        // @@@ Panel @@@
        // @@@@@@@@@@@@@
        panBtnQuit = new JPanel();
        panBtnQuit.setLayout(new BorderLayout());
        panBtnQuit.add(btnQuit, BorderLayout.EAST);

        panCenter = new JPanel();
        panCenter.setLayout(new BorderLayout(15, 15));
        panCenter.setPreferredSize(new Dimension(600, 300));
        panCenter.add(scroll, BorderLayout.CENTER);
        panCenter.add(tabStats, BorderLayout.SOUTH);

        panLabTxf = new JPanel();
        panLabTxf.setLayout(new GridLayout(5, 2, 5, 5));
        panLabTxf.add(labDA);
        panLabTxf.add(txfDA);
        panLabTxf.add(labExam01);
        panLabTxf.add(txfExam01);
        panLabTxf.add(labExam02);
        panLabTxf.add(txfExam02);
        panLabTxf.add(labTP01);
        panLabTxf.add(txfTP01);
        panLabTxf.add(labTP02);
        panLabTxf.add(txfTP02);

        panBtn = new JPanel();
        panBtn.setLayout(new FlowLayout());
        panBtn.add(btnAjout);
        panBtn.add(btnModif);
        panBtn.add(btnSup);

        panEst = new JPanel();
        panEst.setLayout(new BorderLayout());
        panEst.add(panLabTxf, BorderLayout.NORTH);
        panEst.add(panBtn, BorderLayout.CENTER);

        // @@@@@@@@@@@@@
        // @@@ Frame @@@
        // @@@@@@@@@@@@@
        frame.add(panCenter, BorderLayout.WEST);
        frame.add(panEst, BorderLayout.EAST);
        frame.add(panBtnQuit, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // @@@@@@@@@@@@@@@@@@@@@@@@
    // @@@ Méthodes maisons @@@
    // @@@@@@@@@@@@@@@@@@@@@@@@

    public static int nbNoms; // Nombre de noms
    public static String[][] tabNoms; // Tableau des noms

    /**
     * Permet de compter le nombre de noms dans un fichier
     * 
     * @param fileName Le fichier a compter
     * @return Retourne le nombre de noms dans un fichier
     * @throws IOException
     */
    public static int countLinesFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName)); // Le reader
        while (reader.readLine() != null)
            nbNoms++;
        reader.close();
        return nbNoms;
    }

    /**
     * Permet de lire un tableau dans un fichier txt et de l'écrire dans un tableau
     * 
     * @param fileName Le fichier à lire
     * @return Retourne un tableau de strings
     * @throws IOException
     */
    public static String[][] readFileTab(String fileName) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName)); // Le reader
            String line; // La line en cours
            String[] tab; // Le vecteur en cours
            nbNoms = 0;

            while ((line = reader.readLine()) != null) {
                tab = line.split(" ");
                tabNoms[nbNoms][0] = tab[0];
                tabNoms[nbNoms][1] = tab[1];
                tabNoms[nbNoms][2] = tab[2];
                tabNoms[nbNoms][3] = tab[3];
                tabNoms[nbNoms][4] = tab[4];
                tabNoms[nbNoms][5] = String.valueOf((Integer.parseInt(tabNoms[nbNoms][1]) +
                        (Integer.parseInt(tabNoms[nbNoms][2]) +
                                (Integer.parseInt(tabNoms[nbNoms][3]) +
                                        (Integer.parseInt(tabNoms[nbNoms][4])))))
                        / 4);
                nbNoms++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tabNoms;
    }

    /**
     * Permet de sauvegarder un tableau d'entiers dans un fichier txt
     * 
     * @param fileName Le fichier txt a enregistrer
     * @param tab      Le tableau d'entiers
     * @throws IOException
     */
    public static void sauvegarde(String fileName, int[][] tab) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false)); // Le writer
        
        if (tab.length != 0) {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[0].length; j++) {
                    writer.write(tab[i][j] + " ");
                }
                writer.newLine();
            }
            writer.close();
        } else {
            writer.write(" ");
            writer.close();
        }
    }

    /**
     * Permet de supprimer la dernière colonne d'un tableau d'entiers
     * 
     * @param array Le tableau d'entiers
     * @return Retourne le tableau d'entiers sans la dernière colonne
     */
    public static int[][] supDerniereCol(int[][] array) {
        int row = array.length; // Nombre de lignes du tableau
        int col = array[0].length; // Nombres de colonnes du tableau

        int[][] newArray = new int[row][col - 1]; // Nouveau tableau avec une colonne en moins

        for (int i = 0; i < row; i++) {
            for (int j = 0, currColumn = 0; j < col; j++) {
                if (j != 5) {
                    newArray[i][currColumn++] = array[i][j];
                }
            }
        }
        return newArray;
    }

    /**
     * Permet d'ajouter les statistiques du model de notes
     */
    public void ajouterStats() {
        int[][] tabIntTemp = Utils.convertT2D(modelNotes); // Tableau d'entiers temporaire
        int nbEleve = modelNotes.getRowCount(); // Nombre d'éleves

        for (int i = 1; i < tabIntTemp[0].length; i++) {
            modelStats.setValueAt(df.format(Utils.moyenneEval(tabIntTemp, i)), 0, i);
            modelStats.setValueAt(Utils.minEval(tabIntTemp, i), 1, i);
            modelStats.setValueAt(Utils.maxEval(tabIntTemp, i), 2, i);
        }
        modelStats.setValueAt(nbEleve, 3, 1);
    }

    /**
     * Permet de mettre à jour le model de notes et de statistiques
     */
    public void updateState() {
        if (modelNotes.getRowCount() != 0) {
            ajouterStats();
        } else {
            for (int i = 1; i < modelStats.getColumnCount(); i++) {
                modelStats.setValueAt("--", 0, i);
                modelStats.setValueAt("--", 1, i);
                modelStats.setValueAt("--", 2, i);
            }
            modelStats.setValueAt(0, 3, 1);
        }
    }

    /**
     * Permet de vérifier si les valeurs entrer en txf sont correcte.
     * Les valeurs ne doivent pas être en haut de 100 ni une chaine de charactères.
     * 
     * @return Retourne true si elles sont correct, faux si non
     */
    public boolean isValueCorrect() {
        boolean isCorrect = false; // Boolean pour savoir si valeur est correct

        try {
            int txtExam01 = Integer.parseInt(txfExam01.getText()); // La valeur de l'examen 01
            int txtExam02 = Integer.parseInt(txfExam02.getText()); // La valeur de l'examen 02
            int txtTP01 = Integer.parseInt(txfTP01.getText()); // La valeur du tp 01
            int txtTP02 = Integer.parseInt(txfTP02.getText()); // La valeur du tp 02

            if ((txtExam01 <= 100 && txtExam01 >= 0) && (txtExam02 <= 100 && txtExam02 >= 0)
                    && (txtTP01 <= 100 && txtTP01 >= 0) && (txtTP02 <= 100 && txtTP02 >= 0))
                isCorrect = true;
            else
                isCorrect = false;
        } catch (Exception e) {
            messageErreur(1);
        }

        return isCorrect;
    }

    /**
     * Permet d'ajouter/modifier le model de notes.
     * 
     * @param ligneSelectionner // La ligne dont nous voulons selectionner.
     */
    public void addValuesTable(int ligneSelectionner) {
        int dernièreColonne = 5; // Chiffre de la dernière ligne
        int somme = 0; // Somme de tout les colonne d'une ligne
        String total; // Le total en pourcentage
        
        if (isValueCorrect()) {
            modelNotes.setValueAt(txfDA.getText(), ligneSelectionner, 0);
            modelNotes.setValueAt(txfExam01.getText(), ligneSelectionner, 1);
            modelNotes.setValueAt(txfExam02.getText(), ligneSelectionner, 2);
            modelNotes.setValueAt(txfTP01.getText(), ligneSelectionner, 3);
            modelNotes.setValueAt(txfTP02.getText(), ligneSelectionner, 4);

            for (int i = 1; i < 5; i++) {
                somme += Integer.parseInt((String) modelNotes.getValueAt(ligneSelectionner, i));
            }

            somme /= 4;
            total = String.valueOf(somme);

            modelNotes.setValueAt(total, ligneSelectionner, dernièreColonne);
        }
    }

    /**
     * Permet de choisir le type d'erreur
     * 
     * @param choixErreur Le chiffre correspondant a l'erreur
     */
    public void messageErreur(int choixErreur) {
        switch (choixErreur) {
            case 1:
                JOptionPane.showMessageDialog(frame, "Entrer une bonne valeur", "Erreur",
                        JOptionPane.OK_OPTION);
                break;
            case 2:
                JOptionPane.showMessageDialog(frame, "Ce DA est déjà présent", "Erreur",
                        JOptionPane.OK_OPTION);
                break;
            case 3:
                JOptionPane.showMessageDialog(frame, "Aucun élement selectionner", "Erreur",
                        JOptionPane.OK_OPTION);
                break;
            case 4:
                JOptionPane.showMessageDialog(frame, "Aucune données à sauvegarder", "Erreur",
                        JOptionPane.OK_OPTION);
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Erreur", "Erreur",
                        JOptionPane.OK_OPTION);
                break;
        }
    }

    // @@@@@@@@@@@@@@@@@@@@@@@@
    // @@@ Section Listener @@@
    // @@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Permet d'ajouter un élement au model de notes
     */
    public void btnAjoutAction() {
        try {
            int[][] tabTemporaire = Utils.convertT2D(modelNotes); // Un tableau temporaire d'entiers
            int[] tabIndexDA = new int[tabTemporaire.length]; // Un tableau d'index avec le tableau temporaire
            int daChoisit = Integer.parseInt(txfDA.getText()); // Le DA choisit
            int dernièreLigne = modelNotes.getRowCount(); // La dernière ligne du model de notes
            int colonneDA = 0; // La première colonne (le DA)
    
            for (int i = 0; i < tabTemporaire.length; i++) {
                tabIndexDA[i] = i;
            }
    
            Utils.quicksort(tabTemporaire, tabIndexDA, colonneDA);
            
            if (!Utils.isPresentCol(tabTemporaire, tabIndexDA, colonneDA, daChoisit) && isValueCorrect()) {
                modelNotes.addRow(new Object[] { "0", "0", "0", "0", "0", "0"});
                addValuesTable(dernièreLigne);
            } else {
                messageErreur(2);
            }
            
            updateState();
        } catch (Exception e) {
            messageErreur(3);
        }
    }

    /**
     * Permet de modifier une ligne sélectionnée
     */
    public void btnModifAction() {
        int ligneSelectionner = tabNotes.getSelectedRow(); // La ligne sélectionnée

        try {
            addValuesTable(ligneSelectionner);
            updateState();
        } catch (Exception e) {
            messageErreur(3);
        }
    }

    /**
     * Permet de supprimer une ligne sélectionnée
     */
    public void btnSupAction() {
        int ligneSelectionner = tabNotes.getSelectedRow(); // La ligne sélectionnée

        try {
            modelNotes.removeRow(ligneSelectionner);
        } catch (Exception e) {
            messageErreur(3);
        }

        txfDA.setText("");
        txfExam01.setText("");
        txfExam02.setText("");
        txfTP01.setText("");
        txfTP02.setText("");

        updateState();
    }

    /**
     * Permet de quitter l'application et de sauvegarder
     */
    public void btnQuitAction() {
        int reponse = JOptionPane.showConfirmDialog(frame, "Voulez-vous sauvegarder?", "Quitter",
                JOptionPane.YES_NO_OPTION); // Réponse de l'utilisateur

        if (reponse == JOptionPane.YES_OPTION) {
            try {
                sauvegarde(file, supDerniereCol(Utils.convertT2D(modelNotes)));
            } catch (Exception e) {
                messageErreur(4);
            }
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        View v = new View();
    }
}
