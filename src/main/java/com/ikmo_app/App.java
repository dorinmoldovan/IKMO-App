package com.ikmo_app;

import com.ikmo_app.algorithm.IKMO;
import com.ikmo_app.algorithm.Result;
import com.ikmo_app.objective_functions.OFCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {

    public static KeyAdapter addKeyAdapterForDigits() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if((c < '0') && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
                if((c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        };
        return keyAdapter;
    }

    public static void addComponentsToPane(Container pane) {
        pane.setBackground(new java.awt.Color(176, 222, 196));

        pane.setLayout(null);

        JLabel kangaroosLabel = new JLabel("Number of kangaroos (N):");
        pane.add(kangaroosLabel);

        final JTextField kangaroosField = new JTextField(10);
        kangaroosField.addKeyListener(addKeyAdapterForDigits());
        pane.add(kangaroosField);

        JLabel numberOfIterationsLabel = new JLabel("Number of iterations (I):");
        pane.add(numberOfIterationsLabel);

        final JTextField iterationsField = new JTextField(10);
        iterationsField.addKeyListener(addKeyAdapterForDigits());
        pane.add(iterationsField);

        JLabel numberOfDimensionsLabel = new JLabel("Number of dimensions (D):");
        pane.add(numberOfDimensionsLabel);

        final JTextField numberOfDimensionsField = new JTextField(10);
        numberOfDimensionsField.addKeyListener(addKeyAdapterForDigits());
        pane.add(numberOfDimensionsField);

        JLabel mobsLabel = new JLabel("Number of mobs (M):");
        pane.add(mobsLabel);

        final JTextField mobsField = new JTextField(10);
        mobsField.addKeyListener(addKeyAdapterForDigits());
        pane.add(mobsField);

        JLabel mobsUpdatingFrequencyLabel = new JLabel("Mobs updating frequency (K):");
        pane.add(mobsUpdatingFrequencyLabel);

        final JTextField mobsUpdatingFrequencyField = new JTextField(10);
        mobsUpdatingFrequencyField.addKeyListener(addKeyAdapterForDigits());
        pane.add(mobsUpdatingFrequencyField);

        JLabel minSLabel = new JLabel("Minimum strength (sMin):");
        pane.add(minSLabel);

        final JTextField minSField = new JTextField(10);
        minSField.addKeyListener(addKeyAdapterForDigits());
        pane.add(minSField);

        JLabel maxSLabel = new JLabel("Maximum strength (sMax):");
        pane.add(maxSLabel);

        final JTextField maxSField = new JTextField(10);
        maxSField.addKeyListener(addKeyAdapterForDigits());
        pane.add(maxSField);

        JLabel ctLabel = new JLabel("Confrontation threshold (CT):");
        pane.add(ctLabel);

        final JTextField ctField = new JTextField(10);
        ctField.addKeyListener(addKeyAdapterForDigits());
        pane.add(ctField);

        JLabel epsLabel = new JLabel("Epsilon constant (eps):");
        pane.add(epsLabel);

        final JTextField epsField = new JTextField(10);
        pane.add(epsField);

        final JLabel ofLabel = new JLabel("Objective function (OF):");
        pane.add(ofLabel);

        String[] ofs = {"", "OF 1", "OF 2", "OF 3", "OF 4", "OF 5", "OF 6", "OF 7", "OF 8", "OF 9"};
        final JComboBox ofsComboBox = new JComboBox(ofs);
        pane.add(ofsComboBox);

        final JTextArea textArea = new JTextArea(22, 48);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        pane.add(scrollPane);

        Insets insets = pane.getInsets();

        Dimension size = kangaroosLabel.getPreferredSize();
        kangaroosLabel.setBounds(25 + insets.left, 25 + insets.top,
                size.width, size.height);

        size = kangaroosField.getPreferredSize();
        kangaroosField.setBounds(250 + insets.left, 25 + insets.top,
                size.width, size.height);

        size = numberOfIterationsLabel.getPreferredSize();
        numberOfIterationsLabel.setBounds(25 + insets.left, 50 + insets.top,
                size.width, size.height);

        size = iterationsField.getPreferredSize();
        iterationsField.setBounds(250 + insets.left, 50 + insets.top,
                size.width, size.height);

        size = numberOfDimensionsLabel.getPreferredSize();
        numberOfDimensionsLabel.setBounds(25 + insets.left, 75 + insets.top,
                size.width, size.height);

        size = numberOfDimensionsField.getPreferredSize();
        numberOfDimensionsField.setBounds(250 + insets.left, 75 + insets.top,
                size.width, size.height);

        size = mobsLabel.getPreferredSize();
        mobsLabel.setBounds(25 + insets.left, 100 + insets.top,
                size.width, size.height);

        size = mobsField.getPreferredSize();
        mobsField.setBounds(250 + insets.left, 100 + insets.top,
                size.width, size.height);

        size = mobsUpdatingFrequencyLabel.getPreferredSize();
        mobsUpdatingFrequencyLabel.setBounds(25 + insets.left, 125 + insets.top,
                size.width, size.height);

        size = mobsUpdatingFrequencyField.getPreferredSize();
        mobsUpdatingFrequencyField.setBounds(250 + insets.left, 125 + insets.top,
                size.width, size.height);

        size = minSLabel.getPreferredSize();
        minSLabel.setBounds(25 + insets.left, 150 + insets.top,
                size.width, size.height);

        size = minSField.getPreferredSize();
        minSField.setBounds(250 + insets.left, 150 + insets.top,
                size.width, size.height);

        size = maxSLabel.getPreferredSize();
        maxSLabel.setBounds(25 + insets.left, 175 + insets.top,
                size.width, size.height);

        size = maxSField.getPreferredSize();
        maxSField.setBounds(250 + insets.left, 175 + insets.top,
                size.width, size.height);

        size = ctLabel.getPreferredSize();
        ctLabel.setBounds(25 + insets.left, 200 + insets.top,
                size.width, size.height);

        size = ctField.getPreferredSize();
        ctField.setBounds(250 + insets.left, 200 + insets.top,
                size.width, size.height);

        size = epsLabel.getPreferredSize();
        epsLabel.setBounds(25 + insets.left, 225 + insets.top,
                size.width, size.height);

        size = epsField.getPreferredSize();
        epsField.setBounds(250 + insets.left, 225 + insets.top,
                size.width, size.height);

        size = ofLabel.getPreferredSize();
        ofLabel.setBounds(25 + insets.left, 250 + insets.top,
                size.width, size.height);

        size = ofsComboBox.getPreferredSize();
        ofsComboBox.setBounds(250 + insets.left, 250 + insets.top,
                size.width, size.height);

        size = scrollPane.getPreferredSize();
        scrollPane.setBounds(420 + insets.left, 25 + insets.top,
                size.width, size.height);

        JButton simulationButton = new JButton("Start Simulation");
        pane.add(simulationButton);
        simulationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int N = Integer.parseInt(kangaroosField.getText());
                int I = Integer.parseInt(iterationsField.getText());
                int D = Integer.parseInt(numberOfDimensionsField.getText());
                int M = Integer.parseInt(mobsField.getText());
                int K = Integer.parseInt(mobsUpdatingFrequencyField.getText());
                int sMin = Integer.parseInt(minSField.getText());
                int sMax = Integer.parseInt(maxSField.getText());
                int ct = Integer.parseInt(ctField.getText());
                double eps = Double.parseDouble(epsField.getText());
                String OF = (String) ofsComboBox.getSelectedItem();

                textArea.setText(null);

                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  NEW SIMULATION\n");
                textArea.append("  ------------------------------------------------------------\n");

                textArea.append("  Number of kangaroos (N) = " + N + "\n");
                textArea.append("  Number of iterations (I) = " + I + "\n");
                textArea.append("  Number of dimensions (D) = " + D + "\n");
                textArea.append("  Number of mobs (M) = " + M + "\n");
                textArea.append("  Mobs updating frequency (K) = " + K + "\n");
                textArea.append("  Minimum strength (sMin) = " + sMin + "\n");
                textArea.append("  Maximum strength (sMax) = " + sMax + "\n");
                textArea.append("  Confrontation threshold (CT) = " + ct + "\n");
                textArea.append("  Epsilon constant (eps) = " + eps + "\n");
                textArea.append("  Objective function (OF) = " + OF + "\n");

                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  SIMULATION LOGS\n");
                textArea.append("  ------------------------------------------------------------\n");

                IKMO ikmo = new IKMO(N, I, D, M, K, sMin, sMax, ct, eps, OFCreator.generateOF(OF));
                Result result = ikmo.run();

                textArea.append(result.getLogs());
                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  GBEST KANGAROO VALUE\n");
                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  " + result.getGlobalBest().getFitness() + "\n");
                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  RUNNING TIME\n");
                textArea.append("  ------------------------------------------------------------\n");
                textArea.append("  " + result.getRunningTime() + " millis\n");
            }
        });

        size = simulationButton.getPreferredSize();
        simulationButton.setBounds(25 + insets.left, 400 + insets.top,
                size.width + 215, size.height + 10);
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("IKMO App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        addComponentsToPane(frame.getContentPane());
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
