package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Prices", schema = "dbo", catalog = "jsroka_a")
public class PricesEntity {
    private int priceId;
    private double studentDiscount;
    private int daysToConference;
    private BigDecimal price;

    @Id
    @Column(name = "PriceID", nullable = false)
    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Basic
    @Column(name = "StudentDiscount", nullable = false, precision = 0)
    public double getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(double studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    @Basic
    @Column(name = "DaysToConference", nullable = false)
    public int getDaysToConference() {
        return daysToConference;
    }

    public void setDaysToConference(int daysToConference) {
        this.daysToConference = daysToConference;
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

        PricesEntity that = (PricesEntity) o;

        if (priceId != that.priceId) return false;
        if (Double.compare(that.studentDiscount, studentDiscount) != 0) return false;
        if (daysToConference != that.daysToConference) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = priceId;
        temp = Double.doubleToLongBits(studentDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + daysToConference;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
