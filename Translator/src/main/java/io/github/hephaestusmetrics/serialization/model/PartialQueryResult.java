package io.github.hephaestusmetrics.serialization.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.hephaestusmetrics.model.ResultType;
import io.github.hephaestusmetrics.model.queryresults.AbstractQueryResult;

/**
 * Partially parsed query result
 */
public class PartialQueryResult extends AbstractQueryResult {

    private final ArrayNode result;

    public PartialQueryResult(String tag, ResultType type, ArrayNode result) {
        super(type, tag);
        this.result = result;
    }

    public ArrayNode getResult() {
        return result;
    }

}
