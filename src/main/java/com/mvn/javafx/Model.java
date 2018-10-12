package com.mvn.javafx;

import com.mvn.javafx.Enum.operations;

import java.sql.SQLException;

public class Model {

    public float calculation(float a, float b, String operation) throws SQLException, ClassNotFoundException {
        if (operations.PLUS.toString().equals(operation)) {
            return a + b;
        } else if (operations.MINUS.toString().equals(operation)) {
            return a - b;
        } else if (operations.MULTIPLICATION.toString().equals(operation)) {
            return a * b;
        } else if (operations.DIVISION.toString().equals(operation)) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        }
        System.out.println("N/A");
        return (float) 0.0;
    }
}
