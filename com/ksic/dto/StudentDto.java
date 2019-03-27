package com.ksic.dto;

import java.io.Serializable;

//DTO : DB의 한 Row를 클래스화 시킨다. 
//여러개일 경우 이 클래스의 객체를 하나씩 벡터에 집어 넣는다.
public class StudentDto implements Serializable {//순서를 위한 시리얼라이져블 인터페이스
	
	private String id; //DB에서 사용된 칸디날리티
	private String name;
	private String addr;
	
	public StudentDto() {	
	}

	public StudentDto(String id, String name, String addr) { //DTO를 만든다.
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
