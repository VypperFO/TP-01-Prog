package com.company;

import javax.swing.*;

public class View extends JFrame {
    JLabel labDA, labExam01, labExam02, labTP01, labTP02;
    JButton btnAjout, btnModif, btnSup, btnQuit;
    JTextField txfDA, txfExam01, txfExam02, txfTP01, txfTP02;

    JFrame frame = new JFrame("2173242");

    public View() {
        labDA = new JLabel("DA");
        labExam01 = new JLabel("Examen 1");
        labExam02 = new JLabel("Examen 2");
        labTP01 = new JLabel("TP 1");
        labTP02 = new JLabel("TP 2");

        txfDA = new JTextField("");
        txfExam01 = new JTextField("");
        txfExam02 = new JTextField("");
        txfTP01 = new JTextField("");
        txfTP02 = new JTextField("");

        btnAjout = new JButton("Ajouter");
        btnModif = new JButton("Modifier");
        btnSup = new JButton("Supprimer");
        btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(e -> btnQuitAction());
    }

    // Section méthodes maisons
    
    // Section Listener
    public void btnQuitAction() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View v = new View();
    }
}
