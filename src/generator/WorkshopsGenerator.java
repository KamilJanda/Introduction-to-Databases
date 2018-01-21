package generator;

import dbmodel.entities.WorkshopsEntity;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkshopsGenerator {

    private static String[] workshopsNames;
    private static List<TimePair> times;

    private int conferenceDayID;
    private SessionFactory sessionFactory;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");

    static {
        workshopsNames= new String[]{"Astronomy","Earth science","Atmospheric science","Oceanography",
        "Materials science","Aristotelian natural philosophy","Medieval natural philosophy","Newton and the scientific revolution",
        "19th-century developments","Outline of astronomy","Astrophysics"};

        times=new ArrayList<>();

        times.add(new TimePair("08:30:00","12:00:00"));
        times.add(new TimePair("09:30:00","12:00:00"));
        times.add(new TimePair("12:30:00","14:00:00"));
        times.add(new TimePair("07:30:00","08:00:00"));
        times.add(new TimePair("08:30:00","12:40:00"));
        times.add(new TimePair("14:30:00","20:30:00"));
        times.add(new TimePair("11:30:00","15:10:00"));

    }

    public WorkshopsGenerator(SessionFactory sessionFactory) {
        this.conferenceDayID = conferenceDayID;
        this.sessionFactory = sessionFactory;
    }

    private BigDecimal generatePriceValue()
    {
        return  BigDecimal.valueOf((new Random().nextDouble())*100)
                    .setScale(2, RoundingMode.HALF_UP);
    }


    public void generateWorkshops(int seats,int conferenceDayID)
    {
        int index=new Random().nextInt(workshopsNames.length);
        int timesIndex=new Random().nextInt(times.size());

        Calendar calStart=Calendar.getInstance();
        Calendar calEnd=Calendar.getInstance();

        try {
            Date startDate=simpleDateFormat.parse(times.get(timesIndex).startTime);
            Date endDate=simpleDateFormat.parse(times.get(timesIndex).endTime);
            calStart.setTime(startDate);
            calEnd.setTime(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Time start= new Time(calStart.get(Calendar.HOUR_OF_DAY),calStart.get(Calendar.MINUTE),calStart.get(Calendar.SECOND));
        Time end= new Time(calEnd.get(Calendar.HOUR_OF_DAY),calEnd.get(Calendar.MINUTE),calEnd.get(Calendar.SECOND));

        WorkshopsEntity.createWorkshop(workshopsNames[index],seats,start,end,true,generatePriceValue(),conferenceDayID,sessionFactory);
    }

    private static class TimePair
    {
        public TimePair(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        String startTime;
        String endTime;
    }

}
