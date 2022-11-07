package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.metrics.Metric;

public class StringQueryResult extends AbstractQueryResult {

    private final Metric metric;

    public StringQueryResult(String tag, double timestamp, String valueString) {
        super(ResultType.STRING, tag);
        this.metric = new Metric(tag, ResultType.STRING, null, timestamp, valueString);
    }

    public Metric get() {
        return metric;
    }

}