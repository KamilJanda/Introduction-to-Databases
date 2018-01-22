package generator;

import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GeneratorManager {


    private SessionFactory sessionFactory;
    private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public GeneratorManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }


    public void generate(int attendeeStartID, int conferenceDaysStartID, int conferenceReservationStartID, int workshopStartID, int seatsInReservation, int workshopReservationStartID,int numberOfConferences) {
        String[] startTimes = new String[]{"2017-02-12 18:00:00", "2017-02-22 08:00:00",
                "2017-11-12 09:00:00", "2016-03-24 11:00:00", "2016-01-02 11:00:00",
                "2015-04-12 13:00:00", "2015-01-12 09:00:00", "2016-05-17 16:30:00",};

        int maxNumberOfDaysInConference = 4;
        int numberOfOrganizers = 150;
        int numberOfCustomers = 400;

        int maxNumberOfSeats = 100;
        int numberOfConferenceReservation = 5;


        String conferenceStartTime = startTimes[new Random().nextInt(startTimes.length)]; //done
        //int conferenceID = new Random().nextInt(numberOfConferences) + 1; //done
        int conferenceID=1;
        //int seatsOnWorkshop = new Random().nextInt(seatsInReservation) + 1;

        //int numberOfAttendee = new Random().nextInt(seatsInReservation) + 1;
        int conferenceDayID = conferenceDaysStartID; //done
        int customerID=0;
        int seatsOnConference=50;
        int seatsOnWorkshopReservation=seatsOnConference/4;


        for(int i=0;i<150;i++)
        {
            OrganizersGenerator organizersGenerator = new OrganizersGenerator(sessionFactory);
            organizersGenerator.generateOrganizers();
        }


        CustomerGenerator customerGenerator=new CustomerGenerator(sessionFactory);
        customerGenerator.generateCustomers(150,50);

        for(int i=0;i<numberOfConferences;i++)
        {
            int organizerID=new Random().nextInt(numberOfOrganizers)+1;
            ConferencesGenerator conferencesGenerator=new ConferencesGenerator(sessionFactory);
            conferencesGenerator.generateConferences(organizerID);
        }

        for(conferenceID=1;conferenceID<=numberOfConferences;conferenceID++) {


            int numberOfConferenceDays = new Random().nextInt(maxNumberOfDaysInConference) + 1; //rand 1-4


            conferenceStartTime = startTimes[new Random().nextInt(startTimes.length)];
            for (int i = 0; i < numberOfConferenceDays; i++) {
                ConferenceDaysGenerator conferenceDaysGenerator = new ConferenceDaysGenerator(sessionFactory);
                conferenceDaysGenerator.generateConferenceDaysGenerator(conferenceID, conferenceStartTime, seatsOnConference);

                Date tmpDate = null;
                try {
                    tmpDate = dateFormat.parse(conferenceStartTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tmpDate = DateUtility.addDays(tmpDate, 1);
                conferenceStartTime = dateFormat.format(tmpDate);

                conferenceDayID++;

                PricesGenerator pricesGenerator = new PricesGenerator(5, conferenceDayID);
                pricesGenerator.generatePrices(sessionFactory);


                //int numberOfWorkshops = new Random().nextInt(5);
                int numberOfWorkshops = 4;

                for (int j = 0; j < numberOfWorkshops; j++) {
                    WorkshopsGenerator workshopsGenerator = new WorkshopsGenerator(sessionFactory);
                    workshopsGenerator.generateWorkshops(seatsOnConference, conferenceDayID);
                    //workshopStartID++;
                }


                for (int k = 0; k < numberOfConferenceReservation; k++) {
                    //int customerID = new Random().nextInt(numberOfCustomers) + 1; //done it can be random
                    customerID++;
                    ConferenceReservationsGenerator conferenceReservationsGenerator = new ConferenceReservationsGenerator(sessionFactory);
                    conferenceReservationsGenerator.generateConferenceReservations(customerID, conferenceDayID, seatsInReservation, conferenceStartTime);
                    conferenceReservationStartID++;

                    int numberOfAttendee = conferenceReservationsGenerator.getQuantityGenerated();

                    int tmpAttendeeID = attendeeStartID;
                    if (conferenceReservationsGenerator.isPaid()) {
                        AttendeeGenerator attendeeGenerator = new AttendeeGenerator(sessionFactory);
                        attendeeGenerator.generateAttendee(numberOfAttendee, customerID);


                        for (int j = 0; j < numberOfAttendee; j++) {
                            attendeeStartID++;
                            ConferenceParticipantsGenerator conferenceParticipantsGenerator = new ConferenceParticipantsGenerator(sessionFactory);
                            conferenceParticipantsGenerator.generateConferencePrincipants(conferenceReservationStartID, attendeeStartID);

                        }

                    }
                    for (int j = 0; j < numberOfWorkshops; j++) {
                        workshopStartID++;
                        WorkshopReservationsGenerator workshopReservationsGenerator = new WorkshopReservationsGenerator(sessionFactory);
                        workshopReservationsGenerator.generateWorkshopReservations(conferenceReservationStartID, workshopStartID, seatsOnWorkshopReservation, conferenceStartTime);
                        workshopReservationStartID++;

                        if (workshopReservationsGenerator.isPaid() && conferenceReservationsGenerator.isPaid()) {
                            for (int l = 0; l < numberOfAttendee; l++) {
                                WorkshopPrincipantsGenerator workshopPrincipantsGenerator = new WorkshopPrincipantsGenerator(sessionFactory);
                                workshopPrincipantsGenerator.generateWorkshopPrincipants(workshopReservationStartID, tmpAttendeeID);
                                tmpAttendeeID++;
                            }
                        }


                    }


                }

            }

        }
    }


}





