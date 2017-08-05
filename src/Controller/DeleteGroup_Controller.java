package Controller;

import Model.Data;
import Model.Group;
import Model.User;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-9.
 */
public class DeleteGroup_Controller extends Thread implements Initializable {

    @FXML
    private ComboBox choice_group;

    @FXML
    private Label tip1;

    @FXML
    private Label tip2;

    Data data;
    File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (Begin_Interface_Controller.fileIfExist) {
            file = new File("AddressBook", Begin_Interface_Controller.address_book_name + "");
            //System.out.println(file.exists());
            //System.out.println(file==null);
            data = new Data(file);
            //System.out.println(data==null);
        } else {
            String[] pathList = Begin_Interface_Controller.address_book_name.split("[.]");
            data = new Data(pathList[0]);
        }

        ArrayList<String> list = new ArrayList<>();
        for (Group group : data.groupList) {
            list.add(group.groupName);
        }
        ObservableList<String> group_options = FXCollections.observableArrayList(list);
        choice_group.setItems(group_options);
        choice_group.setPromptText("--请选择--");
        choice_group.setVisibleRowCount(3);
        choice_group.setEditable(false);


    }

    public void confirm(ActionEvent actionEvent) throws IOException {

        String group_name = (String) choice_group.getValue();

        if (group_name == null) {
            tip2.setVisible(false);
            tip1.setText("您未选择联系组");
            tip1.setVisible(true);
        } else {
            tip1.setVisible(false);
            for (User user : data.userList) {
                if (user.group != null && user.group.equals(group_name)) {
                    user.group = null;
                }
            }
            data.deleteGroup(group_name);
            tip2.setText("删除成功");
            tip2.setVisible(true);

            data.save();

            VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
            Pane pane = FXMLLoader.load(getClass().getResource("/View/DeleteGroup_Interface.fxml"));
            Stage stage = FXRobotHelper.getStages().get(0);
            BorderPane root = (BorderPane) stage.getScene().getRoot();
            root.setCenter(pane);
            root.setLeft(leftView);
        }
    }
}
