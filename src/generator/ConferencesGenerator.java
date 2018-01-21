package generator;

import dbmodel.entities.ConferencesEntity;
import org.hibernate.SessionFactory;

import java.util.Random;

public class ConferencesGenerator extends Generator {

    private static String[] conferenceNames;

    static {
        conferenceNames=new String[]{"Astronomy","Earth science","Atmospheric science","Oceanography",
                "Materials science","Aristotelian natural philosophy","Medieval natural philosophy","Newton and the scientific revolution",
                "19th-century developments","Outline of astronomy","Astrophysics"};
    }


    public ConferencesGenerator(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void generateConferences(int organizerID)
    {
        int index=new Random().nextInt(conferenceNames.length);

        ConferencesEntity.createConference(conferenceNames[index],new Random().nextBoolean(),organizerID,sessionFactory);
    }

}
