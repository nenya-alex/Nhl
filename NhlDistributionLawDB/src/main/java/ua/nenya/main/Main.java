package ua.nenya.main;

import java.util.List;

import ua.nenya.domain.Unit;

public class Main {
	private final static String TABLE_NAME = "nhl2015";
	private final static float BEGIN_INTERVAL = 0;
	private final static float END_INTERVAL = 1;
	private final static int NUMBER_INTERVALS = 10;

	public static void main(String[] args) {
		System.out.println("START");

		Runner runner = new Runner();

		List<Unit> units = runner.runApp(BEGIN_INTERVAL, END_INTERVAL, NUMBER_INTERVALS, TABLE_NAME);

		for (Unit unit : units) {
			System.out.println(unit);
		}

		System.out.println("DONE");
	}

}
