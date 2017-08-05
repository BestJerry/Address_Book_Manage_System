package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Begin_Interface_Controller {

    public static String address_book_name;

    public static boolean fileIfExist;

    public void createAddressBook(ActionEvent actionEvent) throws IOException {

        fileIfExist = false;
        Stage stage = FXRobotHelper.getStages().get(0);
        Parent root = FXMLLoader.load(getClass().getResource("/View/CreateAddressBook_Interface.fxml"));
        Scene scene = new Scene(root, 720, 480);
        stage.setScene(scene);
    }

    public void systemDescribe(ActionEvent actionEvent) throws IOException {

        Parent root  = FXMLLoader.load(getClass().getResource("/View/Describe_System_View.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        Scene scene = new Scene(root,720,480);
        stage.setScene(scene);
    }

    public void quitSystem(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void importAddressBook(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        try {
            if (file.exists()) {
                fileIfExist = true;
                address_book_name = file.getName();
                turnToMainPage();
            }
        } catch (Exception e) {

        }

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("导入通讯录");
        fileChooser.setInitialDirectory(new File("AddressBook"));
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
