package generator;

import dbmodel.entities.OrganizersEntity;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OrganizersGenerator extends Generator {

    private static int minPhoneNumber = 111111111;
    private static int maxPhoneNumber = 999999999;
    private static String[] organizersNames = {"FakeCompany.zoo", "SuperCompany.inc",
            "SuperFirma", "Luks", "Pigeon" , "Oracle" , "Motorola", "Samson", "Judas",
            "China", "Malboro" , "Ferari" , "Simoleone", "Gabani", "Panini"};

    public OrganizersGenerator(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void generateOrganizers()
    {
        int index=new Random().nextInt(organizersNames.length);
        String email=organizersNames[index]+"@random.com";
        String phone= Integer.toString(ThreadLocalRandom.current().nextInt(minPhoneNumber, maxPhoneNumber + 1));

        OrganizersEntity.createOrganizer(organizersNames[index],email,phone,sessionFactory);
    }

}
