package algorithms;

import java.util.List;

public interface Searchable<T> {
	public State<T> getInitialState();
	public boolean isGoal(State<T> s);
	List<State<T>> getAllPossibleStates(State<T> s);
}
