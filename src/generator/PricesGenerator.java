package generator;

import dbmodel.entities.PricesEntity;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PricesGenerator {


    private int numberOfPrices;
    private int conferenceDayID;

    public PricesGenerator(int numberOfPrices, int conferenceDayID) {
        this.numberOfPrices = numberOfPrices;
        this.conferenceDayID = conferenceDayID;
    }

    private double[] generateDiscounts()
    {
        double[] result=new double[numberOfPrices];

        for (int i=0;i<numberOfPrices;i++)
        {
            result[i]= BigDecimal.valueOf(new Random().nextDouble())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

        }

        Arrays.sort(result);

        return result;
    }

    private int[] generateDaysToConference()
    {
        int[] result=new int[numberOfPrices];
        result[0]=0;


        for (int i=1;i<numberOfPrices;i++)
        {
            result[i]=new Random().nextInt(31);
        }

        Arrays.sort(result);

        for(int i=1;i<numberOfPrices;i++)
        {
            result[i]+=i;
        }

        return result;
    }

    private BigDecimal[] generatePricesValue()
    {
        BigDecimal[] result=new BigDecimal[numberOfPrices];

        for (int i = 0 ;i<numberOfPrices;i++)
        {
            result[i]=BigDecimal.valueOf((new Random().nextDouble())*100)
                    .setScale(2, RoundingMode.HALF_UP);

        }

        Arrays.sort(result, Collections.reverseOrder());


        for(int i=1;i<numberOfPrices;i++)
        {
            if(result[i-1].equals(result[i]))
            {
                result[i] = result[i].add(new BigDecimal(new Random().nextDouble()).setScale(2, RoundingMode.HALF_UP));
            }
        }

        Arrays.sort(result, Collections.reverseOrder());

        return result;
    }


    public void generatePrices(SessionFactory sessionFactory)
    {
        double[] studentDiscount= generateDiscounts();
        int[] daysToConference=generateDaysToConference();
        BigDecimal[] priceValue=generatePricesValue();

        for (int i=0;i<numberOfPrices;i++)
        {
            PricesEntity.createPrice(studentDiscount[i],daysToConference[i],priceValue[i],conferenceDayID,sessionFactory);
        }



    }


}
