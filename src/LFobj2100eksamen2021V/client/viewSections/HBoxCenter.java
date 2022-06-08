package LFobj2100eksamen2021V.client.viewSections;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HBoxCenter extends HBox {
    public HBoxCenter(){
        setAlignment(Pos.CENTER);
    }
    public HBoxCenter(Node... nodes){
        this();
        getChildren().addAll(nodes);
    }
}
