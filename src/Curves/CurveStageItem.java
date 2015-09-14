package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
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
    public void handleCurveChanged(CurveEvent e) {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.RenderCurve(e.getCurve());
    }

    @Override
    public void handleCurveRemoved(CurveEvent e) {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.RemoveCurve(e.getCurve());
    }

    @Override
    public void handleCurveCalculated(CurveEvent e) {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.RenderCurve(e.getCurve());
    }

    @Override
    public void handleCurrentCurveChanged(CurveEvent e) {

    }

    public void ToggleGrid() {
        CurveStageController sc = (CurveStageController)FStageController;
        sc.ToggleGrid();
    }

}
