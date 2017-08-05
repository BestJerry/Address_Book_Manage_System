package Controller;

import Model.Data;
import Model.Group;
import Model.User;
import com.sun.javafx.robot.impl.FXRobotHelper;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

/**
 * Created by jerry on 17-1-8.
 */
public class CreateContact_Controller implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField home_telephone;
    @FXML
    private TextField smart_phone;
    @FXML
    private TextField QQ;
    @FXML
    private TextField email;
    @FXML
    private TextField personal_homepage;
    @FXML
    private TextField birthday;
    @FXML
    private TextField work_unit;
    @FXML
    private TextField address;
    @FXML
    private TextField postcode;
    @FXML
    private TextField group;
    @FXML
    private TextField remark;
    @FXML
    private Label tip;

    File file;
    Data data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Begin_Interface_Controller.fileIfExist) {
            file = new File("AddressBook", Begin_Interface_Controller.address_book_name + "");
            data = new Data(file);

        } else {
            String[] pathList = Begin_Interface_Controller.address_book_name.split("[.]");
            data = new Data(pathList[0]);
        }
    }

    public void createContact(ActionEvent actionEvent) throws IOException, InterruptedException {

        if (name.getText().equals("") || checkName(name.getText())) {

            tip.setText("新建失败!该联系人已存在或姓名为空");
            tip.setVisible(true);

        } else if ((!group.getText().equals("") && !checkGroup(group.getText()))) {
            tip.setText("新建失败!所属组不存在");
            tip.setVisible(true);

        } else {


            if (group.getText().equals("")) {
                group.setText(null);
            }
            String[] contact_data = {name.getText(), home_telephone.getText(), smart_phone.getText(), QQ.getText(), email.getText(),
                    personal_homepage.getText(), birthday.getText(), work_unit.getText(), address.getText(), postcode.getText(),
                    remark.getText(), group.getText()};

            data.addUser(contact_data[0], contact_data[1], contact_data[2], contact_data[3], contact_data[4], contact_data[5],
                    contact_data[6], contact_data[7], contact_data[8], contact_data[9], contact_data[10], contact_data[11]);
            /*for(int i=0 ; i<contact_data.length;i++){
                System.out.println(contact_data[i]);
            }*/
            /*for(User user : data.userList){
                System.out.println(user.name+" "+user.group);
            }
            System.out.println();
            for(Group group : data.groupList){
                System.out.println(group.groupName);
            }*/

            data.save();
            tip.setVisible(true);
            tip.setText("新建成功!");

            VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
            Stage stage = FXRobotHelper.getStages().get(0);
            BorderPane root = (BorderPane) stage.getScene().getRoot();
            root.setLeft(leftView);

            GridPane centerView = FXMLLoader.load(getClass().getResource("/View/CreateContact_Interface.fxml"));
            root.setCenter(centerView);

        }

    }

    private boolean checkName(String text) {
        for (User user : data.userList) {
            if (user.getName().equals(text)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGroup(String group) {

        for (Group group1 : data.groupList) {
            if (group1.groupName.equals(group)) {
                return true;
            }
        }
        return false;
    }
}
