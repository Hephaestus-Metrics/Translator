package io.github.hephaestusmetrics.model.metrics.complex;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.promql.complexqueries.ComplexMetricResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorMetricTest {
    private ComplexMetricResult complexMetricResult;

    private final static double TIMESTAMP = 1659261600;
    private final static double VALUE = 45;

    @BeforeEach
    void setupMetricResult() {
        complexMetricResult = new ComplexMetricResult();
        complexMetricResult.setMetric(Collections.emptyMap());
        complexMetricResult.setResultType(ResultTypes.VECTOR);
    }

    @Test
    void normalVectorMetricTest() {
        //given
        String[] value = {
                String.valueOf(TIMESTAMP), String.valueOf(VALUE)
        };
        complexMetricResult.setValue(value);

        //when
        VectorMetric vectorMetric = new VectorMetric(complexMetricResult);

        //then
        assertEquals(TIMESTAMP, vectorMetric.getTimestamp(), 0.1);
        assertEquals(VALUE, vectorMetric.getValue(), 0.1);

        assertEquals(ResultTypes.VECTOR, vectorMetric.getType());
    }

    @Test
    void emptyValueArrayTest() {
        //given
        String[] value = {};
        complexMetricResult.setValue(value);

        //when
        VectorMetric vectorMetric = new VectorMetric(complexMetricResult);

        //then
        assertEquals(Double.NaN, vectorMetric.getTimestamp());
        assertEquals(Double.NaN, vectorMetric.getValue());
    }
}
