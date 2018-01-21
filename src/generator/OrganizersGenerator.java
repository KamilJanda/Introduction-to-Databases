package generator;

import dbmodel.entities.OrganizersEntity;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OrganizersGenerator extends Generator {

    private static int minPhoneNumber = 111111111;
    private static int maxPhoneNumber = 999999999;
    private static String[] organizersNames = {"University of the Arts London", "Aston University",
            "Technological University of Tajikistan", "Mongolian Academy of Sciences", "Bishop Grosseteste University" , "Misan University" , "Azerbaijan Medical University", "Baku Higher Oil School", "Hamhŭng University of Education",
            "Andijan State University", "National University of Uzbekistan" , "Tashkent Technical University" , "Urgench State University", "Science Academy of Uzbekistan", "Agricultural University of Tajikistan"};

    public OrganizersGenerator(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void generateOrganizers()
    {
        int index=new Random().nextInt(organizersNames.length);
        String email="";
        String[] words=organizersNames[index].split(" ");

        for(int i=0;i<words.length;i++)
        {
            email+=words[i].substring(0,1);
        }
        email+="@random.com";

        //String email=organizersNames[index].replaceAll("\\s+","").toLowerCase()+"@random.com";
        String phone= Integer.toString(ThreadLocalRandom.current().nextInt(minPhoneNumber, maxPhoneNumber + 1));

        OrganizersEntity.createOrganizer(organizersNames[index],email,phone,sessionFactory);
    }

}
