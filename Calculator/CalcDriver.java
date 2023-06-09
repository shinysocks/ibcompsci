package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class CalcDriver extends JFrame {
	//instance
	Calculator panel;
	
	//building a file menu bar
	JMenuBar mb;
	JMenu file;
	JMenuItem scifiCalc, standCalc, close;
	
	//constructor
	
	public CalcDriver()
	{
		this.setTitle("Calculator");
		
		//create menu and place on screen
		mb = new JMenuBar();
		file = new JMenu("File");
		scifiCalc = new JMenuItem("Scientific");
		standCalc = new JMenuItem("Standard");
		close = new JMenuItem("Exit");
		
		//add items to file menu
		file.add(scifiCalc);
		file.add(standCalc);
		file.add(close);
		
		//add file to menu bar
		mb.add(file);
		
		//add menue bar to frame
		this.setJMenuBar(mb);
		
		panel = new Calculator();
		
		//north panel
		JPanel nPanel = new JPanel();
		nPanel.setBackground(Color.BLUE);
		
		//south
		JPanel sPanel = new JPanel();
		sPanel.setBackground(Color.BLUE);
		
		//west
		JPanel ePanel = new JPanel();
		ePanel.setBackground(Color.BLUE);
		
		//east
		JPanel wPanel = new JPanel();
		wPanel.setBackground(Color.BLUE);
		
		
		//add all objects to JFrame
		this.add(nPanel,BorderLayout.NORTH);
		this.add(sPanel,BorderLayout.SOUTH);
		this.add(wPanel,BorderLayout.WEST);
		this.add(ePanel,BorderLayout.EAST);
		this.add(panel,BorderLayout.CENTER);
	}
	
	
	
	public static void main(String[] args) {
		CalcDriver calc = new CalcDriver();
		
		calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
		calc.setVisible(true);
		calc.pack(); //this gets frame to build around main panel
		calc.setResizable(false);
	}

}
