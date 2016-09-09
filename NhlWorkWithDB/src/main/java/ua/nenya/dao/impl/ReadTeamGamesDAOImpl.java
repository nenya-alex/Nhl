package ua.nenya.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ua.nenya.dao.ReadTeamGamesDAO;
import ua.nenya.domain.Game;
import ua.nenya.util.ConnectionManager;

public class ReadTeamGamesDAOImpl implements ReadTeamGamesDAO {

	@Override
	public List<Game> readTeam(String teamName, LocalDate date) {
		ConnectionManager connectionManager = new ConnectionManager();
		List<Game> games = new ArrayList<>();
		Connection connection;
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("TODO");
			//TODO
			statement.close();
			connection.close();
			connectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}

}