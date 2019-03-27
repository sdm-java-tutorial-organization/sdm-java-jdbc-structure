package com.ksic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ksic.dto.StudentDto;
import com.ksic.exc.IdDuplicatedException;
import com.ksic.exc.IdNotFoundException;
import com.ksic.util.StudentsUtil;

public class StudentDao extends AbstractStudent {

	public StudentDao() { 
		super(); //객체가 생성될 때 부모를 불러와 드라이버를 로딩하도록 한다.
	} //Constructor
	
	public Vector getAllStudent() throws SQLException {
		Vector v = new Vector(2,2); //받아온 데이터를 저장할 벡터
		
		Connection conn = null; //연결부
		PreparedStatement psmt = null; //쿼리부, ?에 값을 주기 위해 PrepareStatement사용
		ResultSet rs = null; //리시버
		
		try {
			// 2/6
			conn = this.getConnection(); //이 클래스에 getConnection이 없으므로 부모에서 호출 
			System.out.println("2/6 성공 -- getAllStudent");
			
			// 3/6
			psmt = conn.prepareStatement(StudentsUtil.GETALLSTUDENTS);
			System.out.println("3/6 성공 -- getAllStudent");
			
			// 4/6
			rs = psmt.executeQuery(); //쿼리실행해서 rs에 값을 받아온다. 
			System.out.println("4/6 성공 -- getAllStudent");
			
			//5/6 
			while(rs.next()) { //받아온 값에서 다음값이 없을때까지 실행
				int i=1; //컬럼을 넘기기 위한 변수
				String id = rs.getString(i++); //rs의 값을 받아 저장
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				v.add(new StudentDto(id, name, addr)); //저장한 값을 벡터에 저장한다.
			}
			System.out.println("5/6 성공 -- getAllStudent");
			
		} catch (SQLException e) {	
			System.out.println("실패 -- getAllStudent" + e);
			//throw new SQLException("getAllStudent 실패 " + e);
		} finally { //close가 반드시 실행되도록
			this.close(conn, psmt, rs); //부모의 close호출
		}
		return v;
	} //getAllStudent
	
	public Vector getAllStudentByName(String names) throws SQLException {
		Vector v = new Vector(2,2);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection(); // 2/6
			System.out.println("2/6 성공 -- getAllStudentByName");
			
			psmt = conn.prepareStatement(StudentsUtil.LIKEBYNAME); // 3/6
			psmt.setString(1,"%" + names + "%"); //첫번째 ?에 두번째 아규먼트로 대체한다.
			System.out.println("3/6 성공 -- getAllStudentByName");
			
			rs = psmt.executeQuery(); // 4/6
			System.out.println("4/6 성공 -- getAllStudentByName");
			
			while(rs.next()) { // 5/6
				int i=1;
				String id = rs.getString(i++);
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				v.add(new StudentDto(id, name, addr));
			}
			System.out.println("5/6 성공 -- getAllStudentByName");
			
		} catch (SQLException e) {	
			System.out.println("실패 -- getAllStudentByName" + e);
			throw new SQLException("getAllStudentByName 실패 " + e);
		} finally {
			this.close(conn, psmt, rs);
		}
		return v;
	} //getAllStudentByName
	
	public StudentDto getStudent(String ids) throws SQLException {//1로우이므로 DTO로 반환
		StudentDto dto = new StudentDto();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection(); // 2/6
			System.out.println("2/6 성공 -- getStudent");
			
			psmt = conn.prepareStatement(StudentsUtil.GETSTUDENT); // 3/6
			psmt.setString(1, ids.trim());
			System.out.println("3/6 성공 -- getStudent");
			
			rs = psmt.executeQuery(); // 4/6
			System.out.println("4/6 성공 -- getStudent");
			
			while(rs.next()) { // 5/6
				int i=1;
				String id = rs.getString(i++);
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				dto.setId(id.trim()); //dto에 저장해서 통째 반납한다.
				dto.setName(name.trim());
				dto.setAddr(addr.trim());
			}
			System.out.println("5/6 성공 -- getStudent");
			
		} catch (SQLException e) {	
			System.out.println("실패 -- getStudent" + e);
			throw new SQLException("getStudent 실패 " + e);
		} finally {
			this.close(conn, psmt, rs);
		}
		return dto;
	} //getStudent
	
	//insert, delete, update에서 현재 있는 id인지를 확인하기 위한 메서드
	public boolean hasId(String id) { 
		boolean hasl = false; //리턴값을 주기위한 변수
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();// 2/6
			
			psmt = conn.prepareStatement(StudentsUtil.HASSTUDENT); //3/6
			psmt.setString(1, id.trim() );
			
			rs = psmt.executeQuery(); // 4/6
			
			// 5/6
			if(rs.next()) {
				hasl = true;
			}
			
		} catch (SQLException e) {
			System.out.println("hasId 실패");
		} finally {
			close(conn, psmt, rs);
		}
		return hasl;
	}
	
	public boolean insertStudent(String id, String name, String addr) throws IdDuplicatedException, SQLException {
		
		if(this.hasId(id.trim())) { 
			throw new IdDuplicatedException(id + "가 이미 존재합니다."); //사용자 정의 익셉션
		}
		
		boolean ins = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0; //쿼리실행이 잘 되었나를 확인하기 위한 변수
		
		try {
			conn = this.getConnection();// 2/6
			
			psmt = conn.prepareStatement(StudentsUtil.INSERTSTUDENT); // 3/6
			psmt.setString(1, id.trim());
			psmt.setString(2, name.trim());
			psmt.setString(3, addr.trim());
			
			count = psmt.executeUpdate();
			if (count>0) { //쿼리가 잘 실행되었으면
				ins = true; //반환값을 true로...
			}
			
		} catch (SQLException e) {
			System.out.println("실패했습니다. -- insertStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}
	
	public boolean updateStudent(String id, String name, String addr) throws IdNotFoundException, SQLException {
		
		if(!this.hasId(id.trim())) {
			throw new IdNotFoundException(id + "가 없습니다.");
		}
		
		boolean ins = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = this.getConnection();// 2/6
			
			psmt = conn.prepareStatement(StudentsUtil.UPDATESTUDENT); // 3/6
			psmt.setString(1, name.trim());
			psmt.setString(2, addr.trim());
			psmt.setString(3, id.trim());
			
			count = psmt.executeUpdate();
			if (count>0) {
				ins = true;
			}
			
		} catch (SQLException e) {
			System.out.println("실패했습니다. -- updateStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}
	
	public boolean deleteStudent(String id) throws IdNotFoundException, SQLException {
		
		if(!this.hasId(id.trim())) {
			throw new IdNotFoundException(id + "가 없습니다.");
		}
		
		boolean ins = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = this.getConnection();// 2/6
			
			psmt = conn.prepareStatement(StudentsUtil.DELETESTUDENT); // 3/6
			psmt.setString(1, id.trim());
			
			count = psmt.executeUpdate();
			if (count>0) {
				ins = true;
			}
			
		} catch (SQLException e) {
			System.out.println("실패했습니다. -- deleteStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}

}
