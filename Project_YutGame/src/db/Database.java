package db;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Database {
	
	DefaultListModel<String> gamelistdata = new DefaultListModel<String>();
	
//	Game List를 보여주기 위한 함수
	public DefaultListModel gameShow() {
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
//			user_game 테이블에 있는 모든 값을 선택
			ResultSet srs = stmt.executeQuery("select * from user_game");
			
			while (srs.next()) {
//				GameStatus가 false인 값만 gamelistdata에 입력
				if (!srs.getBoolean("GameStatus")) {
					gamelistdata.addElement(srs.getInt("RoomNum")
							+ "\t|\t" + srs.getString("HostId")
							+ "\t|\t" + srs.getInt("Totalpop")
							+ "\t|\t" + srs.getBoolean("GameStatus"));
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
		
		return gamelistdata;
		
	}

//	Create Room 버튼 클릭 시 작동
	public void createGame (String userId, String order) {
		Connection conn;
		Statement stmt = null;
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
//			RoomNum의 마지막 값을 알려주는 쿼리문
			ResultSet srs = stmt.executeQuery("select last_value(RoomNum) over() as rn_last from game");
			srs.next();
			
//			RoomNum 마지막 값 + 1
			Integer rn_num = srs.getInt("rn_last") + 1;
			String rn = String.valueOf(rn_num);
			
			if (order == "create") {
//				game table에 새로운 data 입력
				PreparedStatement pstmt = conn.prepareStatement("insert into game (RoomNum, HostPlayer) values (?, ?)");
				pstmt.setString(1, rn);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
//				user_game table에 새로운 data 입력
				pstmt = conn.prepareStatement("insert into user_game (RoomNum, HostId, Totalpop, GameStatus) values (?, ?, 1, 0)");
				pstmt.setString(1, rn);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
			}else if (order == "delete") {
				
			}
			
//			gamelistdata 최신화
			gameShow();
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
		}
	}

}
