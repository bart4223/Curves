package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class MainStageItem extends NGCustomStageItem {

    public MainStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "MainStage.fxml";
        FWidth = 500;
        FHeight = 50;
    }

}
