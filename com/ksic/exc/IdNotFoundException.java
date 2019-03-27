package com.ksic.exc;

//사용자 정의 익셉션
//update, delete에서 이미 없는 id의 오류를 처리하기 위해
public class IdNotFoundException extends Exception {

	public IdNotFoundException() {
		super("존재하지 않는 id입니다.");
	}

	public IdNotFoundException(String message) {
		super(message);

	}
}
