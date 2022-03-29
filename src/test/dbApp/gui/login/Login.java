package test.dbApp.gui.login;

import test.dbApp.gui.admin.Admin;
import test.dbApp.plattfot.Plattfot;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class Login {

    public static final String TITLE = "Plattfot Login";

    public Button btnLogin;
    public Button btnClear;

    public TextField usernameField;
    public PasswordField passwordField;

    public void handleLoginBtnClick(ActionEvent e) {
        System.out.println("Brukernavn: "+usernameField.getText().toLowerCase());
        System.out.println("Passord: "+passwordField.getText());
        try {
            Plattfot.setScene(Admin.getRoot(), Admin.TITLE);
        } catch (IOException ioe){
            System.out.println("Problem changing scene");
            ioe.printStackTrace();
        }
    }
    public void handleBtnClear(ActionEvent e){
        usernameField.clear();
        passwordField.clear();
    }

    public static Parent getRoot() throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(Login.class.getResource("login.fxml")));
    }

}
