
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;


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
            /*AttendeeEntity.createAttendee("Karol", "popol",
                    "a2ssd@asd.pl", "ala123",123,2,sessionFactory);
            */
            /*OrganizersEntity.createOrganizer("Adam Wachnik",
                    "kolcho123@123.pl", "123123123", sessionFactory);
            */
            /*ConferencesEntity.createConference("kolchoz to zycie?",
                    false, null, sessionFactory);
            */
            /*ConferenceDaysEntity.createConferenceDay(
                    new Timestamp(2018,1,5,12,0,0,0),
                    new Timestamp(2018,1,5,13,0,0,0),
                    40, 2 , sessionFactory);
            */

            } finally {
            session.close();
        }
    }
}