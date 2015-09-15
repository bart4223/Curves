package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

import java.util.EventObject;

public class CurveControlStageItem extends NGCustomStageItem implements CurveEventListener {

    public CurveControlStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveControlStage.fxml";
        FWidth = 800;
        FHeight = 50;
    }

    @Override
    public void handleCurveAdded(CurveEvent e) {
        CurveControlStageController sc = (CurveControlStageController)FStageController;
        sc.addCurve(e.getCurve());
    }

    @Override
    public void handleCurveChanged(CurveEvent e) {
        CurveControlStageController sc = (CurveControlStageController)FStageController;
        sc.CurveChanged(e.getCurve());
    }

    @Override
    public void handleCurveRemoved(CurveEvent e) {
        CurveControlStageController sc = (CurveControlStageController)FStageController;
        sc.CurveRemoved(e.getCurve());
    }

    @Override
    public void handleCurveCalculated(CurveEvent e) {

    }

    @Override
    public void handleCurrentCurveChanged(CurveEvent e) {
        CurveControlStageController sc = (CurveControlStageController)FStageController;
        sc.setCurrentCurve(e.getCurve());
    }

    @Override
    public void handleScaleChanged(EventObject e) {

    }

}
