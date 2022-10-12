package io.github.hephaestusmetrics.model.metrics.complex;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.promql.complexqueries.ComplexMetricResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixMetricTest {
    private ComplexMetricResult complexMetricResult;

    private final static double TIMESTAMP1 = 1659261600;
    private final static double VALUE1 = 45;

    private final static double TIMESTAMP2 = 1659261620;
    private final static double VALUE2 = 70;

    @BeforeEach
    void setupMetricResult() {
        complexMetricResult = new ComplexMetricResult();
        complexMetricResult.setMetric(Collections.emptyMap());
        complexMetricResult.setResultType(ResultTypes.MATRIX);
    }

    @Test
    void normalMatrixMetricTest() {
        //given
        String[][] values = {
                {String.valueOf(TIMESTAMP1), String.valueOf(VALUE1)},
                {String.valueOf(TIMESTAMP2), String.valueOf(VALUE2)}
        };
        complexMetricResult.setValues(values);

        //when
        MatrixMetric matrixMetric = new MatrixMetric(complexMetricResult);

        //then
        assertEquals(TIMESTAMP1, matrixMetric.getTimestamps().get(0), 0.1);
        assertEquals(VALUE1, matrixMetric.getValues().get(0), 0.1);

        assertEquals(TIMESTAMP2, matrixMetric.getTimestamps().get(1), 0.1);
        assertEquals(VALUE2, matrixMetric.getValues().get(1), 0.1);

        assertEquals(ResultTypes.MATRIX, matrixMetric.getType());
    }

    @Test
    void emptyValuesArrayTest() {
        //given
        String[][] values = {};
        complexMetricResult.setValues(values);

        //when
        MatrixMetric matrixMetric = new MatrixMetric(complexMetricResult);

        //then
        assertEquals(Collections.emptyList(), matrixMetric.getTimestamps());
        assertEquals(Collections.emptyList(), matrixMetric.getValues());
    }
}
