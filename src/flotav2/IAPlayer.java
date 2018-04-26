package flotav2;

import java.util.Random;

public class IAPlayer {
	private int[] memoria;
	
	//Constructores
	public IAPlayer() {
		memoria = new int[2];
	}
	
	//Setters
	public void setMemoria(int[] array) {
		this.memoria = array;
	}
	
	//getters
	public int[] getMemoria() {
		return memoria;
	}
	
	//otros
	public void armIA(Tab tablero) {
		Random rnd = new Random();
		boolean ok = true;
		int x, y;
		do {
			x = rnd.nextInt(Tab.getMax());
			y = rnd.nextInt(Tab.getMax());
			if(tablero.getPos(x, y) == '?') {
				ok = tablero.gestDispar(x, y, Player.MAQUINA);
			}
		}while(ok != true);
	}
	
	/**
	 * Mira al voltant, si hi ha alguna 'B' retorna un false, si no retorna un true;
	 * @param x
	 * @param y
	 * @param tablero
	 * @return
	 */
	private boolean comRededor(int x, int y, Tab tablero) {
		boolean ok = false;
		if(x == 0 && y == 0) {
			
		}
		else {
			if(x == Tab.getMax() && y == 0) {
				
			}
		}
	}
}
