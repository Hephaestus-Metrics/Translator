package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.metrics.Metric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VectorQueryResultTest {
    private static final String TAG = "QUERY_TAG";
    private static final ResultType RESULT_TYPE = ResultType.VECTOR;
    private static final Map<String, String> LABELS = new HashMap<>(){{
        put("LABEL1", "VALUE1");
    }};
    private static final Double TIMESTAMP = 1659261600D;
    private static final Double VALUE = 125D;
    private final Metric metric = new Metric(TAG, RESULT_TYPE, LABELS, TIMESTAMP, VALUE);;

    @Test
    public void sanityTest() {
        //given
        List<Metric> metrics = new ArrayList<>(){{
            add(metric);
        }};

        //when
        VectorQueryResult vectorQueryResult = new VectorQueryResult(TAG, metrics);

        //then
        assertEquals(TAG, vectorQueryResult.getTag());
        assertEquals(1, vectorQueryResult.getSize());
        assertEquals(metrics, vectorQueryResult.getAll());

        assertEquals(TAG, vectorQueryResult.get(0).getQueryTag());
        assertEquals(RESULT_TYPE, vectorQueryResult.get(0).getResultType());
        assertEquals(LABELS, vectorQueryResult.get(0).getLabels());
        assertEquals(TIMESTAMP, vectorQueryResult.get(0).getTimestamp());
        assertEquals(VALUE.toString(), vectorQueryResult.get(0).getValueString());
        assertEquals(VALUE, vectorQueryResult.get(0).getValue());
    }
}
