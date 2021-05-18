package shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import shop.dto.CartDTO;
import sqlmap.MybatisManager;

public class CartDAO {
	public List<CartDTO> cartMoney(){
		SqlSession session=MybatisManager.getInstance().openSession();
		List<CartDTO> items= session.selectList("cart.cart_money");
		return items;
	}
	
	
	public void cartInsert(CartDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("cart.insert_cart", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
	}
	
	
	
	public List<CartDTO> cartList(String userid){
		SqlSession session=null;
		List<CartDTO> list=null;
		try {
			session=MybatisManager.getInstance().openSession();
			list=session.selectList("cart.list_cart",userid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return list;
	}
	
	
	
	
	public void cartDel(CartDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.delete("cart.delete_cart", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
	}
	
	
	
	
	public void cartUpdate(CartDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("cart.update_cart",dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
	}
	
	
	
	
	public void cartClear(String userid) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.delete("cart.clear_cart",userid);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
	}
	
	
	
	public int sumMoney(String userid) {
		int total=0;
		SqlSession session=MybatisManager.getInstance().openSession();
		total=session.selectOne("cart.sum_money", userid);
		session.close();
		return total;
	}
}
