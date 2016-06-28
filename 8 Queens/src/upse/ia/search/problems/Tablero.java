package upse.ia.search.problems;
import java.util.Arrays;

import upse.ia.search.uninformed.*;

public class Tablero implements Estado{

	
	boolean matrizTablero[][];
	private int ultimaColumna = 0;
	
	Tablero()
	{
		int c = 0;
		int f = 0;
		matrizTablero = new boolean[8][8];
		for(c=0;c<8;c++)
		{
			for(f=0;f<8;f++)
			{
				matrizTablero[c][f] = false;
			}
		}
	}
	
	Tablero(Tablero t)
	{
		this.matrizTablero = t.cloneMatrizTablero();
		this.ultimaColumna = t.getUltimaColumna();
	}
	public int getUltimaColumna() {
		return ultimaColumna;
	}

	public void setUltimaColumna(int ultimaColumna) {
		this.ultimaColumna = ultimaColumna;
	}

	public boolean[][] getMatrizTablero() {
		return matrizTablero;
	}

	@Override
	public Estado generarHijo(Estrategia s, Problema p, Estado padre, Accion a) {
		// TODO Auto-generated method stub
		// Validar si la accion es valida
		return null;
	}
	
	public Tablero colocarFicha(ColocarFicha ficha)
	{
		Tablero temp = new Tablero();
		//temp.matrizTablero = cloneTablero();
		//temp.matrizTablero[ficha.columna][ficha.fila] = true;
		temp.matrizTablero = ficha.getTableroNuevo().cloneMatrizTablero();
		if(getUltimaColumna()<=7)
		{
			if(ficha.esValida(temp))
			{
				temp.matrizTablero[ficha.columna][ficha.fila] = true;
			}
			
			if(getUltimaColumna()+1<8)
			{
				temp.ultimaColumna = ficha.getTableroNuevo().getUltimaColumna()+1;
			}
		}else{
			//Aqui falla
			System.out.println("Este tablero falla: " + ficha.getTableroNuevo().toString());
			return null;
		}
		
		//ultimaColumna = ficha.columna+1;
		return temp;
	}

	@Override
	public String toString() {
		String tableroStr = "\n";
		for(int f=0; f<8; f++)
		{
			for(int c=0; c<8; c++)
			{
				if(!matrizTablero[c][f]){
					tableroStr = tableroStr.concat("O |");
				}else{
					tableroStr = tableroStr.concat("X |");
				}
			}
			tableroStr = tableroStr.concat("\n");
		}
		return tableroStr;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public boolean verificarEstadoMeta() {
		// TODO Auto-generated method stub
		boolean esMeta = true;
		int contarReinas = 0;
		Tablero tableroTemp = new Tablero();
		for(int c=0; c<8; c++)
		{
			for(int f=0; f<8; f++)
			{
				if(matrizTablero[c][f])
				{
					contarReinas++;
					ColocarFicha cf = new ColocarFicha(c, f, tableroTemp);
					if(cf.esValida(tableroTemp))
					{
						tableroTemp = cf.getTableroNuevo();
					}else{
						esMeta = false;
						return esMeta;
					}
				}
			}
		}
		if(contarReinas!=8) esMeta=false;
		
		return esMeta;
		
	}
	
	public boolean[][] cloneMatrizTablero() {
	    if (matrizTablero == null) {
	        return null;
	    }

	    final boolean[][] result = new boolean[matrizTablero.length][];
	    for (int i = 0; i < matrizTablero.length; i++) {
	        result[i] = Arrays.copyOf(matrizTablero[i], matrizTablero[i].length);
	        // For Java versions prior to Java 6 use the next:
	        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
	    }
	    return result;
	}
	
	public boolean mismaMatrizTablero(boolean[][] otroMatrizTablero) {
	    boolean isEqual = true;
	    for (int i = 0; i < matrizTablero.length; i++) 
	    {
	    	for(int j = 0; j< matrizTablero.length; j++)
	    	{
	    		if(matrizTablero[i][j]!=otroMatrizTablero[i][j])
	    		{
	    			isEqual = false;
	    			return false;
	    		}
	    	}
	    }
	    return isEqual;
	}
	    
	
	

}
