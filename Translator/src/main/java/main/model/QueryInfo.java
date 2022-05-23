package main.model;

import java.util.List;

public class QueryInfo {

    private List<String> queries;

    public QueryInfo() {
    }

    public QueryInfo(List<String> queries) {
        this.queries = queries;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }
}
