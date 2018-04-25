package flotav2;

import java.util.Random;
import java.util.Scanner;

public class Run {
	
	final static Scanner reader = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tab tablero = new Tab();
		tablero = genTab();
		tablero.reiniTab();
		System.out.println("");
		tablero.viewTab();
		System.out.println("");
		boolean ok = false;
		boolean tmp = false;
		while(ok != true) {
			tmp = false;
			int x = 0;
			int y = 0;
			int aux = 0;
			while(tmp == false) {
				System.out.print("x: ");
				x = reader.nextInt();
				System.out.print("y: ");
				y = reader.nextInt();
				tmp = tablero.gestDispar(x, y, Player.PLAYER);
				if(tmp == false) {
					System.out.println("Tria un altre casella");
					reader.nextLine();
				}
			}
			System.out.println("");
			tablero.viewTab();
			System.out.println("");
			System.out.print("1 = continuar, 2 = salir ");
			aux = reader.nextInt();
			if(aux == 1) ok = false;
			else ok = true;
		}
		tablero.viewBarcos();
		System.out.println("");
		tablero.viewHistory();
	}
	
	private static Tab genTab() {
		Random rnd = new Random();
		Tab TMP = new Tab();
		int x1, y1, dir;
		boolean ok = false;
		
		int i = 0;
		while(i < TMP.getMaxBarco()) {
			ok = false;
			switch(i) {
			case 0: //barco2
				x1 = rnd.nextInt(TMP.getMax());
				y1 = rnd.nextInt(TMP.getMax());
				dir = rnd.nextInt(4);
				TMP.insertarBarco(new Barco2(x1, y1, dir, TMP));
				((Barco2) TMP.retBarco(i)).visualizar();
				System.out.print("Press enter");
				reader.nextLine();
				ok = TMP.comprovar(i);
				System.out.println(ok + " " + i);
				System.out.println("");
				((Barco2) TMP.retBarco(i)).visualizar();
				break;
			case 1: //barco3
			case 2:
				x1 = rnd.nextInt(TMP.getMax());
				y1 = rnd.nextInt(TMP.getMax());
				dir = rnd.nextInt(4);
				TMP.insertarBarco(new Barco3(x1, y1, dir, TMP));
				((Barco3) TMP.retBarco(i)).visualizar();
				System.out.print("Press enter");
				reader.nextLine();
				ok = TMP.comprovar(i);
				System.out.println(ok + " " + i);
				System.out.println("");
				((Barco3) TMP.retBarco(i)).visualizar();
				break;
			case 3: //barco4
				x1 = rnd.nextInt(TMP.getMax());
				y1 = rnd.nextInt(TMP.getMax());
				dir = rnd.nextInt(4);
				TMP.insertarBarco(new Barco4(x1, y1, dir, TMP));
				((Barco4) TMP.retBarco(i)).visualizar();
				System.out.print("Press enter");
				reader.nextLine();
				ok = TMP.comprovar(i);
				System.out.println(ok + " " + i);
				System.out.println("");
				((Barco4) TMP.retBarco(i)).visualizar();
				break;
			case 4: //barco5
				x1 = rnd.nextInt(TMP.getMax());
				y1 = rnd.nextInt(TMP.getMax());
				dir = rnd.nextInt(4);
				TMP.insertarBarco(new Barco5(x1, y1, dir, TMP));
				((Barco5) TMP.retBarco(i)).visualizar();
				System.out.print("Press enter");
				reader.nextLine();
				ok = TMP.comprovar(i);
				System.out.println(ok + " " + i);
				System.out.println("");
				((Barco5) TMP.retBarco(i)).visualizar();
				break;
			}
			if(ok == true) {
				i++;
				TMP.viewTab();
				System.out.println("");
			}
			else TMP.setNumBarco(TMP.getNumBarco() - 1);
		}
		return TMP;
	}

}
