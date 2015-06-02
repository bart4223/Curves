package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGLogManager;

public class CurveApplicationModule extends NGVisualApplicationModule {

    protected CurveManager FCurveManager;

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Control");
        item.setCaption(String.format("%s.Control", FName));
        item.setPosition(1500, 200);
        item = FStageManager.addStageItem("Curve");
        item.setCaption(String.format("%s.Curve", FName));
        item.setPosition(1500, 300);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FCurveManager.Initialize();
    }

    protected void DoFinalize() {
        FCurveManager.Finalize();
        super.DoFinalize();
    }

    public CurveApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FCurveManager = new CurveManager(this);
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
        FStageManager.registerItemClass("Control", "Curves.CurveControlStageItem");
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FCurveManager.setLogManager(FLogManager);
    }

}
