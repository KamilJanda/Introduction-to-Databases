package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ConferenceDays", schema = "dbo", catalog = "jsroka_a")
public class ConferenceDaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conferenceDayId;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp endDate;
    private int seats;

    @ManyToOne
    @JoinColumn(name="ConferenceId")
    private ConferencesEntity conference;

    public static ConferenceDaysEntity createConferenceDay(Timestamp startDate,
                                                        Timestamp endDate,
                                                        int seats,
                                                        int ConferenceID,
                                                        SessionFactory factory){
        ConferenceDaysEntity conferenceDaysEntity = new ConferenceDaysEntity();
        conferenceDaysEntity.startDate = startDate;
        conferenceDaysEntity.endDate = endDate;
        conferenceDaysEntity.seats = seats;
        conferenceDaysEntity.startDate.setYear(conferenceDaysEntity.startDate.getYear()-1900);
        conferenceDaysEntity.endDate.setYear(conferenceDaysEntity.endDate.getYear()-1900);
        conferenceDaysEntity.startDate.setMonth(conferenceDaysEntity.startDate.getMonth()-1);
        conferenceDaysEntity.endDate.setMonth(conferenceDaysEntity.endDate.getMonth()-1);

        Session session = factory.openSession();
        conferenceDaysEntity.setConference(session.load(ConferencesEntity.class,ConferenceID));
        EntitySaver.save(factory,conferenceDaysEntity);
        return conferenceDaysEntity;
    }

    public ConferencesEntity getConference() {
        return conference;
    }

    public void setConference(ConferencesEntity conference) {
        this.conference = conference;
    }


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
