package io.github.hephaestusmetrics.model.metrics.simple;

import io.github.hephaestusmetrics.model.ResultTypes;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringMetricTest {
    @Test
    public void normalStringMetric(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "45";
        String[] values = { String.valueOf(timestamp), value };

        //when
        StringMetric stringMetric = new StringMetric(values, ResultTypes.STRING);

        //then
        assertEquals(value, stringMetric.getValue());
        assertEquals(timestamp, stringMetric.getTimestamp(), 0.1);
    }

    @Test
    public void unparsableTimestamp(){
        //given
        String unparsableTimestamp = "12abc34";
        String value = "45";
        String[] values = { unparsableTimestamp, value };

        //when
        StringMetric stringMetric = new StringMetric(values, ResultTypes.STRING);

        //then
        assertEquals(value, stringMetric.getValue());
        assertEquals(Double.NaN, stringMetric.getTimestamp(), 0.1);
    }

    @Test
    public void wrongResultType(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "45";
        String[] values = { String.valueOf(timestamp), value };

        //when
        StringMetric stringMetric = new StringMetric(values, ResultTypes.MATRIX);

        //then
        assertEquals(value, stringMetric.getValue());
        assertEquals(timestamp, stringMetric.getTimestamp(), 0.1);
        assertEquals(ResultTypes.STRING, stringMetric.getType());
    }
}
