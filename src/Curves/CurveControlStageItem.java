package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class CurveControlStageItem extends NGCustomStageItem {

    public CurveControlStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveControlStage.fxml";
        FWidth = 800;
        FHeight = 50;
    }

}
