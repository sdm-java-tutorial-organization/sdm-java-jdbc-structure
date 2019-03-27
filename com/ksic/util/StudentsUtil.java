package com.ksic.util;

//��� ���Ǹ� ���� Ŭ��
public class StudentsUtil {
	
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//�ǵڿ� ORA92�� ����Ŭ DB�� SID
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:ORA92";
	//id�� pw
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
