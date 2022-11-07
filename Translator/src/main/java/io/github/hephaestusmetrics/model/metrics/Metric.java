package io.github.hephaestusmetrics.model.metrics;

import io.github.hephaestusmetrics.model.ResultType;

import java.util.Map;

public class Metric {

    public final String queryTag;
    public final ResultType resultType;
    public final Map<String, String> labels;
    public final double timestamp;
    public final String valueString;
    public final Double value;

    public Metric(String queryTag, ResultType resultType, Map<String, String> labels, double timestamp, String valueString) {
        this.queryTag = queryTag;
        this.resultType = resultType;
        this.labels = labels;
        this.timestamp = timestamp;
        this.valueString = valueString;
        this.value = parseOrNull(valueString);
    }

    public Metric(String queryTag, ResultType resultType, Map<String, String> labels, double timestamp, Double value) {
        this.queryTag = queryTag;
        this.resultType = resultType;
        this.labels = labels;
        this.timestamp = timestamp;
        this.valueString = value.toString();
        this.value = value;
    }

    private static Double parseOrNull(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getQueryTag() {
        return queryTag;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public String getValueString() {
        return valueString;
    }

}
