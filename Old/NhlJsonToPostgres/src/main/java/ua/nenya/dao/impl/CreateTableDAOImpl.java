package ua.nenya.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.nenya.util.ConnectionManager;

public class CreateTableDAOImpl implements CreateTableDAO {

	@Override
	public void createTable(String tableName) {
		String sqlString = "CREATE TABLE " + tableName
				+ " (id SERIAL PRIMARY KEY, home_team VARCHAR(100), guest_team VARCHAR(100), "
				+ "home_goals INTEGER, guest_goals INTEGER, over_time BOOLEAN," + "odds_home_win REAL, odds_draw REAL,"
				+ " odds_guest_win REAL, date BIGINT);";
		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tableName, null);
			if (!tables.next()) {
				statement.execute(sqlString);
			}
			statement.close();
			connection.close();
			connectionManager.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
