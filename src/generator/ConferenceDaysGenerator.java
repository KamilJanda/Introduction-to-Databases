package generator;

import dbmodel.entities.ConferenceDaysEntity;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConferenceDaysGenerator {

    //private static final int maxSeats=150;
    //private static final int minSeats=1;

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SessionFactory sessionFactory;

    public ConferenceDaysGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void generateConferenceDaysGenerator(int conferenceID, String startTime, int seats)
    {
        Date startDate=null;
        try {
            startDate=simpleDateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal=Calendar.getInstance();
        cal.setTime(startDate);

        int yearStart=cal.get(Calendar.YEAR);
        int monthStart=cal.get(Calendar.MONTH)+1;
        int dayStart=cal.get(Calendar.DAY_OF_MONTH);
        int hourStart=cal.get(Calendar.HOUR_OF_DAY);
        int minuteStart=cal.get(Calendar.MINUTE);
        int secStart=cal.get(Calendar.SECOND);

        int hourEnd=hourStart;
        int hourMinute=new Random().nextInt(60);

        while (hourEnd<=hourStart)
        {
            hourEnd=new Random().nextInt(24);
        }



        ConferenceDaysEntity.createConferenceDay(
                new Timestamp(yearStart,monthStart,dayStart,hourStart,minuteStart,secStart,0),
                new Timestamp(yearStart,monthStart,dayStart,hourEnd,minuteStart,secStart,0),
                seats,
                conferenceID,
                sessionFactory
                );
    }

}
