package com.mvn.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sqlite.JDBC;

import java.io.IOException;
import java.sql.*;

public class DbHandler {
    public static Statement statmt;
    public static ResultSet resSet;
        private static final String CON_STR = "jdbc:sqlite:D:\\IntelliJ IDEA 2018.2.4\\project\\comjavafxmvn\\src\\main\\resources\\dbf\\sqlite.s3db";
//    private static final String CON_STR = "jdbc:sqlite:./ssqlite.s3db";
    private static DbHandler instance = null;
    public ObservableList<Operation> list = FXCollections.observableArrayList();

    public static synchronized DbHandler getInstance() throws SQLException, IOException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    public DbHandler() throws SQLException, IOException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = connection.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Calculation' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'operation' TEXT );");
        System.out.println("Таблица создана или уже существует.");
    }

    public void WriteDB(String op) throws SQLException {
        statmt.execute("INSERT INTO 'Calculation' ('operation') VALUES (" + "'" + op + "'" + "); ");
        System.out.println("Таблица заполнена");
    }

    public void ReadDB() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM Calculation ORDER BY id DESC LIMIT 20");
        while (resSet.next()) {
            int id = resSet.getInt("id");
            String operation = resSet.getString("operation");
            System.out.println("ID = " + id);
            System.out.println("operation = " + operation);
            System.out.println();
        }
        System.out.println("Таблица выведена");
    }

    public void printTable(TableColumn tableColumn, TableColumn tableColumn2, TableView tableView) {
        try {
            ResultSet rs = statmt.executeQuery("SELECT * FROM Calculation ORDER BY id DESC LIMIT 20");
            while (rs.next()) {
                list.add(new Operation(rs.getInt("id"), rs.getString("operation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableColumn.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("id"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<Operation, String>("operation"));
        tableView.setItems(list);
    }

    public void CloseDB() throws ClassNotFoundException, SQLException {
        connection.close();
        statmt.close();
        resSet.close();
        System.out.println("Соединения закрыты");
    }
}


