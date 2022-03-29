package test.dbApp.plattfot;

import test.dbApp.gui.login.Login;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Plattfot extends Application {

    private static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        Plattfot.stage = stage;
        Plattfot.setScene(Login.getRoot(), Login.TITLE);
        stage.show();
    }

    public static void setScene(Parent root, String title){
        stage.setScene(new Scene(root));
        stage.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
