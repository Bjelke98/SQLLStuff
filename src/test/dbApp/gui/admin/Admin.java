package test.dbApp.gui.admin;

import test.dbApp.database.AppDatabase;
import test.dbApp.plattfot.Medlem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Admin {

    ObservableList<Medlem> loadedMembers = FXCollections.observableArrayList();

    public static final String TITLE = "Plattfot Admin";

    public TableView<Medlem> membersTable;

    private AppDatabase database = new AppDatabase();

    public static Parent getRoot() throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(Admin.class.getResource("admin.fxml")));
    }


    @FXML
    public void initialize() {

//        TableColumn<Medlem, String> mailCol = new TableColumn<>("E-Post");
//        mailCol.setCellValueFactory(new PropertyValueFactory<>("epost"));
//        membersTable.getColumns().add(mailCol);
//
//        TableColumn<Medlem, String> nameCol = new TableColumn<>("Navn");
//        nameCol.setCellValueFactory(new PropertyValueFactory<>("navn"));
//        membersTable.getColumns().add(nameCol);
//
//        TableColumn<Medlem, String> adressCol = new TableColumn<>("Adresse");
//        nameCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        membersTable.getColumns().add(adressCol);
//
//        TableColumn<Medlem, String> tlfCol = new TableColumn<>("TLF");
//        nameCol.setCellValueFactory(new PropertyValueFactory<>("tlf"));
//        membersTable.getColumns().add(tlfCol);
//
//        membersTable.setItems(loadedMembers);

        populate();
    }

    private void populate(){
        //database = new AppDatabase();
        //Medlem m1 = new Medlem("Krister.iversen@outlook.com", "Krister Iversen", "Gullbringvegen 30", "91684169");
        //Medlem m2 = new Medlem("Bjelke@bjelko.net", "Bjelke Drifter", "Gullbringvegen 30", "91684169");
        //database.addMember(m1);
        //database.addMember(m2);
        // addTestData();
        try {
            ResultSet rs = database.getContent("Medlem");
            while (rs.next()) {
                Medlem m = new Medlem(rs.getString("epost"), rs.getString("navn"), rs.getString("adresse"), "" + rs.getInt("tlf"));
                loadedMembers.add(m);
                membersTable.getItems().add(m);
            }
        } catch (SQLException throwables) {
            System.out.println("Error getting content from Medlem");
            throwables.printStackTrace();
        }
    }

    private void addTestData(){
        for (int i = 0; i < 30; i++) {
            database.addMember(new Medlem(
                    i+"@mail.com",
                    i+"Name",
                    i+"Adresse",
                    i+"12233"
            ));
        }
    }
}
