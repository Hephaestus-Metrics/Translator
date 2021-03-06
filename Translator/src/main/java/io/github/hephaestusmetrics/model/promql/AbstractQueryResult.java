package io.github.hephaestusmetrics.model.promql;

import io.github.hephaestusmetrics.model.metrics.MetricTemplate;

import java.util.ArrayList;

public abstract class AbstractQueryResult {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public abstract String toString();

    /**
     * @return Metrics received from query
     */
    public abstract <T extends MetricTemplate> ArrayList<T> getMetricObjects();
}