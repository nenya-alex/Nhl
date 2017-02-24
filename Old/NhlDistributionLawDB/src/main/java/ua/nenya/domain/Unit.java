package ua.nenya.domain;

public class Unit {
	
	private int count;
	
	private Cell cell;
	
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Unit [ " + cell.getxMin() + " ; " + cell.getxMax() + " :: " + cell.getyMin() + " ; " + cell.getyMax() + " ] count = " + count;
	}
	
}
