package packjuegos;

import java.util.Random;

public class CrearPuzzle implements Crear {
    private int[][] Tablero;
    private int limites;
    private int numeros;

    public CrearPuzzle(int nivel) {
        this.Tablero = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                this.Tablero[i][j] = 0;
            }
        }
        
        this.limites = nivel + 2;
        this.numeros = (limites * limites) - 1;
        
        settablero();
    }

    public void settablero() {
        Random r = new Random();
        int x;
        int y;
        int pos = 0;
        this.Tablero[limites-1][limites-1] = -1;
        while (pos < this.numeros) {
            x = r.nextInt(this.limites);
            y = r.nextInt(this.limites);
            if (this.Tablero[x][y] == 0) {
                    this.Tablero[x][y] = pos+1;
                    pos++;
            }
        }
    }

    public int[][] tablero(){
    	return this.Tablero;
    }
    public int pos(int x, int y) {
        return this.Tablero[x][y];
    }
    


}