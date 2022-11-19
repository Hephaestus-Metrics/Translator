package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.metrics.Metric;

import java.util.List;

/**
 * Most generic class for metrics
 */
public abstract class AbstractQueryResult {

    private final ResultType type;
    private final String tag;

    protected AbstractQueryResult(ResultType type, String tag) {
        this.type = type;
        this.tag = tag;
    }

    public abstract List<Metric> getMetrics();

    public ResultType getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }
}
