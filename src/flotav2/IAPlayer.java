package flotav2;

import java.util.Random;
import java.util.Random;

public class IAPlayer {
	private int[] memoria;
	final static Random rnd = new Random();
	
	//Constructores
	public IAPlayer() {
		memoria = new int[2];
		this.memReset();
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
		boolean ok = true;
		int x, y;
		do {
			if(memoria[0] == -1) {
				x = rnd.nextInt(Tab.getMax());
				y = rnd.nextInt(Tab.getMax());
				if(tablero.getPos(x, y) == '?') {
					ok = this.comRededor(x, y, tablero);
					if(ok != false) {
						ok = tablero.gestDispar(x, y, Player.MAQUINA);
						if(ok == true && tablero.getPos(x, y) == 'B') {
							this.memoria[0] = x;
							this.memoria[1] = y;
						}
					}
				}
			}
			else {
				do {
					x = memoria[0];
					y = memoria[1];
					int buscar = this.IAgenSearch(x, y, Tab.getMax()); //genera la direcció cap a on buscar en funció de la seva posició
					if(buscar == 0 || buscar == 1) { y = this.IAcol(buscar, y);} //retorna la columna on disparara
					else { x = this.IAfila(buscar, x);} //retorna la fila on disparara
					if((x >= 0 && x <= Tab.getMax() - 1) && (y >= 0 && y <= Tab.getMax() - 1)) {
						if(tablero.getPos(x, y) == '?') {
							ok = true;
						}
						else {
							if(tablero.getPos(x, y) == 'B') {
								if(buscar == 0 || buscar == 1) {
									y = this.ReCol(tablero, x, y, buscar);
								}
								else {
									x = this.ReFila(tablero, x, y, buscar);
								}
								ok = true;
							}
							else ok = false;
						}
					}
					else ok = false;
				}while (ok != true);
				ok = tablero.gestDispar(x, y, Player.MAQUINA);
				if(tablero.undit(x, y)) {
					this.memReset();
				}
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
		boolean ok = true;
		if(x == 0 && y == 0) {
			if(tablero.getPos(x, y) != 'B' && tablero.getPos(x + 1, y) != 'B') {
				if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x + 1, y + 1) == 'B') ok = false;
			}
			else ok = false;
		}
		else {
			if(x == 0 && y == Tab.getMax() - 1) {
				if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x + 1, y - 1) != 'B') {
					if(tablero.getPos(x, y) == 'B' || tablero.getPos(x + 1, y) == 'B') ok = false;
				}
				else ok = false;
			}
			else {
				if(x == Tab.getMax() - 1 && y == 0) {
					if(tablero.getPos(x, y) != 'B' && tablero.getPos(x - 1, y) != 'B') {
						if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x - 1, y + 1) == 'B') ok = false;
					}
					else ok = false;
				}
				else {
					if(x == Tab.getMax() && y == Tab.getMax()) {
						if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x - 1, y - 1) != 'B') {
							if(tablero.getPos(x, y) == 'B' || tablero.getPos(x - 1, y) == 'B') ok = false;
						}
						else ok = false;
					}
					else {
						if(x == 0) { //ok
							if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x + 1, y - 1) != 'B') {
								if(tablero.getPos(x, y) != 'B' && tablero.getPos(x + 1, y) != 'B') {
									if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x + 1, y + 1) == 'B') ok = false;
								} 
								else ok = false;
							}
							else ok = false;
						}
						else {
							if(x == Tab.getMax()) { //ok
								if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x - 1, y - 1) != 'B') {
									if(tablero.getPos(x, y) != 'B' && tablero.getPos(x - 1, y) != 'B') {
										if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x - 1, y + 1) == 'B') ok = false;
									}
									else ok = false;
								}
								else ok = false;
							}
							else {
								if(y == 0) { //ok
									if(tablero.getPos(x - 1, y) != 'B' && tablero.getPos(x - 1, y + 1) != 'B') {
										if(tablero.getPos(x, y) != 'B' && tablero.getPos(x, y + 1) != 'B') {
											if(tablero.getPos(x + 1, y + 1) == 'B' || tablero.getPos(x + 1, y) == 'B') ok = false;
										}
										else ok = false;
									}
									else ok = false;
								}
								else {
									if(y == Tab.getMax()) { //ok
										if(tablero.getPos(x - 1, y - 1) != 'B' && tablero.getPos(x - 1, y) != 'B') {
											if(tablero.getPos(x, y) != 'B' && tablero.getPos(x, y - 1) != 'B') {
												if(tablero.getPos(x + 1, y) == 'B' || tablero.getPos(x + 1, y - 1) == 'B') ok = false;
											}
											else ok = false;
										}
										else ok = false;
									}
									else { //ok
										if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x - 1, y - 1) != 'B' && tablero.getPos(x + 1, y - 1) != 'B') {
											if(tablero.getPos(x, y) != 'B' && tablero.getPos(x - 1, y) != 'B' && tablero.getPos(x + 1, y) != 'B') {
												if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x - 1, y + 1) == 'B' || tablero.getPos(x + 1, y + 1) == 'B') ok = false;
											}
											else ok = false;
										}
										else ok = false;
									}
								}
							}
						}
					}
				}
			}
		}
		return ok;
	}
	
	public int IAgenSearch(int x, int y, int max) {
		int resposta = 0;
		if(x == 0 && y == 0) {
			resposta = rnd.nextInt(2);
			switch(resposta) {
				case 0: resposta = 0;
			break;
				case 1: resposta = 2;
			break;
			}
		}
		else {
			if(x == max - 1 && y == 0) {
				resposta = rnd.nextInt(2);
				switch(resposta) {
					case 0: resposta = 0;
				break;
					case 1: resposta = 3;
				break;
				}
			}
			else {
				if(x == 0 && y == max - 1) {
					resposta = rnd.nextInt(2);
					switch(resposta) {
						case 0: resposta = 1;
					break;
						case 1: resposta = 2;
					break;
					}
				}
				else {
					if(x == max - 1 && y == max - 1) {
						resposta = rnd.nextInt(2);
						switch(resposta) {
							case 0: resposta = 3;
						break;
							case 1: resposta = 1;
						break;
						}
					}
					else {
						if(x == 0) {
							resposta = rnd.nextInt(3);
							switch(resposta) {
								case 0: resposta = 0;
							break;
								case 1: resposta = 1;
							break;
								case 2: resposta = 3;
							break;
							}
						}
						else {
							if(x == max - 1) {
								resposta = rnd.nextInt(3);
								switch(resposta) {
									case 0: resposta = 0;
								break;
									case 1: resposta = 1;
								break;
									case 2: resposta = 3;
								break;
								}
							}
							else {
								if(y == 0) {
									resposta = rnd.nextInt(3);
									switch(resposta) {
										case 0: resposta = 0;
									break;
										case 1: resposta = 2;
									break;
										case 2: resposta = 3;
									break;
									}
								}
								else {
									if(y == max - 1) {
										resposta = rnd.nextInt(3);
										switch(resposta) {
											case 0: resposta = 1;
										break;
											case 1: resposta = 2;
										break;
											case 2: resposta = 3;
										break;
										}
									}
									else {
										resposta = rnd.nextInt(4);
									}
								}
							}
						}
					}
				}
			}
		}
		return resposta;
	}
	
	private void memReset() {
		for(int i = 0; i < 2; i++) {
			memoria[i] = -1;
		}
	}
	
	/**
	 * Method <b>IAcol</b>, modifica el valor de la columna emmagatzemada a la memória en funció a la direcció, en aquet cas modifica la columna si la direcció és 0 o 1 (Dreta o Esquerra).
	 * @param buscar Emmagatzema la direcció cap a on disparara, en aquest method nomes hi hauran dos valors 0 o 1.
	 * @param y Per defecte conte el valor de la posicio de la memória que coorespon a la columna.
	 * @return Retorna el nou valor de la columna.
	 */
	public int IAcol(int buscar, int y) {
		if(buscar == 0) y = y + 1;
		else y = y - 1;
		return y;
	}
	
	/**
	 * Method <b>IAfila</b>, modifica el valor de la fila emmagatzemada a la memória en funció a la direcció, en aquest cas modifica la fila si la direcció és 2 o 3 (Abaix o Adalt).
	 * @param buscar Emmagatzema la direcció cap a on disparara, en aquest method nomes hi hauran dos valors 2 o 3.
	 * @param x Per defecte conte el valor de la posicio de la memória que coorespon a la fila.
	 * @return Retorna el nou valor de la fila.
	 */
	public int IAfila (int buscar, int x) {
		if(buscar == 2) x = x + 1;
		else x = x - 1;
		return x;
	}
	
	/**
	 * Method <b>ReCol</b>, busca la seguent part del baixell a enfonsar, partint de alguna part del mateix baixell ja descobert, horitzontalment.
	 * @param tablero Taulell sobre el que treballa.
	 * @param x Indica la fila on treballa.
	 * @param y Indica la columna on treballa.
	 * @param buscar Indica la posició cap a on buscara una celda buida/sense descobrir.
	 * @return Retorna el valor final de la columna.
	 */
	public int ReCol(Tab tablero, int x, int y, int buscar) {
		if(buscar == 0) {
			if(tablero.getPos(x, y) != '?' && y < Tab.getMax() - 1) y = ReCol(tablero, x, (y + 1), buscar);
		}
		else {
			if(tablero.getPos(x, y) != '?' && y > 0) y = ReCol(tablero, x, (y - 1), buscar);
		}
		return y;
	}
	
	/**
	 * Method <b>ReFila</b>, busca la seguent part del baixell a enfonsar, partint de alguna part del mateix baixell ja descobert, verticalment.
	 * @param tablero Taulell sobre el que treballa.
	 * @param x Indica la fila on treballa.
	 * @param y Indica la columna on treballa.
	 * @param buscar Indica la posició cap a on buscara una celda buida/sense descobrir.
	 * @return Retorna el valor final de la fila.
	 */
	public int ReFila(Tab tablero, int x, int y, int buscar) {
		if(buscar == 2) {
			if(tablero.getPos(x, y) != '?' && x < Tab.getMax() - 1) x = ReFila(tablero, (x - 1), y, buscar);
		}
		else {
			if(tablero.getPos(x, y) != '?' && x > 0) x = ReFila(tablero, (x + 1), y, buscar);
		}
		return x;
	}
}
