package upse.ia.search.uninformed;

import java.util.ArrayList;

public interface Estado {
	double metrica = 0;
	
	public Estado generarHijo(Estrategia s, Problema p, Estado padre, Accion a);
	
}
