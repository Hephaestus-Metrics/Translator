package io.github.hephaestusmetrics.model.metrics.simple;

import io.github.hephaestusmetrics.model.ResultTypes;
import io.github.hephaestusmetrics.model.metrics.MetricTemplate;

/**
 * Abstract class for simple metrics: STRING, SCALAR
 */
public abstract class SimpleMetricTemplate extends MetricTemplate {

    public SimpleMetricTemplate(ResultTypes type) {
        super(type);
    }

    public abstract void getDataFromResult();


    @Override
    public abstract String toString();

}
