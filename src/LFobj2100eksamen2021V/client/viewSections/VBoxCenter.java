package LFobj2100eksamen2021V.client.viewSections;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VBoxCenter extends VBox {
    public VBoxCenter(){
        setAlignment(Pos.CENTER);
    }
    public VBoxCenter(Node... nodes){
        this();
        getChildren().addAll(nodes);
    }
}
