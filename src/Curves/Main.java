package Curves;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    protected CurveStageManager FStageManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CurveStage.fxml"));
        primaryStage.setTitle("Curves");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        // ToDo
        FStageManager = new CurveStageManager();
        FStageManager.newStage();
        FStageManager.Initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
