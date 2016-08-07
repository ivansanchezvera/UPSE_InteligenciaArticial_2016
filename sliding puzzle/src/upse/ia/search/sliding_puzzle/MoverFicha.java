package upse.ia.search.sliding_puzzle;

import upse.ia.search.uninformed.Accion;

public class MoverFicha implements Accion {
	public int filaInicial = -1;
	public int filaFinal = -1;
	public int columnaInicial = -1;
	public int columnaFinal = -1;
	private PuzzleState ps;
	
	public MoverFicha(int fi, int ff, int ci, int cf, PuzzleState ps) {
		filaInicial = fi;
		filaFinal = ff;
		columnaInicial = ci;
		columnaFinal = cf;
		this.ps = ps;
	}
	
	@Override
	public boolean esValida(){
		if(filaInicial>ps.getTamanoTablero()-1 || filaInicial<0
				|| filaFinal>ps.getTamanoTablero()-1 || filaFinal<0 ||
				columnaInicial>ps.getTamanoTablero()-1 || columnaInicial<0
				|| columnaFinal>ps.getTamanoTablero()-1 || columnaFinal<0)
		{
			return false;
		}
		
		if(ps.getPuzzleBoard()[filaFinal][columnaFinal]==0)
		{
			if(filaInicial == filaFinal || columnaInicial == columnaFinal)
			{
				return true;
			}
		}
		return false;
	}

	public int getFilaInicial() {
		return filaInicial;
	}

	public int getFilaFinal() {
		return filaFinal;
	}

	public int getColumnaInicial() {
		return columnaInicial;
	}

	public int getColumnaFinal() {
		return columnaFinal;
	}
}
