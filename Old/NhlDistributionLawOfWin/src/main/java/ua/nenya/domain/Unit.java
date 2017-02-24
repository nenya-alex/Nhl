package ua.nenya.domain;

import java.util.ArrayList;
import java.util.List;

public class Unit {
	
	private List<Integer> counts;
	
	private int count;
	
	private double average;
	
	private Cell cell;
	
	public Unit() {
		this.counts = new ArrayList<>();
	}
	
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}	
	public List<Integer> getCounts() {
		return counts;
	}
	public void setCounts(List<Integer> counts) {
		this.counts = counts;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Unit [ " + cell.getxMin() + " ; " + cell.getxMax() + " :: " + cell.getyMin() + " ; " + cell.getyMax() 
		+ " ] average = " + average+", counts = "+counts+" count = "+count;
	}
	
}
