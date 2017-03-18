package customTools;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Product;

public class ProductDB {

	public static void insert(Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(aProduct);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(aProduct);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(aProduct));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static ArrayList<Product> selectAll() {
		List<Product> pList = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from Product e";
		TypedQuery<Product> q = (TypedQuery<Product>) em.createQuery(qString, Product.class);
		try {
			pList = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return  new ArrayList<Product>( pList);
	}

	public static Product getProduct(String pname) {
		Product product = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from Product e where e.name=" + pname;
		TypedQuery<Product> q = (TypedQuery<Product>) em.createQuery(qString, Product.class);
		try {
			product = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return  product;
	}
	public static Product getProduct(int pid) {
		Product product = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		String qString = "select e from Product e where e.id=" + pid;
		TypedQuery<Product> q = (TypedQuery<Product>) em.createQuery(qString, Product.class);
		try {
			product = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return  product;
	}	 
/*	public static TheProduct internalCopy(Product aProduct) {
		TheProduct theProduct = new TheProduct();
		theProduct.setId(aProduct.getId());
		theProduct.setName(aProduct.getName());
		theProduct.setDescription(aProduct.getDescription());
		theProduct.setUnitprice(aProduct.getUnitprice());
		theProduct.setPic(aProduct.getPic());
		return theProduct;
	}	 
	public static Product dbCopy(TheProduct aProduct) {
		Product theProduct = new Product();
		theProduct.setId(aProduct.getId());
		theProduct.setName(aProduct.getName());
		theProduct.setDescription(aProduct.getDescription());
		theProduct.setUnitprice(aProduct.getUnitprice());
		theProduct.setPic(aProduct.getPic());
		return theProduct;
	}*/
}
