package test;

import guestbook.dto.GuestBookDTO;

class A {
	int num;
	public A() { //default constructor 기본 생성자
		System.out.println("생성자 호출");
	}
	public A(int n) {
		num=n;
	}
}

public class Test {
	public static void main(String[] args) {
		A a=new A();
		a.num=100; 
		System.out.println(a.num);
		A aa=new A(1000);
		System.out.println(aa.num);
		
		
		GuestBookDTO dto=new GuestBookDTO();
		dto.setName("kim");
		dto.setEmail("kim@naver.com");
		dto.setPasswd("1234");
		
		//GuestBookDTO dto2=new GuestBookDTO("kim","kim@naver.com","1234");
		
	}
}












