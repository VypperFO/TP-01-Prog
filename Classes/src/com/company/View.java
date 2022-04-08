package com.company;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class View extends JFrame {
    JLabel labDA, labExam01, labExam02, labTP01, labTP02;
    JButton btnAjout, btnModif, btnSup, btnQuit;
    JTextField txfDA, txfExam01, txfExam02, txfTP01, txfTP02;
    JTable tabNotes, tabStats;
    DefaultTableModel modelNotes, modelStats;

    Dimension dimTxf = new Dimension(125, 25);
    String[] colNames = { "DA", "Examen 1", "Examen 2", "TP 1", "TP 2", "Total %" };

    JPanel panCenter, panEst;
    JFrame frame = new JFrame("2173242");

    public View() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        modelNotes = new DefaultTableModel(colNames, 10) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabNotes = new JTable(modelNotes);
        tabNotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        modelStats = new DefaultTableModel();
        tabStats = new JTable(modelStats);

        JScrollPane scroll = new JScrollPane(tabNotes);
        scroll.setPreferredSize(new Dimension(300, 200));

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

        panCenter = new JPanel();
        panCenter.add(scroll, BorderLayout.CENTER);
        panCenter.add(tabStats, BorderLayout.NORTH);

        panEst = new JPanel();
        panEst.setLayout(new FlowLayout());
        panEst.add(labDA);
        panEst.add(txfDA);
        panEst.add(labExam01);
        panEst.add(txfExam01);
        panEst.add(labExam02);
        panEst.add(labTP01);
        panEst.add(txfTP01);
        panEst.add(labTP02);
        panEst.add(txfTP02);

        btnAjout = new JButton("Ajouter");
        btnModif = new JButton("Modifier");
        btnSup = new JButton("Supprimer");
        btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(e -> btnQuitAction());

        frame.add(panCenter, BorderLayout.WEST);
        frame.add(panEst, BorderLayout.EAST);
        frame.setVisible(true);
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
