package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGComponent;
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

    public CurveStageItem(NGComponent aOwner, Stage aStage) {
        super(aOwner, aStage);
        FCurveManager = new CurveManager(this);
        FCurveManager.setLogManager(FLogManager);
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

}
