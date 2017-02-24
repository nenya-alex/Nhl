package ua.nenya.main;

import java.util.List;

import ua.nenya.calculator.Calculator;
import ua.nenya.dao.ReadTeamGamesDAO;
import ua.nenya.dao.impl.ReadTeamGamesDAOImpl;
import ua.nenya.domain.Cell;
import ua.nenya.domain.Dot;
import ua.nenya.domain.Game;
import ua.nenya.domain.Team;
import ua.nenya.domain.Unit;

public class Runner {
	private ReadTeamGamesDAO readGame = new ReadTeamGamesDAOImpl();
	private Calculator calculator = new Calculator();

	public List<Unit> runApp(float beginInterval, float endInterval, int numberIntervals, String tableName) {

		List<Unit> units = calculator.getUnits(beginInterval, endInterval, numberIntervals);

		for (int id = 231; id < 1231; id++) {

			System.out.println("Game id: "+id);
			
			Game game = readGame.readGameById(id, tableName);

			if (!game.isOverTime()) {

				String winnerTeamName = calculator.getWinnerTeamName(game);

				String homeTeamName = game.getHomeTeamName();
				List<Game> homeTeamGames = readGame.getAllGamesByTeamName(homeTeamName, id, tableName);

				String guestTeamName = game.getGuestTeamName();
				List<Game> guestTeamGames = readGame.getAllGamesByTeamName(guestTeamName, id, tableName);

				Team homeTeam = calculator.calculateTeamStat(homeTeamGames, homeTeamName);

				Team guestTeam = calculator.calculateTeamStat(guestTeamGames, guestTeamName);

				fillUnitCellsByCounts(units, winnerTeamName, homeTeam, guestTeam);
			}
		}
		return units;
	}

	private void fillUnitCellsByCounts(List<Unit> units, String winnerTeamName, Team homeTeam, Team guestTeam) {

		if (homeTeam.equals(winnerTeamName)) {

			Dot dot = new Dot();
			dot.setX(homeTeam.getPointPstg());
			dot.setY(guestTeam.getPointPstg());

			for (Unit unit : units) {
				if (isDotInUnitCell(dot, unit)) {
					int count = unit.getCount();
					count++;
					unit.setCount(count);
					break;
				}
			}
		} else {
			
			Dot dot = new Dot();
			dot.setX(guestTeam.getPointPstg());
			dot.setY(homeTeam.getPointPstg());
			
			for (Unit unit : units) {
				if (isDotInUnitCell(dot, unit)) {
					int count = unit.getCount();
					count++;
					unit.setCount(count);
					break;
				}
			}
		}

	}

	private boolean isDotInUnitCell(Dot dot, Unit unit) {
		Cell cell = unit.getCell();
		float x = dot.getX();
		float y = dot.getY();
		return cell.getxMin() < x && x <= cell.getxMax() && cell.getyMin() < y && y <= cell.getyMax();
	}

}
