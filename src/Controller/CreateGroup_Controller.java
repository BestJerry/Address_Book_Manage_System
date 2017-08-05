package Controller;

import Model.Data;
import Model.Group;
import com.intellij.codeInsight.template.postfix.templates.ElseExpressionPostfixTemplateBase;
import com.sun.javafx.robot.impl.FXRobotHelper;
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

/**
 * Created by jerry on 17-1-8.
 */
public class CreateGroup_Controller implements Initializable{

    @FXML
    private Label tip1;

    @FXML
    private Label tip2;

    @FXML
    private TextField group_name;

    File file = null;
    Data data = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Begin_Interface_Controller.fileIfExist){
            file = new File("AddressBook",Begin_Interface_Controller.address_book_name+"");
            //System.out.println(file.exists());
            //System.out.println(file==null);
            data = new Data(file);
            //System.out.println(data==null);
        }
        else {
            String[] pathList = Begin_Interface_Controller.address_book_name.split("[.]");
            data = new Data(pathList[0]);
        }

    }


    public void confirm(ActionEvent actionEvent) throws IOException {
        String new_group_name = group_name.getText();

        if(!new_group_name.equals("")&&!checkIfExist(new_group_name)){
            data.addGroup(new_group_name);
            data.save();
            tip1.setVisible(false);
            tip2.setVisible(true);
            VBox leftView = FXMLLoader.load(getClass().getResource("/View/LeftView.fxml"));
            Stage stage = FXRobotHelper.getStages().get(0);
            BorderPane root = (BorderPane) stage.getScene().getRoot();
            root.setLeft(leftView);
        }
        else {
            tip2.setVisible(false);
            tip1.setVisible(true);
        }
    }

    private boolean checkIfExist(String new_group_name) {

        for(Group group : data.groupList){
            if(group.groupName.equals(new_group_name)){
                return true;
            }
        }
        return false;

    }


}
