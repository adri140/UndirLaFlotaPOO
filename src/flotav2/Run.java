package flotav2;

import java.util.Random;
import java.util.Scanner;

public class Run {
	
	final static Scanner reader = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tab tablero = new Tab();
		tablero = genTab();
		
	}
	
	private static Tab genTab() {
		Random rnd = new Random();
		Tab TMP = new Tab();
		int x1, y1, dir;
		boolean ok = false;
		
		int i = 0;
		while(i < 5) {
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
