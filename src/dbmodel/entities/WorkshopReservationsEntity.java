package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "WorkshopReservations", schema = "dbo", catalog = "jsroka_a")
public class WorkshopReservationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workshopReservationId;

    private int quantity;
    private int studentsIncluded;
    private boolean paid;
    private Timestamp reservationDate;
    private boolean isCanceled;

    protected WorkshopReservationsEntity(){}

    public static WorkshopReservationsEntity createWorkshopReservation(int quantity,
                                                                       int studentsIncluded,
                                                                       boolean paid,
                                                                       Timestamp reservationDate,
                                                                       boolean isCanceled,
                                                                       int ConferenceReservationID,
                                                                       int WorkshopID,
                                                                       SessionFactory factory) {
        WorkshopReservationsEntity workshopReservationsEntity = new WorkshopReservationsEntity();
        workshopReservationsEntity.quantity = quantity;
        workshopReservationsEntity.studentsIncluded = studentsIncluded;
        workshopReservationsEntity.paid = paid;
        workshopReservationsEntity.reservationDate = reservationDate;
        workshopReservationsEntity.isCanceled = isCanceled;

        workshopReservationsEntity.reservationDate.setYear(workshopReservationsEntity.reservationDate.getYear()-1900);
        workshopReservationsEntity.reservationDate.setMonth(workshopReservationsEntity.reservationDate.getMonth()-1);

        Session session = factory.openSession();
        workshopReservationsEntity.setWorkshop(session.load(WorkshopsEntity.class,WorkshopID));
        workshopReservationsEntity.setConferenceReservation(session.load(ConferenceReservationsEntity.class,ConferenceReservationID));
        EntitySaver.save(factory,workshopReservationsEntity);
        return workshopReservationsEntity;
    }
    @ManyToOne
    @JoinColumn(name="ConferenceReservatioID")
    private ConferenceReservationsEntity conferenceReservation;

    @ManyToOne
    @JoinColumn(name="WorkshopID")
    private WorkshopsEntity workshop;

    public WorkshopsEntity getWorkshop() {
        return workshop;
    }

    public void setWorkshop(WorkshopsEntity workshop) {
        this.workshop = workshop;
    }

    public ConferenceReservationsEntity getConferenceReservation() {
        return conferenceReservation;
    }

    public void setConferenceReservation(ConferenceReservationsEntity conferenceReservation) {
        this.conferenceReservation = conferenceReservation;
    }

    @Id
    @Column(name = "WorkshopReservationID", nullable = false)
    public int getWorkshopReservationId() {
        return workshopReservationId;
    }

    public void setWorkshopReservationId(int workshopReservationId) {
        this.workshopReservationId = workshopReservationId;
    }

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "StudentsIncluded", nullable = false)
    public int getStudentsIncluded() {
        return studentsIncluded;
    }

    public void setStudentsIncluded(int studentsIncluded) {
        this.studentsIncluded = studentsIncluded;
    }

    @Basic
    @Column(name = "Paid", nullable = false)
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "reservationDate", nullable = false)
    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Basic
    @Column(name = "isCanceled", nullable = false)
    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkshopReservationsEntity that = (WorkshopReservationsEntity) o;

        if (workshopReservationId != that.workshopReservationId) return false;
        if (quantity != that.quantity) return false;
        if (studentsIncluded != that.studentsIncluded) return false;
        if (paid != that.paid) return false;
        if (isCanceled != that.isCanceled) return false;
        if (reservationDate != null ? !reservationDate.equals(that.reservationDate) : that.reservationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workshopReservationId;
        result = 31 * result + quantity;
        result = 31 * result + studentsIncluded;
        result = 31 * result + (paid ? 1 : 0);
        result = 31 * result + (reservationDate != null ? reservationDate.hashCode() : 0);
        result = 31 * result + (isCanceled ? 1 : 0);
        return result;
    }
}
