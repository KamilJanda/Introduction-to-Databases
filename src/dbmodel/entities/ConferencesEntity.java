package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
@Table(name = "Conferences", schema = "dbo", catalog = "jsroka_a")
public class ConferencesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conferenceId;

    private boolean isCanceled;
    private String conferenceName;

    @ManyToOne
    @JoinColumn(name="OrganizerId")
    private OrganizersEntity organizer;


    public static ConferencesEntity createConference(String conferenceName,
                                                boolean isCanceled,
                                                Integer organizerID,
                                                SessionFactory factory){
        ConferencesEntity conferencesEntity = new ConferencesEntity();
        conferencesEntity.isCanceled = isCanceled;
        conferencesEntity.conferenceName = conferenceName;

        if (organizerID != null){
            Session session = factory.openSession();
            conferencesEntity.setOrganizer(session.load(OrganizersEntity.class,new Integer(organizerID)));
        }
        EntitySaver.save(factory,conferencesEntity);
        return conferencesEntity;
    }


    public OrganizersEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizersEntity organizer) {
        this.organizer = organizer;
    }

    @Column(name = "ConferenceID", nullable = false)
    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
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
    @Column(name = "ConferenceName", nullable = true)
    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferencesEntity that = (ConferencesEntity) o;

        if (conferenceId != that.conferenceId) return false;
        if (isCanceled != that.isCanceled) return false;
        if (conferenceName != null ? !conferenceName.equals(that.conferenceName) : that.conferenceName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferenceId;
        result = 31 * result + (isCanceled ? 1 : 0);
        result = 31 * result + (conferenceName != null ? conferenceName.hashCode() : 0);
        return result;
    }
}
