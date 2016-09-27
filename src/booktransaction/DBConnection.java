package booktransaction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Prabhath on 9/15/2016.
 */
public class DBConnection {
    SessionFactory factory = null;
    Session session = null;
    public void saveBook1Lending(Book1 book1Object) {

        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Book1.class)
                    .buildSessionFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("saving the Data object............................");
            session.save(book1Object);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    public void saveBook2Lending(Book2 book1Object) {

        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Book2.class)
                    .buildSessionFactory();
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("saving the Data object............................");
            session.save(book1Object);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
