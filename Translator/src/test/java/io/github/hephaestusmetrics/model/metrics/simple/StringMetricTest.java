package io.github.hephaestusmetrics.model.metrics.simple;

import io.github.hephaestusmetrics.model.ResultTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringMetricTest {
    private final static double TIMESTAMP = 1659261600;
    private final static String VALUE = "45";

    private final static String UNPARSABLE = "12abc45";

    @Test
    public void normalStringMetricTest(){
        //given
        String[] values = { String.valueOf(TIMESTAMP), VALUE };

        //when
        StringMetric stringMetric = new StringMetric(values);

        //then
        assertEquals(TIMESTAMP, stringMetric.getTimestamp(), 0.1);
        assertEquals(VALUE, stringMetric.getValue());

        assertEquals(ResultTypes.STRING, stringMetric.getType());
    }

    @Test
    public void emptyValuesArrayTest(){
        //given
        String[] values = {};

        //when
        StringMetric stringMetric = new StringMetric(values);

        //then
        assertEquals(0.0, stringMetric.getTimestamp(), 0.1);
        assertNull(stringMetric.getValue());

        assertEquals(ResultTypes.STRING, stringMetric.getType());
    }

    @Test
    public void unparsableTimestampTest(){
        //given
        String[] values = { UNPARSABLE, VALUE };

        //when
        StringMetric stringMetric = new StringMetric(values);

        //then
        assertEquals(Double.NaN, stringMetric.getTimestamp(), 0.1);
        assertEquals(VALUE, stringMetric.getValue());
    }

}
