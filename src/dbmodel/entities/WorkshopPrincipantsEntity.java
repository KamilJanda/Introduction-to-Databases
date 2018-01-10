package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
@Table(name = "WorkshopPrincipants", schema = "dbo", catalog = "jsroka_a")
public class WorkshopPrincipantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workshopPrincipantId;

    @ManyToOne
    @JoinColumn(name="AttendeeId")
    private AttendeeEntity attendee;

    @ManyToOne
    @JoinColumn(name="WorkshopReservationId")
    private WorkshopReservationsEntity workshopReservation;

    protected WorkshopPrincipantsEntity() {}

    public static WorkshopPrincipantsEntity createWorkshopPrincipant(int AttendeeID,
                                                                     int WorkshopReservationID,
                                                                     SessionFactory factory){
        WorkshopPrincipantsEntity workshopPrincipantsEntity = new WorkshopPrincipantsEntity();

        Session session = factory.openSession();
        workshopPrincipantsEntity.setWorkshopReservation(
                session.load(WorkshopReservationsEntity.class,WorkshopReservationID));
        workshopPrincipantsEntity.setAttendee(session.load(AttendeeEntity.class,AttendeeID));
        EntitySaver.save(factory,workshopPrincipantsEntity);
        return workshopPrincipantsEntity;
    }

    public WorkshopReservationsEntity getWorkshopReservation() {
        return workshopReservation;
    }

    public void setWorkshopReservation(WorkshopReservationsEntity workshopReservation) {
        this.workshopReservation = workshopReservation;
    }

    public AttendeeEntity getAttendee() {
        return attendee;
    }

    public void setAttendee(AttendeeEntity attendee) {
        this.attendee = attendee;
    }

    @Id
    @Column(name = "WorkshopPrincipantID", nullable = false)
    public int getWorkshopPrincipantId() {
        return workshopPrincipantId;
    }

    public void setWorkshopPrincipantId(int workshopPrincipantId) {
        this.workshopPrincipantId = workshopPrincipantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkshopPrincipantsEntity that = (WorkshopPrincipantsEntity) o;

        if (workshopPrincipantId != that.workshopPrincipantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return workshopPrincipantId;
    }
}
