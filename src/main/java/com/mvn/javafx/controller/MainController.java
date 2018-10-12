package com.mvn.javafx.controller;

import com.mvn.javafx.DbHandler;
import com.mvn.javafx.Model;
import com.mvn.javafx.Operation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.sql.SQLException;

public class MainController  {

    @FXML
    private Text output;
    private float num1=0;
    private float num2=0;
    private boolean begin = true;
    private String operator = "";
    private Model model = new Model();
    String buffer = "";

    @FXML
    TabableView tblVw;
    @FXML
    TableColumn<Operation, String> column_id;
    @FXML
    TableColumn<Operation, String> column_oper;
    @FXML
    private TableView<Operation> tableView;
    @FXML
    private void procNum(ActionEvent event){
        if(begin){
            output.setText("");
            begin=false;
        }
        String val = ((javafx.scene.control.Button)event.getSource()).getText();
        output.setText(output.getText()+val);
    }
    @FXML
    private  void procOperation(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String val = ((javafx.scene.control.Button)event.getSource()).getText();
        if (!"=".equals(val)){
            if(!operator.isEmpty()) {
                return;
            }
            operator=val;
            num1 = Float.parseFloat(output.getText());
            output.setText("");
        }else {
            if(operator.isEmpty()) return;
            num2=Float.parseFloat(output.getText());
            String result = String.valueOf(model.calculation(num1,num2,operator));
            output.setText(result);
            buffer=String.valueOf(num1)+operator+String.valueOf(num2)+"="+result;
            System.out.println(buffer);
            operator="";
            execDB();
            begin=true;

        }
    }

    public String getBuffer(){
        return buffer;
    }



    public void execDB() throws SQLException, ClassNotFoundException, IOException {
        DbHandler dbh = new DbHandler();
        dbh.CreateDB();
        dbh.WriteDB(getBuffer());
        dbh.ReadDB();
        dbh.printTable(column_id,column_oper,tableView);
      //  dbh.initialize();
        dbh.CloseDB();
    }


}
