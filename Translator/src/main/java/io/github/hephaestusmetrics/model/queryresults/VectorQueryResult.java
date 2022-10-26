package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.metrics.Metric;

import java.util.List;

public class VectorQueryResult extends AbstractQueryResult {

    private final List<Metric> metrics;

    public VectorQueryResult(String tag, List<Metric> metrics) {
        super(ResultType.STRING, tag);
        this.metrics = metrics;
    }

    public List<Metric> getAll() {
        return metrics;
    }

    public Metric get(int i) {
        return metrics.get(i);
    }

    public int getSize() {
        return metrics.size();
    }

}
