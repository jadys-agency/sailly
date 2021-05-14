package fr.jadys.sailly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/chess.fxml"));
        fxmlLoader.load();
        ChessController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        parent.setStyle("-fx-background-radius: 5px;");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

}