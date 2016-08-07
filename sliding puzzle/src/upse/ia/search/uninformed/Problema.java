package upse.ia.search.uninformed;

import java.util.ArrayList;

public abstract class Problema {

	private Estado estadoInicial;
	private ArrayList<Estado> estadosMeta = new ArrayList<Estado>();
	private ArrayList<Estado> estadosPosibles = new ArrayList<Estado>();
	private ArrayList<Accion> acciones = new ArrayList<Accion>();
	double costoRuta = 0;
	
	public abstract boolean PruebaEstadoMeta(Estado e);
	
	public abstract Estado modeloTransicion(Estado e, Accion a);
	
	
	
}
