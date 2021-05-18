package common;

import javax.servlet.http.Cookie;

public class Util {
	public static String getCookie(Cookie[] cookies, String name) {
		String result="";
		if(cookies!=null) { //쿠키 배열에 저장된 값이 있으면
			for(int i=0; i<cookies.length; i++) {
				//쿠키변수 이름이 맞으면
				if(cookies[i].getName().equals(name)) {
					//쿠키변수에 저장된 값을 변수에 저장하고 반복문 종료
					result=cookies[i].getValue();
					break;
				}
			}
		}
		return result; //cookie value 리턴
	}
}
