package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;

public class CurveApplicationModule extends NGVisualApplicationModule {

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Control");
        item.setCaption(String.format("%s.Control", FName));
        item.setPosition(1500, 200);
        item = FStageManager.addStageItem("Curve");
        item.setCaption(String.format("%s.Curve", FName));
        item.setPosition(1500, 300);
        getCurveManager().addEventListener((CurveStageItem)item);
        item = FStageManager.addStageItem("Console");
        item.setCaption(String.format("%s.Console", FName));
        item.setPosition(1500, 1150);
        getCurveManager().addEventListener((CurveConsoleStageItem)item);
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        getCurveManager().LoadCurves();
    }

    public CurveApplicationModule(NGComponent aOwner, String aName, String aDescription) {
        super(aOwner, aName, aDescription);
        FComponentManager.registerComponent(new CurveManager(this, CurvesConsts.C_COMPONENT_CURVEMANAGER));
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
        FStageManager.registerItemClass("Control", "Curves.CurveControlStageItem");
        FStageManager.registerItemClass("Console", "Curves.CurveConsoleStageItem");
    }

    protected CurveManager getCurveManager() {
        return (CurveManager)FComponentManager.getComponent(CurvesConsts.C_COMPONENT_CURVEMANAGER);
    }

}
