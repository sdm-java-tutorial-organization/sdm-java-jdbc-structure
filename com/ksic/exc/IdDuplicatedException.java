package com.ksic.exc;

//����� ���� �ͼ���
//insert���� �̹� �ִ� id�� ��츦 ���ؼ�....
public class IdDuplicatedException extends Exception {

	public IdDuplicatedException() {
		super("id�� �̹� �����մϴ�.");
	}

	public IdDuplicatedException(String message) {
		super(message);
	}
}
