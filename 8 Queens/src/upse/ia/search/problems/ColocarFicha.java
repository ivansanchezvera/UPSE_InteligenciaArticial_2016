package upse.ia.search.problems;

import upse.ia.search.uninformed.*;

public class ColocarFicha implements Accion{

	int columna;
	int fila;
	private Tablero tableroOriginal;
	private Tablero tableroNuevo;
	private boolean esValida;
	
	ColocarFicha(int columna, int fila, Tablero t)
	{
		this.columna = columna;
		this.fila = fila;
		this.tableroOriginal = t;
		this.tableroNuevo = new Tablero(t);
		if(esValida(t))
		{
			tableroNuevo.colocarFicha(this);
		}
	}
	public boolean esValida(Tablero t) {
		// TODO Auto-generated method stub
		// verificar que no existe otra ficha en esa casila
		// verificar que sea un movimiento valido (Depende de la estrategia)
		if(t.matrizTablero[columna][fila])
		{
			return false;
		}else{
			if(verificarNoInterseccion()){
				tableroNuevo = new Tablero(tableroOriginal);
				tableroNuevo.matrizTablero[columna][fila] = true;
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Override
	public boolean esValida() {
		// TODO Auto-generated method stub
		return esValida(tableroOriginal);
	}
	
	private boolean verificarNoInterseccion()
	{
		return verificarDiagonal() && verificarFilasColumnas();
	}
	
	private boolean verificarFilasColumnas()
	{
		boolean verificacion = true;
		//Verificar por fila y columna
				for(int cf=0; cf<8; cf++)
				{
					if(tableroOriginal.matrizTablero[cf][this.fila])
					{
						return false;
					}
					
					if(tableroOriginal.matrizTablero[this.columna][cf])
					{
						return false;
					}
				}
		return verificacion;
	}

	private boolean verificarDiagonal()
	{
		boolean verificacion = true;
		int tempColumna = columna;
		int tempFila = fila;
		while(tempColumna<8 && tempColumna>=0 && tempFila<8 && tempFila>=0)
		{
			tempColumna = tempColumna-1;
			tempFila = tempFila-1;
			if(tempColumna<0 || tempFila<0)
			{
				break;
			}
			if(tableroOriginal.matrizTablero[tempColumna][tempFila])
			{
				return false;
			}
		}
		
		tempColumna = columna;
		tempFila = fila;
		while(tempColumna<8 && tempColumna>=0 && tempFila<8 && tempFila>=0)
		{
			tempColumna = tempColumna+1;
			tempFila = tempFila-1;
			if(tempColumna>=8 || tempFila<0)
			{
				break;
			}
			if(tableroOriginal.matrizTablero[tempColumna][tempFila])
			{
				return false;
			}
		}
		
		tempColumna = columna;
		tempFila = fila;
		while(tempColumna<8 && tempColumna>=0 && tempFila<8 && tempFila>=0)
		{
			tempColumna = tempColumna+1;
			tempFila = tempFila+1;
			if(tempColumna>=8 || tempFila>=8)
			{
				break;
			}
			if(tableroOriginal.matrizTablero[tempColumna][tempFila])
			{
				return false;
			}
		}
		
		tempColumna = columna;
		tempFila = fila;
		while(tempColumna<8 && tempColumna>=0 && tempFila<8 && tempFila>=0)
		{
			tempColumna = tempColumna-1;
			tempFila = tempFila+1;
			if(tempColumna<0 || tempFila>=8)
			{
				break;
			}
			if(tableroOriginal.matrizTablero[tempColumna][tempFila])
			{
				return false;
			}
		}
	return verificacion;
	}
	
	@Override
	public String toString() {
		
		return "ColocarFicha [columna=" + columna + ", fila=" + fila + ", tablero nuevo=" + tableroNuevo+ "]";
	}
	
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public Tablero getTableroOriginal() {
		return tableroOriginal;
	}
	public void setTableroOriginal(Tablero tableroOriginal) {
		this.tableroOriginal = tableroOriginal;
	}
	public Tablero getTableroNuevo() {
		return tableroNuevo;
	}
	public void setTableroNuevo(Tablero tableroNuevo) {
		this.tableroNuevo = tableroNuevo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna;
		result = prime * result + fila;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColocarFicha other = (ColocarFicha) obj;
		if (columna != other.columna)
			return false;
		if (fila != other.fila)
			return false;
		if(!other.getTableroNuevo().mismaMatrizTablero(tableroNuevo.getMatrizTablero()))
			return false;
		return true;
	}
	
	
}
