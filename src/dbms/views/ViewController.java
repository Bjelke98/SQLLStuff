package dbms.views;

import dbms.database.UserTable;
import dbms.user.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ViewController {

    private static final UserTable userTable = UserTable.getInstance();

    public TextField loginUsername;
    public PasswordField loginPassword;
    public VBox loginBox;

    public void clear(ActionEvent event){
        loginUsername.clear();
        loginPassword.clear();
    }

    public void denyLoginMessage(){
        System.out.println("Incorrect username or password");
    }

    public void login(ActionEvent event){
        //try {
        //    userTable.add(new User("t", "t", true));
        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}
        User loggingIn = null;
        try {
            loggingIn = userTable.get(loginUsername.getText().toLowerCase());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(loggingIn==null){
            denyLoginMessage();
            return;
        }
        if(loggingIn.getPassword().equals(loginPassword.getText())){
            System.out.println("Logged in");

            Timeline timeline3 = new Timeline(
                    new KeyFrame(Duration.millis(200), e->{
                        loginBox.setTranslateX(loginBox.getTranslateX()+30);
                    }));
            timeline3.setCycleCount(10);
            timeline3.setOnFinished(e-> switchToHome(event));

            Timeline timeline2 = new Timeline(
                    new KeyFrame(Duration.millis(1000), e->{

                    }));
            timeline2.setCycleCount(1);
            timeline2.setOnFinished(e-> timeline3.play());

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(10), e->{
                        loginBox.setRotate(loginBox.getRotate()+3.6);
                    }));
            timeline.setCycleCount(84);
            timeline.setOnFinished(e-> timeline2.play());


            timeline.play();

        } else {
            denyLoginMessage();
        }
    }

    public void logout(ActionEvent event){
        switchToLogin(event);
    }

    public void switchToLogin(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(new Scene(root));
        } catch (IOException ex){
            System.out.println("Failed to switch scene to Login");
            ex.printStackTrace();
        }
    }
    public void switchToHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(new Scene(root));
        } catch (IOException ex){
            System.out.println("Failed to switch scene to Home");
            ex.printStackTrace();
        }
    }
}
