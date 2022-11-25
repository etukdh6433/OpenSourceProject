package db;

import java.sql.*;
import java.util.*;

public class Database {
	
	Vector<String> gamelistdata = new Vector<String>();
	
	public Vector gameShow() {
		gamelistdata.removeAllElements();
		Connection conn;
		Statement stmt = null;
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			
			stmt = conn.createStatement();
			ResultSet srs = stmt.executeQuery("select * from user_game");
			
			while (srs.next()) {
				gamelistdata.add(srs.getInt("RoomNum")
						+ "\t|\t" + srs.getString("HostId")
						+ "\t|\t" + srs.getInt("Totalpop")
						+ "\t|\t" + srs.getBoolean("GameStatus"));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
		
		return gamelistdata;
		
	}

}
