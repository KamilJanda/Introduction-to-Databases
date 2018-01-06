
import model.AttendeeEntity;
import model.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            sessionFactory.getProperties().put("hibernate.order_inserts", "true");
            sessionFactory.getProperties().put("hibernate.order_updates", "true");

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(final String[] args) throws Exception {
        final Session session = sessionFactory.openSession();
        try {
            /*CustomerEntity.createCustomer("xdfirm",
                    "poland", "krakow", "xd/xd",
                    "915548756", null, false, sessionFactory);
            */
            AttendeeEntity.createAttendee("Karol", "fala",
                    "a2sd@asd.pl", "ala123",123,2,sessionFactory);
        } finally {
            session.close();
        }
    }
}