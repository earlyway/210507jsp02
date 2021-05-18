package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import config.DB;

public class EmpDAO {
	public void insert() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
			conn.setAutoCommit(false); //수동커밋으로 설정
			long before=System.currentTimeMillis(); //현재 시간
			for(int i=1; i<=100000; i++) {
				String sql=
"insert into emp2 (empno,ename,deptno) values (?,?,?)";
				pstmt=conn.prepareStatement(sql); // Statement 생성
				pstmt.setInt(1, i);
				pstmt.setString(2, "kim"+i);
				pstmt.setInt(3, i);
				pstmt.executeUpdate(); //1건의 레코드 저장
				pstmt.close(); //Statement 닫기
			}
			long after=System.currentTimeMillis();
			conn.commit(); //커밋
			conn.setAutoCommit(true); //오토커밋으로 설정
			System.out.println("실행시간:"+(after-before));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn!=null) conn.rollback(); //롤백
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
	}
	public void insert_batch() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
			conn.setAutoCommit(false);
			String sql="insert into emp2 (empno,ename,deptno) values (?,?,?)";
			pstmt=conn.prepareStatement(sql); //Statement 생성
			long before=System.currentTimeMillis();
			for(int i=1; i<=100000; i++) {
				pstmt.setInt(1, i);
				pstmt.setString(2, "kim"+i);
				pstmt.setInt(3, i);
				pstmt.addBatch(); //작업 예약
			}
			pstmt.executeBatch(); //10만건을 일괄처리
			conn.commit();
			conn.setAutoCommit(true);
			long after=System.currentTimeMillis();
			System.out.println("실행시간:"+(after-before));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(conn!=null) conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
	}
}
