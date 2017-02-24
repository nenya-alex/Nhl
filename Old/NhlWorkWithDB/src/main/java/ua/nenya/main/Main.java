package ua.nenya.main;

public class Main {
	private final static String TABLE_NAME = "nhl2015";
	private final static long GAME_ID = 1230L;
		
	public static void main(String[] args) {
		System.out.println("START");
		
		Runner runner = new Runner();
		for (int i = 900; i < 1000; i++) {
			runner.runApp(i, TABLE_NAME);
		}
		
		System.out.println("DONE");
	}
}
