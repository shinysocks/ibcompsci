package calculator;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class CalculatorDriver extends JFrame {

    Calculator panel;

    // building a file menu bar
    JMenuBar menuBar;
    JMenu file;
    JMenuItem scientificCalc, standardCalc, close;

    // constructor
    public CalculatorDriver() {
        setTitle("calculator");

        // create menu and place on screen
        menuBar = new JMenuBar();
        file = new JMenu("File");
        scientificCalc = new JMenuItem("Scientific");
        standardCalc = new JMenuItem("Standard");
        close = new JMenuItem("Exit");

        // add items to file menu
        file.add(scientificCalc);
        file.add(standardCalc);
        file.add(close);

        menuBar.add(file);

        setJMenuBar(menuBar);

        // north panel
        JPanel nPanel = new JPanel();
        nPanel.setBackground(Color.BLUE);

        // south
        JPanel sPanel = new JPanel();
        sPanel.setBackground(Color.BLUE);

        // east
        JPanel ePanel = new JPanel();
        ePanel.setBackground(Color.BLUE);

        // west
        JPanel wPanel = new JPanel();
        wPanel.setBackground(Color.BLUE);

        // add objects to JFrame
        add(nPanel, BorderLayout.NORTH);
        add(sPanel, BorderLayout.SOUTH);
        add(ePanel, BorderLayout.EAST);
        add(wPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        CalculatorDriver calc = new CalculatorDriver();

        calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
        calc.setVisible(true);
        calc.pack();
        calc.setResizable(false);
    }
}
