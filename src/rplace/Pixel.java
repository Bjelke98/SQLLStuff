package rplace;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel extends Rectangle {
    public Pixel(Point2D p, Color c){
        super(p.getX(), p.getY(), 1, 1);
        setFill(c);
    }
}
