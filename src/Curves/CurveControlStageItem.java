package Curves;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CurveControlStageItem extends NGCustomStageItem {

    @Override
    protected void CreateStage() {
        super.CreateStage();
        FStage = new Stage();
    }

    @Override
    protected void LoadStage() {
        super.LoadStage();
        FXMLLoader lXMLLoader;
        lXMLLoader = new FXMLLoader(getClass().getResource("CurveControlStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle(String.format("%s.%s", NGApplication.Application.getName(), getCaption()));
            Scene Scene = new Scene(lRoot, 800, 50, Color.LIGHTGRAY);
            FStage.setScene(Scene);
            FStage.setResizable(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public CurveControlStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
    }

}
