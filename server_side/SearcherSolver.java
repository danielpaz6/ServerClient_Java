package server_side;

import algorithms.MatrixProblem;
import algorithms.Searchable;
import algorithms.Searcher;

public class SearcherSolver<Problem, Solution, StateType> implements Solver<Problem, Solution> {
	private Searcher<StateType> s;
	
	public SearcherSolver(Searcher<StateType> s) {
		this.s = s;
	}
	
	@Override
	public Solution solve(Problem p) {
		Searchable<StateType> matrixSolver = (Searchable) p;
		return (Solution) s.search((Searchable<StateType>) p);
	}

}
