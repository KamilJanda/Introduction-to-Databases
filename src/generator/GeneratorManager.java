package generator;

import org.hibernate.SessionFactory;

import java.util.Random;

public class GeneratorManager {


    private SessionFactory sessionFactory;
    private ConferenceDaysGenerator conferenceDaysGenerator;
    private ConferenceReservationsGenerator conferenceReservationsGenerator;
    private ConferenceParticipantsGenerator conferenceParticipantsGenerator;
    private CustomerGenerator customerGenerator;

    public GeneratorManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        conferenceDaysGenerator = new ConferenceDaysGenerator(sessionFactory);
        conferenceReservationsGenerator = new ConferenceReservationsGenerator(sessionFactory);
        conferenceParticipantsGenerator = new ConferenceParticipantsGenerator(sessionFactory);
        customerGenerator = new CustomerGenerator(sessionFactory);
    }


    public void generate1() {

    }


    public void generate(int attendeeStartID, int conferenceDaysStartID, int conferenceReservationStartID, int workshopStartID, int numbersOfSeats, int workshopReservationStartID) {
        String[] startTimes = new String[]{"2017-02-12 18:00:00", "2017-02-22 08:00:00",
                "2017-11-12 09:00:00", "2016-03-24 11:00:00", "2016-01-02 11:00:00",
                "2015-04-12 13:00:00", "2015-01-12 09:00:00", "2016-05-17 16:30:00",};

        int maxNumberOfDaysInConference = 4;
        int numberOfOrganizers = 150;
        int numberOfCustomers = 400;
        int numberOfConferences = 300;
        int maxNumberOfSeats = 100;
        int numberOfConferenceReservation = 5;


        int maxSeatsOnConference = new Random().nextInt(maxNumberOfSeats) + 1; //done
        String conferenceStartTime = startTimes[new Random().nextInt(startTimes.length)]; //done
        int conferenceID = new Random().nextInt(numberOfConferences) + 1; //done
        int seatsOnWorkshop = new Random().nextInt(maxSeatsOnConference) + 1;
        //int numberOfAttendee = new Random().nextInt(maxSeatsOnConference) + 1;
        int conferenceDayID = conferenceDaysStartID; //done


        int numberOfConferenceDays = new Random().nextInt(maxNumberOfDaysInConference) + 1; //rand 1-4

        for (int i = 0; i < numberOfConferenceDays; i++) {
            ConferenceDaysGenerator conferenceDaysGenerator = new ConferenceDaysGenerator(sessionFactory);
            conferenceDaysGenerator.generateConferenceDaysGenerator(conferenceID, conferenceStartTime, maxSeatsOnConference);
            conferenceDayID++;

            PricesGenerator pricesGenerator=new PricesGenerator(5,conferenceDayID);
            pricesGenerator.generatePrices(sessionFactory);


            int numberOfWorkshops = new Random().nextInt(5);

            for (int j = 0; j < numberOfWorkshops; j++) {
                WorkshopsGenerator workshopsGenerator = new WorkshopsGenerator(sessionFactory);
                workshopsGenerator.generateWorkshops(seatsOnWorkshop, conferenceDayID);
                workshopStartID++;
            }


            for (int k = 0; k < numberOfConferenceReservation; k++) {
                int customerID = new Random().nextInt(numberOfCustomers) + 1; //done it can be random
                ConferenceReservationsGenerator conferenceReservationsGenerator = new ConferenceReservationsGenerator(sessionFactory);
                conferenceReservationsGenerator.generateConferenceReservations(customerID, conferenceDayID, maxSeatsOnConference, conferenceStartTime);
                conferenceReservationStartID++;

                int numberOfAttendee = maxSeatsOnConference / numberOfConferenceReservation;

                AttendeeGenerator attendeeGenerator = new AttendeeGenerator(sessionFactory);
                attendeeGenerator.generateAttendee(numberOfAttendee, customerID);


                int tampAttendeeID = attendeeStartID;
                for (int j = 0; j < numberOfAttendee; j++) {
                    ConferenceParticipantsGenerator conferenceParticipantsGenerator = new ConferenceParticipantsGenerator(sessionFactory);
                    conferenceParticipantsGenerator.generateConferencePrincipants(conferenceReservationStartID, tampAttendeeID);
                    tampAttendeeID++;
                }


                for (int j = 0; j < numberOfWorkshops; j++) {
                    WorkshopReservationsGenerator workshopReservationsGenerator = new WorkshopReservationsGenerator(sessionFactory);
                    workshopReservationsGenerator.generateWorkshopReservations(conferenceReservationStartID, workshopStartID, seatsOnWorkshop, conferenceStartTime);
                    workshopReservationStartID++;

                    for (int l = 0; l < numberOfAttendee; l++) {
                        WorkshopPrincipantsGenerator workshopPrincipantsGenerator = new WorkshopPrincipantsGenerator(sessionFactory);
                        workshopPrincipantsGenerator.generateWorkshopPrincipants(workshopReservationStartID, attendeeStartID);
                        attendeeStartID++;
                    }


                }


            }

        }


    }


}





