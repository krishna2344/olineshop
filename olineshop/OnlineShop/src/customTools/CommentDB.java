package customTools;

import java.util.ArrayList;
import java.util.List;

//import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.*;

public class CommentDB {

	public static void insert(Comment Comment) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(Comment);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Comment Comment) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(Comment);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Comment Comment) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(Comment));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Comment> selectAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Comment> tList = new ArrayList<Comment>();
		String qString = "select e from TComment e";
		TypedQuery<Comment> q = (TypedQuery<Comment>) em.createQuery(qString, Comment.class);
		try {
			q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tList;
	}
	
	public static List<Comment> getCommentByUserName(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select e from Comment e where e.user_name = :userName";
		TypedQuery<Comment> q = (TypedQuery<Comment>) em.createQuery(qString, Comment.class);
		List<Comment> tComment = null;
		try {
			tComment = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tComment;
	}
	
	public static List<Comment> getCommentsByProductName(String productName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select e from Comment e where e.product_name = :productName";
		TypedQuery<Comment> q = (TypedQuery<Comment>) em.createQuery(qString, Comment.class);
		List<Comment> tComment = null;
		try {
			tComment = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tComment;
	}
	public static List<Comment> getComments(String loginName, String productName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from Comment e where e.user_name = :loginName and "
													+ "e.product_name = :productName";
		TypedQuery<Comment> q = (TypedQuery<Comment>) em.createQuery(qString, Comment.class);
		q.setParameter("loginName", loginName);
		q.setParameter("productName", productName);
		List<Comment> tComment = null;
		try {
			tComment = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tComment ;
	}	 
}