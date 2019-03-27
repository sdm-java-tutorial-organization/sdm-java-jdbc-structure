package com.ksic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ksic.util.StudentsUtil;

//�߻�Ŭ������ ����� ���������ν� �ڽ��� StudentDao�� ��ü�� ������ �� 
//�ڵ����� �� Ŭ������ �ö� �� �ֵ��� �Ѵ�.
//��� �ڵ����� �ҷ��� �� �� �ְ� �ϱ� ���ؼ�.
public abstract class AbstractStudent { 
	
	public AbstractStudent() { //�����ɶ� ����̹� �ε�
		init();
	} //Construct
	
	private void init() { //����̹� �ε� �޼�Ʈ
		try {
			Class.forName(StudentsUtil.DRIVER);
			System.out.println("1/6 ����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 ����̹� �ε� ����");
		}
	} //init
	
	public Connection getConnection() { //DB����
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(StudentsUtil.URL, StudentsUtil.USER, StudentsUtil.PASS);
			System.out.println("2/6 ���� ����");
		} catch (SQLException e) {
			System.out.println("2/6 ���� ����");
		}
		return conn;
	} //getConnection
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		if(rs!=null) { //�޼��忡 ���� ���� ��쵵 �����Ƿ� null�� �ƴҶ��� �ݾ��ش�.
			try {
				rs.close(); //������ �ֱٿ� ���ͺ��� �ݴ´�.
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
				System.out.println("6/6 �ݱ� ����");
			} catch (SQLException e) {
				System.out.println("6/6 �ݱ� ����");
			}
		}
	}
}
