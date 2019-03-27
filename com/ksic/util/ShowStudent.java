package com.ksic.util;

import java.util.Vector;

import com.ksic.dto.StudentDto;

//단순히 받아온 벡터나 DTO를 출력해주기 위한 클래스.
public class ShowStudent {
	public static void show(Vector v) {
		for (int i=0; i<v.size(); i++) {
			StudentDto dto = (StudentDto)v.get(i);
			System.out.println(dto);
			//show(dto);
		}
	} //show
	
	public static void show(StudentDto dto) {
		System.out.println(dto);
	}
}
