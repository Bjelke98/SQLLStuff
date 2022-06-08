package arcTest;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class ArcExt extends Arc {
    public ArcExt(){

        setCenterX(100);
        setCenterY(100);
        setRadiusX(50);
        setRadiusY(50);
        setFill(Color.LIMEGREEN);
        setType(ArcType.ROUND);
        setLength(30);

    }
}
