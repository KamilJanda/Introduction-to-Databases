package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
@Table(name = "Organizers", schema = "dbo", catalog = "jsroka_a")
public class OrganizersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organizerId;

    private String organizatorName;
    private String email;
    private String phone;

    protected OrganizersEntity(){ }


    public static OrganizersEntity createOrganizer(String organizatorName,
                                                String email,
                                                String phone,
                                                SessionFactory factory){
        OrganizersEntity organizerEntity = new OrganizersEntity();
        organizerEntity.organizatorName = organizatorName;
        organizerEntity.phone = phone;
        organizerEntity.email = email;

        EntitySaver.save(factory,organizerEntity);
        return organizerEntity;
    }

    @Column(name = "OrganizerID", nullable = false)
    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    @Basic
    @Column(name = "OrganizatorName", nullable = false)
    public String getOrganizatorName() {
        return organizatorName;
    }

    public void setOrganizatorName(String organizatorName) {
        this.organizatorName = organizatorName;
    }

    @Basic
    @Column(name = "Email", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizersEntity that = (OrganizersEntity) o;

        if (organizerId != that.organizerId) return false;
        if (organizatorName != null ? !organizatorName.equals(that.organizatorName) : that.organizatorName != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizerId;
        result = 31 * result + (organizatorName != null ? organizatorName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
