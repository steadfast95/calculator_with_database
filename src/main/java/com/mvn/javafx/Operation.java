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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
