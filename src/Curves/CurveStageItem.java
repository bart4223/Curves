package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class CurveStageItem extends NGCustomStageItem {

    public CurveStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveStage.fxml";
        FHeight = 800;
        FWidth = 800;
    }

}
