package packjuegos;

public class ManejarPuzzle  {
	private CrearPuzzle p;
	private int x;
	private int y;
	private int limites,Nivel;
	
	public ManejarPuzzle(int nivel) {
		p = new CrearPuzzle(nivel);
		x = nivel + 1;
		y = nivel + 1;
		limites = nivel + 2; 
		Nivel = nivel;
	}
	
	public void moverabajo() {
		if(y > 0) {
			p.tablero()[x][y] = p.tablero()[x][y-1];
			p.tablero()[x][y-1] = -1;
			this.y--;
		}
	}
	public void moverarriba() {
		
		if(y < limites -1) {
			
			p.tablero()[x][y] = p.tablero()[x][y+1];
			p.tablero()[x][y+1] = -1;
			this.y++;
			
			
		}
	}
	
	public void moverderecha() {
		if(x > 0) {
			p.tablero()[x][y] = p.tablero()[x-1][y];
			p.tablero()[x-1][y] = -1;
			this.x--;
		}
	}
	public void moverizquierda() {
		if(x < limites-1) {
			p.tablero()[x][y] = p.tablero()[x+1][y];
			p.tablero()[x+1][y] = -1;
			this.x++;
		}
	}
	
	public int[][] tablero(){
		return p.tablero();
	}
	
public int escojerpos(int i) {
		
		int cont = i;
		
		for(int y=0; y<Nivel+2; y++) {
			for(int x=0; x<Nivel+2; x++) {
				if(cont != 0 ) {
					cont--;
				}else {
					return p.pos(x, y);
				}
			}
		}
		return -1;
		
	}
   public int[] vacia() {
	   int[] arreglo = new int[3];
	   arreglo[0] = x;
	   arreglo[1] = y;
	   
		int  cont = 0;
		for(int y=0; y<Nivel+2; y++) {
			for(int x=0; x<Nivel+2; x++) {
				if(p.pos(x, y) == -1) {
				    arreglo[2] = cont ;
				}else {
					cont++;
				}
				
				
			}
		}
	   
	return arreglo;
	   
   }
   
   public boolean orden() {
	   
	   int  cont = 1;
		for(int y=0; y<Nivel+2; y++) {
			for(int x=0; x<Nivel+2; x++) {
				if(p.pos(x, y) !=cont &&  p.pos(x, y) != -1  ) {
				    return false;
				}else {
					cont++;
				}
				
				
			}
		}
		return true;
	   
   }
	
	public static void main(String[] args) {
		ManejarPuzzle p = new ManejarPuzzle(3);
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(p.tablero()[i][j] + "\t");
            }
            System.out.println("\n");
        }
		
		p.moverarriba();
		 System.out.println("\n");
		
		
		
	}

	
	

}
