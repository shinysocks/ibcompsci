import java.awt.Color;
import java.awt.Graphics;

import NeuralNetwork.Network;

public class Agent extends Network<Agent> {
	private int x, y, direc;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirec() {
		return direc;
	}

	public void setDirec(int direc) {
		if (direc > 3) {
			direc = 0;
		}
	}

	public Agent() {
		super(3, 3, 1, 10, 0.3, 0.2);
		x = 10;
		y = 50;
		direc = 0;
	}

	@Override
	public float[] getInput() {
		return new float[] {x, y, direc};
	}

	@Override
	public float fitness() {
		return x+y;
	}

	@Override
	public Agent child() {
		return new Agent();
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x, y, 1, 1);
	}

	public void move() {
		switch (direc) {
		case 0:
			y++;
			return;
		case 1:
			x++;
			return;
		case 2:
			y--;
			return;
		case 3:
			x--;
			return;
		}
	}
}
