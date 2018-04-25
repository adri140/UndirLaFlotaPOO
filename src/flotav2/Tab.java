package flotav2;

public class Tab {
	private char[][] tablero;
	private int max;
	private Barco[] barcos;
	private int numBarco = 0;
	private static int maxBarco = 5;
	private History[] historial; //almacena las tiradas de los jugadores sobre este tablero
	private int numHistorial = 0;
	private int maxHistorial;
	
	//Constructores
	public Tab() {
		max = 10;
		tablero = new char[max][max];
		barcos = new Barco[maxBarco];
		this.iniTablero('A');
		historial = new History[(max * max)];
		maxHistorial = (max * max);
	}
	
	public Tab(int max) {
		this();
		if(compMax(max)) { 
			this.max = max;
			tablero = new char[max][max];
			this.iniTablero('A');
			historial = new History[(max * max)];
			maxHistorial = (max * max);
		}
	}
	
	//Setters
	public void setNumBarco(int num) {
		if(num >= 0 && num < maxBarco) numBarco = num;
	}
	
	//getters
	public char[][] getTablero(){
		return tablero;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMaxBarco() {
		return maxBarco;
	}
	
	public Barco[] getBarcos(){
		return barcos;
	}
	
	public int getNumBarco() {
		return numBarco;
	}
	
	//Otros
	/**
	 * Clona un barco en el array de los barcos
	 * @param b
	 * @return
	 */
	public boolean insertarBarco(Barco b) {
		if(numBarco < maxBarco) {
			if(b instanceof Barco2 != false) barcos[numBarco] = new Barco2(b, this);
			else {
				if(b instanceof Barco3 != false) barcos[numBarco] = new Barco3(b, this);
				else {
					if(b instanceof Barco4 != false) barcos[numBarco] = new Barco4(b, this);
					else {
						if(b instanceof Barco5 != false) barcos[numBarco] = new Barco5(b, this);
					}
				}
			}
			numBarco++;
			return true;//a�adido
		}
		return false; //no a�adido
	}
	
	/**
	 * Devuelve el valor de una posicion del tablero
	 * @param x
	 * @param y
	 * @return
	 */
	public char getPos(int x, int y) { //retorna una posicio del taulell
		if((x >= 0 && x < max) && (y >= 0 && y < max)) {
			return tablero[x][y];
		}
		return 'D';
	}
	
	private boolean compMax(int value) {
		if(value > 10) return true;
		else return false;
	}
	
	public void viewTab() {
		System.out.print("   ");
		for(int p = 0; p < max; p++) {
			System.out.print(p + " ");
		}
		for(int i = 0; i < max; i++) {
			System.out.print("\n"+i + " |");
			for(int p = 0; p < max; p++) {
				System.out.print(tablero[i][p] + "|");
			}
		}
	}
	
	private void iniTablero(char car) {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = car; // ?
			}
		}
	}
	
	public boolean comprovar(int num) {
		boolean ok = false;
		if(num >= 0 && num < maxBarco) {
			ok = calPosFin(barcos[num].getX1(), barcos[num].getY1(), barcos[num].getPosiciones(), this, barcos[num].getDireccion());
			if(ok == true) {
				if(barcos[num] instanceof Barco2 != false) ((Barco2) barcos[num]).calPos();
				else {
					if(barcos[num] instanceof Barco3 != false) ((Barco3) barcos[num]).calPos();
					else {
						if(barcos[num] instanceof Barco4 != false) ((Barco4) barcos[num]).calPos();
						else {
							if(barcos[num] instanceof Barco5 != false) ((Barco5) barcos[num]).calPos();
						}
					}
				}
			}
		}
		return ok;
	}
	
	private boolean calPosFin(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = false;
		int x = x1;
		int y = y1;
		switch(direc) { //calcula la posicio final hon terminara el baixell, per la seva comprovaci�
		case 0:
			y = y + (pos - 1);
			break;
		case 1:
			y = y - (pos - 1);
			break;
		case 2:
			x = x - (pos - 1);
			break;
		case 3:
			x = x + (pos - 1);
		}
		if((x >= 0 && x < tablero.getMax()) && (y >= 0 && y < tablero.getMax())) {
			switch(direc) { //calcula la posici� inicial, on comen�ara les comprovacions i on comen�ara a escribir
			case 1:
				y1 = y1 - (pos - 1);
				break;
			case 2:
				x1 = x1 - (pos - 1);
				break;
			}
			ok = comprovarPosiciones(x1, y1, pos, tablero, direc);
			if(ok != false) { escribir(tablero, x1, y1, direc, pos);}
		}
		return ok;
	}
	
	private boolean comprovarPosiciones(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = true;
		if(direc == 0 || direc == 1) ok = comprovarPos01(x1, y1, pos, tablero);
		else ok = comprovarPos23(x1, y1, pos, tablero);
		return ok;
	}
	
	private boolean comprovarPos01(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = tablero.getMax(); //temporal
		if(x1 == 0 && y1 == 0) {
			while(pos > 0 && y1 <= max - 1 && ok != false) {
				if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B') {
					pos--;
					y1++;
				}
				else ok = false;
			}
			if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
		}
		else {
			if(x1 == 0 && y1 == max - 1) {
				if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
				else ok = false;
			}
			else {
				if(x1 == max - 1 && y1 == 0) {
					while(pos > 0 && y1 <= max - 1 && ok != false) {
						if(map[x1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
							pos--;
							y1++;
						}
						else ok = false;
					}
					if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
				}
				else {
					if(x1 == max - 1 && y1 == max - 1) {
						if(map[x1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
						else ok = false;
					}
					else {
						if(x1 == 0) {
							if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B') {
								while(pos > 0 && y1 <= max - 1 && ok != false) {
									if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B') {
										pos--;
										y1++;
									}
									else ok = false;
								}
								if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;}
							}
							else ok = false;
						}
						else {
							if(x1 == max - 1) {
								if(map[x1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
									while(pos > 0 && y1 <= max - 1 && ok != false) {
										if(map[x1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
											pos--;
											y1++;
										}
										else ok = false;
									}
									if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
								}
								else ok = false;
							}
							else {
								if(y1 == 0) {
									while(ok != false && y1 <= max - 1 && pos > 0) {
										if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
											y1++;
											pos--;
										}
										else ok = false;
									}
									if(ok != false && y1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
								}
								else {
									if(y1 == max - 1) {
										if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
										else ok = false;
									}
									else {
										if(map[x1][y1 - 1] != 'B' && map[x1 + 1][y1 - 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(y1 <= max - 1 && pos > 0 && ok != false) {
												if(map[x1][y1] != 'B' && map[x1 + 1][y1] != 'B' && map[x1 - 1][y1] != 'B') {
													pos--;
													y1++;
												}
												else ok = false;
											}
											if(y1 <= max - 1 && ok != false) { if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;}
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
	
	private boolean comprovarPos23(int x1, int y1, int pos, Tab tablero) {
		boolean ok = true;
		char[][] map = tablero.getTablero();
		int max = tablero.getMax(); //temporal
		if(x1 == 0 && y1 == 0) {
			while(ok != false && pos > 0 && x1 <= max - 1) {
				if(map[x1][y1] != 'B' && map[x1][y1 + 1] != 'B') {
					x1++;
					pos--;
				}
				else ok = false;
			}
			if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
		}
		else {
			if(x1 == 0 && y1 == max - 1) {
				while(ok != false && pos > 0 && x1 <= max - 1) {
					if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B') {
						x1++;
						pos--;
					}
					else ok = false;
				}
				if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
			}
			else {
				if(x1 == max - 1 && y1 == 0) {
					if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 + 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
					else ok = false;
				}
				else {
					if(x1 == max - 1 && y1 == max - 1) {
						if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
						else ok = false;
					}
					else {
						if(x1 == 0) {
							while(ok != false && pos > 0 && x1 <= max - 1) {
								if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B' && map[x1][y1 + 1] != 'B') {
									x1++;
									pos--;
								}
								else ok = false;
							}
							if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
						}
						else {
							if(x1 == max - 1) {
								if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B' && map[x1 - 1][y1 + 1] != 'B') { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;}
								else ok = false;
							}
							else {
								if(y1 == 0) {
									while(ok != false && pos > 0 && x1 <= max - 1) {
										if(map[x1][y1] != 'B' && map[x1][y1 + 1] != 'B') {
											x1++;
											pos--;
										}
										else ok = false;
									}
									if(ok != false && x1 <= max - 1) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
								}
								else {
									if(y1 == max - 1) {
										while(ok != false && pos > 0 && x1 <= max - 1) {
											if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B') {
												x1++;
												pos--;
											}
											else ok = false;
										}
										if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
									}
									else {
										if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 + 1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(ok != false && pos > 0 && x1 <= max - 1) {
												if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B' && map[x1][y1 + 1] != 'B') {
													x1++;
													pos--;
												}
												else ok = false;
											}
											if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
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
	
	/**
	 * escribe en el tablero (matriz de caracteres)
	 */
	private void escribir(Tab tablero, int x1, int y1, int dir, int pos) {
		char[][] map = tablero.getTablero();
		if(dir == 0 || dir == 1) {
			while(pos > 0) {
				map[x1][y1] = 'B';
				y1++;
				pos--;
			}
		}
		else {
			while(pos > 0) {
				map[x1][y1] = 'B';
				x1++; //ojooooooooooo
				pos--;
			}
		}
	}
	
	public void reiniTab() {
		this.iniTablero('?');
	}
	
	/**
	 * Devuelve un barco del array barcos
	 * @param num
	 * @return
	 */
	public Barco retBarco(int num) {
		if(numBarco > 0) {
			return barcos[num];
		}
		else return null;
	}
	
	/**
	 * Inserta un log en el historial
	 * @param h
	 */
	public void insertarHistorial(History h) {
		historial[numHistorial] = h;
		numHistorial++;
	}
	
	/**
	 * Gestiona la tirada, si devuelve false es que la posicion lla esta descubierta, si devuelve true indica que la tirada es coorecte
	 * @param x
	 * @param y
	 * @param player
	 * @return
	 */
	public boolean gestDispar(int x, int y, Player player) {
		boolean ok = false;
		boolean b = false;
		ok = disparar(x, y);
		if(ok == true) {
			if(numHistorial < maxHistorial) {
				if(tablero[x][y] == 'B') {
					b = true;
					//compBarco(x, y); //comprueba si el barco esta undido
				}
				historial[numHistorial] = new History(player, x, y, b, this);
				numHistorial++;
			}
		}
		return ok; //si devuelve un true es que se ha disparado a la posicion, si devuelve un false es que en la posicion se ha disparado antes
	}
	
	/**
	 * Comprueba si hay algun barco en la casilla, si hay escribira 'B' si no 'A', pero primero comprueba que no este descubierta, si lo esta devuelve false
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean disparar(int x, int y) {
		boolean ok = false;
		int p;
		int xb, yb;
		if(tablero[x][y] == '?') {
			for(int i = 0; i < maxBarco; i++) {
				if(barcos[i] instanceof Barco2 != false) {
					p = 0;
					xb = barcos[p].getX1();
					yb = barcos[p].getY1();
					while(ok != true && p < 2) {
						ok = igualPos(xb, yb, x, y);
						if(ok) {
							tablero[x][y] = 'B';
						}
						else {
							p++;
							switch(p) {
							case 1:
								xb = ((Barco2) barcos[i]).getX2();
								yb = ((Barco2) barcos[i]).getY2();
								break;
							}
						}
					}
				}
				else {
					if(barcos[i] instanceof Barco3 != false) {
						p = 0;
						xb = barcos[i].getX1();
						yb = barcos[i].getY1();
						while(ok != true && p < 3) {
							ok = igualPos(xb, yb, x, y);
							if(ok) {
								tablero[x][y] = 'B';
							}
							else {
								p++;
								switch(p) {
								case 1:
									xb = ((Barco3) barcos[i]).getX2();
									yb = ((Barco3) barcos[i]).getY2();
									break;
								case 2:
									xb = ((Barco3) barcos[i]).getX3();
									yb = ((Barco3) barcos[i]).getY3();
									break;
								}
							}
						}
					}
					else {
						if(barcos[i] instanceof Barco4 != false) {
							p = 0;
							xb = barcos[i].getX1();
							yb = barcos[i].getY1();
							while(ok != true && p < 4) {
								ok = igualPos(xb, yb, x, y);
								if(ok) {
									tablero[x][y] = 'B';
								}
								else {
									p++;
									switch(p) {
									case 1:
										xb = ((Barco4) barcos[i]).getX2();
										yb = ((Barco4) barcos[i]).getY2();
										break;
									case 2:
										xb = ((Barco4) barcos[i]).getX3();
										yb = ((Barco4) barcos[i]).getY3();
										break;
									case 3:
										xb = ((Barco4) barcos[i]).getX4();
										yb = ((Barco4) barcos[i]).getY4();
										break;
									}
								}
							}
						}
						else {
							if(barcos[i] instanceof Barco5 != false) {
								p = 0;
								xb = barcos[i].getX1();
								yb = barcos[i].getY1();
								while(ok != true && p < 5) {
									ok = igualPos(xb, yb, x, y);
									if(ok) {
										tablero[x][y] = 'B';
									}
									else {
										p++;
										switch(p) {
										case 1:
											xb = ((Barco5) barcos[i]).getX2();
											yb = ((Barco5) barcos[i]).getY2();
											break;
										case 2:
											xb = ((Barco5) barcos[i]).getX3();
											yb = ((Barco5) barcos[i]).getY3();
											break;
										case 3:
											xb = ((Barco5) barcos[i]).getX4();
											yb = ((Barco5) barcos[i]).getY4();
											break;
										case 4:
											xb = ((Barco5) barcos[i]).getX5();
											yb = ((Barco5) barcos[i]).getY5();
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			if(ok == false) {
				tablero[x][y] = 'A';
				ok = true;
			}
		}
		return ok;
	}
	
	private boolean igualPos(int xb, int yb, int x, int y) {
		if(xb == x && yb == y) return true;
		return false;
	}
	
	public void viewHistory() {
		for(int i = 0; i < numHistorial; i++) {
			historial[i].visualizar();
		}
	}
}
