package io.github.hephaestusmetrics.model.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValueConverterTest {
    @Test
    public void normalConvert(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        double value = 45;
        String[] values = { String.valueOf(timestamp), String.valueOf(value) };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(timestamp, convertedValues[0], 0.1);
        assertEquals(value, convertedValues[1], 0.1);
    }

    @Test
    public void unparsableTimestamp(){
        //given
        String unparsableTimestamp = "12abc34";
        double value = 45;
        String[] values = { unparsableTimestamp, String.valueOf(value) };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(Double.NaN, convertedValues[0], 0.1);
        assertEquals(value, convertedValues[1], 0.1);
    }

    @Test
    public void unparsableValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String unparsableValue = "12abc34";
        String[] values = { String.valueOf(timestamp), unparsableValue };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(timestamp, convertedValues[0], 0.1);
        assertEquals(Double.NaN, convertedValues[1], 0.1);
    }

    @Test
    public void unparsableTimestampAndValue(){
        //given
        String unparsableTimestamp = "12abc34";
        String unparsableValue = "12abc34";
        String[] values = { unparsableTimestamp, unparsableValue };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(Double.NaN, convertedValues[0], 0.1);
        assertEquals(Double.NaN, convertedValues[1], 0.1);
    }

    @Test
    public void positiveInfinityValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "inf";
        String[] values = { String.valueOf(timestamp), value };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(timestamp, convertedValues[0], 0.1);
        assertEquals(Double.POSITIVE_INFINITY, convertedValues[1], 0.1);
    }

    @Test
    public void negativeInfinityValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "-inf";
        String[] values = { String.valueOf(timestamp), value };

        //when
        double[] convertedValues = ValueConverter.convert(values);

        //then
        assertEquals(timestamp, convertedValues[0], 0.1);
        assertEquals(Double.NEGATIVE_INFINITY, convertedValues[1], 0.1);
    }
}
