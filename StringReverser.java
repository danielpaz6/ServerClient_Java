package server_side;

public class StringReverser<Problem, Solution> implements Solver<Problem, Solution> {
	@Override
	public Solution solve(Problem p) {
		StringBuilder newstring = new StringBuilder();
		newstring.append(p);
		newstring.reverse();
		
		return (Solution) newstring.toString();
	}
}