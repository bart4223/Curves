package Curves;

import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;

public class CurveApplicationModule extends NGVisualApplicationModule {

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FStageManager.addStageItem("Curve");
    }

    public CurveApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
    }

}
