package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ConferenceReservations", schema = "dbo", catalog = "jsroka_a")
public class ConferenceReservationsEntity {
    private int conferenceReservationId;
    private int quantity;
    private int studentsIncluded;
    private boolean paid;
    private Timestamp reservationDate;
    private boolean isCanceled;

    @Id
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
