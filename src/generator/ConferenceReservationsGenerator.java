package generator;

import dbmodel.entities.ConferenceReservationsEntity;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ConferenceReservationsGenerator {

    private static int maxDayToConference=100;

    private int customerID;
    private int conferenceDayID;
    private int maxSeatsOnConference;
    private String ConferenceStartTime;
    private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ConferenceReservationsGenerator(int customerID, int conferenceDayID, int maxSeatsOnConference, String conferenceStartTime) {
        this.customerID = customerID;
        this.conferenceDayID = conferenceDayID;
        this.maxSeatsOnConference = maxSeatsOnConference;
        ConferenceStartTime = conferenceStartTime;
    }

    private Date generateDate(String conferenceStartTime)
    {
        Date result=null;

        try {
            Date date=dateFormat.parse(conferenceStartTime);
            int daysToConference=new Random().nextInt(maxDayToConference)-maxDayToConference;

            result = DateUtility.addDays(date,daysToConference);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void generateConferenceReservations(SessionFactory sessionFactory, boolean paid)
    {
        int quantity = new Random().nextInt(maxSeatsOnConference)+1;
        int studentsIncluded = new Random().nextInt(quantity+1);
        Date reservationDate=generateDate(this.ConferenceStartTime);

        Calendar cal=Calendar.getInstance();
        cal.setTime(reservationDate);

        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);

        boolean isCancelled;

        if(paid)
            isCancelled=false;
        else
            isCancelled=new Random().nextBoolean();



        ConferenceReservationsEntity.createConferenceReservation(
                quantity,
                studentsIncluded,
                paid,
                new Timestamp(year,month,day,hour,minute,sec,0),
                isCancelled,
                customerID,
                conferenceDayID,
                sessionFactory);
    }



}
