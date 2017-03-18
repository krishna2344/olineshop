package customTools;

	import java.util.ArrayList;
	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityTransaction;
	import javax.persistence.TypedQuery;
	import model.*;

	public class CartitemDB {

		public static void insert(Cartitem item) {
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

		public static void update(Cartitem item) {
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

		public static void delete(Cartitem item) {
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
		public static List<Cartitem> selectAll() {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			List<Cartitem> tList = new ArrayList<Cartitem>();
			String qString = "select e from Cartitem e";
			TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
			try {
				q.getResultList();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				em.close();
			}
			return tList;
		}
		
		public static List<Cartitem> getCartitemByUserName(String userName) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select e from Cartitem e where e.user_name = :userName";
			TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
			List<Cartitem> tCartitem = null;
			try {
				tCartitem = q.getResultList();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				em.close();
			}
			return tCartitem;
		}
		
		public static List<Cartitem> getCartitemsByProductName(String productName) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select e from Cartitem e where e.product_name = :productName";
			TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
			List<Cartitem> tCartitem = null;
			try {
				tCartitem = q.getResultList();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				em.close();
			}
			return tCartitem;
		}
		public static List<Cartitem> getCartitems(String loginName, String productName) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();

			String qString = "select e from Cartitem e where e.user_name = :loginName and "
														+ "e.product_name = :productName";
			TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
			q.setParameter("loginName", loginName);
			q.setParameter("productName", productName);
			List<Cartitem> tCartitem = null;
			try {
				tCartitem = q.getResultList();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				em.close();
			}
			return tCartitem ;
		}
	}