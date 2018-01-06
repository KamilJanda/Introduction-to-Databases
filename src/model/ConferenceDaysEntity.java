package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ConferenceDays", schema = "dbo", catalog = "jsroka_a")
public class ConferenceDaysEntity {
    private int conferenceDayId;
    private Timestamp startDate;
    private Timestamp endDate;
    private int seats;

    @Id
    @Column(name = "ConferenceDayID", nullable = false)
    public int getConferenceDayId() {
        return conferenceDayId;
    }

    public void setConferenceDayId(int conferenceDayId) {
        this.conferenceDayId = conferenceDayId;
    }

    @Basic
    @Column(name = "StartDate", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "Seats", nullable = false)
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceDaysEntity that = (ConferenceDaysEntity) o;

        if (conferenceDayId != that.conferenceDayId) return false;
        if (seats != that.seats) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferenceDayId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + seats;
        return result;
    }
}
