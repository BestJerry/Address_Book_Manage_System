package Controller;

import Model.Data;
import Model.Group;
import Model.User;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-7.
 */
public class LeftView_Controller implements Initializable {

    @FXML
    private TreeView<String> contacts;

    @FXML
    private TreeView<String> contact_group;

    public static String name = null;

    File file;
    Data data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TreeItem<String> contacts_rootItem = new TreeItem<>("联系人");
        contacts_rootItem.setExpanded(true);
        TreeItem<String> contact_group_rootItem = new TreeItem<>("联系组");
        contact_group_rootItem.setExpanded(true);

        file = new File("AddressBook", Begin_Interface_Controller.address_book_name + "");
        /**存在该通讯录*/
        if (file.exists()) {
            data = new Data(file);
            /**导入联系人与联系组*/
            for (User user : data.userList) {
                //System.out.println("+"+user.name+" "+user.group);
                TreeItem<String> userLeaf = new TreeItem<>(user.getName());
                contacts_rootItem.getChildren().add(userLeaf);

            }


            for (Group group : data.groupList) {
                //System.out.println(group.groupName);
                TreeItem<String> groupNode = new TreeItem<>(group.groupName);
                contact_group_rootItem.getChildren().add(groupNode);
                for (String member_name : group.member) {
                    TreeItem<String> member = new TreeItem<>(member_name);
                    groupNode.getChildren().add(member);
                }

            }
            contact_group.setRoot(contact_group_rootItem);
            contacts.setRoot(contacts_rootItem);

        }
        /**不存在该通讯录*/
        else {
            contacts.setRoot(contacts_rootItem);
            contact_group.setRoot(contact_group_rootItem);

        }

        contacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
                TreeItem<String> selectedItem = newValue;
                //System.out.println(selectedItem.getValue());
                try {
                    if (selectedItem.isLeaf()) {
                        updateCenterView(selectedItem.getValue());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        contact_group.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
                TreeItem<String> selectedItem = newValue;
                //System.out.println(selectedItem.getValue());
                try {
                    if (selectedItem.isLeaf()&&checkIsGroupMember(selectedItem.getValue())) {
                        updateCenterView(selectedItem.getValue());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkIsGroupMember(String value) {
        for(Group group :  data.groupList){
            //System.out.println(group.groupName);
           for(String member : group.getMember()){
               if(value.equals(member)){
                   return true;
               }
           }
        }
        return  false;
    }

    private void updateCenterView(String value) throws IOException {

        name = value;
        GridPane centerView = FXMLLoader.load(getClass().getResource("/View/CenterView.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(centerView);

    }

}
