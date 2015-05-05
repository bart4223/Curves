package Curves;

import Uniwork.Base.NGComponent;

public class CurveStageItem extends NGComponent {

    protected CurveManager FCurveManager;

    protected void DoInitialize() {
        super.DoInitialize();
        FCurveManager.Initialize();
    }

    protected void DoFinalize() {
        FCurveManager.Finalize();
        super.DoFinalize();
    }

    public CurveStageItem(NGComponent aOwner) {
        super(aOwner);
        FCurveManager = new CurveManager(this);
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

}
