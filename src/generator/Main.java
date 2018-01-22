package generator;

import dbmodel.entities.CustomerEntity;
import dbmodel.entities.WorkshopPrincipantsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Random;


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
            /*CustomerEntity.createCustomer("xdfirm3",
                    "poland", "krakow", "xd/xd",
                    "915548756", null, false, sessionFactory);
            */
            /*AttendeeEntity.createAttendee("Karol", "popol",
                    "a2ssd@asd.pl", "ala123",123,2,sessionFactory);
            */
            /*for (int i=0;i<100; i++)
                OrganizersEntity.createOrganizer("Adam Wachnik",
                    "kolcho123@123.pl", "123123123", sessionFactory);
            */
            /*
            ConferencesEntity.createConference("kolchoz to zycie 2 ?",
                    false, null, sessionFactory);
            */
            /*
            ConferenceDaysEntity.createConferenceDay(
                    new Timestamp(2018,3,4,12,0,0,0),
                    new Timestamp(2018,3,4,13,0,0,0),
                    100, 3 , sessionFactory);
            */
            /*
            PricesEntity.createPrice(0.1,0,
                    new BigDecimal(1),46, sessionFactory);
            System.out.println("operation done succesfully");
            */
            /*
            ConferenceReservationsEntity.createConferenceReservation(10,1,true,
                    new Timestamp(2018,1,10,15,0,0,0),false,
                    1,46, sessionFactory);
            */
            /*
            ConferencePrincipantsEntity.createConferencePrincipant(1, 14,
                    sessionFactory);

             */
            /*
            WorkshopsEntity.createWorkshop(null,30,
                    new Time(12,12,12), new Time(13,13,13),
                    false, new BigDecimal(10), 46, sessionFactory);
            */
            /*
            WorkshopReservationsEntity.createWorkshopReservation(2,0,
            true, new Timestamp(2018,1,10,15,0,0,0),false,
                    14, 3,sessionFactory);
            */
            /*WorkshopPrincipantsEntity.createWorkshopPrincipant(1,4,
                    sessionFactory);
            */

            //CustomerGenerator gen = new CustomerGenerator(15,3);
            //gen.generateCustomers(sessionFactory);
            /*
            AttendeeGenerator attendeeGenerator = new AttendeeGenerator(1,1);
            attendeeGenerator.generateAttendee(sessionFactory);



            ConferenceReservationsGenerator conferenceReservationsGenerator=new ConferenceReservationsGenerator(1,10,40,"2018-02-05 12:00:00");
            conferenceReservationsGenerator.generateConferenceReservations(sessionFactory,true);


            ConferenceDaysGenerator conferenceDaysGenerator=new ConferenceDaysGenerator(5);
            conferenceDaysGenerator.generateConferenceDaysGenerator(sessionFactory,"2018-02-05 12:00:00",44);

            PricesGenerator pricesGenerator=new PricesGenerator(3,38);
            pricesGenerator.generatePrices(sessionFactory);


            WorkshopsGenerator workshopsGenerator=new WorkshopsGenerator(sessionFactory);
            workshopsGenerator.generateWorkshops(44,2);


            WorkshopReservationsGenerator workshopReservationsGenerator=new WorkshopReservationsGenerator(sessionFactory);
            workshopReservationsGenerator.generateWorkshopReservations(1,1,19,"2018-01-02 08:00:00");
*/
/*
            String[] startTimes=new String[]{"2017-02-12 18:00:00","2017-02-22 08:00:00",
                    "2017-11-12 09:00:00","2016-03-24 11:00:00","2016-01-02 11:00:00",
                    "2015-04-12 13:00:00","2015-01-12 09:00:00","2016-05-17 16:30:00",};

            int maxNumberOfDaysInConference=4;
            int numberOfOrganizers=150;
            int numberOfCustomers=400;
            int numberOfConferences=300;
            int maxNumberOfSeats=100;

            for(int i=0;i<150;i++)
            {
                OrganizersGenerator organizersGenerator = new OrganizersGenerator(sessionFactory);
                organizersGenerator.generateOrganizers();
            }


            CustomerGenerator customerGenerator=new CustomerGenerator(sessionFactory);
            customerGenerator.generateCustomers(150,50);



            for(int i=0;i<300;i++)
            {
                int organizerID=new Random().nextInt(numberOfOrganizers)+1;
                ConferencesGenerator conferencesGenerator=new ConferencesGenerator(sessionFactory);
                conferencesGenerator.generateConferences(organizerID);
            }

*/


            GeneratorManager generatorManager=new GeneratorManager(sessionFactory);
            generatorManager.generate(0,0,0,0,10,0,40);


            System.out.println("operation done successfully");
            } finally {
            System.out.println("closing session");
            session.close();
            StandardServiceRegistryBuilder.destroy(sessionFactory.getSessionFactoryOptions().getServiceRegistry());
            System.out.println("session closed");
        }

    }
}