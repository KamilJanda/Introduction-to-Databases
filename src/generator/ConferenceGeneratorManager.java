package generator;

import org.hibernate.SessionFactory;

public class ConferenceGeneratorManager {


    private SessionFactory sessionFactory;
    private ConferenceDaysGenerator conferenceDaysGenerator;
    private ConferenceReservationsGenerator conferenceReservationsGenerator;
    private ConferenceParticipantsGenerator conferenceParticipantsGenerator;
    private CustomerGenerator customerGenerator;

    public ConferenceGeneratorManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        conferenceDaysGenerator=new ConferenceDaysGenerator(sessionFactory);
        conferenceReservationsGenerator=new ConferenceReservationsGenerator(sessionFactory);
        conferenceParticipantsGenerator=new ConferenceParticipantsGenerator(sessionFactory);
        customerGenerator=new CustomerGenerator(sessionFactory);
    }




}
