
public class Test {
	public static void main(String[] args) {
		String url="http://localhost/jsp02/member_servlet/join.do";
		System.out.println(url.indexOf("join.do"));
		System.out.println(url.indexOf("Join.do")); //없으면 -1
	}
}
