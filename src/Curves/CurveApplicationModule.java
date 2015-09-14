package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGCustomToolboxItem;
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
        registerObjectRequest("CurveModule", this, "AddCurveToolbox", "AddCurve");
        registerObjectRequest("CurveModule", this, "Help", "ToggleHelp");
        registerObjectRequest("CurveModule", this, "CurveManagerToolbox", "ToggleProps");
        registerObjectRequest("CurveModule", this, "CurveGrid", "ToggleGrid");
    }

    public CurveApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FComponentManager.registerComponent(new CurveManager(this, CurvesConsts.C_COMPONENT_CURVEMANAGER));
        FStageManager.registerItemClass("Curve", "Curves.CurveStageItem");
        FStageManager.registerItemClass("Control", "Curves.CurveControlStageItem");
        FStageManager.registerItemClass("Console", "Uniwork.UI.NGUIConsoleStageItem");
        FToolboxManager.registerItemClass("Help", "Uniwork.UI.NGUIHelpToolboxItem");
        FToolboxManager.registerItemClass("Curve", "Curves.CurveToolboxItem");
        FToolboxManager.registerItemClass("CurveManager", "Curves.CurveManagerToolboxItem");
        FToolboxManager.registerItemClass("AddCurve", "Curves.AddCurveToolboxItem");
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

    public void AddCurve() {
        FToolboxManager.ShowToolbox("AddCurve", String.format("%s.AddToolbox", getDescription()), FCurveManager);
    }

    public void ToggleHelp() {
        NGCustomStageItem item = FToolboxManager.CreateToolbox("Help", String.format("%s.Help", getDescription()), new NGUIHelpToolboxContext(Application.LoadResourceFileContent("help/curve.txt")));
        item.ToggleShow();
    }

    public void ToggleProps() {
        NGCustomStageItem item = FToolboxManager.CreateToolbox("CurveManager", String.format("%s.Props", getDescription()));
        item.setPosition(2325, 300);
        item.ToggleShow();
    }

    public void ToggleGrid() {
        CurveStageItem item = (CurveStageItem)FStageManager.getItem("Curve");
        item.ToggleGrid();
    }

}
