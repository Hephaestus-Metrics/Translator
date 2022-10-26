package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.metrics.Metric;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixQueryResult extends AbstractQueryResult {

    private final List<List<Metric>> metrics;

    public MatrixQueryResult(String tag, List<List<Metric>> metrics) {
        super(ResultType.STRING, tag);
        this.metrics = metrics;
    }

    public List<List<Metric>> getAll() {
        return metrics;
    }

    public List<Metric> getAllFlattened() {
        return metrics.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Metric> get(int i) {
        return metrics.get(i);
    }
    
    public int getSize() {
        return metrics.size();
    }

}
