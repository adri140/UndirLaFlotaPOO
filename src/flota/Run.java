package flota;

import java.util.Random;

public class Run {
	
	final static Random rnd = new Random();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Progecto en desarollo.");
		Tab tabMaquina = new Tab();
		Barco barMaquina = new Barco(0);
		boolean ok = false;
		
		while(!ok) {
			createBarco2(barMaquina.getLanchas(), tabMaquina);
			ok = barMaquina.comprLan(tabMaquina);
		}
		System.out.println(barMaquina.getLanchas().toString());
		ok = false;
		while(!ok) {
			createBarco3(barMaquina.getDestructor(), tabMaquina);
			ok = barMaquina.comprDes(tabMaquina);
		}
		System.out.println(barMaquina.getDestructor().toString());
		ok = false;
		while(!ok) {
			createBarco3(barMaquina.getSubmarino(), tabMaquina);
			ok = barMaquina.comprSub(tabMaquina);
		}
		System.out.println(barMaquina.getSubmarino().toString());
		ok = false;
		while(!ok) {
			createBarco4(barMaquina.getAcorazado(), tabMaquina);
			ok = barMaquina.comprAco(tabMaquina);
		}
		System.out.println(barMaquina.getAcorazado().toString());
		ok = false;
		while(!ok) {
			createBarco5(barMaquina.getPortaviones(), tabMaquina);
			ok = barMaquina.comprPor(tabMaquina);
		}
		System.out.println(barMaquina.getPortaviones().toString());
		
		tabMaquina.viewTab();
	}
	
	private static void createBarco2(Barco2 Barco, Tab tablero) {
		Barco.setX1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setY1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setDireccion(rnd.nextInt(4));
		
		/*Barco.setX1(0, tablero);
		System.out.print("x1 " + Barco.getX1() + " ");
		Barco.setY1(9, tablero);
		System.out.print("y1 " + Barco.getY1() + " ");
		Barco.setDireccion(1);
		System.out.print("direccion " + Barco.getDireccion() + "\n");*/
	}
	
	private static void createBarco3(Barco3 Barco, Tab tablero) {
		Barco.setX1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setY1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setDireccion(rnd.nextInt(4));
		//System.out.print("direccion " + Barco.getDireccion() + "\n");
	}
	
	private static void createBarco4(Barco4 Barco, Tab tablero) {
		Barco.setX1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setY1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setDireccion(rnd.nextInt(4));
		//System.out.print("direccion " + Barco.getDireccion() + "\n");
	}
	
	private static void createBarco5(Barco5 Barco, Tab tablero) {
		Barco.setX1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setY1(rnd.nextInt(tablero.getMax()), tablero);
		Barco.setDireccion(rnd.nextInt(4));
		//System.out.print("direccion " + Barco.getDireccion() + "\n");
	}
}
