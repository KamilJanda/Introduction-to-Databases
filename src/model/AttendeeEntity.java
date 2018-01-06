package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "Attendee", schema = "dbo", catalog = "jsroka_a")
public class AttendeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendeeId;

    private Integer studentIdCardNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name="CustomerId")
    private CustomerEntity customer;



    protected AttendeeEntity(){}

    public static AttendeeEntity createAttendee(String firstName,
                                                String lastName,
                                                String email,
                                                String password,
                                                Integer studentIdCardNumber,
                                                int customerID,
                                                SessionFactory factory){
        AttendeeEntity attendeeEntity = new AttendeeEntity();
        attendeeEntity.firstName = firstName;
        attendeeEntity.lastName = lastName;
        attendeeEntity.email = email;
        attendeeEntity.password = password;
        attendeeEntity.studentIdCardNumber = studentIdCardNumber;

        Session session = factory.openSession();
        attendeeEntity.setCustomer(session.load(CustomerEntity.class,new Integer(customerID)));
        EntitySaver.save(factory,attendeeEntity);
        return attendeeEntity;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Column(name = "AttendeeID", nullable = false)
    public int getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(int attendeeId) {
        this.attendeeId = attendeeId;
    }

    @Basic
    @Column(name = "StudentIDCardNumber", nullable = true)
    public Integer getStudentIdCardNumber() {
        return studentIdCardNumber;
    }

    public void setStudentIdCardNumber(Integer studentIdCardNumber) {
        this.studentIdCardNumber = studentIdCardNumber;
    }

    @Basic
    @Column(name = "FirstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendeeEntity that = (AttendeeEntity) o;

        if (attendeeId != that.attendeeId) return false;
        if (studentIdCardNumber != null ? !studentIdCardNumber.equals(that.studentIdCardNumber) : that.studentIdCardNumber != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attendeeId;
        result = 31 * result + (studentIdCardNumber != null ? studentIdCardNumber.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
