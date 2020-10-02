package packjuegos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;



public class Graficos extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel paneljugador1,paneljugador2;
	private JTextField Scorejug1,Scorejug2,jugador1,jugador2;

	private int[][] colores = {{0,0,0},
			{255, 0, 0},
			{139, 0, 0},
			{255, 20, 147},
			{255, 192, 203},
			{219, 112, 147},
			{255, 69, 0},
			{255, 165, 0},
			{189, 183, 107},
			{230, 230, 250},
			{221, 160, 2},
			{255, 0, 25},
			{147, 112, 219},
			{123, 104, 238},
			{173, 255, 47},
			{0, 250, 154},
			{34, 139, 34}
			,{154, 205, 50}
			,{128, 128, 0},
			{143, 188, 139},
			{0, 255, 255},
			{123, 104, 238},
			{255, 228, 196},
			{188, 143, 143},
			{128, 0, 0},
			{205, 92, 92}
			};
	private JButton[] botonesjug1;
	private JButton[] botonesjug2;
	private JButton enter;
	private ManejadorBotonM manejadorB;
	private ManejadorkeyM manejadorK;
	private ManejadorBotonP manejadorBp;
	private ManejadorkeyP manejadorKp;
	private int Nivel,Score1,Score2,posjug2,escogio1;
	private ManejarMemorama jug1,jug2;
	private ManejarPuzzle jug1p,jug2p;
	private String nombre1,nombre2;
			
	public Graficos(int nivel,int juego,int score1,int score2,String Nombre1,String Nombre2) {
		super("JUEGO");
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1300,((nivel+2) * (nivel+2)*15 )+130);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		Nivel = nivel;
		Score1 = score1; 
		Score2 = score2;
		posjug2 = 0;
		escogio1 = -1;
		nombre1 = Nombre1;
		nombre2 = Nombre2;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setFocusable( true );
		
		
		if(juego == 1) {
			
			manejadorB = new ManejadorBotonM();
			manejadorK = new ManejadorkeyM();
			addKeyListener(manejadorK);
			jug1 = new ManejarMemorama(nivel);
			jug2 = new ManejarMemorama(nivel);
			crearbotonesm();
			
		}else {
			
			manejadorBp = new ManejadorBotonP();
			manejadorKp = new ManejadorkeyP();
			addKeyListener(manejadorKp);
			jug1p = new ManejarPuzzle(nivel);
			jug2p = new ManejarPuzzle(nivel);
			crearbotonesp();
			
		}
		Crearscore();
		setVisible(true);
		
	}
	
	@SuppressWarnings("static-access")
	private void finjuego(int Score,int juga) {
		JFrame fin = new JFrame();
		fin.setSize(300, 150);
		fin.setDefaultLookAndFeelDecorated(true);
		fin.setLocationRelativeTo(null);
		fin.setResizable(false);
		fin.setLayout(new GridLayout(2,2,50,30));
		fin.setVisible(true);
		setVisible(false);
		
		
		JTextField quieng = new JTextField();
		JTextField quieng2 = new JTextField();
		quieng.setEditable(false);
		quieng2.setEditable(false);
		quieng.setBorder(null);
		quieng2.setBorder(null);
		if(juga == 1) {
			quieng2.setText("Ganó:"+ nombre1);
		}else {
			
			quieng2.setText("Ganó:"+nombre2);
		}
		quieng.setText("Score : "+Score);
		fin.add(quieng2);
		fin.add(quieng);
		
		
		
		
		if(Score <= 1000) {
			
			JButton boton = new JButton("Seguir Jugando");
			fin.add(boton);
			boton.addMouseListener((MouseListener) new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					fin.setVisible(false);
					new InicioGraficos(Score1,Score2,nombre1,nombre2);
					dispose();
				}
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}    
			});
			
			JButton boton2 = new JButton("Finalizar juego");
			boton2.addMouseListener((MouseListener) new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					fin.setVisible(false);
					
					if(Score1 > Score2) {
						JOptionPane.showMessageDialog(null,"Ganó: "+nombre1+"\nScore: "+Score1+"\nGAME OVER");
					}else {
						JOptionPane.showMessageDialog(null,"Ganó: "+nombre2+"\nScore: "+Score2+"\nGAME OVER");
					}
					dispose();
					fin.dispose();
				}
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}    
			});
			fin.add(boton2);
			
		}else {
			fin.setVisible(false);
			if(Score1 > Score2) {
				JOptionPane.showMessageDialog(null,"Ganó: "+nombre1+"\nScore: "+Score1+"\nGAME OVER");
			}else {
				JOptionPane.showMessageDialog(null,"Ganó: "+nombre2+"\nScore: "+Score2+"\nGAME OVER");
			}
			
			dispose();
			fin.dispose();
		}
		
		
		
		
		
		
		
		setVisible(false);
	}
	
	
	private void crearbotonesm() {
		botonesjug1 = new JButton[(Nivel+2) * (Nivel+2)];
		paneljugador1 = new JPanel();
		paneljugador1.setBounds(0,20,(Nivel+2) * (Nivel+2)*20,(Nivel+2) * (Nivel+2)*15);
		paneljugador1.setLayout(new GridLayout(Nivel+2,Nivel+2,2,2));
		
		
		
		for (int i=0; i<(Nivel+2) * (Nivel+2); i++)
	    {
			botonesjug1[i] = new JButton();
	        botonesjug1[i].setBackground(Color.gray);
	        botonesjug1[i].addMouseListener(manejadorB);
	        paneljugador1.add(botonesjug1[i]);
	             
	    }
		add(paneljugador1);
		
		
		
		
	    botonesjug2 = new JButton[(Nivel+2) * (Nivel+2)];
		paneljugador2 = new JPanel();
		paneljugador2.setBounds(650,20,20 * (Nivel+2) * (Nivel+2),(Nivel+2) * (Nivel+2)*15);
		paneljugador2.setLayout(new GridLayout(Nivel+2,Nivel+2,2,2));
		
		botonesjug2[0] = new JButton();
        botonesjug2[0].setBackground(Color.LIGHT_GRAY);
       // botonesjug2[0].addKeyListener(manejadorK);
        paneljugador2.add(botonesjug2[0]);
		
		
		for (int i=1; i<(Nivel+2) * (Nivel+2); i++)
	    {
			
			botonesjug2[i] = new JButton();
	        botonesjug2[i].setBackground(Color.gray);
	        botonesjug2[i].addMouseListener(manejadorB);
	        paneljugador2.add(botonesjug2[i]);
	             
	    }
		add(paneljugador2);
		
		
		
	}
	
	private void crearbotonesp() {
		botonesjug1 = new JButton[(Nivel+2) * (Nivel+2)];
		paneljugador1 = new JPanel();
		paneljugador1.setBounds(0,20,(Nivel+2) * (Nivel+2)*20,(Nivel+2) * (Nivel+2)*15);
		paneljugador1.setLayout(new GridLayout(Nivel+2,Nivel+2,2,2));
		
		
		
		for (int i=0; i<((Nivel+2) * (Nivel+2)) - 1; i++)
	    {
			botonesjug1[i] = new JButton(Integer.toString(jug1p.escojerpos(i)));
	        botonesjug1[i].setBackground(Color.LIGHT_GRAY);
	        botonesjug1[i].addMouseListener(manejadorBp);
	        paneljugador1.add(botonesjug1[i]);
	             
	    }
		botonesjug1[(Nivel+2) * (Nivel+2)-1] = new JButton();
        botonesjug1[(Nivel+2) * (Nivel+2)-1].addMouseListener(manejadorBp);
        botonesjug1[(Nivel+2) * (Nivel+2)-1].setVisible(false);
        paneljugador1.add(botonesjug1[(Nivel+2) * (Nivel+2)-1]);
	    enter = new JButton("Fin");
		enter.setBackground(Color.LIGHT_GRAY);
		enter.setBounds(0, ((Nivel+2) * (Nivel+2)*15)+ (2 * Nivel+2) + 30, 50, 25);
		enter.addMouseListener(manejadorBp);
		add(enter);
        
        
		add(paneljugador1);
		
		
		
		
	    botonesjug2 = new JButton[(Nivel+2) * (Nivel+2)];
		paneljugador2 = new JPanel();
		paneljugador2.setBounds(650,20,20 * (Nivel+2) * (Nivel+2),(Nivel+2) * (Nivel+2)*15);
		paneljugador2.setLayout(new GridLayout(Nivel+2,Nivel+2,2,2));
       
		
		
		for (int i=0; i<((Nivel+2) * (Nivel+2))-1; i++)
	    {
			
			botonesjug2[i] = new JButton(Integer.toString(jug2p.escojerpos(i)));
			botonesjug2[i].addMouseListener(manejadorBp);
	        botonesjug2[i].setBackground(Color.gray);
	        paneljugador2.add(botonesjug2[i]);
	             
	    }
		botonesjug2[(Nivel+2) * (Nivel+2)-1] = new JButton();
        botonesjug2[(Nivel+2) * (Nivel+2)-1].setBackground(Color.gray);
        botonesjug2[(Nivel+2) * (Nivel+2)-1].setVisible(false);
        paneljugador2.add(botonesjug2[(Nivel+2) * (Nivel+2)-1]);
        
		
		add(paneljugador2);
		
		
		
	}
	
	
	private void Crearscore() {
		jugador1 = new JTextField();
		jugador1.setForeground(Color.black);
		jugador1.setFont(new Font("Palatino",Font.PLAIN,14));
		jugador1.setText(nombre1);
		jugador1.setBounds(0, 0, 81, 20);
		jugador1.setBorder(null);
		jugador1.setEditable(false);
		add(jugador1);
		
		jugador2 = new JTextField();
		jugador2.setFont(new Font("Palatino",Font.PLAIN,14));
		jugador2.setText(nombre2);
		jugador2.setBounds(650, 0,83,20);
		jugador2.setBorder(null);
		jugador2.setEditable(false);
		add(jugador2);
		
		Scorejug1= new JTextField();
		Scorejug1.setFont(new Font("Palatino",Font.PLAIN,14));
		Scorejug1.setText("Score: "+ Integer.toString(Score1));
		Scorejug1.setBounds(0,((Nivel+2) * (Nivel+2)*15)+ (2 * Nivel+2) + 12,500,20);
		Scorejug1.setBorder(null);
		Scorejug1.setEditable(false);
		add(Scorejug1);
		
		Scorejug2= new JTextField();
		Scorejug2.setFont(new Font("Palatino",Font.PLAIN,14));
		Scorejug2.setText("Score: "+ Integer.toString(Score2));
		Scorejug2.setBounds(650,((Nivel+2) * (Nivel+2)*15)+ (2 * Nivel+2) + 12 ,500,20);
		Scorejug2.setBorder(null);
		Scorejug2.setEditable(false);
		add(Scorejug2);
	}
	
	
	private class ManejadorBotonM implements MouseListener{
		int despintar;
		@Override
		public void mouseClicked(MouseEvent e) {
			
			for(int i=0; i<(Nivel+2) * (Nivel+2); i++) {
				if(e.getSource() == botonesjug1[i]) {
					
					jug1.escojerpos(i);
					
					botonesjug1[i].setBackground(new java.awt.Color(colores[jug1.cualcolor(i)][0],colores[jug1.cualcolor(i)][1],colores[jug1.cualcolor(i)][2]));
					//botonesjug1[i].setBackground(Color.black);
					
					if(jug1.yaescogio()) {
						
						
						if(jug1.soniguales() && jug1.cualpos()[0] != jug1.cualpos()[1]) {
							botonesjug1[jug1.cualpos()[0]].setVisible(false);
							botonesjug1[jug1.cualpos()[1]].setVisible(false);
							Scorejug1.setText("Score: "+ Integer.toString(Score1));
							jug1.eliminarpos();
							
							if(jug1.parejas() <= 0) {
								Score1 = Score1 +(Nivel*100);
								finjuego(Score1,1);
							}else if(jug1.parejas() <= 1 && Nivel != 2 ) {
								Score1 = Score1 +(Nivel*100);
								finjuego(Score1,1);
							}
							
							
						}else {
							despintar= 1;
							
						}
					}
				}
					
			}
			requestFocus();
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
			if(despintar == 1) {
				botonesjug1[jug1.cualpos()[0]].setBackground(Color.gray);
				botonesjug1[jug1.cualpos()[1]].setBackground(Color.gray);
				jug1.desescojerpos();
				despintar = 0;
			}
			
		}	
	}
	
	private class ManejadorkeyM implements KeyListener{
		int despintar;
		public void keyPressed(KeyEvent e) {
		
			
			char keyp = e.getKeyChar();
		    keyp = Character.toUpperCase(keyp);
			
			switch(keyp) {
			
			case 'A':
				if(despintar == 1) {
					botonesjug2[jug2.cualpos()[0]].setBackground(Color.gray);
					botonesjug2[jug2.cualpos()[1]].setBackground(Color.LIGHT_GRAY);
					jug2.desescojerpos();
					despintar = 0;
				}
				if(posjug2 > 0) {
					
					int probarpos = posjug2;
					
					while(probarpos > 0) {
						//System.out.println("probarpos = "+probarpos+" color ="+ jug1.cualcolor(probarpos));
						if(jug2.cualcolor(probarpos - 1) != -1) {
							
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.gray);
							}
							posjug2 = probarpos - 1;
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.LIGHT_GRAY);
							}
							break;
						}else {
							
							probarpos--;
							
						}
						
						
						
					}
					
					
					
					
				}
			break;
			
			case 'D':
				if(despintar == 1) {
					botonesjug2[jug2.cualpos()[0]].setBackground(Color.gray);
					botonesjug2[jug2.cualpos()[1]].setBackground(Color.LIGHT_GRAY);
					jug2.desescojerpos();
					despintar = 0;
				}
				if(posjug2 < ((Nivel+2) * (Nivel+2)) -1) {
					
					int probarpos = posjug2;
					
					while(probarpos < ((Nivel+2) * (Nivel+2)) -1) {
						
						if(jug2.cualcolor(probarpos + 1) != -1) {
							
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.gray);
							}
							
							posjug2 = probarpos + 1;
							
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.LIGHT_GRAY);
							}
							
							break;
							}else {
								probarpos++;
						}
						
						
					}
					
					
				}
			break;
			
			case 'S':
				
				if(despintar == 1) {
					botonesjug2[jug2.cualpos()[0]].setBackground(Color.gray);
					botonesjug2[jug2.cualpos()[1]].setBackground(Color.LIGHT_GRAY);
					jug2.desescojerpos();
					despintar = 0;
				}
				if(posjug2 +Nivel+2 <= ((Nivel+2) * (Nivel+2)) -1) {
					
					int probarpos = posjug2;
					
					while(probarpos +Nivel+2 <= ((Nivel+2) * (Nivel+2)) -1) {
						
						if(jug2.cualcolor(probarpos+Nivel+2)!= -1) {
							
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.gray);
							}
							posjug2 = probarpos + Nivel + 2;
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.LIGHT_GRAY);
							}
							break;
							
						}else {
							probarpos = probarpos + Nivel + 2;
						}
						
					}
						
					
					
				}
			break;
			
			case 'W':
				if(despintar == 1) {
					botonesjug2[jug2.cualpos()[0]].setBackground(Color.gray);
					botonesjug2[jug2.cualpos()[1]].setBackground(Color.LIGHT_GRAY);
					jug2.desescojerpos();
					despintar = 0;
				}
				
				if(posjug2 - (Nivel+2) >= 0) {
					
					int probarpos = posjug2;
					
					while(probarpos - (Nivel + 2) >= 0) {
						if(jug2.cualcolor(probarpos - (Nivel + 2)) != -1) {
							
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.gray);
							}
							posjug2 = probarpos - (Nivel + 2);
							if(escogio1 != posjug2) {
								botonesjug2[posjug2].setBackground(Color.LIGHT_GRAY);
							}
							break;
						}else {
							probarpos = probarpos - (Nivel + 2);
						}	
					}
					
					
					
				}
			break;
			
			case '\n':
				
				jug2.escojerpos(posjug2);
				botonesjug2[posjug2].setBackground(new java.awt.Color(colores[jug2.cualcolor(posjug2)][0],colores[jug2.cualcolor(posjug2)][1],colores[jug2.cualcolor(posjug2)][2]));
				if(jug2.yaescogio()) {
					
					
					if(jug2.soniguales() && jug2.cualpos()[0] != jug2.cualpos()[1]) {
						botonesjug2[jug2.cualpos()[0]].setVisible(false);
						botonesjug2[jug2.cualpos()[1]].setVisible(false);
						
						Scorejug2.setText("Score: "+ Integer.toString(Score2));
						jug2.eliminarpos();
						if(jug2.parejas() <= 0 && Nivel == 2) {
							Score2 = Score2 + (Nivel*100);
							finjuego(Score2,2);
						}else if(jug2.parejas() <= 1 && Nivel != 2 ) {
							Score2 = Score2 + (Nivel*100);
							finjuego(Score2,2);
						}
						
					}else {
						despintar = 1;
						
					}
					escogio1 = -1;
				}else {
					escogio1 = posjug2;
				}
				
				break;
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	
	private class ManejadorBotonP implements MouseListener{

		@Override
		public void mouseReleased(MouseEvent e) {
			for(int i=0;i<(Nivel+2) *(Nivel+ 2);i++) {
				
				if(e.getSource() == botonesjug1[i]) {
					
				
					
					
					if(jug1p.vacia()[2] == i+1) {
						
						jug1p.moverderecha();
						i = i + 1;
						
						
						botonesjug1[i].setText(Integer.toString(jug1p.escojerpos(i)));
						botonesjug1[i].setVisible(true);
						botonesjug1[i].setBackground(Color.LIGHT_GRAY);
						botonesjug1[jug1p.vacia()[2]].setVisible(false);
						break;
					}else
					
					if(jug1p.vacia()[2] == i+ Nivel +  2) {
						
						jug1p.moverabajo();
						i = i + Nivel + 2;
						botonesjug1[i].setText(Integer.toString(jug1p.escojerpos(i)));
						botonesjug1[i].setVisible(true);
						botonesjug1[i].setBackground(Color.LIGHT_GRAY);
						botonesjug1[jug1p.vacia()[2]].setVisible(false);
						break;
					}else
					
					if(jug1p.vacia()[2] == i - 1) {
						jug1p.moverizquierda();
						i = i - 1;
						botonesjug1[i].setText(Integer.toString(jug1p.escojerpos(i)));
						botonesjug1[i].setVisible(true);
						botonesjug1[i].setBackground(Color.LIGHT_GRAY);
						botonesjug1[jug1p.vacia()[2]].setVisible(false);
						break;
						
					}else
						
						if(jug1p.vacia()[2] == i - (Nivel +  2)) {
							
							jug1p.moverarriba();
							i = i - (Nivel +  2);
							
							
							botonesjug1[i].setText(Integer.toString(jug1p.escojerpos(i)));
							botonesjug1[i].setVisible(true);
							botonesjug1[i].setBackground(Color.LIGHT_GRAY);
							botonesjug1[jug1p.vacia()[2]].setVisible(false);
							break;
						
						}else {
							
							
							
							
							
						}
					
					
					
				
				}
				
				
				
			}
			
			if(e.getSource() == enter) {
				if(jug1p.orden()) {
					Score1 = Score1 + (Nivel*100);
					Scorejug1.setText("Score: "+ Integer.toString(Score1));
					finjuego(Score1,1);
				}
				
			}
			
			requestFocus();
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private class ManejadorkeyP implements KeyListener{

		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(KeyEvent.getKeyText(e.getKeyCode())) {
			
			case "D":
			case "Right":
			case "d":
				jug2p.moverderecha();
				botonesjug2[jug2p.vacia()[2]+1].setText(Integer.toString(jug2p.escojerpos(jug2p.vacia()[2]+1)));
				botonesjug2[jug2p.vacia()[2]+1].setVisible(true);
				botonesjug2[jug2p.vacia()[2]+1].setBackground(Color.gray);
				botonesjug2[jug2p.vacia()[2]].setVisible(false);
				
				break;
			case "A":
			case "Left":
			case "a":
				jug2p.moverizquierda();
				botonesjug2[jug2p.vacia()[2]-1].setText(Integer.toString(jug2p.escojerpos(jug2p.vacia()[2]-1)));
				botonesjug2[jug2p.vacia()[2]-1].setVisible(true);
				botonesjug2[jug2p.vacia()[2]-1].setBackground(Color.gray);
				botonesjug2[jug2p.vacia()[2]].setVisible(false);
				
				break;
			case "W":
			case "Up":
			case "w":
				jug2p.moverarriba();
				botonesjug2[jug2p.vacia()[2]- (Nivel+2)].setText(Integer.toString(jug2p.escojerpos(jug2p.vacia()[2]-(Nivel+2))));
				botonesjug2[jug2p.vacia()[2]- (Nivel+2)].setVisible(true);
				botonesjug2[jug2p.vacia()[2]-(Nivel+2)].setBackground(Color.gray);
				botonesjug2[jug2p.vacia()[2]].setVisible(false);
				
				break;
				
			case "S":
			case "Down":
			case "s":
				jug2p.moverabajo();
				botonesjug2[jug2p.vacia()[2]+ (Nivel+2)].setText(Integer.toString(jug2p.escojerpos(jug2p.vacia()[2]+(Nivel+2))));
				botonesjug2[jug2p.vacia()[2]+ (Nivel+2)].setVisible(true);
				botonesjug2[jug2p.vacia()[2]+(Nivel+2)].setBackground(Color.gray);
				botonesjug2[jug2p.vacia()[2]].setVisible(false);
				
				break;
				
			case"Enter":
				if(jug2p.orden()) {
					Score2 = Score2 + (Nivel*100);
					Scorejug2.setText("Score: "+ Integer.toString(Score2));
					finjuego(Score2,2);
				}
				
				
			
			
			}
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
		}

		

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
	
	
	/*public static void main(String[] args) {
		Graficos a = new Graficos(1,1,0,0);
		
		
		
	}*/
}
