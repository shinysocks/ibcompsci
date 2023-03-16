package MethodOverloading;

import java.awt.Color;
import java.awt.Graphics;

public class Rect {
    private int width;
    private int height;
    private int x = 50, y = 0;
    private Color color = Color.PINK;

    public Rect(int w, int h) {
        width = w;
        height = h;
    }
    
    public Rect(int w, int h, int theX, int theY) {
        width = w;
        height = h;
        x = theX;
        y = theY;
    }
    
    public Rect(int w, int h, int theX, int theY, Color c) {
        width = w;
        height = h;
        x = theX;
        y = theY;
        color = c;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
