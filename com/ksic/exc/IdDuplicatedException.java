package com.ksic.exc;

//사용자 정의 익셉션
//insert에서 이미 있는 id일 경우를 위해서....
public class IdDuplicatedException extends Exception {

	public IdDuplicatedException() {
		super("id가 이미 존재합니다.");
	}

	public IdDuplicatedException(String message) {
		super(message);
	}
}
