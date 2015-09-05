package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGComponent;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.UI.NGUIHelpToolboxContext;

public class CurveApplicationModule extends NGVisualApplicationModule {

    protected CurveManager FCurveManager;

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGCustomStageItem item = FStageManager.addStageItem("Control");
        item.setCaption(String.format("%s.Control", getDescription()));
        item.setPosition(1500, 200);
        getCurveManager().addEventListener((CurveEventListener)item);
        item = FStageManager.addStageItem("Curve");
        item.setCaption(String.format("%s.Curve", getDescription()));
        item.setPosition(1500, 300);
        getCurveManager().addEventListener((CurveEventListener)item);
        item = FStageManager.addStageItem("Console");
        item.setCaption(String.format("%s.Console", getDescription()));
        item.setPosition(1500, 1150);
        item.setWidth(800);
        getCurveManager().addLogListener(item);
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        getCurveManager().LoadCurves();
    }

    @Override
    protected void registerObjectRequests() {
        NGObjectRequestMethod requestMethod = registerObjectRequest("Curve", FCurveManager, "CurrentCurve", "setCurrentCurveByID");
        requestMethod.addParam("aID", NGObjectRequestParameter.ParamKind.String);
        requestMethod = registerObjectRequest("Curve", FCurveManager, "LineSize", "setAllCurveLineSize");
        requestMethod.addParam("aLineSize", NGObjectRequestParameter.ParamKind.Integer);
        registerObjectRequest("Curve", FCurveManager, "RemoveCurrent", "removeCurrentCurve");
        registerObjectRequest("CurveModule", this, "CurveToolbox", "ShowCurrentCurve");
        registerObjectRequest("CurveModule", this, "Help", "ShowHelp");
    }

    public CurveApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FComponentManager.registerComponent(new CurveManager(this, CurvesConsts.C_COMPONENT_CURVEMANAGER));
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
        FStageManager.registerItemClass("Control", "Curves.CurveControlStageItem");
        FStageManager.registerItemClass("Console", "Uniwork.UI.NGUIConsoleStageItem");
        FToolboxManager.registerItemClass("Help", "Uniwork.UI.NGUIHelpToolboxItem");
        FToolboxManager.registerItemClass("Curve", "Curves.CurveToolboxItem");
    }

    protected CurveManager getCurveManager() {
        if (FCurveManager == null)
            FCurveManager = (CurveManager)FComponentManager.getComponent(CurvesConsts.C_COMPONENT_CURVEMANAGER);
        return FCurveManager;
    }

    public void ShowCurrentCurve() {
        CustomCurve curve = FCurveManager.getCurrentCurve();
        if (curve != null) {
            NGCustomStageItem tb = FToolboxManager.ShowToolbox("Curve", curve.getName(), getDescription(), new CurveToolboxContext(curve));
            FCurveManager.addEventListener((CurveEventListener)tb);
        }
    }

    public void ShowHelp() {
        FToolboxManager.ShowToolbox("Help", String.format("%s.Help", getDescription()), new NGUIHelpToolboxContext(Application.LoadResourceFileContent("help/curve.txt")));
    }

}
