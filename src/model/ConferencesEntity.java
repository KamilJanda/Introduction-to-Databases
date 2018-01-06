package model;

import javax.persistence.*;

@Entity
@Table(name = "Conferences", schema = "dbo", catalog = "jsroka_a")
public class ConferencesEntity {
    private int conferenceId;
    private boolean isCanceled;
    private String conferenceName;

    @Id
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
