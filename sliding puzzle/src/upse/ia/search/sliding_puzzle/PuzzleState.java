package upse.ia.search.sliding_puzzle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import upse.ia.search.uninformed.*;

public class PuzzleState implements Estado, Comparator<PuzzleState> {

	private int[][] puzzleBoard;
	private int tamanoTablero = 0;
	private int numeroLimite = 0;
	private double fn = Double.POSITIVE_INFINITY;
	private double gn = 0;
	private double hn = Double.POSITIVE_INFINITY;
	private int blankTileColumn;
	private int blankTileRow;
	private Timestamp time;
	
	public PuzzleState(int tamanoTablero)
	{
		this.tamanoTablero = tamanoTablero;
		this.numeroLimite = (tamanoTablero*tamanoTablero)-1;
		puzzleBoard = new int[tamanoTablero][tamanoTablero];
		generarEstadoAlRandom();
		stampTimeNow();		
	}

	private void stampTimeNow() {
		Date date= new java.util.Date();
		time = new Timestamp(date.getTime());
	}
	
	public PuzzleState(int tamanoTablero, ArrayList<Integer> fichas) throws Exception
	{
		this.tamanoTablero = tamanoTablero;
		this.numeroLimite = (tamanoTablero*tamanoTablero)-1;
		puzzleBoard = new int[tamanoTablero][tamanoTablero];
		
		if(fichas.size()==numeroLimite+1)
		{
			llenarTablero(fichas);
		}else{
			throw new Exception("Numero Incorrecto de fichas para este rompecabezas");
		}
		stampTimeNow();	
	}
	
	public PuzzleState(PuzzleState ps) {
		this.tamanoTablero = ps.getTamanoTablero();
		this.numeroLimite = ps.getNumeroLimite();
		this.blankTileColumn = ps.getBlankTileColumn();
		this.blankTileRow = ps.getBlankTileRow();
		this.fn = ps.getFn();
		this.gn = ps.getGn();
		this.hn = ps.getHn();
		this.puzzleBoard = new int[tamanoTablero][tamanoTablero];
		for(int i = 0; i<this.tamanoTablero; i++)
		{
			for(int j = 0; j<this.tamanoTablero; j++)
			{
				this.puzzleBoard[i][j] = ps.getPuzzleBoard()[i][j];
			}
		}
		
		stampTimeNow();	
	}
	
	private void llenarTablero(ArrayList<Integer> fichas) {
		// TODO Auto-generated method stub
		int index = 0;
		for(int i=0; i<tamanoTablero; i++)
		{
			for(int j=0; j<tamanoTablero; j++)
			{
				int ficha = fichas.get(index);
				this.puzzleBoard[i][j] = ficha;
				if(ficha == 0)
				{
					this.blankTileRow = i;
					this.blankTileColumn = j;
				}
				index++;
			}
		}
	}

	private void generarEstadoAlRandom() {
		Random r = new Random();
		HashSet<Integer> fichasColocadas = new HashSet<Integer>();
		for(int i=0; i<tamanoTablero; i++)
		{
			for(int j=0; j<tamanoTablero; j++)
			{
				Integer ficha;
				do{
					ficha = r.nextInt(numeroLimite+1);
				}while(fichasColocadas.contains(ficha));
				fichasColocadas.add(ficha);
				if(ficha==0){
					this.blankTileRow = i;
					this.blankTileColumn = j;
				}
				
				this.puzzleBoard[i][j] = ficha;
			}
		}
	}

	@Override
	public Estado generarHijo(Estrategia s, Problema p, Estado padre, Accion a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "PuzzleState [tamanoTablero=" + tamanoTablero
				+", f(n)=" + fn +", g(n)=" + gn + ", h(n)=" + hn
				+ ", numeroLimite=" + numeroLimite + "]" + "puzzleBoard=\n" +  getPrintablePuzzleBoard() + "\n";
	}
	
	public String getPrintablePuzzleBoard()
	{
		String printableBoard = new String();
		for(int i=0; i<tamanoTablero; i++)
		{
			for(int j=0; j<tamanoTablero; j++)
			{
				printableBoard = printableBoard.concat(String.valueOf(this.puzzleBoard[i][j]));
				printableBoard = printableBoard.concat("\t");
			}
			printableBoard = printableBoard.concat("\n");
		}
		return printableBoard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroLimite;
		result = prime * result + Arrays.deepHashCode(puzzleBoard);
		result = prime * result + tamanoTablero;
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
		PuzzleState other = (PuzzleState) obj;
		if (numeroLimite != other.numeroLimite)
			return false;
		if (!Arrays.deepEquals(puzzleBoard, other.puzzleBoard))
			return false;
		if (tamanoTablero != other.tamanoTablero)
			return false;
		return true;
	}

	public int[][] getPuzzleBoard() {
		return puzzleBoard;
	}

	public int getTamanoTablero() {
		return tamanoTablero;
	}

	public int getNumeroLimite() {
		return numeroLimite;
	}
	
	public double calculateFN(PuzzleState estadoMeta) throws Exception
	{
		//Calcular f(n) = g(n) + h(n)
		this.hn = calculateHN(estadoMeta);
		return this.fn = this.gn + this.hn;
		
	}

	private double calculateHN(PuzzleState estadoMeta) throws Exception {
		//Por ahora la heuristica es el numero de piezas mal colocadas
		double tempHN = 0;
		if(this.tamanoTablero != estadoMeta.getTamanoTablero())
		{
			throw new Exception("Imposible calcular para tableros de diversos tamanos");
		}else{
			for(int i = 0; i<this.tamanoTablero; i++)
			{
				for(int j = 0; j<this.tamanoTablero; j++)
				{
					if(this.puzzleBoard[i][j]!=estadoMeta.getPuzzleBoard()[i][j])
						tempHN++;
				}
			}
		}
		return tempHN;
	}

	@Override
	public int compare(PuzzleState o1, PuzzleState o2) {
		if(Double.isInfinite(o1.getFn())||Double.isInfinite(o2.getFn())){
			try {
				throw new Exception("Distancia a Estado meta es infinita o no calculada.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(o1.getFn()>o2.getFn())
			{
				return 1;
			}else if(o1.getFn()<o2.getFn()){
				return -1;
			}else{
				if(o1.time.compareTo(o2.time)<0)
				{
					return 1;
				}else{
					return -1;
				}
			}
		}
		return 0;
	}

	public double getGn() {
		return gn;
	}

	public void setGn(double gn) {
		this.gn = gn;
	}

	public double getFn() {
		return fn;
	}

	public double getHn() {
		return hn;
	}

	public int getBlankTileColumn() {
		return blankTileColumn;
	}

	public int getBlankTileRow() {
		return blankTileRow;
	}
	
	public void ejecutarMovimiento(MoverFicha mf) throws Exception{
		if(this.puzzleBoard[mf.getFilaFinal()][mf.getColumnaFinal()]!=0)
		{
			throw new Exception("Movimiento no permitido, solo se puede mover hacia el casillero vacio");
		}else{
			int fichaAMover = this.puzzleBoard[mf.getFilaInicial()][mf.getColumnaInicial()];
			this.puzzleBoard[mf.getFilaFinal()][mf.getColumnaFinal()]=fichaAMover;
			this.puzzleBoard[mf.getFilaInicial()][mf.getColumnaInicial()] = 0;
			this.blankTileColumn = mf.getColumnaInicial();
			this.blankTileRow = mf.getFilaInicial();
			this.gn++;
		}
	}
	

}
