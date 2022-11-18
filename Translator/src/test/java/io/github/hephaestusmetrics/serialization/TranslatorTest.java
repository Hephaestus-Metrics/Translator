package io.github.hephaestusmetrics.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.queryresults.AbstractQueryResult;
import io.github.hephaestusmetrics.model.queryresults.RawQueryResult;
import io.github.hephaestusmetrics.model.queryresults.VectorQueryResult;
import io.github.hephaestusmetrics.serialization.model.PartialQueryResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslatorTest {
    private final Translator translator = new Translator();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String TAG = "GIVEN_TAG";
    private static ResultType RESULT_TYPE;
    private static String RESULT = "";
    private static String METRIC = "";
    private static String JSON_STRING = "";

    private static final String VECTOR_TYPE = "vector";
    private static final String VECTOR_VALUE = "[125,\"15\"]";

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ParameterizedTranslatorTests {
        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void partiallyDeserializeRawMetricStringTest(String resultType, String value) throws JsonProcessingException {
            //given
            prepareStrings(resultType, value);

            //when
            PartialQueryResult partialQueryResult = translator.partiallyDeserialize(JSON_STRING);

            //then
            assertEquals(RESULT_TYPE, partialQueryResult.getType());
            assertEquals(TAG, partialQueryResult.getTag());
            assertEquals(mapper.readTree(RESULT), partialQueryResult.getResult());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void partiallyDeserializeRawQueryResultTest(String resultType, String value) throws JsonProcessingException {
            //given
            prepareStrings(resultType, value);
            RawQueryResult rawQueryResult = new RawQueryResult(TAG, METRIC);

            //when
            PartialQueryResult partialQueryResult = translator.partiallyDeserialize(rawQueryResult);

            //then
            assertEquals(ResultType.fromString(resultType), partialQueryResult.getType());
            assertEquals(TAG, partialQueryResult.getTag());
            assertEquals(mapper.readTree(RESULT), partialQueryResult.getResult());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void partiallyDeserializeTree(String resultType, String value) throws JsonProcessingException {
            //given
            prepareStrings(resultType, value);

            //when
            PartialQueryResult partialQueryResult = translator.partiallyDeserialize(mapper.readTree(JSON_STRING));

            //then
            assertEquals(RESULT_TYPE, partialQueryResult.getType());
            assertEquals(TAG, partialQueryResult.getTag());
            assertEquals(mapper.readTree(RESULT), partialQueryResult.getResult());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void partiallyDeserializeTagAndTree(String resultType, String value) throws JsonProcessingException {
            //given
            prepareStrings(resultType, value);

            //when
            PartialQueryResult partialQueryResult = translator.partiallyDeserialize(TAG, mapper.readTree(METRIC));

            //then
            assertEquals(RESULT_TYPE, partialQueryResult.getType());
            assertEquals(TAG, partialQueryResult.getTag());
            assertEquals(mapper.readTree(RESULT), partialQueryResult.getResult());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void parseResultRawMetricStringTest(String resultType, String value) {
            //given
            prepareStrings(resultType, value);

            //when
            AbstractQueryResult abstractQueryResult = translator.parseResult(JSON_STRING);

            //then
            assertEquals(RESULT_TYPE, abstractQueryResult.getType());
            assertEquals(TAG, abstractQueryResult.getTag());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void pareResultRawQueryResultTest(String resultType, String value) {
            //given
            prepareStrings(resultType, value);
            RawQueryResult rawQueryResult = new RawQueryResult(TAG, METRIC);

            //when
            AbstractQueryResult abstractQueryResult = translator.parseResult(rawQueryResult);

            //then
            assertEquals(RESULT_TYPE, abstractQueryResult.getType());
            assertEquals(TAG, abstractQueryResult.getTag());
        }

        @ParameterizedTest
        @MethodSource("resultTypesSource")
        void parseResultPartialQueryResultTest(String resultType, String value) throws JsonProcessingException {
            //given
            prepareStrings(resultType, value);
            PartialQueryResult partialQueryResult = new PartialQueryResult(TAG, RESULT_TYPE, (ArrayNode) mapper.readTree(RESULT));

            //when
            AbstractQueryResult abstractQueryResult = translator.parseResult(partialQueryResult);

            //then
            assertEquals(RESULT_TYPE, abstractQueryResult.getType());
            assertEquals(TAG, abstractQueryResult.getTag());
        }

        private Stream<Arguments> resultTypesSource() {
            return Stream.of(
                    Arguments.of(VECTOR_TYPE, VECTOR_VALUE)
            );
        }
    }
    
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ParseVectorResultTests {
        @BeforeAll
        void prepareNestedStrings() {
            prepareStrings(VECTOR_TYPE, VECTOR_VALUE);
        }

        @Test
        void parseVectorResultRawMetricStringTest() {
            //when
            VectorQueryResult vectorQueryResult = translator.parseVectorResult(JSON_STRING);

            //then
            assertEquals(RESULT_TYPE, vectorQueryResult.getType());
            assertEquals(TAG, vectorQueryResult.getTag());
            assertEquals(1, vectorQueryResult.getSize());
        }

        @Test
        void parseVectorResultRawQueryResultTest() {
            //given
            RawQueryResult rawQueryResult = new RawQueryResult(TAG, METRIC);

            //when
            VectorQueryResult vectorQueryResult = translator.parseVectorResult(rawQueryResult);

            //then
            assertEquals(RESULT_TYPE, vectorQueryResult.getType());
            assertEquals(TAG, vectorQueryResult.getTag());
            assertEquals(1, vectorQueryResult.getSize());
        }

        @Test
        void parseVectorResultPartialQueryResult() throws JsonProcessingException {
            //given
            PartialQueryResult partialQueryResult = new PartialQueryResult(TAG, RESULT_TYPE, (ArrayNode) mapper.readTree(RESULT));

            //when
            VectorQueryResult vectorQueryResult = translator.parseVectorResult(partialQueryResult);

            //then
            assertEquals(RESULT_TYPE, vectorQueryResult.getType());
            assertEquals(TAG, vectorQueryResult.getTag());
            assertEquals(1, vectorQueryResult.getSize());
        }
    }

    private void prepareStrings(String resultType, String value) {
        RESULT_TYPE = ResultType.fromString(resultType);
        RESULT = "[{\"metric\":{}, \"value\":" + value + "}]";
        METRIC = "{\"data\":{\"resultType\":\"" + resultType + "\",\"result\":" + RESULT + "}}";
        JSON_STRING = "{\"tag\":\"" + TAG + "\",\"metric\":" + METRIC + "}";
    }
}
