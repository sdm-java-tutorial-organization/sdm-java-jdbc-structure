package com.ksic.test;

import com.ksic.dao.StudentDao;
import com.ksic.util.ShowStudent;

public class StudentTestMain {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
		try {
			//dao.insertStudent("s004", "������", "��⵵ �Ȼ��"); //�μ�Ʈ
			//dao.updateStudent("s004", "�ƹ���", "�λ��"); //������Ʈ
			//dao.deleteStudent("s004"); //����Ʈ
			//ShowStudent.show(dao.getAllStudent()); //�����ֱ�
			//ShowStudent.show(dao.getAllStudent()); //��� �л� ���
			//ShowStudent.show(dao.getAllStudentByName("��")); //���� �� �л� ���
			ShowStudent.show(dao.getStudent("s005")); //�ش� �л� ���
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
