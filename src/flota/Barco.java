package flota;

public class Barco {
	private int numPlayer;
	private Barco2 lanchas;
	private Barco3 submarino;
	private Barco3 destructor;
	private Barco4 acorazado;
	private Barco5 portaviones;
	
	//constructor
	public Barco(int Player) {
		if(Player == 0 || Player == 1) numPlayer = Player;
		else numPlayer = 0;
	}
	
	public Barco(int Player, Barco2 lancha, Barco3 submarino, Barco3 destructor, Barco4 acorazado, Barco5 portaviones) {
		this(Player);
		this.lanchas = lancha;
		this.submarino = submarino;
		this.destructor = destructor;
		this.acorazado = acorazado;
		this.portaviones = portaviones;
	}
	
	//setters
	public void setNumPlayer(int Player) {
		if(Player == 0 || Player == 1) numPlayer = Player;
		else numPlayer = 0;
	}
	
	public void setLanchas(Barco2 barco) {
		lanchas = barco;
	}
	
	public void setSubmarino(Barco3 barco) {
		submarino = barco;
	}
	
	public void setDestructor(Barco3 barco) {
		destructor = barco;
	}
	
	public void setAcorazado(Barco4 barco) {
		acorazado = barco;
	}
	
	public void setPortaviones(Barco5 barco) {
		portaviones = barco;
	}
	
	//getters
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public Barco2 getLanchas() {
		return lanchas;
	}
	
	public Barco3 getSubmarino() {
		return submarino;
	}
	
	public Barco3 getDestructor() {
		return destructor;
	}
	
	public Barco4 getAcorazado() {
		return acorazado;
	}
	
	public Barco5 getPortaviones() {
		return portaviones;
	}
	
	//otros
	public boolean comprLan(Tab tablero) {
		boolean ok = false;
		ok = comprovar(lanchas.getX1(), lanchas.getY1(), 2, tablero, lanchas.getDireccion());
		if(ok) lanchas.calPosiciones();
		return ok;
	}
	
	public boolean comprDes(Tab tablero) {
		boolean ok = false;
		ok = comprovar(destructor.getX1(), destructor.getY1(), 3, tablero, destructor.getDireccion());
		if(ok) destructor.calPosiciones();
		return ok;
	}
	
	public boolean comprSub(Tab tablero) {
		boolean ok = false;
		ok = comprovar(submarino.getX1(), submarino.getY1(), 3, tablero, submarino.getDireccion());
		if(ok) submarino.calPosiciones(); //revisar
		return ok;
	}
	
	public boolean comprAco(Tab tablero) {
		boolean ok = false;
		ok = comprovar(acorazado.getX1(), acorazado.getY1(), 4, tablero, acorazado.getDireccion());
		if(ok) acorazado.calPosiciones(); //revisar
		return ok;
	}
	
	public boolean comprPor(Tab tablero) {
		boolean ok = false;
		ok = comprovar(portaviones.getX1(), portaviones.getY1(), 5, tablero, portaviones.getDireccion());
		return ok;
	}
	
