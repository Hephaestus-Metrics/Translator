package io.github.hephaestusmetrics.model.promql.simplequeries;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.metrics.simple.SimpleMetricTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleDataTest {
    private final static double TIMESTAMP = 1659261600;
    private final static double VALUE = 45;

    @ParameterizedTest(name = "[{index}] shouldGetMetricObjects: {0}")
    @MethodSource("rightResultTypes")
    void shouldGetMetricObjects(ResultTypes resultType) {
        //given
        SimpleData simpleData = new SimpleData();
        simpleData.setResult(new String[]{
                String.valueOf(TIMESTAMP), String.valueOf(VALUE)
        });
        simpleData.setResultType(resultType.toString());

        //when
        ArrayList<SimpleMetricTemplate> metricObjects = simpleData.getMetricObjects();

        //then
        assertEquals(1, metricObjects.size());
        assertEquals(resultType, metricObjects.get(0).getType());
    }

    @ParameterizedTest(name = "[{index}] shouldNotGetMetricObjects: {0}")
    @MethodSource("wrongResultTypes")
    void shouldNotGetMetricObjects(ResultTypes resultType) {
        //given
        SimpleData simpleData = new SimpleData();
        simpleData.setResult(new String[]{
                String.valueOf(TIMESTAMP), String.valueOf(VALUE)
        });
        simpleData.setResultType(resultType.toString());

        //when
        ArrayList<SimpleMetricTemplate> metricObjects = simpleData.getMetricObjects();

        //then
        assertNull(metricObjects);
    }

    private static Stream<ResultTypes> rightResultTypes(){
        return Stream.of(
          ResultTypes.SCALAR,
          ResultTypes.STRING
        );
    }

    private static Stream<ResultTypes> wrongResultTypes(){
        return Stream.of(
                ResultTypes.MATRIX,
                ResultTypes.VECTOR
        );
    }
}
