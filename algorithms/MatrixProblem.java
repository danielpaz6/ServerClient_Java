package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MatrixProblem implements Searchable<Position> {

	int[][] costs;
	int width; // Width of the matrix ( from 1 to n )
	int height; // Height of the matrix ( from 1 to n )
	
	State<Position> start;
	HashSet<State<Position>> goals;
	
	public MatrixProblem() {
		costs = null;
		width = 0;
		height = 0;
		start = null;
		goals = null;
	}
	
	public int[][] getCosts() {
		return costs;
	}
	
	public void setCosts(int[][] costs) {
		this.costs = costs;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public State<Position> getStart() {
		return start;
	}

	public void setStart(State<Position> start) {
		this.start = start;
	}

	public HashSet<State<Position>> getGoals() {
		return goals;
	}

	public void setGoals(HashSet<State<Position>> goals) {
		this.goals = goals;
	}



	public MatrixProblem(int[][] costs, int width, int height, Position start, List<Position> goals) {
		this.costs = costs;
		this.width = width;
		this.height = height;
		this.start = new State(start, costs[start.getRow()][start.getColumn()]);
		
		this.goals = new HashSet<>();
		for (Position position : goals)
			this.goals.add(new State(position, costs[position.getRow()][position.getColumn()]));
	}

	@Override
	public State<Position> getInitialState() {
		return start;
	}

	@Override
	public boolean isGoal(State<Position> s) {
		return goals.contains(s); // Giving answer in O(1)
	}

	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		Position StatePosition = s.getState();
		ArrayList<State<Position>> neighbors = new ArrayList<>();
		
		if(StatePosition.getColumn() != 0)
		{
			neighbors.add(
					new State(
							new Position(StatePosition.getRow(), StatePosition.getColumn() - 1),
							costs[StatePosition.getRow()][StatePosition.getColumn() - 1]));
		}
		
		if(StatePosition.getRow() != 0)
		{
			neighbors.add(
					new State(
							new Position(StatePosition.getRow() - 1, StatePosition.getColumn()),
							costs[StatePosition.getRow() - 1][StatePosition.getColumn()]));
		}
		
		if(StatePosition.getColumn() != width - 1)
		{
			neighbors.add(
					new State(
							new Position(StatePosition.getRow(), StatePosition.getColumn() + 1),
							costs[StatePosition.getRow()][StatePosition.getColumn() + 1]));
		}
		
		if(StatePosition.getRow() != height - 1)
		{
			neighbors.add(
					new State(
							new Position(StatePosition.getRow() + 1, StatePosition.getColumn()),
							costs[StatePosition.getRow() + 1][StatePosition.getColumn()]));
		}
		
		return neighbors;
	}
	
	public String[] getDirection(List<State<Position>> pathList)
	{
		LinkedList<String> paths = new LinkedList<>();
		
		for (State<Position> p : pathList)
		{
			if(p.getCameFrom() == null)
				break;
			
			if(p.getState().getColumn() > p.getCameFrom().getState().getColumn())
				paths.add("Right");
			else if(p.getState().getColumn() < p.getCameFrom().getState().getColumn())
				paths.add("Left");
			else if(p.getState().getRow() > p.getCameFrom().getState().getRow())
				paths.add("Down");
			else if(p.getState().getRow() < p.getCameFrom().getState().getRow())
				paths.add("Up");
		}
		
		Collections.reverse(paths);
		return (String[]) paths.toArray(new String[0]);
	}
}
