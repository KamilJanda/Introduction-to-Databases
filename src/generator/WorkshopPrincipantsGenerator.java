package generator;

import dbmodel.entities.WorkshopPrincipantsEntity;
import org.hibernate.SessionFactory;

public class WorkshopPrincipantsGenerator {

    private SessionFactory sessionFactory;

    public WorkshopPrincipantsGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void generateWorkshopPrincipants(int workshopReservationID,int attendeeID)
    {
        WorkshopPrincipantsEntity.createWorkshopPrincipant(attendeeID,workshopReservationID,sessionFactory);
    }
}
