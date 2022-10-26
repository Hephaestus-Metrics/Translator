package io.github.hephaestusmetrics.model.queryresults;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RawQueryResult {

    private final String tag;
    private final String metric; // metric value as string, as returned by Prometheus

    @JsonCreator
    public RawQueryResult(@JsonProperty("tag") String tag, @JsonProperty("metric") String metric) {
        this.tag = tag;
        this.metric = metric;
    }

    public String getTag() {
        return tag;
    }

    public String getMetric() {
        return metric;
    }

}

