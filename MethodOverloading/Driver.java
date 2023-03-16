package MethodOverloading;

import javax.swing.JFrame;

public class Driver extends JFrame {

    Panel p;

    public Driver() {
        this.setTitle("Rectangle Overload!");

        p = new Panel();

        this.add(p);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Driver();
    }
}
