package io.github.hephaestusmetrics.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultTypesTest {
    @Test
    public void numberOfEnumTypes() {
        //given
        ResultTypes[] resultTypes = ResultTypes.values();

        //when
        int numberOfResultTypes = resultTypes.length;

        //then
        assertEquals(4, numberOfResultTypes);
    }

    @Test
    public void enumTypes() {
        //given
        List<ResultTypes> resultTypes = Arrays.asList(ResultTypes.values());

        //when
        boolean scalarPresent = resultTypes.contains(ResultTypes.SCALAR);
        boolean stringPresent = resultTypes.contains(ResultTypes.STRING);
        boolean matrixPresent = resultTypes.contains(ResultTypes.MATRIX);
        boolean vectorPresent = resultTypes.contains(ResultTypes.VECTOR);

        //then
        assertTrue(scalarPresent);
        assertTrue(stringPresent);
        assertTrue(matrixPresent);
        assertTrue(vectorPresent);
    }
}
