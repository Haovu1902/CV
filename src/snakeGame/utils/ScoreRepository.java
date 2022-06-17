package snakeGame.utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import snakeGame.Score;

public class ScoreRepository
{
	static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/snakedb";
	static final String USER = "root";
	static final String PASS = "";
	
	public static List<Score> readAll() throws SQLException, ClassNotFoundException
	{
		Connection conn = null;
		Statement stmt = null;
		
		List<Score> scores = new ArrayList<Score>();
		try
		{
			// Register JDBC driver
			Class.forName(DRIVER_CLASS);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute a query
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE");
			// Extract data from result set
			while (rs.next())
			{
				int id = rs.getInt("id");
				int score = rs.getInt("score");
				scores.add(new Score(id, score));
			}
			rs.close();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			// Close connection
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return scores;
	}
	
	public static void add(Score score) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			//Register JDBC driver
			Class.forName(DRIVER_CLASS);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute a query
			stmt = conn.createStatement();
			
			stmt.executeUpdate("INSERT INTO SCORE(SCORE) VALUES (" + score.getScore() + ")");
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			// Close connection
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
	
	public static void delete(int scoreID) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			//Register JDBC driver
			Class.forName(DRIVER_CLASS);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute a query
			stmt = conn.createStatement();
			
			stmt.executeUpdate("DELETE FROM SCORE WHERE ID = " + scoreID);
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			// Close connection
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
	
}