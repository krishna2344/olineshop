package customTools;

import java.util.ArrayList;
import java.util.List;

//import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.*;

public class UserDB {

	public static void insert(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<User> selectAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<User> tList = new ArrayList<User>();
		String qString = "select e from Tuser e";
		TypedQuery<User> q = (TypedQuery<User>) em.createQuery(qString, User.class);
		try {
			q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tList;
	}
	
	public static User getUserById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select e from User e where e.id = :userId";
		TypedQuery<User> q = (TypedQuery<User>) em.createQuery(qString, User.class);
		User tuser = null;
		try {
			tuser = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tuser;
	}
	
	public static boolean existUser(String loginName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
/*		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM DemoCustomer c WHERE c.customerId = 2L", Long.class);
		long total = query.getSingleResult();
		if (total>0)
		{
		out.printf("%s", "Customer exists");
		}else{
		out.printf("%s", "Customer does not exist");
		}*/
		boolean UserExist = false;
		String qString = "select e from User e where e.name = :loginName";
		TypedQuery<User> q = (TypedQuery<User>) em.createQuery(qString, User.class);
		q.setParameter("loginName", loginName);
		User tuser = null;
		try {
			tuser = q.getSingleResult();
			if (tuser != null) UserExist = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return UserExist ;
	}
	public static User getUserByName(String loginName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from User e where e.name = :loginName";
		TypedQuery<User> q = (TypedQuery<User>) em.createQuery(qString, User.class);
		q.setParameter("loginName", loginName);
		User tuser = null;
		try {
			tuser = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tuser ;
	}	 
/*	public static TheUser internalCopy(User aUser) {
		TheUser theUser = new TheUser();
		theUser.setId(aUser.getId());
		theUser.setName(aUser.getName());

		return theUser;
	}	 
	public static User dbCopy(TheUser aUser) {
		User theUser = new User();
		theUser.setId(aUser.getId());
		theUser.setName(aUser.getName());

		return theUser;
	}
*/}