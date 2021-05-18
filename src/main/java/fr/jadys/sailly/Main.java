package fr.jadys.sailly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Chargement du fichier FXML
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/chess.fxml"));
        fxmlLoader.load();

        // Récupération du contrôleur
        ChessController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        // Création de la scène
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        parent.setStyle("-fx-background-radius: 5px;");

        // Affichage de la fenêtre
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/artefacts/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

}