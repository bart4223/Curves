package Curves;

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
            FStage.setTitle("Curves");
            FStage.setScene(new Scene(root, 500, 50));
            FStage.show();
        }
        catch (Exception e) {

        }

    }

    public MainStageItem(NGComponent aOwner, Stage aStage) {
        super(aOwner, aStage);
    }

}
