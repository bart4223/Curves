package Curves;

import Uniwork.Appl.NGCustomToolboxItem;
import Uniwork.Appl.NGStageManager;
import Uniwork.Misc.NGStrings;
import javafx.stage.Stage;

import java.util.EventObject;

public class CurveToolboxItem extends NGCustomToolboxItem implements CurveEventListener {

    @Override
    protected void CreateStage() {
        super.CreateStage();
        FStage.setOpacity(0.5);
    }

    @Override
    protected void setContextToController(Object aContext) {
        super.setContextToController(aContext);
        if (aContext instanceof CurveToolboxContext) {
            CustomCurve curve = ((CurveToolboxContext)aContext).getCurve();
            CurveToolboxController sc = (CurveToolboxController)FStageController;
            setCaption(String.format("%s.Toolbox", NGStrings.getFirstString(getCaption(), ".")));
            sc.setContext(curve);
        }
    }

    public CurveToolboxItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveToolbox.fxml";
        FWidth = 400;
        FHeight = 400;
        FPosition.setX(1075);
        FPosition.setY(300);
        FUnique = true;
    }

    @Override
    public void handleCurveAdded(CurveEvent e) {

    }

    @Override
    public void handleCurveChanged(CurveEvent e) {

    }

    @Override
    public void handleCurveRemoved(CurveEvent e) {
        if (e.getCurve().equals(((CurveToolboxContext)FContext).getCurve()))
            Close();
    }

    @Override
    public void handleCurveCalculated(CurveEvent e) {
        CurveToolboxController sc = (CurveToolboxController)FStageController;
        sc.UpdateStage(e.getCurve());
    }

    @Override
    public void handleCurrentCurveChanged(CurveEvent e) {

    }

    @Override
    public void handleScaleChanged(EventObject e) {

    }

}
