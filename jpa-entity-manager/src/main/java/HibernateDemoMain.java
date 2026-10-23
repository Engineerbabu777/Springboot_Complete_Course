
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDemoMain {

    public static void main(String[] args) {

        try {
            EntityManager em = JPAUtil.getEntityManager();

            try {
                UserClassHibernate user =
                        new UserClassHibernate("Alice");

                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();

                System.out.println("Added new user in DB");

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                em.close();
            }

        } finally {
            JPAUtil.close();
        }
    }
}


/*
* public static void main(String[] args) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            try {
                UserClassHibernate user =
                        new UserClassHibernate("Alice");

                session.persist(user);

                transaction.commit();

                System.out.println("Added new user in DB");

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();

            } finally {
                session.close();
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
* */