package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConferencePrincipants", schema = "dbo", catalog = "jsroka_a")
public class ConferencePrincipantsEntity {
    private int conferencePrincipantId;

    @Id
    @Column(name = "ConferencePrincipantID", nullable = false)
    public int getConferencePrincipantId() {
        return conferencePrincipantId;
    }

    public void setConferencePrincipantId(int conferencePrincipantId) {
        this.conferencePrincipantId = conferencePrincipantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferencePrincipantsEntity that = (ConferencePrincipantsEntity) o;

        if (conferencePrincipantId != that.conferencePrincipantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return conferencePrincipantId;
    }
}
