package ua.nenya.calculator;

import java.util.ArrayList;
import java.util.List;

import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;

public class Calculator {

	public void calculateNewOdds(Game game, List<Dot> dots) {
		int homePoints = game.getHomeTeam().getPoints();
		int guestPoints = game.getGuestTeam().getPoints();

		List<Double> newOddsOfWin = new ArrayList<>();
		List<Double> newOddsOfDraw = new ArrayList<>();
		List<Double> newOddsOfLose = new ArrayList<>();

		for (Dot dot : dots) {
			if (homePoints == dot.getX() && guestPoints == dot.getY()) {
				int allDrawsInDot = dot.getDrawLoseET() + dot.getDrawWinET() + dot.getDrawLosePen()
						+ dot.getDrawWinPen();
				int allGamesInDot = dot.getWins() + allDrawsInDot + dot.getLost();

				newOddsOfWin.add((double) allGamesInDot / (double) dot.getWins());
				newOddsOfDraw.add((double) allGamesInDot / (double) allDrawsInDot);
				newOddsOfLose.add((double) allGamesInDot / (double) dot.getLost());
				game.setNewOddsOfWin(newOddsOfWin);
				game.setNewOddsOfDraw(newOddsOfDraw);
				game.setNewOddsOfLose(newOddsOfLose);
				break;
			}
		}
	}

}
