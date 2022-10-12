package io.github.hephaestusmetrics.model.promql.simplequeries;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.metrics.simple.ScalarMetric;
import io.github.hephaestusmetrics.model.metrics.simple.SimpleMetricTemplate;
import io.github.hephaestusmetrics.model.metrics.simple.StringMetric;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * POJO for JSON conversion
 */
public class SimpleData implements Serializable {

    private ResultTypes resultType;
    private String[] result;


    public SimpleData() {
        //required by Jackson
    }

    public ResultTypes getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = ResultTypes.valueOf(resultType.toUpperCase());
    }

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "resultType=" + resultType +
                ", result=" + Arrays.toString(result) +
                '}';
    }

    public ArrayList<SimpleMetricTemplate> getMetricObjects() {
        switch (resultType) {
            case STRING:
                return new ArrayList<>(List.of(new StringMetric(result)));
            case SCALAR:
                return new ArrayList<>(List.of(new ScalarMetric(result)));
            default:
                break;

        }
        return null;
    }

}
