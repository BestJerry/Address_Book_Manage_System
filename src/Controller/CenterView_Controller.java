package Controller;

import Model.Data;
import Model.Group;
import Model.User;
import com.sun.javafx.robot.impl.FXRobotHelper;
import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
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
import org.intellij.lang.annotations.PrintFormat;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-7.
 */
public class CenterView_Controller implements Initializable {
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
    private Label tip1;
    @FXML
    private Label tip2;

    File file;
    Data data;
    String contact_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (Begin_Interface_Controller.fileIfExist) {
            file = new File("AddressBook", Begin_Interface_Controller.address_book_name + "");
            data = new Data(file);

        } else {
            String[] pathList = Begin_Interface_Controller.address_book_name.split("[.]");
            data = new Data(pathList[0]);
        }

        contact_name = LeftView_Controller.name;
        for (User user : data.userList) {
            if (user.getName()!= null && user.getName().equals(contact_name)) {
                name.setText(user.getName());
                home_telephone.setText(user.call);
                smart_phone.setText(user.phone);
                QQ.setText(user.qq);
                email.setText(user.mail);
                personal_homepage.setText(user.personalPage);
                birthday.setText(user.birthday);
                work_unit.setText(user.workPlace);
                address.setText(user.location);
                postcode.setText(user.postCode);
                if (user.group != null) {
                    group.setText(user.group);
                } else {
                    group.setText("");
                }
                remark.setText(user.remark);
                break;
            }
        }

    }

    public void save(ActionEvent actionEvent) throws InterruptedException, IOException {

        if (!checkName(name.getText()) || !checkGroup(group.getText())) {

            tip1.setText("更改失败!姓名或所属组填写有误");
            tip1.setVisible(true);

        } else {

            for (User user : data.userList) {
                if (user.getName()!= null && user.getName().equals(contact_name)) {
                    data.updataUser(name.getText(), home_telephone.getText(), smart_phone.getText(), QQ.getText(), email.getText(),
                            personal_homepage.getText(), birthday.getText(), work_unit.getText(), address.getText(), postcode.getText(),
                            remark.getText(), group.getText().equals("") ? null : group.getText(), user);
                    break;
                }

            }

            data.save();

            /*for(User user : data.userList){
                System.out.println(user.name);
            }*/
            tip1.setText("更改成功!");
            tip1.setVisible(true);
            VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
            Stage stage = FXRobotHelper.getStages().get(0);
            BorderPane root = (BorderPane) stage.getScene().getRoot();
            root.setLeft(leftView);

        }
    }

    private boolean checkName(String text) {
        if (text.equals("")) {
            return false;
        } else {
            int count = 0;
            for (User user : data.userList) {
                if (user.getName()!= null && user.getName().equals(text)) {
                    count++;
                }
            }
            if (count > 1) {
                return false;
            }
        }
        return true;

    }

    private boolean checkGroup(String group) {

        if (group.equals("")) {
            return true;
        }
        for (Group group1 : data.groupList) {
            if (group1.groupName.equals(group)) {
                return true;
            }
        }
        return false;
    }

    public void delete_contact(ActionEvent actionEvent) throws IOException {

    boolean isFound = false;
    User current_user = new User();
        for (User user : data.userList) {
            if (user.getName()!=null&&(user.getName().equals(name.getText())||user.getName().equals(contact_name))) {

                current_user = user;
                isFound = true;
            }
        }
        if(isFound){
            data.deleteUser(current_user);
            tip2.setText("删除成功!");
            tip2.setVisible(true);
            data.save();
            VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
            GridPane pane = FXMLLoader.load(getClass().getResource("/View/EmptyView.fxml"));
            Stage stage = FXRobotHelper.getStages().get(0);
            BorderPane root = (BorderPane) stage.getScene().getRoot();
            root.setLeft(leftView);
            root.setCenter(pane);
        }

    }
}
