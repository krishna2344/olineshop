package customTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("ShopOnline");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static java.sql.Date currentSqlDate() {
		//sort of equivalent to sql sysdate
		Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
        String formattedDate = sdf.format(today);
        java.util.Date utilDate = null;
		try {
			utilDate = sdf.parse(formattedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

}
