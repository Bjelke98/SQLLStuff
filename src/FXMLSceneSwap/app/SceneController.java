package FXMLSceneSwap.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

//    private Stage stage;
//    private Scene scene;
//    private Parent root;

    public void switchToScene1(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
            Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(new Scene(root));
        } catch (IOException ex){
            System.out.println("Failed to switch scene to Scene 1");
            ex.printStackTrace();
        }
    }
    public void switchToScene2(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene2.fxml")));
            Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
            stage.setScene(new Scene(root));
        } catch (IOException ex){
            System.out.println("Failed to switch scene to Scene 2");
            ex.printStackTrace();
        }
    }
}
