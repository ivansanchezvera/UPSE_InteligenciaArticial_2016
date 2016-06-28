package upse.ia.search.problems;

import java.util.ArrayList;

import upse.ia.search.uninformed.Accion;
import upse.ia.search.uninformed.Estado;
import upse.ia.search.uninformed.Estrategia;
import upse.ia.search.uninformed.Problema;

public class Solucionador8Reinas {

	//OchoReinas or = new OchoReinas();
	
	public static Tablero ingenua(OchoReinas p) throws Exception
	{
		Tablero estadoActual = p.getEstadoInicial();
		ArrayList<Estado> frontera = new ArrayList<Estado>();
		ArrayList<Estado> explorados = new ArrayList<Estado>(); 
		frontera.add(estadoActual); //Ojo esto debe ser LIFO
		
			do{
				if(frontera.isEmpty())
				{
					throw new Exception("Hey No hay solucion");
				}
				
				estadoActual = (Tablero) frontera.remove(frontera.size()-1);
				System.out.println("Estado actual del tablero :" + estadoActual);
				
				if(p.PruebaEstadoMeta(estadoActual))
				{
					return estadoActual;
				}
				
				explorados.add(estadoActual);
				
				do{
					ArrayList<ColocarFicha> expansion = new ArrayList<ColocarFicha>();
					Accion nuevaAccion = p.generarAccion(estadoActual, Estrategia.INGENUA, expansion);
					if(expansion.contains(nuevaAccion))
					{
						break;
					}else{
						expansion.add((ColocarFicha) nuevaAccion);
						estadoActual = estadoActual.colocarFicha((ColocarFicha)nuevaAccion);
						if(!frontera.contains(estadoActual)&&explorados.contains(estadoActual))
						{frontera.add(estadoActual);}
					}
				}while(true);
				
			}while(true);
	}
	
	public static Tablero DFS(OchoReinas p) throws Exception
	{
		Tablero estadoActual = p.getEstadoInicial();
		ArrayList<Estado> frontera = new ArrayList<Estado>();
		ArrayList<Estado> explorados = new ArrayList<Estado>(); 
		ArrayList<Estado> expansion = new ArrayList<Estado>(); 
		frontera.add(estadoActual); //Ojo esto debe ser LIFO
		
		int contador = 0;
		ArrayList<ColocarFicha> movimientoExpansion = new ArrayList<ColocarFicha>();
			do{
				if(frontera.isEmpty())
				{
					throw new Exception("Hey No hay solucion");
				}
				
				estadoActual = (Tablero) frontera.remove(0);
				System.out.println("***********************************************");
				System.out.println("Estado actual del tablero :" + estadoActual);
				
				if(p.PruebaEstadoMeta(estadoActual))
				{
					System.out.println("Solucion encontrada luego de " + contador + " estados explorados.");
					return estadoActual;
				}
				
				explorados.add(estadoActual);
				
				do{
					Accion nuevaAccion;
					try{
						nuevaAccion = p.generarAccion(estadoActual, Estrategia.DFS, movimientoExpansion);
					}catch(NoMasAccionesEnColumnaException nmace)
					{
						System.err.println(nmace.getMessage());
						frontera.addAll(0, expansion);
						expansion = new ArrayList<Estado>();
						break;
					}
					/*if(nuevaAccion==null||movimientoExpansion.contains(nuevaAccion))
					{
						frontera.addAll(0, expansion);
						expansion = new ArrayList<Estado>();
						break;
					}else{*/
						movimientoExpansion.add((ColocarFicha) nuevaAccion);
						Tablero nuevoEstado = new Tablero(estadoActual);
						nuevoEstado = nuevoEstado.colocarFicha((ColocarFicha)nuevaAccion);
						if(!frontera.contains(nuevoEstado)&&!explorados.contains(nuevoEstado))
						{
							expansion.add(nuevoEstado);
						}
					//}
				}while(true);
				contador++;
			}while(true);
	}
}
