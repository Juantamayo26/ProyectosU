package packjuegos;
import java.util.Random;
import java.lang.Math;

public class CrearMemorama implements Crear {

    private int[][] Tablero;
    int limites;
    int colores;

    public CrearMemorama(int nivel) {
        this.Tablero = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Tablero[i][j] = 0;
            }
        }
        this.limites = nivel + 2;
        this.colores = (int) Math.ceil((float)(limites * limites) / 2);
        settablero();
    }

    public void settablero() {


        Random r = new Random();
        int x;
        int y;
        Boolean cont = false;

        while (0 < this.colores) {

            x = r.nextInt(this.limites);
            y = r.nextInt(this.limites);
            if (this.Tablero[x][y] == 0) {
                this.Tablero[x][y] = this.colores;
                if (cont || (colores == 1 && limites != 4)) {
                    this.colores--;
                    cont = false;
                } else {
                    cont = true;
                }
            }
        }
    }

    public int pos(int x, int y) {
        return this.Tablero[x][y];
    }
    public int[][] tablero(){
    	return this.Tablero;
    }

}