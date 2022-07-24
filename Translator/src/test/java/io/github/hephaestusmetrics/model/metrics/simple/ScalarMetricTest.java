package io.github.hephaestusmetrics.model.metrics.simple;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.utilities.ValueConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScalarMetricTest {
    @Test
    public void normalScalarMetric(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        double value = 45;
        String[] values = { String.valueOf(timestamp), String.valueOf(value) };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(timestamp, scalarMetric.getTimestamp(), 0.1);
        assertEquals(value, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void unparsableTimestamp(){
        //given
        String unparsableTimestamp = "12abc34";
        double value = 45;
        String[] values = { unparsableTimestamp, String.valueOf(value) };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(Double.NaN, scalarMetric.getTimestamp(), 0.1);
        assertEquals(value, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void unparsableValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String unparsableValue = "12abc34";
        String[] values = { String.valueOf(timestamp), unparsableValue };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(timestamp, scalarMetric.getTimestamp(), 0.1);
        assertEquals(Double.NaN, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void unparsableTimestampAndValue(){
        //given
        String unparsableTimestamp = "12abc34";
        String unparsableValue = "12abc34";
        String[] values = { unparsableTimestamp, unparsableValue };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(Double.NaN, scalarMetric.getTimestamp(), 0.1);
        assertEquals(Double.NaN, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void positiveInfinityValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "inf";
        String[] values = { String.valueOf(timestamp), value };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(timestamp, scalarMetric.getTimestamp(), 0.1);
        assertEquals(Double.POSITIVE_INFINITY, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void negativeInfinityValue(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        String value = "-inf";
        String[] values = { String.valueOf(timestamp), value };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.SCALAR);

        //then
        assertEquals(timestamp, scalarMetric.getTimestamp(), 0.1);
        assertEquals(Double.NEGATIVE_INFINITY, scalarMetric.getValue(), 0.1);
    }

    @Test
    public void wrongResultType(){
        //given
        double timestamp = 1659261600; //unix timestamp 1659261600 is 2022-07-31 12:00:00
        double value = 45;
        String[] values = { String.valueOf(timestamp), String.valueOf(value) };

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values, ResultTypes.MATRIX);

        //then
        assertEquals(timestamp, scalarMetric.getTimestamp(), 0.1);
        assertEquals(value, scalarMetric.getValue(), 0.1);
        assertEquals(ResultTypes.SCALAR, scalarMetric.getType());
    }
}
