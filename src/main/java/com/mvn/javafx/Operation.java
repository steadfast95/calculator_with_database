package com.mvn.javafx;

public class Operation {
    private String operation;
    private Integer id;

    public Operation(Integer id, String operation) {
        this.operation = operation;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

}
