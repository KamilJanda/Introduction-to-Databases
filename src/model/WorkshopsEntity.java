package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name = "Workshops", schema = "dbo", catalog = "jsroka_a")
public class WorkshopsEntity {
    private int workshopId;
    private String workshopName;
    private int seats;
    private Time startTime;
    private Time endTime;
    private boolean isCanceled;
    private BigDecimal price;

    @Id
    @Column(name = "WorkshopID", nullable = false)
    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    @Basic
    @Column(name = "WorkshopName", nullable = true)
    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    @Basic
    @Column(name = "Seats", nullable = false)
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Basic
    @Column(name = "StartTime", nullable = false)
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime", nullable = false)
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "isCanceled", nullable = false)
    public boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Basic
    @Column(name = "Price", nullable = false, precision = 4)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkshopsEntity that = (WorkshopsEntity) o;

        if (workshopId != that.workshopId) return false;
        if (seats != that.seats) return false;
        if (workshopName != null ? !workshopName.equals(that.workshopName) : that.workshopName != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workshopId;
        result = 31 * result + (workshopName != null ? workshopName.hashCode() : 0);
        result = 31 * result + seats;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
