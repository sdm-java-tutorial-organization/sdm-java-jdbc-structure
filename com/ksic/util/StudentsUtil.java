package com.ksic.util;

//상수 정의를 위한 클래
public class StudentsUtil {
	
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//맨뒤에 ORA92는 오라클 DB의 SID
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:ORA92";
	//id와 pw
	public static final String USER = "scott";
	public static final String PASS = "tiger";
	
	public static final String GETALLSTUDENTS = "select * from hkstudents";
	public static final String GETSTUDENT = "select * from hkstudents where id=?";
	public static final String HASSTUDENT = "select id from hkstudents where id=?";
	
	public static final String INSERTSTUDENT = "insert into hkstudents values(?, ?, ?)";
	public static final String UPDATESTUDENT = "update hkstudents set name=?, addr=?"
		+ " where id=?";
	
	public static final String DELETESTUDENT = "delete from hkstudents where id=?";
	
	public static final String LIKEBYNAME = "select * from hkstudents where name like ?";
}
