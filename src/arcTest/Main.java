package arcTest;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.helpers.AttributesImpl;

import java.security.Key;
import java.sql.Time;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Group g = new Group();

        int slices = 10;
        pane.getChildren().add(g);
        double size = 360.0/slices;
        double current = 0;
        //g.setRotate(size);



        int winner = 5;
        double winnerDeg = winner* size;
        Timeline t2 = new Timeline();
        t2.setCycleCount(Timeline.INDEFINITE);
        t2.getKeyFrames().add(new KeyFrame(Duration.millis(5) ,e->{
            double rotate = g.getRotate()-(360*((int)(g.getRotate()/360)));
            if (rotate<1){
                rotate = 360.0;
            }


            if(rotate+size*3==winnerDeg){
                t2.stop();
            } else {
                g.setRotate(g.getRotate()+1);
            }

        }));

        Timeline t = new Timeline();
        t.setCycleCount(360*2);
        t.getKeyFrames().add(new KeyFrame(Duration.millis(5), e->{
            g.setRotate(g.getRotate()+1);
            if(g.getRotate()-(360*((int)(g.getRotate()/360)))==0){
                System.out.println(360.0);
            } else {
                System.out.println(g.getRotate()-(360*((int)(g.getRotate()/360))));
            }
        }));
        t.setOnFinished(e->t2.play());
//        t.play();

//        RotateTransition rt2 = new RotateTransition(Duration.millis(1000), g);
//        rt2.setToAngle(360+size*winner-size*3);
//        rt2.setCycleCount(1);
//        rt2.setInterpolator(Interpolator.EASE_OUT);

        RotateTransition rt = new RotateTransition(Duration.millis(5000), g);
        rt.setByAngle(360*10+size*winner-size/2);
        rt.setCycleCount(1);
        rt.setInterpolator(Interpolator.EASE_BOTH);
//        rt.play();

        Rectangle r = new Rectangle(20, 20, 20, 20);
        r.setFill(Color.GREEN);

        Polygon p = new Polygon(400+300, 400,
                                400+350, 430,
                                400+350, 370);
        p.setFill(Color.GREEN);
        pane.getChildren().add(p);

        r.setOnMousePressed(e-> {
            g.setRotate(0);
            rt.play();
        });

        pane.getChildren().add(r);

        for (int i = 0; i < slices; i++) {
            Arc a = new Arc();
            a.setCenterX(800/2.0);
            a.setCenterY(800/2.0);
            a.setLength(size);
            a.setRadiusX(300);
            a.setRadiusY(300);
            a.setStartAngle(current);
            a.setType(ArcType.ROUND);
            //a.setStroke(Color.GREEN);
            //a.setStrokeWidth(3);
//            a.setFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
            switch (i){
                case 0 -> a.setFill(Color.RED);
                case 4 -> a.setFill(Color.GREEN);
                default -> a.setFill(Color.BLUE);
            }
            g.getChildren().add(a);
            current+=size;
        }

        stage.setScene(new Scene(pane, 800, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
