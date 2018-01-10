package dbmodel.entities;

import dbmodel.EntitySaver;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Customer", schema = "dbo", catalog = "jsroka_a")
public class CustomerEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String country;
    private String city;
    private String address;
    private String phone;
    private boolean isCompany;
    private String nip;

    protected CustomerEntity(){}

    public static CustomerEntity createCustomer(String customerName,
                                                String country,
                                                String city,
                                                String address,
                                                String phone,
                                                String nip,
                                                boolean isCompany,
                                                SessionFactory factory){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.customerName = customerName;
        customerEntity.country = country;
        customerEntity.city = city;
        customerEntity.address = address;
        customerEntity.phone = phone;
        customerEntity.nip = nip;
        customerEntity.isCompany = isCompany;
        EntitySaver.save(factory, customerEntity);
        return customerEntity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CustomerName", nullable = false)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "Country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "City", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Phone", nullable = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "isCompany", nullable = false)
    public boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean company) {
        isCompany = company;
    }

    @Basic
    @Column(name = "NIP", nullable = true)
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerId != that.customerId) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (nip != null ? !nip.equals(that.nip) : that.nip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (nip != null ? nip.hashCode() : 0);
        return result;
    }
}
