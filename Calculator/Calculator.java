package Calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JPanel implements ActionListener{
	//class variables
	final int WIDTH = 300;
	final int HEIGHT = 400;
	
	//instance variables
	JTextField jtWindow;
	Font font = new Font("Courier New", Font.PLAIN,24);
	
	//button variables
	JButton b0, b1, b2, b3, b4, b5, b6,b7,b8, b9;
	JButton bAdd, bSub, bMult, bDiv, bDec, bClear, bMod, bSquare, bEqual;
	
	//constructor
	public Calculator()
	{
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.DARK_GRAY);
		
		jtWindow  = new JTextField(20); 
		jtWindow.setFont(font);
		
		this.add(jtWindow);
		
		//button panel
		JPanel buttonPanel = buildButtPanel();
		
		this.add(buttonPanel);
	}
	private JPanel buildButtPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,4)); //5 rows x 4 col
		p.setPreferredSize(new Dimension(WIDTH,HEIGHT - 50));
		
		//build buttons
		//top row
		
		bMod = new JButton("%");
		bMod.setFont(font);
		
		p.add(bMod);
		
		bSquare = new JButton("x²"); //ALT+0178
		bSquare.setFont(font);
		
		p.add(bSquare);
		
		JButton bEmpty1 = new JButton("");
		p.add(bEmpty1);
		
		JButton bEmpty2 = new JButton("");
		p.add(bEmpty2);
		
		//**********************************************
		b7 = new JButton("7");
		b7.setFont(font);
		
		p.add(b7);
		
		b8 = new JButton("8");
		b8.setFont(font);
		
		p.add(b8);
		
		b9 = new JButton("9");
		b9.setFont(font);
		
		p.add(b9);
		
		bDiv = new JButton("÷"); //ALT + 0247 
		bDiv.setFont(font);
		
		p.add(bDiv);
		
		//*******************************************
		b4 = new JButton("4");
		b4.setFont(font);
		
		p.add(b4);
		
		b5 = new JButton("5");
		b5.setFont(font);
		
		p.add(b5);
		
		b6 = new JButton("6");
		b6.setFont(font);
		
		p.add(b6);
		
		bMult = new JButton("x");
		bMult.setFont(font);
		
		p.add(bMult);
		
		//************************************************
		b1 = new JButton("1");
		b1.setFont(font);
		
		p.add(b1);
		
		b2 = new JButton("2");
		b2.setFont(font);
		
		p.add(b2);
		
		b1 = new JButton("1");
		b1.setFont(font);
		
		p.add(b1);
		
		bSub = new JButton("-");
		bSub.setFont(font);
		
		p.add(bSub);
		
		//********************************************
		bClear = new JButton("C");
		bClear.setFont(font);
		
		p.add(bClear);
		
		b0 = new JButton("0");
		b0.setFont(font);
		
		p.add(b0);
		
		bEqual = new JButton("=");
		bEqual.setFont(font);
		
		p.add(bEqual);
		
		bAdd = new JButton("+");
		bAdd.setFont(font);
		
		p.add(bAdd);
		
		return p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
