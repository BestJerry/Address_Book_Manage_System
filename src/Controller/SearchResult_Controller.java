package Controller;

import Model.Data;
import Model.User;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by jerry on 17-1-10.
 */
public class SearchResult_Controller implements Initializable {

    @FXML
    private ListView<String> search_result_list;

    String search_item;
    File file;
    Data data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new File("AddressBook", Begin_Interface_Controller.address_book_name + "");
        data = new Data(file);
        search_item = TopView_Controller.search_item;
        ArrayList<User> users = data.search(search_item);
        ArrayList<String> list = new ArrayList<>();
        for (User user : users) {
            if (user.getName() != null) {
                list.add(user.getName());
            }
        }
        ObservableList<String> items = FXCollections.observableArrayList(list);
        search_result_list.setItems(items);
        search_result_list.setFixedCellSize(40);

        search_result_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (User user : data.userList) {
                        if (user.getName()!= null && user.getName().equals(newValue)) {
                            try {
                                updateCenterView(newValue);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

    }

    private void updateCenterView(String value) throws IOException {
        LeftView_Controller.name = value;
        GridPane centerView = FXMLLoader.load(getClass().getResource("/View/CenterView.fxml"));
        Stage stage = FXRobotHelper.getStages().get(0);
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        root.setCenter(centerView);

    }
}
