package ua.nenya.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ua.nenya.dao.ReadTeamGamesDAO;
import ua.nenya.domain.Game;
import ua.nenya.util.ConnectionManager;

public class ReadTeamGamesDAOImpl implements ReadTeamGamesDAO {

	@Override
	public Game readGameById(long id, String tableName) {
		
		ConnectionManager connectionManager = new ConnectionManager();
		Game game = new Game();
		Connection connection;
		try{
			connection = connectionManager.getConnection();
			String selectString = "SELECT home_team, guest_team, home_goals, guest_goals, odds_home_win, odds_draw, odds_guest_win, date, over_time FROM "+tableName+" WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(selectString);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			game.setId(id);
			game.setHomeTeamName(resultSet.getString("home_team"));
			game.setGuestTeamName(resultSet.getString("guest_team"));
			game.setHomeGoals(resultSet.getInt("home_goals"));
			game.setGuestGoals(resultSet.getInt("guest_goals"));
			game.setOddsHomeWin(resultSet.getDouble("odds_home_win"));
			game.setOddsDraw(resultSet.getDouble("odds_draw"));
			game.setOddsGuestWin(resultSet.getDouble("odds_guest_win"));
			game.setOverTime(resultSet.getBoolean("over_time"));
			
			long milis = resultSet.getLong("date");
			Date date = new Date(milis);
			LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
			game.setDate(localDate);
			
			statement.close();
			connection.close();
			connectionManager.closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game;
	}

	@Override
	public List<Game> getAllGamesByTeamName(String team, long id, String tableName) {
		ConnectionManager connectionManager = new ConnectionManager();
		List<Game> games = new ArrayList<>();
		Connection connection;
		try {
			connection = connectionManager.getConnection();
			String selectString = "SELECT * FROM "+tableName+" WHERE (home_team=? OR guest_team=?) AND id<?";
			PreparedStatement statement = connection.prepareStatement(selectString);
			statement.setString(1, team);
			statement.setString(2, team);
			statement.setLong(3, id);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Game game = new Game();
				game.setId(resultSet.getLong("id"));
				game.setId(id);
				game.setHomeTeamName(resultSet.getString("home_team"));
				game.setGuestTeamName(resultSet.getString("guest_team"));
				game.setHomeGoals(resultSet.getInt("home_goals"));
				game.setGuestGoals(resultSet.getInt("guest_goals"));
				game.setOverTime(resultSet.getBoolean("over_time"));
				game.setOddsHomeWin(resultSet.getDouble("odds_home_win"));
				game.setOddsDraw(resultSet.getDouble("odds_draw"));
				game.setOddsGuestWin(resultSet.getDouble("odds_guest_win"));
				//game.setDate(resultSet.getDate("date").toLocalDate());
				games.add(game);
			}
			statement.close();
			connection.close();
			connectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}

}
