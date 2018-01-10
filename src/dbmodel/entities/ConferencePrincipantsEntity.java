package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
@Table(name = "ConferencePrincipants", schema = "dbo", catalog = "jsroka_a")
public class ConferencePrincipantsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conferencePrincipantId;

    @ManyToOne
    @JoinColumn(name="AttendeeID")
    private AttendeeEntity attendee;

    @ManyToOne
    @JoinColumn(name="ConferenceReservationID")
    private ConferenceReservationsEntity conferenceReservation;

    protected ConferencePrincipantsEntity(){}

    public static ConferencePrincipantsEntity createConferencePrincipant(int AttendeeID,
                                                                         int ConferenceReservationID,
                                                                         SessionFactory factory){
        ConferencePrincipantsEntity conferencePrincipantsEntity = new ConferencePrincipantsEntity();

        Session session = factory.openSession();
        conferencePrincipantsEntity.setConferenceReservation(session.load(ConferenceReservationsEntity.class,ConferenceReservationID));
        conferencePrincipantsEntity.setAttendee(session.load(AttendeeEntity.class,AttendeeID));
        EntitySaver.save(factory,conferencePrincipantsEntity);
        return conferencePrincipantsEntity;
    }

    public ConferenceReservationsEntity getConferenceReservation() {
        return conferenceReservation;
    }

    public void setConferenceReservation(ConferenceReservationsEntity conferenceReservation) {
        this.conferenceReservation = conferenceReservation;
    }

    public AttendeeEntity getAttendee() {
        return attendee;
    }

    public void setAttendee(AttendeeEntity attendee) {
        this.attendee = attendee;
    }

    @Id
    @Column(name = "ConferencePrincipantID", nullable = false)
    public int getConferencePrincipantId() {
        return conferencePrincipantId;
    }

    public void setConferencePrincipantId(int conferencePrincipantId) {
        this.conferencePrincipantId = conferencePrincipantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferencePrincipantsEntity that = (ConferencePrincipantsEntity) o;

        if (conferencePrincipantId != that.conferencePrincipantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return conferencePrincipantId;
    }
}
