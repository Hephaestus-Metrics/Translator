package io.github.hephaestusmetrics.model.queryresults;

import io.github.hephaestusmetrics.model.ResultType;

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

    public ResultType getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }
}
