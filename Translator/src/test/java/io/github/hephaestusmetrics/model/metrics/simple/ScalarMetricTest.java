package io.github.hephaestusmetrics.model.metrics.simple;

import io.github.hephaestusmetrics.model.ResultTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScalarMetricTest {
    private final static double TIMESTAMP = 1659261600;
    private final static double VALUE = 45;

    @Test
    public void normalScalarMetricTest() {
        //given
        String[] values = {String.valueOf(TIMESTAMP), String.valueOf(VALUE)};

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values);

        //then
        assertEquals(TIMESTAMP, scalarMetric.getTimestamp(), 0.1);
        assertEquals(VALUE, scalarMetric.getValue(), 0.1);

        assertEquals(ResultTypes.SCALAR, scalarMetric.getType());
    }

    @Test
    public void emptyValuesArrayTest() {
        //given
        String[] values = {};

        //when
        ScalarMetric scalarMetric = new ScalarMetric(values);

        //then
        assertEquals(Double.NaN, scalarMetric.getTimestamp(), 0.1);
        assertEquals(Double.NaN, scalarMetric.getValue(), 0.1);
    }
}
