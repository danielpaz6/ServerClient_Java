package algorithms;

import java.util.List;

public interface Searcher<T>
{
	// The search method
	public List<State<T>> search(Searchable<T> s);
	
	// Get how many nodes/states were evaluated by the algorithm
	public int getNumberOfStatesEvaluated();
}
