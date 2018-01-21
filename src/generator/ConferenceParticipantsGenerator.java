package generator;

import dbmodel.entities.ConferencePrincipantsEntity;
import org.hibernate.SessionFactory;

public class ConferenceParticipantsGenerator {

    private SessionFactory sessionFactory;

    public ConferenceParticipantsGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void generateConferencePrincipants(int conferenceReservationID, int attendeeID)
    {
        ConferencePrincipantsEntity.createConferencePrincipant(attendeeID,conferenceReservationID,sessionFactory);
    }

}
