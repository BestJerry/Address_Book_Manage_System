package Controller;

import Model.Data;
import Model.User;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-7.
 */
public class TopView_Controller implements Initializable {
    @FXML
    private Label address_book_name;

    @FXML
    private TextField search;

    public static String search_item;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] pathList = Begin_Interface_Controller.address_book_name.split("[.]");
        address_book_name.setText(pathList[0]);

    }

    public void BackToStart(ActionEvent actionEvent) throws IOException {
        Stage stage = FXRobotHelper.getStages().get(0);
        Parent root = FXMLLoader.load(getClass().getResource("/View/Begin_Interface.fxml"));
        Scene scene = new Scene(root, 720, 480);
        stage.setScene(scene);
    }

    public void createGroup(ActionEvent actionEvent) throws IOException {

        Pane pane = FXMLLoader.load(getClass().getResource("/View/CreateGroup_Interface.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(pane);
    }

    public void deleteGroup(ActionEvent actionEvent) throws IOException {

        Pane pane = FXMLLoader.load(getClass().getResource("/View/DeleteGroup_Interface.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(pane);
    }

    /**
     * 新建联系人
     */
    public void createContact(ActionEvent actionEvent) throws IOException {
        GridPane centerView = FXMLLoader.load(getClass().getResource("/View/CreateContact_Interface.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(centerView);
    }

    public void search(ActionEvent actionEvent) throws IOException {
            search_item = search.getText();
            if (search_item.equals("")) {
                return;
            }
        else if (search_item.length() > 15) {
                search_item = search_item.substring(0, 15);
            }
        Pane centerView = FXMLLoader.load(getClass().getResource("/View/SearchResult_Interface.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(centerView);
    }
}
