package com.mvn.javafx;

import java.sql.SQLException;

import static com.mvn.javafx.Enum.operations.*;

public class Model {

    public float calculation(float a, float b, String operation) throws SQLException, ClassNotFoundException {
        if (PLUS.toString().equals(operation)) {
            return a + b;
        } else if (MINUS.toString().equals(operation)) {
            return a - b;
        } else if (MULTIPLICATION.toString().equals(operation)) {
            return a * b;
        } else if (DIVISION.toString().equals(operation)) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        }
        System.out.println("N/A");
        return (float) 0.0;
    }
}
