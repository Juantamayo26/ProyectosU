package packjuegos;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class InicioGraficos  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int juego = 3;
	
	
	private JButton nivel1,nivel2,nivel3;
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private String nombre1,nombre2;
	int Score1, Score2;
	
	public InicioGraficos(int score1,int score2,String Nombre1,String Nombre2) {
		
		super("Inicio");
		nombre1=Nombre1;
		nombre2=Nombre2;
		setDefaultLookAndFeelDecorated(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500,500,500,250);
		ManejadorBoton manejador = new ManejadorBoton();
		getContentPane().setLayout(null);
		
		
		JTextField display = new JTextField();
		display.setBounds(0,0, 500,20);
	    display.setForeground(Color.black);
	    display.setFont(new Font("Palatino",Font.PLAIN,14));
	    display.setText("ELIJA EL JUEGO");
	    display.setEditable(false);
	    add(display);
	    
	    Score1=score1;
	    Score2=score2;
		 
		JPanel topPanel=new JPanel();
	    topPanel.setBounds(0,20,500,40);
	    topPanel.setLayout(new GridLayout(0,4,2,2));
		add(topPanel);
		
		 
		this.checkbox1 = new JCheckBox("Memorama");
		topPanel.add(checkbox1);
		checkbox1.setBackground(Color.BLUE);
		checkbox1.setForeground(Color.white);
		checkbox1.addItemListener((ItemListener) new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					juego = 1;
					checkbox2.setVisible(false);
					display.setText("Memorama");
					nivel1.setBackground(Color.GREEN);
					nivel2.setBackground(Color.orange);
					nivel3.setBackground(new java.awt.Color(247,77,66));
				}else {
					juego = 3;
					checkbox2.setVisible(true);
					display.setText("ELIJA EL JUEGO");
					nivel1.setBackground(Color.LIGHT_GRAY);
					nivel2.setBackground(Color.LIGHT_GRAY);
					nivel3.setBackground(Color.LIGHT_GRAY);
				}
				
				
				
			}    
		});
		
		
		
		checkbox2 = new JCheckBox("Puzzle");
		topPanel.add(checkbox2);
		checkbox2.setBackground(new java.awt.Color(130,224,234));
		checkbox2.addItemListener((ItemListener) new ItemListener() {
					
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == 1) {
							juego = 2;
							checkbox1.setVisible(false);
							display.setText("Puzzle");
							nivel1.setBackground(Color.GREEN);
							nivel2.setBackground(Color.orange);
							nivel3.setBackground(new java.awt.Color(247,77,66));
						}else {
							juego = 3;
							checkbox1.setVisible(true);
							display.setText("ELIJA EL JUEGO");
							nivel1.setBackground(Color.LIGHT_GRAY);
							nivel2.setBackground(Color.LIGHT_GRAY);
							nivel3.setBackground(Color.LIGHT_GRAY);
						}
						
						
						
					}    
				});
		
		
		JTextField display2 = new JTextField();
		display2.setBounds(0,60,500,20);
	    display2.setForeground(Color.black);
	    display2.setFont(new Font("Palatino",Font.PLAIN,14));
	    display2.setText("ELIJA EL NIVEL");
	    display2.setEditable(false);
	    add(display2);
		 
		JPanel lowPanel=new JPanel();
	    lowPanel.setBounds(0,80,500,40);
	    lowPanel.setLayout(new GridLayout(0,4,2,2));
		add(lowPanel);
		
		
		nivel1= new JButton("Nivel 1");
		nivel1.setBackground(Color.LIGHT_GRAY);
		nivel1.addActionListener(manejador);
		lowPanel.add(nivel1);
		
		nivel2= new JButton("Nivel 2");
		nivel2.setBackground(Color.LIGHT_GRAY);
		nivel2.addActionListener(manejador);
		lowPanel.add(nivel2);
		
		nivel3= new JButton("Nivel 3");
		nivel3.setBackground(Color.LIGHT_GRAY);
		nivel3.addActionListener(manejador);
		lowPanel.add(nivel3);
		
		
		
		setVisible(true);
	}
	
	private class ManejadorBoton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(juego != 3) {
				if(e.getSource() == nivel1) {
					setVisible(false);
					new Graficos(1,juego,Score1,Score2,nombre1,nombre2);
					dispose();
					
				}
				
				if(e.getSource() == nivel2) {
					setVisible(false);
					new Graficos(2,juego,Score1,Score2,nombre1,nombre2);
					dispose();
					
				}
				if(e.getSource() == nivel3) {
					setVisible(false);
					new Graficos(3,juego,Score1,Score2,nombre1,nombre2);
					dispose();
					
				}
			}
			
			
		}
		
	}

	

}
