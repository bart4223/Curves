package Curves;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStageItem extends NGCustomStageItem {

    @Override
    protected void LoadStage() {
        super.LoadStage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
            FStage.setTitle(String.format("%s.%s", NGApplication.Application.getName(), getName()));
            FStage.setScene(new Scene(root, 500, 50));
            FStage.show();
        }
        catch (Exception e) {

        }

    }

    public MainStageItem(NGComponent aOwner, String aName, Stage aStage) {
        super(aOwner, aName, aStage);
    }

}
