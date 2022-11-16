package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScalarQueryResultTest {
    @Test
    public void sanityTestWithStringValue() {
        //given
        String tag = "TAG";
        Double timestamp = 1659261600D;
        String stringValue = "125";

        //when
        ScalarQueryResult scalarQueryResult = new ScalarQueryResult(tag, timestamp, stringValue);

        //then
        assertEquals(tag, scalarQueryResult.getTag());
        assertEquals(tag, scalarQueryResult.get().getQueryTag());
        assertEquals(timestamp, scalarQueryResult.get().getTimestamp());
        assertEquals(stringValue, scalarQueryResult.get().getValueString());
        assertEquals(Double.parseDouble(stringValue), scalarQueryResult.get().getValue());
        assertEquals(ResultType.SCALAR, scalarQueryResult.get().getResultType());
        assertNull(scalarQueryResult.get().getLabels());
    }

    @Test
    public void sanityTestWithDoubleValue() {
        //given
        String tag = "TAG";
        Double timestamp = 1659261600D;
        Double doubleValue = 125D;

        //when
        ScalarQueryResult scalarQueryResult = new ScalarQueryResult(tag, timestamp, doubleValue);

        //then
        assertEquals(tag, scalarQueryResult.getTag());
        assertEquals(tag, scalarQueryResult.get().getQueryTag());
        assertEquals(timestamp, scalarQueryResult.get().getTimestamp());
        assertEquals(doubleValue.toString(), scalarQueryResult.get().getValueString());
        assertEquals(doubleValue, scalarQueryResult.get().getValue());
        assertEquals(ResultType.SCALAR, scalarQueryResult.get().getResultType());
        assertNull(scalarQueryResult.get().getLabels());
    }
}
