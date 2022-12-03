package io.github.hephaestusmetrics.model;

public enum ResultType {
    VECTOR,
    MATRIX,
    STRING,
    SCALAR;

    public static ResultType fromString(String s) {
        if (s.equalsIgnoreCase("string")) {
            return STRING;
        } else if (s.equalsIgnoreCase("scalar")) {
            return SCALAR;
        } else if (s.equalsIgnoreCase("vector")) {
            return VECTOR;
        } else if (s.equalsIgnoreCase("matrix")) {
            return MATRIX;
        } else {
            throw new RuntimeException("Result type " + s + " not recognized");
        }
    }

}
