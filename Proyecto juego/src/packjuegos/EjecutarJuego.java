package packjuegos;
//Autores: Juan Pablo Medina 1004735957
//         Juan Carlos Yepes 1010101857
//         Juan Esteban Pino 1192715790


import javax.swing.JOptionPane;

public class EjecutarJuego {
	 public static void main(String args[]){
		String nombre1 = JOptionPane.showInputDialog("Introduzca el nombre del jugador 1: ");
		String nombre2 = JOptionPane.showInputDialog("Introduzca el nombre del jugador 2: ");
		if(nombre1.isEmpty()) {
			nombre1 = "Mario";
		}
		if(nombre2.isEmpty()) {
			nombre2 = "Luigi";
		}
		new InicioGraficos(0,0,nombre1,nombre2);
	 }

}
