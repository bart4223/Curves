package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import Uniwork.Misc.NGLogEvent;
import javafx.stage.Stage;

public class CurveStageItem extends NGCustomStageItem implements CurveEventListener {

    public CurveStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveStage.fxml";
        FHeight = 800;
        FWidth = 800;
    }

    @Override
    public void handleCurveAdded(CurveEvent e) {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.addCurve(e.getCurve());
    }

    @Override
    public void handleCurveCalculated(CurveEvent e) {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.RenderCurve(e.getCurve());
    }

    @Override
    public void handleLogAdded(NGLogEvent e) {

    }

    @Override
    public void handleLogClear() {

    }

}
