package algorithms;

public class Position {
	private int row;
	private int	column;
	
	public Position() {
		row = 0;
		column = 0;
	}
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return row + "," + column;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return equals((Position)obj);
	}
	
	public boolean equals(Position other) {
		return getRow() == other.getRow() && getColumn() == other.getColumn();
	}
	
}
