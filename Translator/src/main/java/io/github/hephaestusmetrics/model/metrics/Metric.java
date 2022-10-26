package io.github.hephaestusmetrics.model.metrics;

import io.github.hephaestusmetrics.model.ResultType;

import java.util.Map;

public class Metric {

    private final String queryTag;
    private final ResultType resultType;
    private final Map<String, String> labels;
    private final double timestamp;
    private final String valueString;
    private final Double parsed;

    public Metric(String queryTag, ResultType resultType, Map<String, String> labels, double timestamp, String valueString) {
        this.queryTag = queryTag;
        this.resultType = resultType;
        this.labels = labels;
        this.timestamp = timestamp;
        this.valueString = valueString;
        this.parsed = parseOrNull(valueString);
    }

    public Metric(String queryTag, ResultType resultType, Map<String, String> labels, double timestamp, Double value) {
        this.queryTag = queryTag;
        this.resultType = resultType;
        this.labels = labels;
        this.timestamp = timestamp;
        this.valueString = value.toString();
        this.parsed = value;
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
        return parsed;
    }

    public String getValueString() {
        return valueString;
    }

}
