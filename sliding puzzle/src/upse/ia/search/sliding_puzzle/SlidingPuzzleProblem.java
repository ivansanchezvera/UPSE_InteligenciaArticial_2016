package upse.ia.search.sliding_puzzle;

import java.util.ArrayList;

import upse.ia.search.uninformed.Accion;
import upse.ia.search.uninformed.Estado;
import upse.ia.search.uninformed.Estrategia;
import upse.ia.search.uninformed.Problema;

public class SlidingPuzzleProblem extends Problema{

	private Estado estadoMeta = new PuzzleState(3);
	private Estado estadoInicial = new PuzzleState(3);
	
	public SlidingPuzzleProblem(PuzzleState estadoInicial, PuzzleState estadoMeta) {
		this.estadoInicial = estadoInicial;
		this.estadoMeta = estadoMeta;
	}
	
	public SlidingPuzzleProblem(PuzzleState estadoMeta) {
		this.estadoInicial = new PuzzleState(estadoMeta.getTamanoTablero());
		this.estadoMeta = estadoMeta;
	}
	
	@Override
	public boolean PruebaEstadoMeta(Estado e) {
		if(e.equals(estadoMeta)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Estado modeloTransicion(Estado e, Accion a) {
		// TODO Auto-generated method stub
		return null;
	}

	public Estado getEstadoMeta() {
		return estadoMeta;
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public ArrayList<MoverFicha> generarAcciones(PuzzleState estadoActual) {
		ArrayList<MoverFicha> listaAcciones = new ArrayList<MoverFicha>();
		
		MoverFicha mov1 = new MoverFicha(estadoActual.getBlankTileRow()+1, estadoActual.getBlankTileRow(), estadoActual.getBlankTileColumn(), estadoActual.getBlankTileColumn(), estadoActual);
		if(mov1.esValida())	listaAcciones.add(mov1);
		
		MoverFicha mov2 = new MoverFicha(estadoActual.getBlankTileRow()-1, estadoActual.getBlankTileRow(), estadoActual.getBlankTileColumn(), estadoActual.getBlankTileColumn(), estadoActual);
		if(mov2.esValida())	listaAcciones.add(mov2);
		
		MoverFicha mov3 = new MoverFicha(estadoActual.getBlankTileRow(), estadoActual.getBlankTileRow(), estadoActual.getBlankTileColumn()-1, estadoActual.getBlankTileColumn(), estadoActual);
		if(mov3.esValida())	listaAcciones.add(mov3);
		
		MoverFicha mov4 = new MoverFicha(estadoActual.getBlankTileRow(), estadoActual.getBlankTileRow(), estadoActual.getBlankTileColumn()+1, estadoActual.getBlankTileColumn(), estadoActual);
		if(mov4.esValida())	listaAcciones.add(mov4);
		
		return listaAcciones;


		
	}
	
	
	
	
	
}
