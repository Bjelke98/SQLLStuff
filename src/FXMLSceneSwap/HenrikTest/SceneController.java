package FXMLSceneSwap.HenrikTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SceneController {
    public ListView<String> listViewLeft;
    public Button btnClickable;

    public void handleButtonClick(ActionEvent e){
        System.out.println("CLICK!");
    }

    @FXML
    public void initialize(){
    }
}
