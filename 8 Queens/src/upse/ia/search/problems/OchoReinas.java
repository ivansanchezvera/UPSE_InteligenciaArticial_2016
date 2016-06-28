package upse.ia.search.problems;

import java.util.ArrayList;

import upse.ia.search.uninformed.Accion;
import upse.ia.search.uninformed.Estado;
import upse.ia.search.uninformed.Estrategia;
import upse.ia.search.uninformed.Problema;

public class OchoReinas extends Problema{

	private Tablero tableroInicial;
	
	OchoReinas()
	{
		//Generar Tablero Vacio
		tableroInicial = new Tablero();
	}
	
	OchoReinas(Tablero t)
	{
		tableroInicial = t;
	}
	
	
	@Override
	public boolean PruebaEstadoMeta(Estado e) {
		// TODO Auto-generated method stub
		// TODO Implementar prueba de estado meta
		// En el tablero ninguna reina debe chocar con otra
		return PruebaEstadoMeta((Tablero)e);
		//return false;
	}
	
	private boolean PruebaEstadoMeta(Tablero t){
		boolean isEstadoMeta = false;
		
		isEstadoMeta = t.verificarEstadoMeta();
		
		return isEstadoMeta;
	}

	public Tablero getEstadoInicial()
	{
		return tableroInicial;
	}

	@Override
	public Estado modeloTransicion(Estado e, Accion a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ColocarFicha generarAccion(Tablero t, Estrategia estrategia, ArrayList<ColocarFicha> expansion) throws Exception
	{
		ColocarFicha cf = null;
		switch (estrategia) {
		case INGENUA: 
			cf = ingenuamenteGenerarAccion(t);
			break;
		case DFS:
			cf =DFSGenerarAccion(t, expansion);
			break;
		case BFS:
			cf =BFSGenerarAccion(t);
			break;
		case BACKTRACKING:
			break;
		default:
			throw new Exception("Debe Seleccionar una estrategia de generacion de hijos");
		}
		if(cf==null)
		{
			//throw new Exception("No hay acciones posibles");
		}
		return cf;
	}
	
	private ColocarFicha BFSGenerarAccion(Tablero t) {
		// TODO Auto-generated method stub
		return null;
	}

	private ColocarFicha DFSGenerarAccion(Tablero t, ArrayList<ColocarFicha> expansion) throws NoMasAccionesEnColumnaException
	{
		ColocarFicha cf = null;
			for(int f=0; f<8; f++)
			{
				if(t.getUltimaColumna()<1)
				{
					cf = new ColocarFicha(0, f, t);
					if(!expansion.contains(cf))
					{
						System.out.println(cf);
						return cf;
					}else{
						if(f!=7)
						{
							continue;
						}else{
							throw new NoMasAccionesEnColumnaException();
						}
					}
				}else
				{
					cf = new ColocarFicha(t.getUltimaColumna(), f, t);
					if(!expansion.contains(cf)&&cf.esValida())
					{
						System.out.println(cf);
						return cf;
					}else
					{
						if(f!=7)
						{
							continue;
						}else{
							throw new NoMasAccionesEnColumnaException();
						}
					}
				}
			}
		return cf;
		//throw new Exception("No hay acciones posibles");
	}
	
	private ColocarFicha ingenuamenteGenerarAccion(Tablero t) throws Exception 
	{
		ColocarFicha cf;

		for(int c=0; c<8; c++)
		{
			for(int f=0; f<8; f++)
			{
				cf = new ColocarFicha(c, f, t);
				if(cf.esValida())
				{
					System.out.println(cf);
					return cf;
				}
			}
		}
		
		throw new Exception("No hay acciones posibles");
		
	}
	

}
