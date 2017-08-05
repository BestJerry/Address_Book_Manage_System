package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by jerry on 17-1-7.
 */
public class Describe_System_View_Controller {
    public void BackToStart(ActionEvent actionEvent) throws IOException {

        Stage stage = FXRobotHelper.getStages().get(0);
        Parent root = FXMLLoader.load(getClass().getResource("/View/Begin_Interface.fxml"));
        Scene scene = new Scene(root, 720, 480);
        stage.setScene(scene);
    }
}
