package LFobj2100eksamen2021V.client;

import LFobj2100eksamen2021V.client.viewSections.HBoxCenter;
import LFobj2100eksamen2021V.client.viewSections.VBoxCenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class App extends Application {

    double appWidth = 600;
    double appHeight = 400;

    TextField displaynameInput = new TextField();
    Button cancelButton = new Button("Cancel");
    Button joinButton = new Button("Join");

    String titleLogin = "QuickChat - Login";
    String titleRoom = "QuickChat - RoomView";
    String titleChat = "QuickChat - Chat";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(titleLogin);
        stage.setScene(loginSceneFactory());
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

        cancelButton.setOnAction(e->loginClear());
        cancelButton.setCancelButton(true);

        joinButton.setOnAction(this::loginJoin);
        joinButton.setDefaultButton(true);

        /**
         * Socket test 1
         */
        int port = 8000;
        String host = "localhost";
        ObjectInputStream in;
        Socket socket;
        socket = new Socket(host, port);
        in = new ObjectInputStream(socket.getInputStream());
        String s = (String)(in.readObject());
        System.out.println(s);
        in.close();
        socket.close();

    }

    public void loginClear(){
        displaynameInput.setText("");
    }

    public void loginJoin(ActionEvent e){
        if(!displaynameInput.getText().isBlank()){
            System.out.println("Join: "+displaynameInput.getText());
            loginClear();
            Stage stage = (Stage) (((Node)e.getSource()).getScene().getWindow());
            stage.setScene(homeSceneFactory());
        }
    }

    private Scene homeSceneFactory(){


        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem fileMenuLogout = new MenuItem("Logout");
        MenuItem fileMenuExit = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileMenuLogout, fileMenuExit);

        Menu actionMenu = new Menu("Action");
        MenuItem actionMenuDisconnect = new MenuItem("Disconnect chat");
        MenuItem actionMenuToggleDark = new MenuItem("Toggle dark");
        actionMenu.getItems().addAll(actionMenuDisconnect, actionMenuToggleDark);

        Menu helpMenu = new Menu("Help");
        MenuItem helpMenuAbout = new MenuItem("About");
        helpMenu.getItems().add(helpMenuAbout);

        menuBar.getMenus().addAll(fileMenu, actionMenu, helpMenu);



        BorderPane roomAction = new BorderPane();

        ListView<String> roomList = new ListView<>();
        roomList.getItems().addAll("Room1", "Room2", "Room3");

        BorderPane roomInput = new BorderPane();
        TextField roomNameTF = new TextField();
        Button roomNameBTN = new Button("New room");
        roomInput.setCenter(roomNameTF);
        roomInput.setRight(roomNameBTN);

        roomAction.setTop(new Label("Select a room"));
        roomAction.setCenter(roomList);
        roomAction.setBottom(roomInput);



        BorderPane chatPane = new BorderPane();

        TextArea chatText = new TextArea();

        BorderPane chatInput = new BorderPane();
        TextField messageInput = new TextField();
        Button messageSend = new Button("Send");
        chatInput.setCenter(messageInput);
        chatInput.setRight(messageSend);

        chatPane.setTop(new Label("Connected as: "));
        chatPane.setCenter(chatText);
        chatPane.setBottom(chatInput);



        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(roomAction);
        root.setCenter(chatPane);

        return new Scene(root, appWidth, appHeight);
    }

    private Scene loginSceneFactory(){
        Label welcomeLabel = new Label("Welcome to QuickChat");
        Label displaynameLabel = new Label("Displayname: ");

        HBoxCenter displaynameBox = new HBoxCenter(displaynameLabel, displaynameInput);
        HBoxCenter buttonBox = new HBoxCenter(cancelButton, joinButton);

        VBoxCenter root = new VBoxCenter(welcomeLabel, displaynameBox, buttonBox);

        return new Scene(root, appWidth, appHeight);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
