package generator;

import dbmodel.entities.AttendeeEntity;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.UUID;

public class AttendeeGenerator {

    private static int cnt=1;
    private static final String[] firstNames;
    private static final String[] lastNames;
    private static final int maxID = 999999;
    private static final int minID = 100000;

    private SessionFactory sessionFactory;

    static {
        firstNames=createFirstNames();
        lastNames=createLastNames();

    }

    public AttendeeGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void generateAttendee(int numberOfAttendee, int customerID)
    {
        int firstNameIndex=0;
        int lastNameIndex=0;
        String firstName=null;
        String lastName=null;
        String email=null;
        String password=null;
        int studentID=0;

        for (int i=0;i<numberOfAttendee;i++)
        {
            firstNameIndex=new Random().nextInt(firstNames.length);
            lastNameIndex=new Random().nextInt(lastNames.length);
            firstName=firstNames[firstNameIndex];
            lastName=lastNames[lastNameIndex];
            email=firstName.toLowerCase()+lastName.toLowerCase()+Integer.toString(cnt)+"@random.com";
            cnt++;
            password= UUID.randomUUID().toString().substring(0,15);
            studentID=new Random().nextInt((maxID-minID)+1)+minID;

            AttendeeEntity.createAttendee(
                    firstName,
                    lastName,
                    email,
                    password,
                    studentID,
                    customerID,
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
