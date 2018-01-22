package generator;

import dbmodel.entities.WorkshopReservationsEntity;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class WorkshopReservationsGenerator {

    private SessionFactory sessionFactory;
    private int quantityGenerated;
    boolean paid;

    private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int maxDayToWorkshop=30;


    public WorkshopReservationsGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Date generateDate(String conferenceStartTime)
    {
        Date result=null;

        try {
            Date date=dateFormat.parse(conferenceStartTime);
            int daysToConference=new Random().nextInt(maxDayToWorkshop)-maxDayToWorkshop;

            result = DateUtility.addDays(date,daysToConference);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    public void generateWorkshopReservations(int conferenceReservationID,int workshopID,int maxSeatsOnWorkshop,String conferenceDayStartDate)
    {
        int quantity=new Random().nextInt(maxSeatsOnWorkshop/2)+1;
        this.quantityGenerated=quantity;
        int studentIncluded=new Random().nextInt(quantity);

        boolean isCancelled;
        boolean paid=new Random().nextBoolean();
        this.paid=paid;

        if(paid)
            isCancelled=false;
        else
            isCancelled=new Random().nextBoolean();


        Date reservationDate=generateDate(conferenceDayStartDate);

        Calendar cal=Calendar.getInstance();
        cal.setTime(reservationDate);

        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);

        WorkshopReservationsEntity.createWorkshopReservation(
                quantity,
                studentIncluded,
                paid,
                new Timestamp(year,month,day,hour,minute,sec,0),
                isCancelled,
                conferenceReservationID,
                workshopID,
                sessionFactory
                );

    }


    public int getQuantityGenerated() {
        return quantityGenerated;
    }


    public boolean isPaid() {
        return paid;
    }
}
