package customTools;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.*;

public class LineItemDB {

	public static void insert(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(item));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static List<Lineitem> selectAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Lineitem> tList = new ArrayList<Lineitem>();
		String qString = "select e from Lineitem e";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString, Lineitem.class);
		try {
			q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tList;
	}
	
	public static List<Lineitem> getLineitemByUserName(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select e from Lineitem e where e.user_name = :userName";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString, Lineitem.class);
		List<Lineitem> tLineitem = null;
		try {
			tLineitem = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tLineitem;
	}
	
	public static List<Lineitem> getLineitemsByProductName(String productName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select e from Lineitem e where e.product_name = :productName";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString, Lineitem.class);
		List<Lineitem> tLineitem = null;
		try {
			tLineitem = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tLineitem;
	}
	public static List<Lineitem> getLineitems(String loginName, String productName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from Lineitem e where e.user_name = :loginName and "
													+ "e.product_name = :productName";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString, Lineitem.class);
		q.setParameter("loginName", loginName);
		q.setParameter("productName", productName);
		List<Lineitem> tLineitem = null;
		try {
			tLineitem = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tLineitem ;
	}	 
/*	public static ALineitem internalCopy(Lineitem theLineitem) {
		ALineitem aLineitem = new ALineitem();
		aLineitem.setId(theLineitem.getId());
		aLineitem.setQuantity(theLineitem.getQuantity());
		aLineitem.setTotal(theLineitem.getTotal());
		aLineitem.setProduct(ProductDB.internalCopy(theLineitem.getProduct()));
		aLineitem.setShopper(UserDB.internalCopy(theLineitem.getShopper()));
		return aLineitem;
	}
	
		public static Lineitem dbCopy(ALineitem theLineitem) {
			Lineitem aLineitem = new Lineitem();
			aLineitem.setId(theLineitem.getId());
			aLineitem.setQuantity(theLineitem.getQuantity());
			aLineitem.setTotal(theLineitem.getTotal());
			aLineitem.setProduct(ProductDB.dbCopy(theLineitem.getProduct()));
			aLineitem.setShopper(UserDB.dbCopy(theLineitem.getShopper()));
			return aLineitem;
		}*/

}
