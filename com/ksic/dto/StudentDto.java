package com.ksic.dto;

import java.io.Serializable;

//DTO : DB�� �� Row�� Ŭ����ȭ ��Ų��. 
//�������� ��� �� Ŭ������ ��ü�� �ϳ��� ���Ϳ� ���� �ִ´�.
public class StudentDto implements Serializable {//������ ���� �ø���������� �������̽�
	
	private String id; //DB���� ���� ĭ�𳯸�Ƽ
	private String name;
	private String addr;
	
	public StudentDto() {	
	}

	public StudentDto(String id, String name, String addr) { //DTO�� �����.
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
	}
	
	//getter / setter+
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "[" + this.id + "/" + this.name + "/" + this.addr + "]";
	}
}
