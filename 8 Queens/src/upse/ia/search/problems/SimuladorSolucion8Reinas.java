package upse.ia.search.problems;

public class SimuladorSolucion8Reinas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Probar que la prueba de estado meta funciona
		Tablero t = new Tablero();
		ColocarFicha cf0 = new ColocarFicha(0, 4, t);
		t=t.colocarFicha(cf0);
		ColocarFicha cf1 = new ColocarFicha(1, 2, t);
		t=t.colocarFicha(cf1);
		ColocarFicha cf2 = new ColocarFicha(2, 0, t);
		t=t.colocarFicha(cf2);
		ColocarFicha cf3 = new ColocarFicha(3, 6, t);
		t=t.colocarFicha(cf3);
		ColocarFicha cf4 = new ColocarFicha(4, 1, t);
		t=t.colocarFicha(cf4);
		ColocarFicha cf5 = new ColocarFicha(5, 7, t);
		t=t.colocarFicha(cf5);
		ColocarFicha cf6 = new ColocarFicha(6, 5, t);
		t=t.colocarFicha(cf6);
		ColocarFicha cf7 = new ColocarFicha(7, 3, t);
		t=t.colocarFicha(cf7);
		
		
		System.out.println("***********************************");
		if(t.verificarEstadoMeta())
		{
			System.out.println("Prueba de estado meta funciona");
		}else{
			System.out.println("Prueba de estado meta no funciona");
		}
		System.out.println("***********************************");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		OchoReinas or= new OchoReinas();
		try {
			//Solucionador8Reinas.ingenua(or);
			Tablero tableroSolucion = Solucionador8Reinas.DFS(or);
			System.out.println("La solucion es: " + tableroSolucion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
