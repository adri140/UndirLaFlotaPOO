package flota;

public class Tab {
	private char[][] tablero;
	private int max;
	private Barco barcos;
	
	//Constructores
	public Tab() {
		max = 10;
		tablero = new char[max][max];
		this.iniTablero();
	}
	
	public Tab(int max) {
		this();
		if(compMax(max)) { 
			this.max = max;
			tablero = new char[max][max];
			this.iniTablero();
		}
	}
	
	//Setters
	public void setBarcos(Barco bar) {
		barcos = bar;
	}
	
	//getters
	public char[][] getTablero(){
		return tablero;
	}
	
	public int getMax() {
		return max;
	}
	
	public Barco getBarcos(){
		return barcos;
	}
	
	//Otros
	private boolean compMax(int value) {
		if(value > 10) return true;
		else return false;
	}
	
	/*public boolean comprovarPosiciones(int x1, int y1, int posiciones, int direccion) {
		boolean ok = false;
		ok = comprovarPos(tablero, x1, y1, posiciones, direccion);
		return ok;
	}
	
	private boolean comprovarPos(char[][] tablero, int x1, int y1, int poss, int direc) {
		boolean valid = false;
		if(direc == 1 || direc == 3) { // 0 = derecha, 1 = izquierda, 2 = arriba de 9 a 0, 3 a bajo de 0 a 9
			switch(direc) {
			case 1: x1 = x1 - poss;
				if(x1 >= 0 && x1 <= max - 1) valid = true;
				break;
			case 3: y1 = y1 + poss;
				if(y1 >= 0 && y1 <= max - 1) valid = true;
			}
		}
		if (direc == 0 || direc == 1 && valid) valid = compDir01(tablero, x1, y1, poss, direc);
		return valid;
	}
	
	private boolean compDir01(char[][] tablero, int x1, int y1, int poss, int direc) {
		boolean valid = true;
		int p = x1; //fila
		int i = y1; //columna
		if(p == 0 && i == 0) {
		}
		else {
			if(p == 9 && i == 0) {
				
			}
			else {
				if(p == 0 && i == 9) {
					
				}
				else {
					if(p == 9 && i == 9) {
						
					}
					else {
						if(p == 0) {
							
						}
						else {
							if(p == 9) {
								
							}
							else {
								if(i == 0) {
									
								}
								else {
									if(i == 9) {
										
									}
									else {
										
									}
								}
							}
						}
					}
				}
			}
		}
		return valid;
	}*/
	
	public void viewTab() {
		System.out.print("   A B C D E F G H I J K L M");
		for(int i = 0; i < max; i++) {
			System.out.print("\n"+i + " |");
			for(int p = 0; p < max; p++) {
				System.out.print(tablero[i][p] + "|");
			}
		}
	}
	
	private void iniTablero() {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = 'A';
			}
		}
	}
}
