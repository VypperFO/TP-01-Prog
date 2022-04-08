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
    Dimension dimBtn = new Dimension(125, 25);
    String[] colNames = { "DA", "Examen 1", "Examen 2", "TP 1", "TP 2", "Total %" };
    String[] rowNames = { "Moyenne", "Note minimum", "Note maximum", "Nombre d'eleves" };

    JPanel panCenter, panEst, panLabTxf, panBtn, panBtnQuit;
    JFrame frame = new JFrame("2173242");

    public View() {
        //
        // Frame
        //
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1050, 350));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // JTable
        modelNotes = new DefaultTableModel(colNames, 10) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabNotes = new JTable(modelNotes);
        tabNotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabNotes);
        scroll.setPreferredSize(new Dimension(300, 200));

        modelStats = new DefaultTableModel(4, 6) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabStats = new JTable(modelStats);
        tabStats.setCellSelectionEnabled(false);

        for (int j = 0; j < rowNames.length; j++) {
            modelStats.setValueAt(rowNames[j], j, 0);
        }

        //
        // Label et TextField
        //
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

        //
        // Button
        //
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

        //
        // Panel
        //
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

        //
        // Frame 2.0
        //
        frame.add(panCenter, BorderLayout.WEST);
        frame.add(panEst, BorderLayout.EAST);
        frame.add(panBtnQuit, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Section méthodes maisons

    // Section Listener

    public void btnAjoutAction() {
    }

    public void btnModifAction() {
    }

    public void btnSupAction() {
    }

    public void btnQuitAction() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View v = new View();
    }
}
