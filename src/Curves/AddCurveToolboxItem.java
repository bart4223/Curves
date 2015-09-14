package Curves;

import Uniwork.Appl.NGCustomToolboxItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class AddCurveToolboxItem extends NGCustomToolboxItem {

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof CurveManager) {
            AddCurveToolboxController sc = (AddCurveToolboxController)FStageController;
            sc.CurveManager = (CurveManager)aContext;
        }
    }

    public AddCurveToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "AddCurveToolbox.fxml";
        FWidth = 400;
        FHeight = 600;
        FPosition.setX(1075);
        FPosition.setY(300);
        FUnique = true;
    }

}
