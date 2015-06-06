package praktik;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public TableView table;
    public TableColumn tc_company;
    public TableColumn tc_activities;
    public TableColumn tc_phone;
    public TableColumn tc_mail;
    public TableColumn tc_website;
    public TableColumn tc_state;
    public Button b_add;
    public Button b_delete;
    private ObservableList<Company> data = FXCollections.observableArrayList();

    public void action_save(ActionEvent actionEvent) {
    }

    public void action_load(ActionEvent actionEvent) {
    }

    public void action_about(ActionEvent actionEvent) {
    }

    public void action_add(ActionEvent actionEvent) throws IOException {
        // Open a window with a form to enter a new company
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_add.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add a company");
        stage.setScene(new Scene(root, 600, 330));
        stage.show();

        // Add the new company to the table's data
        CompanyController controller = loader.getController();
        controller.getData().addListener((ListChangeListener<Company>) c -> data.addAll(c.getList()));

        tc_company.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        tc_activities.setCellValueFactory(new PropertyValueFactory<Company,String>("activities"));
        tc_phone.setCellValueFactory(new PropertyValueFactory<Company,String>("phone"));
        tc_mail.setCellValueFactory(new PropertyValueFactory<Company,String>("mail"));
        tc_website.setCellValueFactory(new PropertyValueFactory<Company,String>("website"));
        tc_state.setCellValueFactory(new PropertyValueFactory<Company,String>("state"));
        table.setItems(data);

        b_delete.setDisable(false);
    }

    public void action_delete(ActionEvent actionEvent) {
        data.remove(table.getSelectionModel().getSelectedItem());
        table.setItems(data);
        if(data.size() == 0) {
            b_delete.setDisable(true);
        }
    }
}
