package flotav2;

public class IAPlayer implements java.io.Serializable {
	private int[] memoria;
	private int dificultad = 2;
	
	//Constructores
	public IAPlayer() {
		memoria = new int[2];
		this.memReset();
	}
	
	//Setters
	public void setMemoria(int[] array) {
		this.memoria = array;
	}
	
	public void setDificultad(int dif) {
		if(dif >= 1 && dif < 3) dificultad = dif;
	}
	
	//getters
	public int[] getMemoria() {
		return memoria;
	}
	
	//otros
	/*Gestiona tota la tirada de la maquina.*/
	public boolean armIA(Tab tablero) {
		boolean ok = false;
		int x, y;
		boolean undit = false;
		do {
			if(memoria[0] == -1) {
				x = Entradas.RandomInt(Tab.getMax());
				y = Entradas.RandomInt(Tab.getMax());
				if(tablero.getPos(x, y) == '?') {
					if(dificultad > 1) ok = this.comRededor(x, y, tablero); //revisarrr
					else ok = true;
					if(ok != false) {
						ok = tablero.gestDispar(x, y, Player.MAQUINA);
						if(ok == true && tablero.getPos(x, y) == 'B') {
							System.out.println("Baixell aliat tocat.");
							this.memoria[0] = x;
							this.memoria[1] = y;
						}
					}
				}
			}
			else {
				int direccion;
				do {
					x = memoria[0];
					y = memoria[1];
					if(tablero.getPos(x + 1, y) != 'B' && tablero.getPos(x - 1, y) != 'B' && tablero.getPos(x, y + 1) != 'B' && tablero.getPos(x, y - 1) != 'B') {
						direccion = this.IAgenSearch(x, y, Tab.getMax());
						switch(direccion) {
						case 0:
							y = y + 1;
							break;
						case 1:
							y = y - 1;
							break;
						case 2:
							x = x - 1;
							break;
						case 3:
							x = x + 1;
							break;
						}
						if(tablero.getPos(x, y) == '?') {
							ok = true;
						}
						else {
							ok = false;
						}
					}
					else {
						if((tablero.getPos(x + 1, y) == 'B' || tablero.getPos(x - 1, y) == 'B')||(tablero.getPos(x, y + 1) == 'A' && tablero.getPos(x, y - 1) == 'A')) {
							direccion = Entradas.RandomInt(2) + 2;
							if(tablero.getPos(x + 1, y) == 'B' || tablero.getPos(x - 1, y) == 'B') {
								x = this.recursivityX(x, y, tablero, direccion);
								ok = true;
							}
						}
						else {
							direccion = Entradas.RandomInt(2);
							if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x, y - 1) == 'B') {
								y = this.recursivityY(x, y, tablero, direccion);
								ok = true;
							}
						}
					}
					
				}while (ok != true);
				
				//System.out.println(x + ";" + y + ";" + direccion);
				ok = tablero.gestDispar(x, y, Player.MAQUINA);
				
				if(ok == true && tablero.getPos(x, y) == 'B') {
					if(tablero.undit(x, y)) {
						System.out.println("Baixell aliat enfonsat.");
						this.memReset();
						undit = true;
					}
					else {
						System.out.println("Baixell aliat tocal.");
						this.memoria[0] = x;
						this.memoria[1] = y;
					}
				}
			}
		}while(ok != true);
		return undit;
	}
	
	/*Busca els baixells en bertical*/
	private int recursivityX(int x, int y, Tab tablero, int direccion) {
		if(direccion == 3 && x < Tab.getMax() - 1) {
			if(tablero.getPos(x, y) != '?' && tablero.getPos(x, y) != 'A') x = this.recursivityX(x + 1, y, tablero, direccion);
		}
		else {
			if(x > 0) if(tablero.getPos(x, y) != '?' && tablero.getPos(x, y) != 'A') x = this.recursivityX(x - 1, y, tablero, direccion);
		}
		return x;
	}
	
	/*Busca les posicions del baixell en oritzontal.*/
	private int recursivityY(int x, int y, Tab tablero, int direccion) {
		if(direccion == 1 && y > 0) {
			if(tablero.getPos(x, y) != '?' && tablero.getPos(x, y) != 'A') y = this.recursivityY(x, y - 1, tablero, direccion);
		}
		else {
			if(y < Tab.getMax() - 1) if(tablero.getPos(x, y) != '?' && tablero.getPos(x, y) != 'A') y = this.recursivityY(x, y + 1, tablero, direccion);
		}
		return y;
	}
	
	/**
	 * Mira al voltant, si hi ha alguna 'B' retorna un false, si no retorna un true, nomes s'utilitza quan dispara 100% aleatoriament.
	 * @param x
	 * @param y
	 * @param tablero
	 * @return
	 */
	private boolean comRededor(int x, int y, Tab tablero) { //revisarrr
		boolean ok = true;
		if(x == 0 && y == 0) { //ok
			if(tablero.getPos(x, y) != 'B' && tablero.getPos(x + 1, y) != 'B') {
				if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x + 1, y + 1) == 'B') ok = false;
			}
			else ok = false;
		}
		else {
			if(x == 0 && y == Tab.getMax() - 1) { //ok
				if(tablero.getPos(x, y - 1) != 'B' && tablero.getPos(x + 1, y - 1) != 'B') {
					if(tablero.getPos(x, y) == 'B' || tablero.getPos(x + 1, y) == 'B') ok = false;
				}
				else ok = false;
			}
			else {
				if(x == Tab.getMax() - 1 && y == 0) {//ok
					if(tablero.getPos(x, y) != 'B' && tablero.getPos(x - 1, y) != 'B') {
						if(tablero.getPos(x, y + 1) == 'B' || tablero.getPos(x - 1, y + 1) == 'B') ok = false;
					}
					else ok = false;
				}
				else {
					if(x == Tab.getMax() && y == Tab.getMax()) {//ok
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
	
	/*Genera una direccio aleatoria segons la posicio en la que es troba actualment.*/
	public int IAgenSearch(int x, int y, int max) {
		int resposta = 0;
		if(x == 0 && y == 0) {
			resposta = Entradas.RandomInt(2);
			switch(resposta) {
				case 0: resposta = 0;
			break;
				case 1: resposta = 3;
			break;
			}
		}
		else {
			if(x == max - 1 && y == 0) {
				resposta = Entradas.RandomInt(2);
				switch(resposta) {
					case 0: resposta = 0;
				break;
					case 1: resposta = 2;
				break;
				}
			}
			else {
				if(x == 0 && y == max - 1) {
					resposta = Entradas.RandomInt(2);
					switch(resposta) {
						case 0: resposta = 1;
					break;
						case 1: resposta = 3;
					break;
					}
				}
				else {
					if(x == max - 1 && y == max - 1) {
						resposta = Entradas.RandomInt(2);
						switch(resposta) {
							case 0: resposta = 2;
						break;
							case 1: resposta = 1;
						break;
						}
					}
					else {
						if(x == 0) {
							resposta = Entradas.RandomInt(3);
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
								resposta = Entradas.RandomInt(3);
								switch(resposta) {
									case 0: resposta = 0;
								break;
									case 1: resposta = 1;
								break;
									case 2: resposta = 2;
								break;
								}
							}
							else {
								if(y == 0) {
									resposta = Entradas.RandomInt(3);
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
										resposta = Entradas.RandomInt(3);
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
										resposta = Entradas.RandomInt(4);
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
	
	/*Realitza un reset a la memoria de la IA.*/
	private void memReset() {
		for(int i = 0; i < 2; i++) {
			memoria[i] = -1;
		}
	}
}
