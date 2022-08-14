package io.github.hephaestusmetrics.model.metrics.complex;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.promql.complexqueries.ComplexMetricResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexMetricTemplateTest {
    private ComplexMetricResult complexMetricResult;

    @BeforeEach
    void setupMetricResult() {
        complexMetricResult = new ComplexMetricResult();
        complexMetricResult.setMetric(new HashMap<>() {{
            put("__name__", "metricName");
            put("label", "metricLabel");
        }});
        complexMetricResult.setResultType(ResultTypes.VECTOR);
        complexMetricResult.setValue(new String[0]);
    }

    @Test
    void complexMetricNameTest() {
        //given
        //nothing to do

        //when
        VectorMetric vectorMetric = new VectorMetric(complexMetricResult);

        //then
        assertEquals("metricName", vectorMetric.getName());
    }

    @Test
    void complexMetricLabelsTest() {
        //given
        //nothing to do

        //when
        VectorMetric vectorMetric = new VectorMetric(complexMetricResult);
        Map<String, String> labels = vectorMetric.getLabels();

        //then
        assertEquals(2, labels.size());
        assertEquals("metricName", labels.get("__name__"));
        assertEquals("metricLabel", labels.get("label"));
    }
}
