package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "WorkshopReservations", schema = "dbo", catalog = "jsroka_a")
public class WorkshopReservationsEntity {
    private int workshopReservationId;
    private int quantity;
    private int studentsIncluded;
    private boolean paid;
    private Timestamp reservationDate;
    private boolean isCanceled;

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
