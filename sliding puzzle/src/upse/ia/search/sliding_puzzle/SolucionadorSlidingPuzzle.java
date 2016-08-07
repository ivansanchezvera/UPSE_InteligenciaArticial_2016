package upse.ia.search.sliding_puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import upse.ia.search.uninformed.Accion;
import upse.ia.search.uninformed.Estado;
import upse.ia.search.uninformed.Estrategia;

public class SolucionadorSlidingPuzzle {

	public static PuzzleState aStar(SlidingPuzzleProblem spp) throws Exception
	{
		PuzzleState estadoActual = (PuzzleState) spp.getEstadoInicial();
		estadoActual.calculateFN((PuzzleState) spp.getEstadoMeta());
		//TODO Fronterea debe ser un priority queue
		Comparator<PuzzleState> comparador = new PuzzleState(estadoActual.getTamanoTablero());
		PriorityQueue<PuzzleState> frontera = new PriorityQueue<PuzzleState>(comparador);
		ArrayList<Estado> explorados = new ArrayList<Estado>(); 
		frontera.add(estadoActual); 
		
		int contador = 0;
		int contadorSoluciones = 0;
		
		do{
			if(frontera.isEmpty())
			{
				throw new Exception("Hey No hay solucion");
			}
			
			estadoActual = frontera.poll();
			System.out.println("***********************************************");
			System.out.println("Explorados :" + contador);
			
			System.out.println("Estado actual del Puzzle :" + estadoActual);
			
			if(spp.PruebaEstadoMeta(estadoActual))
			{
				contadorSoluciones++;
				System.out.println("Solucion encontrada luego de " + contador + " estados explorados.");
				return estadoActual;

			}
			
			explorados.add(estadoActual);
			ArrayList<MoverFicha> nuevasAcciones = spp.generarAcciones(estadoActual);
			
			for(MoverFicha mf: nuevasAcciones)
			{
				PuzzleState estadoGenerado = new PuzzleState(estadoActual);
				estadoGenerado.ejecutarMovimiento(mf);
				estadoGenerado.calculateFN((PuzzleState) spp.getEstadoMeta());
				
				if(!explorados.contains(estadoGenerado) && !frontera.contains(estadoGenerado))
				{
					frontera.offer(estadoGenerado);
					System.out.println("\nEstado Generado : " + estadoGenerado);
				}else{
					System.out.println("\nEstado Generado pero repetido: " + estadoGenerado);
				}
			}
			System.out.println("-------------------------------------");
			
			contador++;
			if(contador%100==0)
			{
				//Thread.sleep(1000);
			}
		}while(true);
	}
}
