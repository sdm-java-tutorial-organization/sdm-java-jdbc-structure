package com.ksic.exc;

//����� ���� �ͼ���
//update, delete���� �̹� ���� id�� ������ ó���ϱ� ����
public class IdNotFoundException extends Exception {

	public IdNotFoundException() {
		super("�������� �ʴ� id�Դϴ�.");
	}

	public IdNotFoundException(String message) {
		super(message);

	}
}
