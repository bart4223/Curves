package Curves;

import Uniwork.Appl.NGStageManager;
import Uniwork.Appl.NGToolboxItem;
import javafx.stage.Stage;

public class CurveManagerToolboxItem extends NGToolboxItem {

    public CurveManagerToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveManagerToolbox.fxml";
        FWidth = 200;
        FHeight = 800;
        FUnique = true;
    }
}
