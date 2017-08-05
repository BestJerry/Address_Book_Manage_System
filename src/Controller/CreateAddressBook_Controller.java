package Controller;

import Model.Data;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-7.
 */
public class CreateAddressBook_Controller implements Initializable {

    @FXML
    private Label check_result;

    @FXML
    private TextField address_book_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void confirm(ActionEvent actionEvent) throws IOException {
        String new_addressbook_name = address_book_name.getText();
        if (new_addressbook_name.length() > 16) {
            new_addressbook_name = new_addressbook_name.substring(0, 16);
        }
        //System.out.print(new_addressbook_name);
        if (!checkIfExist(new_addressbook_name + ".csv")) {
            check_result.setVisible(true);
        } else {
            Begin_Interface_Controller.address_book_name = new_addressbook_name + ".csv";
            Begin_Interface_Controller.fileIfExist = true;
            Data data = new Data(new_addressbook_name);
            data.save();
            check_result.setVisible(false);
            turnToMainPage();
        }

    }

    private boolean checkIfExist(String new_addressbook_name) {
        File file = new File("AddressBook");
        File[] files = file.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            if (files[i].getName().equals(new_addressbook_name) || new_addressbook_name.equals("")) {
                return false;
            }
        }
        return true;
    }

    public void BackToStart(ActionEvent actionEvent) throws IOException {
        Stage stage = FXRobotHelper.getStages().get(0);
        Parent root = FXMLLoader.load(getClass().getResource("/View/Begin_Interface.fxml"));
        Scene scene = new Scene(root, 720, 480);
        stage.setScene(scene);
    }

    public void turnToMainPage() throws IOException {

        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = FXMLLoader.load(getClass().getResource("/View/Main_Interface.fxml"));
        HBox topView = FXMLLoader.load(getClass().getResource("/View/TopView_.fxml"));
        VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
        GridPane CenterView = FXMLLoader.load(getClass().getResource("/View/EmptyView.fxml"));
        root.setTop(topView);
        root.setLeft(leftView);
        root.setCenter(CenterView);
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
    }
}
