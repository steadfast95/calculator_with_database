package com.mvn.javafx;

import java.sql.SQLException;

public class Model {
    public float calculation(float a, float b, String operation) throws SQLException, ClassNotFoundException {
        if ("+".equals(operation)) {
            return a + b;
        } else if ("-".equals(operation)) {
            return a - b;
        } else if ("*".equals(operation)) {
            return a * b;
        } else if ("/".equals(operation)) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        }
        System.out.println("N/A");
        return (float) 0.0;
    }
}
