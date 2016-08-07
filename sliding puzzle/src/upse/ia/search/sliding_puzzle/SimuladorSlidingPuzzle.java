package upse.ia.search.sliding_puzzle;

import java.util.ArrayList;

public class SimuladorSlidingPuzzle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PuzzleState ps = new PuzzleState(3);
		ArrayList<Integer> fichasMeta = new ArrayList<Integer>();
		fichasMeta.add(0);
		fichasMeta.add(1);
		fichasMeta.add(2);
		fichasMeta.add(3);
		fichasMeta.add(4);
		fichasMeta.add(5);
		fichasMeta.add(6);
		fichasMeta.add(7);
		fichasMeta.add(8);
		PuzzleState psMeta;
		
		ArrayList<Integer> fichasInicio = new ArrayList<Integer>();
		fichasInicio.add(7);
		fichasInicio.add(2);
		fichasInicio.add(4);
		fichasInicio.add(5);
		fichasInicio.add(0);
		fichasInicio.add(6);
		fichasInicio.add(8);
		fichasInicio.add(3);
		fichasInicio.add(1);
		PuzzleState psInicio;
		
		try {
			psMeta = new PuzzleState(3, fichasMeta);
			System.out.println(psMeta.toString());
			psInicio = new PuzzleState(3, fichasInicio);
			SlidingPuzzleProblem spp = new SlidingPuzzleProblem(psInicio, psMeta);
			SolucionadorSlidingPuzzle.aStar(spp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
