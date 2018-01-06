package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkshopPrincipants", schema = "dbo", catalog = "jsroka_a")
public class WorkshopPrincipantsEntity {
    private int workshopPrincipantId;

    @Id
    @Column(name = "WorkshopPrincipantID", nullable = false)
    public int getWorkshopPrincipantId() {
        return workshopPrincipantId;
    }

    public void setWorkshopPrincipantId(int workshopPrincipantId) {
        this.workshopPrincipantId = workshopPrincipantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkshopPrincipantsEntity that = (WorkshopPrincipantsEntity) o;

        if (workshopPrincipantId != that.workshopPrincipantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return workshopPrincipantId;
    }
}
