package flotav2;

public class Tab {
	private char[][] tablero;
	private Barco[] barcos;
	private int numBarco = 0;
	private History[] historial; //almacena las tiradas de los jugadores sobre este tablero
	private int numHistorial = 0;
	
	private static int maxHistorial; //indica el numero maximo de historiales almacenados en este tablero
	private static int maxBarco = 5; //indica el numero maximo de varcos por tablero
	private static int max; //indica el maximo tamanyo del tablero quadrado
	
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
			tablero = new char[max][max];
			this.iniTablero('A');
			Tab.setMax(max);
			historial = new History[(max * max)];
			maxHistorial = (max * max);
		}
	}
	
	//Setters
	public void setNumBarco(int num) {
		if(num >= 0 && num < maxBarco) numBarco = num;
	}
	
	public static void setMax(int max) {
		Tab.max = max;
	}
	
	//getters
	public char[][] getTablero(){
		return tablero;
	}
	
	public static int getMax() {
		return Tab.max;
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
			return true;//añadido
		}
		return false; //no añadido
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
		System.out.println("");
	}
	
	private void iniTablero(char car) {
		for(int i = 0; i < max; i++) {
			for(int p = 0; p < max; p++) {
				tablero[i][p] = car; // ?
			}
		}
	}
	
	/**
	 * Inici la comprovacion de si el barco puede posicionarse o no en esas posiciones
	 * @param num
	 * @return
	 */
	public boolean comprovar(int num) {
		boolean ok = false;
		if(barcos[num].getX1() != -1 && barcos[num].getY1() != -1 && barcos[num].getDireccion() != -1) {
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
		}
		return ok;
	}
	
	private boolean calPosFin(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = false;
		int x = x1;
		int y = y1;
		switch(direc) { //calcula la posicio final hon terminara el baixell, per la seva comprovació
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
		if((x >= 0 && x < Tab.getMax()) && (y >= 0 && y < Tab.getMax())) {
			switch(direc) { //calcula la posició inicial, on començara les comprovacions i on començara a escribir
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
		int max = Tab.getMax(); //temporal
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
		int max = Tab.getMax(); //temporal
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
										if(map[x1 - 1][y1] != 'B' && map[x1 - 1][y1 - 1] != 'B') {
											while(ok != false && pos > 0 && x1 <= max - 1) {
												if(map[x1][y1] != 'B' && map[x1][y1 - 1] != 'B') {
													x1++;
													pos--;
												}
												else ok = false;
											}
											if(ok != false && x1 <= max - 1) { if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;}
										}
										else ok = false;
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
	/**
	 * Rescriu el taulell, amb el caracter ?
	 */
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
		if((x >= 0 && x < max) && (y >= 0 && y < max)) {
			ok = disparar(x, y);
			if(ok == true) {
				if(numHistorial < maxHistorial) {
					if(tablero[x][y] == 'B') b = true;
					historial[numHistorial] = new History(player, x, y, b, this);
					numHistorial++;
				}
			}
		}
		return ok; //si devuelve un true es que se ha disparado a la posicion, si devuelve un false es que en la posicion se ha disparado antes
	}
	
	/**
	 * Comprueba si el barco esta undido 
	 * @param pos
	 */
	private void compBarco(int pos) {
		boolean ok = false;
		if(barcos[pos] instanceof Barco2 != false) {
			ok = this.barco2Ok((Barco2) barcos[pos]);
		}
		else {
			if(barcos[pos] instanceof Barco3 != false) {
				ok = this.barco3Ok((Barco3) barcos[pos]);
			}
			else {
				if(barcos[pos] instanceof Barco4 != false) {
					ok = this.barco4Ok((Barco4) barcos[pos]);
				}
				else {
					if(barcos[pos] instanceof Barco5 != false) {
						ok = this.barco5Ok((Barco5) barcos[pos]);
					}
				}
			}
		}
		if(ok) System.out.println("Barco undido :)");
	}
	
	private boolean barco2Ok(Barco2 barco) {
		int xb, yb;
		boolean ok = true;
		int p = 0;
		xb = barco.getX1();
		yb = barco.getY1();
		
		while(ok != false && p < 2) {
			if(tablero[xb][yb] != 'B') ok = false;
			else {
				p++;
				switch(p) {
				case 1:
					xb = barco.getX2();
					yb = barco.getY2();
					break;
				}
			}
		}
		if(ok == true) barco.setUndido(true);
		return ok;
	}
	
	private boolean barco3Ok(Barco3 barco) {
		int xb, yb;
		boolean ok = true;
		int p = 0;
		xb = barco.getX1();
		yb = barco.getY1();
		
		while(ok != false && p < 3) {
			if(tablero[xb][yb] != 'B') ok = false;
			else {
				p++;
				switch(p) {
				case 1:
					xb = barco.getX2();
					yb = barco.getY2();
					break;
				case 2:
					xb = barco.getX3();
					yb = barco.getY3();
					break;
				}
			}
		}
		if(ok == true) barco.setUndido(true);
		return ok;
	}
	
	private boolean barco4Ok(Barco4 barco) {
		int xb, yb;
		boolean ok = true;
		int p = 0;
		xb = barco.getX1();
		yb = barco.getY1();
		
		while(ok != false && p < 4) {
			if(tablero[xb][yb] != 'B') ok = false;
			else {
				p++;
				switch(p) {
				case 1:
					xb = barco.getX2();
					yb = barco.getY2();
					break;
				case 2:
					xb = barco.getX3();
					yb = barco.getY3();
					break;
				case 3:
					xb = barco.getX4();
					yb = barco.getY4();
					break;
				}
			}
		}
		if(ok == true) barco.setUndido(true);
		return ok;
	}
	
	private boolean barco5Ok(Barco5 barco) {
		int xb, yb;
		boolean ok = true;
		int p = 0;
		xb = barco.getX1();
		yb = barco.getY1();
		
		while(ok != false && p < 5) {
			if(tablero[xb][yb] != 'B') ok = false;
			else {
				p++;
				switch(p) {
				case 1:
					xb = barco.getX2();
					yb = barco.getY2();
					break;
				case 2:
					xb = barco.getX3();
					yb = barco.getY3();
					break;
				case 3:
					xb = barco.getX4();
					yb = barco.getY4();
					break;
				case 4:
					xb = barco.getX5();
					yb = barco.getY5();
					break;
				}
			}
		}
		if(ok == true) barco.setUndido(true);
		return ok;
	}
	
	/**
	 * Comprueba si hay algun barco en la casilla, si hay escribira 'B' si no 'A', pero primero comprueba que no este descubierta, si lo esta devuelve false
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean disparar(int x, int y) {
		boolean ok = false;
		if(tablero[x][y] == '?') {
			int i = 0;
			for(i = 0; i < maxBarco && ok != true; i++) {
				if(barcos[i] instanceof Barco2 != false) {
					ok = this.comBarco2(i, x, y);
				}
				else {
					if(barcos[i] instanceof Barco3 != false) {
						ok = this.comBarco3(i, x, y);
					}
					else {
						if(barcos[i] instanceof Barco4 != false) {
							ok = this.comBarco4(i, x, y);
						}
						else {
							if(barcos[i] instanceof Barco5 != false) {
								ok = this.comBarco5(i, x, y);
							}
						}
					}
				}
			}
			if(ok == false) {
				tablero[x][y] = 'A';
				ok = true;
			}
			else {
				i--;
				this.compBarco(i);
			}
		}
		return ok;
	}
	
	/**
	 * Comprueba si la posicion en la que se ha disparado se encuentra algun barco2
	 * @param pos
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean comBarco2(int pos, int x, int y) {
		boolean ok = false;
		int p = 0;
		int xb = barcos[pos].getX1();
		int yb = barcos[pos].getY1();
		while(ok != true && p < 2) {
			ok = igualPos(xb, yb, x, y);
			if(ok) {
				tablero[x][y] = 'B';
			}
			else {
				p++;
				switch(p) {
				case 1:
					xb = ((Barco2) barcos[pos]).getX2();
					yb = ((Barco2) barcos[pos]).getY2();
					break;
				}
			}
		}
		return ok;
	}
	
	private boolean comBarco3(int pos, int x, int y) {
		boolean ok = false;
		int p = 0;
		int xb = barcos[pos].getX1();
		int yb = barcos[pos].getY1();
		while(ok != true && p < 3) {
			ok = igualPos(xb, yb, x, y);
			if(ok) {
				tablero[x][y] = 'B';
			}
			else {
				p++;
				switch(p) {
				case 1:
					xb = ((Barco3) barcos[pos]).getX2();
					yb = ((Barco3) barcos[pos]).getY2();
					break;
				case 2:
					xb = ((Barco3) barcos[pos]).getX3();
					yb = ((Barco3) barcos[pos]).getY3();
					break;
				}
			}
		}
		return ok;
	}
	
	private boolean comBarco4(int pos, int x, int y) {
		boolean ok = false;
		int p = 0;
		int xb = barcos[pos].getX1();
		int yb = barcos[pos].getY1();
		while(ok != true && p < 4) {
			ok = igualPos(xb, yb, x, y);
			if(ok) {
				tablero[x][y] = 'B';
			}
			else {
				p++;
				switch(p) {
				case 1:
					xb = ((Barco4) barcos[pos]).getX2();
					yb = ((Barco4) barcos[pos]).getY2();
					break;
				case 2:
					xb = ((Barco4) barcos[pos]).getX3();
					yb = ((Barco4) barcos[pos]).getY3();
					break;
				case 3:
					xb = ((Barco4) barcos[pos]).getX4();
					yb = ((Barco4) barcos[pos]).getY4();
					break;
				}
			}
		}
		return ok;
	}
	
	private boolean comBarco5(int pos, int x, int y) {
		boolean ok = false;
		int p = 0;
		int xb = barcos[pos].getX1();
		int yb = barcos[pos].getY1();
		while(ok != true && p < 5) {
			ok = igualPos(xb, yb, x, y);
			if(ok) {
				tablero[x][y] = 'B';
			}
			else {
				p++;
				switch(p) {
				case 1:
					xb = ((Barco5) barcos[pos]).getX2();
					yb = ((Barco5) barcos[pos]).getY2();
					break;
				case 2:
					xb = ((Barco5) barcos[pos]).getX3();
					yb = ((Barco5) barcos[pos]).getY3();
					break;
				case 3:
					xb = ((Barco5) barcos[pos]).getX4();
					yb = ((Barco5) barcos[pos]).getY4();
					break;
				case 4:
					xb = ((Barco5) barcos[pos]).getX5();
					yb = ((Barco5) barcos[pos]).getY5();
					break;
				}
			}
		}
		return ok;
	}
	
	private boolean igualPos(int xb, int yb, int x, int y) {
		if(xb == x && yb == y) return true;
		return false;
	}
	
	/**
	 * Muestra el hostorial de disparos realizados sobre este tablero
	 */
	public void viewHistory() {
		for(int i = numHistorial - 1; i >= 0; i--) {
			historial[i].visualizar();
		}
	}
	
	public void viewBarcos() {
		for(int p = 0; p < maxBarco; p++){
			barcos[p].visualizar();
		}
	}
}
