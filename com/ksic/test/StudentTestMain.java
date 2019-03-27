package com.ksic.test;

import com.ksic.dao.StudentDao;
import com.ksic.util.ShowStudent;

public class StudentTestMain {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
		try {
			//dao.insertStudent("s004", "송은희", "경기도 안산시"); //인서트
			//dao.updateStudent("s004", "아무개", "부산시"); //업데이트
			//dao.deleteStudent("s004"); //델리트
			//ShowStudent.show(dao.getAllStudent()); //보여주기
			//ShowStudent.show(dao.getAllStudent()); //모든 학생 출력
			//ShowStudent.show(dao.getAllStudentByName("양")); //글자 들어간 학생 출력
			ShowStudent.show(dao.getStudent("s005")); //해당 학생 출력
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
