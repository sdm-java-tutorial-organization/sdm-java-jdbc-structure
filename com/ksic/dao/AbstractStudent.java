package com.ksic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ksic.util.StudentsUtil;

//추상클래스로 상속을 강제함으로써 자식인 StudentDao의 객체가 생성될 때 
//자동으로 이 클래스도 올라갈 수 있도록 한다.
//계속 자동으로 불러서 쓸 수 있게 하기 위해서.
public abstract class AbstractStudent { 
	
	public AbstractStudent() { //생성될때 드라이버 로딩
		init();
	} //Construct
	
	private void init() { //드라이버 로딩 메서트
		try {
			Class.forName(StudentsUtil.DRIVER);
			System.out.println("1/6 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 드라이버 로딩 실패");
		}
	} //init
	
	public Connection getConnection() { //DB연결
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(StudentsUtil.URL, StudentsUtil.USER, StudentsUtil.PASS);
			System.out.println("2/6 연결 성공");
		} catch (SQLException e) {
			System.out.println("2/6 연결 실패");
		}
		return conn;
	} //getConnection
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		if(rs!=null) { //메서드에 따라 없는 경우도 있으므로 null이 아닐때만 닫아준다.
			try {
				rs.close(); //닫을땐 최근에 연것부터 닫는다.
			} catch (SQLException e) {
			}
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
				System.out.println("6/6 닫기 성공");
			} catch (SQLException e) {
				System.out.println("6/6 닫기 실패");
			}
		}
	}
}
