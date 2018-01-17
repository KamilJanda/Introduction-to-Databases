package generator;

import dbmodel.entities.AttendeeEntity;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.UUID;

public class AttendeeGenerator {

    private static final String[] firstNames;
    private static final String[] lastNames;
    private static final int maxID = 999999;
    private static final int minID = 100000;

    private int numberOfAttendee;
    private int numberOfCustomers;

    static {
        firstNames=createFirstNames();
        lastNames=createLastNames();

    }

    public AttendeeGenerator(int numberOfAttendee, int numberOfCustomers) {
        this.numberOfAttendee = numberOfAttendee;
        this.numberOfCustomers = numberOfCustomers;
    }

    public void generateAttendee(SessionFactory sessionFactory)
    {
        int firstNameIndex=0;
        int lastNameIndex=0;
        String firstName=null;
        String lastName=null;
        String email=null;
        String password=null;
        int studentID=0;
        int customerId=0;

        for (int i=0;i<numberOfAttendee;i++)
        {
            firstNameIndex=new Random().nextInt(firstNames.length);
            lastNameIndex=new Random().nextInt(lastNames.length);
            firstName=firstNames[firstNameIndex];
            lastName=lastNames[lastNameIndex];
            email=firstName.toLowerCase()+lastName.toLowerCase()+"@random.com";
            password= UUID.randomUUID().toString().substring(0,15);
            studentID=new Random().nextInt((maxID-minID)+1)+minID;
            //TO DO
            customerId=new Random().nextInt(numberOfCustomers)+100;

            AttendeeEntity.createAttendee(
                    firstName,
                    lastName,
                    email,
                    password,
                    studentID,
                    customerId,
                    sessionFactory);
        }
    }

    private static String[] createFirstNames()
    {
        String[] result= {"Zuzanna", "Julia", "Lena", "Maja", "Hanna", "Zofia",
                "Amelia", "Alicja", "Aleksandra", "Natalia", "Oliwia", "Maria", "Wiktoria", "Emilia", "Antonina",
                "Laura", "Anna", "Nadia", "Pola", "Liliana","Antoni","Jakub","Szymon","Jan","Filip","Franciszek","Mikołaj"
                ,"Aleksander","Kacper","Wojciech","Adam","Michał","Marcel","Wiktor"
        };

        return result;
    }

    private static String[] createLastNames()
    {
        String[] result={"Nowak","Kowalski","Kowalczyk","Lewandowski","Wozniak","Mazur","Krawczyk","Piotrowski","Grabowski",
                "Majewski","Olszewski","Król"};

        return result;
    }

}
