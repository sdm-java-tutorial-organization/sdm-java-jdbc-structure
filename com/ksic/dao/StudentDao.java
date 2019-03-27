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
		super(); //��ü�� ������ �� �θ� �ҷ��� ����̹��� �ε��ϵ��� �Ѵ�.
	} //Constructor
	
	public Vector getAllStudent() throws SQLException {
		Vector v = new Vector(2,2); //�޾ƿ� �����͸� ������ ����
		
		Connection conn = null; //�����
		PreparedStatement psmt = null; //������, ?�� ���� �ֱ� ���� PrepareStatement���
		ResultSet rs = null; //���ù�
		
		try {
			// 2/6
			conn = this.getConnection(); //�� Ŭ������ getConnection�� �����Ƿ� �θ𿡼� ȣ�� 
			System.out.println("2/6 ���� -- getAllStudent");
			
			// 3/6
			psmt = conn.prepareStatement(StudentsUtil.GETALLSTUDENTS);
			System.out.println("3/6 ���� -- getAllStudent");
			
			// 4/6
			rs = psmt.executeQuery(); //���������ؼ� rs�� ���� �޾ƿ´�. 
			System.out.println("4/6 ���� -- getAllStudent");
			
			//5/6 
			while(rs.next()) { //�޾ƿ� ������ �������� ���������� ����
				int i=1; //�÷��� �ѱ�� ���� ����
				String id = rs.getString(i++); //rs�� ���� �޾� ����
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				v.add(new StudentDto(id, name, addr)); //������ ���� ���Ϳ� �����Ѵ�.
			}
			System.out.println("5/6 ���� -- getAllStudent");
			
		} catch (SQLException e) {	
			System.out.println("���� -- getAllStudent" + e);
			//throw new SQLException("getAllStudent ���� " + e);
		} finally { //close�� �ݵ�� ����ǵ���
			this.close(conn, psmt, rs); //�θ��� closeȣ��
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
			System.out.println("2/6 ���� -- getAllStudentByName");
			
			psmt = conn.prepareStatement(StudentsUtil.LIKEBYNAME); // 3/6
			psmt.setString(1,"%" + names + "%"); //ù��° ?�� �ι�° �ƱԸ�Ʈ�� ��ü�Ѵ�.
			System.out.println("3/6 ���� -- getAllStudentByName");
			
			rs = psmt.executeQuery(); // 4/6
			System.out.println("4/6 ���� -- getAllStudentByName");
			
			while(rs.next()) { // 5/6
				int i=1;
				String id = rs.getString(i++);
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				v.add(new StudentDto(id, name, addr));
			}
			System.out.println("5/6 ���� -- getAllStudentByName");
			
		} catch (SQLException e) {	
			System.out.println("���� -- getAllStudentByName" + e);
			throw new SQLException("getAllStudentByName ���� " + e);
		} finally {
			this.close(conn, psmt, rs);
		}
		return v;
	} //getAllStudentByName
	
	public StudentDto getStudent(String ids) throws SQLException {//1�ο��̹Ƿ� DTO�� ��ȯ
		StudentDto dto = new StudentDto();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection(); // 2/6
			System.out.println("2/6 ���� -- getStudent");
			
			psmt = conn.prepareStatement(StudentsUtil.GETSTUDENT); // 3/6
			psmt.setString(1, ids.trim());
			System.out.println("3/6 ���� -- getStudent");
			
			rs = psmt.executeQuery(); // 4/6
			System.out.println("4/6 ���� -- getStudent");
			
			while(rs.next()) { // 5/6
				int i=1;
				String id = rs.getString(i++);
				String name = rs.getString(i++);
				String addr = rs.getString(i++);
				dto.setId(id.trim()); //dto�� �����ؼ� ��° �ݳ��Ѵ�.
				dto.setName(name.trim());
				dto.setAddr(addr.trim());
			}
			System.out.println("5/6 ���� -- getStudent");
			
		} catch (SQLException e) {	
			System.out.println("���� -- getStudent" + e);
			throw new SQLException("getStudent ���� " + e);
		} finally {
			this.close(conn, psmt, rs);
		}
		return dto;
	} //getStudent
	
	//insert, delete, update���� ���� �ִ� id������ Ȯ���ϱ� ���� �޼���
	public boolean hasId(String id) { 
		boolean hasl = false; //���ϰ��� �ֱ����� ����
		
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
			System.out.println("hasId ����");
		} finally {
			close(conn, psmt, rs);
		}
		return hasl;
	}
	
	public boolean insertStudent(String id, String name, String addr) throws IdDuplicatedException, SQLException {
		
		if(this.hasId(id.trim())) { 
			throw new IdDuplicatedException(id + "�� �̹� �����մϴ�."); //����� ���� �ͼ���
		}
		
		boolean ins = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0; //���������� �� �Ǿ����� Ȯ���ϱ� ���� ����
		
		try {
			conn = this.getConnection();// 2/6
			
			psmt = conn.prepareStatement(StudentsUtil.INSERTSTUDENT); // 3/6
			psmt.setString(1, id.trim());
			psmt.setString(2, name.trim());
			psmt.setString(3, addr.trim());
			
			count = psmt.executeUpdate();
			if (count>0) { //������ �� ����Ǿ�����
				ins = true; //��ȯ���� true��...
			}
			
		} catch (SQLException e) {
			System.out.println("�����߽��ϴ�. -- insertStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}
	
	public boolean updateStudent(String id, String name, String addr) throws IdNotFoundException, SQLException {
		
		if(!this.hasId(id.trim())) {
			throw new IdNotFoundException(id + "�� �����ϴ�.");
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
			System.out.println("�����߽��ϴ�. -- updateStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}
	
	public boolean deleteStudent(String id) throws IdNotFoundException, SQLException {
		
		if(!this.hasId(id.trim())) {
			throw new IdNotFoundException(id + "�� �����ϴ�.");
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
			System.out.println("�����߽��ϴ�. -- deleteStudent" + e);
			throw new SQLException();
		} finally {
			close(conn, psmt, null);
		}
		
		return ins;
	}

}
