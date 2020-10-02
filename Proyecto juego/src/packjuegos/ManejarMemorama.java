package packjuegos;

public class ManejarMemorama {
	private CrearMemorama M;
	private int[] posiciones;
	private int escogidos,Nivel,parejas;
	
	
	public ManejarMemorama(int nivel) {
		Nivel=nivel;
		M = new CrearMemorama(nivel);
		posiciones = new int[4];
		escogidos = 0;
		parejas = (int) Math.ceil((float)(nivel+2) *( nivel+2) / 2);
	}
	
	
	public void escojerpos(int i) {
		
		int cont = i;
		
		for(int y=0; y<Nivel+2; y++) {
			for(int x=0; x<Nivel+2; x++) {
				if(cont != 0 ) {
					cont--;
				}else {
					posiciones[escogidos] = x;
					escogidos++;
					posiciones[escogidos] = y;
					escogidos++;
					cont--;
				}
				
				
			}
		}
		
	}
	
	public Boolean yaescogio() {
		if(escogidos == 4) {
			return true;
		}else {
			return false;
		}
	}
	
	public int[] cualpos() {
		
		
		int[] posicion = new int[2];
		int size = 0;
		
		
		for(int posis = 0;posis<3; posis = posis + 2) {
			
			int cont = 0;
			for(int y=0; y<Nivel+2; y++) {
				for(int x=0; x<Nivel+2; x++) {
					if(posiciones[posis]== x && posiciones[posis+1]== y  ) {
						posicion[size] = cont;
						size++;
					}else {
						cont++;
					}
				}
			}
		}
		
		
		return posicion;
		
	}
	
public int cualcolor(int pos) {
			for(int y=0; y<Nivel+2; y++) {
				for(int x=0; x<Nivel+2; x++) {
					if(pos > 0  ) {
						pos--;
					}else {
					    return M.pos(x, y);
					}
				}
			}
			return 0;
		}
		
		
		
		
	
	
	public Boolean soniguales() {
		return M.pos(posiciones[0], posiciones[1]) == M.pos(posiciones[2], posiciones[3]);
	}
	
	
	public void eliminarpos() {
		M.tablero()[posiciones[0]][posiciones[1]] = -1;
		M.tablero()[posiciones[2]][posiciones[3]] = -1;
		parejas--;
		escogidos = 0;
	}
	
	public void desescojerpos() {
		escogidos = 0;
	}
	
	public int parejas() {
		return parejas;
	}

	
	public int pos(int x,int y) {
		return M.pos(x, y);
	}
	
	
	
	
	

}
