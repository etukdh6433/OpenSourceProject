package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.*;

public class Database {
	DefaultListModel<String> userlistdata = new DefaultListModel<String>();
	
//	User List를 보여주기 위한 함수
	public DefaultListModel loginUserShow() {
		userlistdata.removeAllElements();
		Connection conn;
		Statement stmt = null;
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			
//			user 테이블에 있는 모든 값을 선택
			ResultSet srs = stmt.executeQuery("select * from user");
			while (srs.next()) {
				if (srs.getBoolean("LoginStatus")) {
					userlistdata.addElement(srs.getString("Id"));
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
		}
		
		return userlistdata;
	}

//	user_game, game table data 생성 및 삭제
	public void gameOrder (String userId, String order) {
		Connection conn;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		String rn_c;
		String rn_d;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			
//			RoomNum의 마지막 값을 알려주는 쿼리문
			ResultSet srs = stmt.executeQuery("select last_value(RoomNum) over() as rn_last from game");
			srs.next();
			
//			RoomNum 마지막 값 + 1
			Integer rn_num = srs.getInt("rn_last");
			System.out.println(rn_num);
			
			rn_c = String.valueOf(rn_num + 1);
			rn_d = String.valueOf(rn_num);
			
//			data 생성
			if (order == "create") {
//				game table에 새로운 data 입력
				pstmt = conn.prepareStatement("insert into game (RoomNum, HostPlayer) values (?, ?)");
				pstmt.setString(1, rn_c);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
//				user_game table에 새로운 data 입력
				pstmt = conn.prepareStatement("insert into user_game (RoomNum, HostId, Totalpop, GameStatus) values (?, ?, 1, 0)");
				pstmt.setString(1, rn_c);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
			}
//			data 삭제
			else if (order == "delete") {
//				user_game table에 새로운 data 입력
				pstmt = conn.prepareStatement("delete from user_game where RoomNum = (?)");
				pstmt.setString(1, rn_d);
				pstmt.executeUpdate();
//				game table에 새로운 data 입력
				pstmt = conn.prepareStatement("delete from game where RoomNum = (?)");
				pstmt.setString(1, rn_d);
				pstmt.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
		}
	}
	
//	chat data 저장
	public void chatting(String userId, String text) {
		Connection conn;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		LocalDateTime now = LocalDateTime.now();
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			
//			user_game table에 새로운 data 입력
			pstmt = conn.prepareStatement("insert into chat (Id, CreateDate, Text) values (?, ?, ?)");
			pstmt.setString(1, userId);
			pstmt.setString(2, now.toString());
			pstmt.setString(3, text);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver load error");
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
		}
	}

	public void createUser(String _i, String _p) {
		Connection conn;
		Statement stmt = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String pw = "2017018023";
			
	
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pw);
				stmt = conn.createStatement();
				System.out.println("MySQL 서버 연동 성공");
			} catch(Exception e) {
				System.out.println("MySQL 서버 연동 실패 > " + e.toString());
				}
	}


	static public boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		Connection conn;
		Statement stmt = null;
		Statement stmt2 = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String password = "2017018023";
			

				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
				stmt = conn.createStatement();
				stmt2 = conn.createStatement();
				
				System.out.println("MySQL 서버 연동 성공");
			} catch(Exception e) {
				System.out.println("MySQL 서버 연동 실패 > " + e.toString());
				}
		
		
		try {
			String checkingStr = "SELECT password FROM user WHERE Id='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);
			
			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("password"))) {
					String status = "UPDATE user SET LoginStatus='1' WHERE Id= '" + id +"'";
					
					flag = true;
					System.out.println("로그인 성공");
					stmt2.executeUpdate(status);
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	
	static public boolean joinCheck(String _i, String _p) {
		boolean flag = false;
		String id = _i;
		String pw = _p;
		Connection conn;
		Statement stmt = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String password = "2017018023";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
				
			System.out.println("MySQL 서버 연동 성공");
			} catch(Exception e) {
				System.out.println("MySQL 서버 연동 실패 > " + e.toString());
				}
			
		try {
			String insertStr = "INSERT INTO user (Id, Password, LoginStatus) VALUES('" + id + "', '" + pw + "', '" + 0 + "')";
			stmt.executeUpdate(insertStr);
				
			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
		}
			
		return flag;
		}
	
	static public boolean logoutcheck (String _i) {
		boolean flag = false;
		String id = _i;
		Connection conn;
		Statement stmt = null;
		Statement stmt2 = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Opensource";
			String user = "root";
			String password = "2017018023";

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			
			System.out.println("MySQL 서버 연동 성공");
			
			} catch(Exception e) {
				System.out.println("MySQL 서버 연동 실패 > " + e.toString());
			}
		
		try {
			String checkingStatus = "SELECT LoginStatus FROM user WHERE Id='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStatus);
			
			int count = 0;
			while(result.next()) {
				if(result.getBoolean("LoginStatus")) {
					String status = "UPDATE user SET LoginStatus='0' WHERE Id= '" + id +"'";
					
					flag = true;
					System.out.println("Success");
					stmt2.executeUpdate(status);
				}
				
				else {
					flag = false;
					System.out.println("Fail");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("Fail > " + e.toString());
		}
		
		return flag;
	}
}

