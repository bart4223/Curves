package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import javafx.stage.Stage;

public class CurveStageItem extends NGCustomStageItem {

    protected CurveManager FCurveManager;

    protected void DoInitialize() {
        super.DoInitialize();
        FCurveManager.Initialize();
    }

    protected void DoFinalize() {
        FCurveManager.Finalize();
        super.DoFinalize();
    }

    public CurveStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FCurveManager = new CurveManager(this);
        FCurveManager.setLogManager(FLogManager);
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

}
