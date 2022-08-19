package io.github.hephaestusmetrics.model.promql.complexqueries;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.metrics.complex.ComplexMetricTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ComplexMetricResultTest {
    ComplexMetricResult complexMetricResult;

    @BeforeEach
    void setMetricResult() {
        complexMetricResult = new ComplexMetricResult();
        complexMetricResult.setMetric(new HashMap<>() {{
            put("__name__", "metricName");
        }});
        complexMetricResult.setValue(new String[0]);
        complexMetricResult.setValues(new String[0][0]);
    }

    @ParameterizedTest(name = "[{index}] shouldGetMetricObject: {0}")
    @MethodSource("rightResultTypes")
    void shouldGetMetricObject(ResultTypes resultType) {
        //given
        complexMetricResult.setResultType(resultType);

        //when
        ComplexMetricTemplate complexMetric = complexMetricResult.toMetricObject();

        //then
        assertEquals(resultType, complexMetric.getType());
    }

    @ParameterizedTest(name = "[{index}] shouldNotGetMetricObject: {0}")
    @MethodSource("wrongResultTypes")
    void shouldNotGetMetricObject(ResultTypes resultType) {
        //given
        complexMetricResult.setResultType(resultType);

        //when
        ComplexMetricTemplate complexMetric = complexMetricResult.toMetricObject();

        //then
        assertNull(complexMetric);
    }

    private static Stream<ResultTypes> rightResultTypes() {
        return Stream.of(
                ResultTypes.MATRIX,
                ResultTypes.VECTOR
        );
    }

    private static Stream<ResultTypes> wrongResultTypes() {
        return Stream.of(
                ResultTypes.SCALAR,
                ResultTypes.STRING
        );
    }
}
