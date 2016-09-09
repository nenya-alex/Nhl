package ua.nenya.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import ua.nenya.domain.Game;
import ua.nenya.util.ConnectionManager;

public class InsertInTableDAOImpl implements InsertInTableDAO {

	@Override
	public void insert(List<Game> games, String tableName) {
		String countSql = "SELECT count(id) FROM "+tableName+";";
		String insertSql = "INSERT INTO "+tableName+" (home_team, guest_team, "
				+ "home_goals, guest_goals, over_time," + "odds_home_win, odds_draw,"
				+ " odds_guest_win, date) VALUES";
		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(countSql);
			resultSet.next();
			long count = resultSet.getLong(1);			
			if(count==0){
				for(int i=games.size()-1; i>=0; i--){
					Game game = games.get(i);
					String homeTeam = game.getHomeTeam();
					String guestTeam = game.getGuestTeam();					
					int homeGoals = game.getHomeGoals();
					int guestGoals = game.getGuestGoals();
					boolean overTime = game.isOverTime();
					double oddsHomeWin = game.getOddsHomeWin();
					double oddsDraw = game.getOddsDraw();
					double oddsGuestWin = game.getOddsGuestWin();
					LocalDate date = game.getDate();
					
					String insert = insertSql+"('"+homeTeam+"', "
							+ "'"+guestTeam+"', "+homeGoals+", "
							+guestGoals+", "+overTime+", "
							+oddsHomeWin+", "+oddsDraw+", "
							+oddsGuestWin+", "+Date.valueOf(date).getTime()+");";
					statement.execute(insert);
				}
				
			}
			
			statement.close();
			connection.close();
			connectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
