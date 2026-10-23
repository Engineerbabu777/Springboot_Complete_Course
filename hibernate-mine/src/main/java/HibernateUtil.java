
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(UserClassHibernate.class)
                    .buildSessionFactory();

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
