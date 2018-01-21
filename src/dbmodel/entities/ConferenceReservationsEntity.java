package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "ConferenceReservations", schema = "dbo", catalog = "jsroka_a")
public class ConferenceReservationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conferenceReservationId;
    private int quantity;
    private int studentsIncluded;
    private boolean paid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;
    private boolean isCanceled;

    @ManyToOne
    @JoinColumn(name="CustomerId")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="ConferenceDayId")
    private ConferenceDaysEntity conferenceDay;

    protected ConferenceReservationsEntity(){}

    public static ConferenceReservationsEntity createConferenceReservation(int quantity,
                                                                           int studentsIncluded,
                                                                           boolean paid,
                                                                           Date reservationDate,
                                                                           boolean isCanceled,
                                                                           int CustomerID,
                                                                           int ConferenceDayId,
                                                                           SessionFactory factory){
        ConferenceReservationsEntity conferenceReservationsEntity = new ConferenceReservationsEntity();
        conferenceReservationsEntity.quantity = quantity;
        conferenceReservationsEntity.studentsIncluded = studentsIncluded;
        conferenceReservationsEntity.paid = paid;
        conferenceReservationsEntity.reservationDate = reservationDate;
        conferenceReservationsEntity.isCanceled = isCanceled;

        conferenceReservationsEntity.reservationDate.setYear(conferenceReservationsEntity.reservationDate.getYear()-1900);
        conferenceReservationsEntity.reservationDate.setMonth(conferenceReservationsEntity.reservationDate.getMonth()-1);

        Session session = factory.openSession();
        conferenceReservationsEntity.setCustomer(session.load(CustomerEntity.class,CustomerID));
        conferenceReservationsEntity.setConferenceDay(session.load(ConferenceDaysEntity.class,ConferenceDayId));
        EntitySaver.save(factory,conferenceReservationsEntity);
        return conferenceReservationsEntity;
    }

    public ConferenceDaysEntity getConferenceDay() {
        return conferenceDay;
    }

    public void setConferenceDay(ConferenceDaysEntity conferenceDay) {
        this.conferenceDay = conferenceDay;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }


    @Column(name = "ConferenceReservationID", nullable = false)
    public int getConferenceReservationId() {
        return conferenceReservationId;
    }

    public void setConferenceReservationId(int conferenceReservationId) {
        this.conferenceReservationId = conferenceReservationId;
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
    @Column(name = "ReservationDate", nullable = false)
    public Date getReservationDate() {
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

        ConferenceReservationsEntity that = (ConferenceReservationsEntity) o;

        if (conferenceReservationId != that.conferenceReservationId) return false;
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
        int result = conferenceReservationId;
        result = 31 * result + quantity;
        result = 31 * result + studentsIncluded;
        result = 31 * result + (paid ? 1 : 0);
        result = 31 * result + (reservationDate != null ? reservationDate.hashCode() : 0);
        result = 31 * result + (isCanceled ? 1 : 0);
        return result;
    }
}