	private boolean comprovar(int x1, int y1, int pos, Tab tablero, int direc) {
		boolean ok = true;
		switch(direc) {
		case 1:
			y1 = y1 - pos;
			break;
		case 2:
			x1 = x1 - pos;
		}
		if(x1 >= 0 && x1 < tablero.getMax() && y1 >= 0 && y1 < tablero.getMax()) {
			ok = comprovarPosiciones(x1, y1, pos, tablero, direc);
			if(ok) escribir(tablero, x1, y1, pos, direc);
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
		if(x1 == 0 && y1 == 0) {
			while(pos > 0 && ok && y1 <= tablero.getMax() - 1) {
				if(map[x1][y1] == 'B' || map [x1 + 1][y1] == 'B') ok = false;
				else {
					pos--;
					y1++;
				}
			}
			if(ok && y1 <= tablero.getMax() - 1) {
				if(map[x1][y1] == 'B' && map[x1 + 1][y1] == 'B') ok = false;
			}
		}
		else {
			if(x1 == 0 && y1 == tablero.getMax() - 1) {
				if(map[x1][y1 - 1] == 'B' || map[x1 + 1][y1 - 1] == 'B') ok = false;
				else if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;
			}
			else {
				if(x1 == tablero.getMax() - 1 && y1 == 0) {
					while(pos > 0 && ok && y1 <= tablero.getMax() - 1) {
						if(map[x1][y1] == 'B' || map [x1 - 1][y1] == 'B') ok = false;
						else {
							pos--;
							y1++;
						}
					}
					if(ok && y1 <= tablero.getMax() - 1) {
						if(map[x1][y1] == 'B' && map[x1 - 1][y1] == 'B') ok = false;
					}
				}
				else {
					if(x1 == tablero.getMax() - 1 && y1 == tablero.getMax() - 1) {
						if(map[x1][y1 - 1] == 'B' || map[x1 + 1][y1 - 1] == 'B') ok = false;
						else if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B') ok = false;
					}
					else {
						if(x1 == 0) {
							while(pos > 0 && ok && y1 <= tablero.getMax() - 1) {
								if(map[x1][y1] == 'B' || map [x1 + 1][y1] == 'B') ok = false;
								else {
									pos--;
									y1++;
								}
							}
							if(ok && y1 <= tablero.getMax() - 1) {
								if(map[x1][y1] == 'B' && map[x1 + 1][y1] == 'B') ok = false;
							}
						}
						else {
							if(x1 == tablero.getMax() - 1) {
								while(pos > 0 && ok && y1 <= tablero.getMax() - 1) {
									if(map[x1][y1] == 'B' || map [x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
									else {
										pos--;
										y1++;
									}
								}
								if(ok && y1 <= tablero.getMax() - 1) {
									if(map[x1][y1] == 'B' && map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
								}
							}
							else {
								if(y1 == 0) {
									while(ok && pos > 0 && y1 <= tablero.getMax() - 1) {
										if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1] [y1] == 'B') ok = false;
										else {
											pos--;
											y1++;
										}
									}
									if(ok && y1 <= tablero.getMax() - 1) if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
								}
								else {
									if(y1 == tablero.getMax() - 1) {
										if(map[x1][y1 - 1] == 'B' || map[x1 + 1][y1 - 1] == 'B' || map[x1 - 1][y1 - 1] == 'B') ok = false;
										else if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
									}
									else {
										if(map[x1][y1 - 1] == 'B' || map[x1 + 1][y1 - 1] == 'B' || map[x1 - 1][y1 - 1] == 'B') ok = false;
										else {
											while(!ok && y1 <= tablero.getMax() - 1 && pos > 0) {
												if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
												else {
													pos--;
													y1++;
												}
											}
											if(ok && y1 <= tablero.getMax()) if(map[x1][y1] == 'B' || map[x1 + 1][y1] == 'B' || map[x1 - 1][y1] == 'B') ok = false;
										}
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
		if(x1 == 0 && y1 == 0) { //para abajo de 0 a 9
			while(ok && x1 <= tablero.getMax() - 1 && pos > 0) {
				if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
				else {
					pos--;
					x1++;
				}
				if(ok && x1 <= tablero.getMax()) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
			}
		}
		else {
			if(x1 == 0 && y1 == tablero.getMax() - 1) {
				while(ok && x1 <= tablero.getMax() - 1 && pos > 0) {
					if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
					else {
						pos--;
						x1++;
					}
					if(ok && x1 <= tablero.getMax()) if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
				}
			}
			else {
				if(x1 == tablero.getMax() - 1 && y1 == 0) {
					if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 + 1] == 'B') ok = false;
					else if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
				}
				else {
					if(x1 == tablero.getMax() - 1 && y1 == tablero.getMax() - 1) {
						if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 - 1] == 'B') ok = false;
						else if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
					}
					else {
						if(x1 == 0) {
							while(ok && pos > 0 && x1 <= tablero.getMax() - 1) {
								if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
								else {
									pos--;
									x1++;
								}
							}
							if(ok && x1 <= tablero.getMax() - 1) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
							
						}
						else {
							if(x1 == tablero.getMax() - 1) {
								if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 + 1] == 'B' || map[x1 - 1][y1 - 1] == 'B') ok = false;
								else if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
							}
							else {
								if(y1 == 0) {
									if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 + 1] == 'B') ok = false;
									else {
										while(ok && pos > 0 && x1 <= tablero.getMax() - 1) {
											if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
											else {
												pos--;
												x1++;
											}
										}
										if(ok && x1 <= tablero.getMax()) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
									}
								}
								else {
									if(y1 == tablero.getMax() - 1) {
										if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 - 1] == 'B') ok = false;
										else {
											while(ok && pos > 0 && x1 <= tablero.getMax() - 1) {
												if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
												else {
													pos--;
													x1++;
												}
											}
											if(ok && x1 <= tablero.getMax()) if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
										}
									}
									else {
										if(map[x1 - 1][y1] == 'B' || map[x1 - 1][y1 - 1] == 'B' || map[x1 - 1] [y1 + 1] == 'B') ok = false;
										else {
											while(ok && pos > 0 && x1 <= tablero.getMax() - 1) {
												if(map[x1][y1] == 'B' || map[x1][y1 - 1] == 'B' || map[x1][y1 + 1] == 'B') ok = false;
												else {
													pos--;
													x1++;
												}
											}
											if(ok && x1 <= tablero.getMax() - 1) if(map[x1][y1] == 'B' || map[x1][y1 + 1] == 'B' || map[x1][y1 - 1] == 'B') ok = false;
										}
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
	
	private void escribir(Tab tablero, int x1, int y1, int dir, int pos) {
		char[][] map = tablero.getTablero();
		if(dir == 0 || dir == 1) {
			while(pos > 1) {
				map[x1][y1] = 'B';
				y1++;
				pos--;
			}
		}
		else {
			while(pos > 1) {
				map[x1][y1] = 'B';
				x1++; //ojooooooooooo
				pos--;
			}
		}
	}
	
}
