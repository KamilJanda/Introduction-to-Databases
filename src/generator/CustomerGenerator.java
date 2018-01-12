package generator;

import dbmodel.entities.CustomerEntity;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerGenerator {
    private final static long minPhoneNumber = 111111111;
    private final static long maxPhoneNumber = 999999999;
    private final static long minNIP = 1111111111;
    private final static long maxNIP = 2000000000;
    private static final List<Place> places = new ArrayList<Place>(){{
        add(new Place("Poland","Krakow", "Majowa 5"));
        add(new Place("Poland","Krakow", "Majowa 6"));
        add(new Place("Poland","Krakow", "Majowa 7"));
        add(new Place("Poland","Krakow", "Majowa 8"));
        add(new Place("Poland","Krakow", "Majowa 9"));
        add(new Place("Poland","Warsaw", "Majowa 5"));
        add(new Place("Poland","Warsaw", "Majowa 6"));
        add(new Place("Poland","Warsaw", "Majowa 7"));
        add(new Place("Poland","Warsaw", "Majowa 8"));
        add(new Place("Poland","Warsaw", "Majowa 9"));
        add(new Place("Poland","Katowice", "Ogrodowa 5"));
        add(new Place("Poland","Katowice", "Ogrodowa 6"));
        add(new Place("Poland","Katowice", "Ogrodowa 7"));
        add(new Place("Poland","Katowice", "Ogrodowa 8"));
        add(new Place("Poland","Katowice", "Ogrodowa 9"));
        add(new Place("Poland","Wroclaw", "Ogrodowa 5"));
        add(new Place("Poland","Wroclaw", "Ogrodowa 6"));
        add(new Place("Poland","Wroclaw", "Ogrodowa 7"));
        add(new Place("Poland","Wroclaw", "Ogrodowa 8"));
        add(new Place("Poland","Wroclaw", "Ogrodowa 9"));
    }};
    private static String[] companyNames = {"FakeCompany.zoo", "SuperCompany.inc",
    "SuperFirma", "Luks", "Pigeon" , "Oracle" , "Motorola", "Samson", "Judas",
    "China", "Malboro" , "Ferari" , "Simoleone", "Gabani", "Panini"};
    private static String[] noCompanyCustomerNames ={"PrivateCustomer"};

    private int numberOfCompanies;
    private int numberOfNotCompanyCustomers;


    public void generateCustomers(SessionFactory sessionFactory){
        this.generateCompanies(sessionFactory);
        this.generateNoCompanyCustomers(sessionFactory);
    }

    private void generateCompanies(SessionFactory sessionFactory) {
        final int N = places.size()-1;
        for(int i=0;i<numberOfCompanies;i++){
            CustomerEntity.createCustomer(companyNames[i],
                    places.get(i%N).country,places.get(i%N).city, places.get(i%N).address,
                    Long.toString((long) (Math.random() * (maxPhoneNumber - minPhoneNumber) + minPhoneNumber)),
                    Long.toString((long) (Math.random() * (maxNIP - minNIP) + minNIP)),
                    true, sessionFactory);
        }
    }

    private void generateNoCompanyCustomers(SessionFactory sessionFactory) {
        final int N = places.size()-1;
        for(int i=0;i<numberOfNotCompanyCustomers;i++){
            CustomerEntity.createCustomer(noCompanyCustomerNames[0],
                    places.get((N-i)%N).country,places.get((N-i)%N).city, places.get((N-i)%N).address,
                    Long.toString((long) (Math.random() * (maxPhoneNumber - minPhoneNumber) + minPhoneNumber)),
                    null,
                    false, sessionFactory);
        }
    }

    private static class Place{
        String country;
        String city;
        String address;

        Place(String country, String city, String address){
            this.country = country;
            this.city = city;
            this.address = address;
        }
    }


    public CustomerGenerator(int numberOfCompanies, int numberOfNotCompanyCustomers){
        this.numberOfCompanies = numberOfCompanies;
        this.numberOfNotCompanyCustomers = numberOfNotCompanyCustomers;
    }


}
