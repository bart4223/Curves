package Curves;

import Curves.Definitions.FirstDegreePolynomialFunctionDefinition;
import Curves.Solutions.FirstDegreePolynomialFunctionSolutionProcedure;
import Uniwork.Base.NGComponent;
import Uniwork.Base.NGComponentManager;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public class CurveManager extends NGComponent implements NGLogEventListener {

    protected ArrayList<CustomCurve> FCurves;
    protected ArrayList<CurveEventListener> FEventListeners;

    protected void DoLoadCurves() {
        // ToDo load from curve composition
        CustomCurveSolutionProcedure proc = new FirstDegreePolynomialFunctionSolutionProcedure();
        proc.setLogManager(FLogManager);
        Curve2D curve = new Curve2D(this, "First", new FirstDegreePolynomialFunctionDefinition(1, 1, 0, 0, 0, 100), proc);
        addCurve(curve);
        curve.setParameterValue("a", 1.0);
        curve.setParameterValue("b", 0.0);
    }

    protected void DoCalculateCurve(CustomCurve aCurve) {
        aCurve.Calculate();
        raiseCurveCalculatedEvent(aCurve);
    }

    protected void DoCalculateCurves() {
        for (CustomCurve curve : FCurves) {
            DoCalculateCurve(curve);
        }
    }

    protected void BeginCalculateCurves() {

    }

    protected void InternalCalculateCurves() {
        BeginCalculateCurves();
        try {
            DoCalculateCurves();
        }
        finally {
            EndCalculateCurves();
        }
    }

    protected void EndCalculateCurves() {

    }

    protected synchronized void raiseCurveAddedEvent(CustomCurve aCurve) {
        CurveEvent event = new CurveEvent(this, aCurve);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleCurveAdded(event);
        }
    }

    protected synchronized void raiseCurveCalculatedEvent(CustomCurve aCurve) {
        CurveEvent event = new CurveEvent(this, aCurve);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleCurveCalculated(event);
        }
    }

    public CurveManager() {
        this("");
    }

    public CurveManager(String aName) {
        this(null, aName);
    }

    public CurveManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FCurves = new ArrayList<CustomCurve>();
        FEventListeners = new ArrayList<CurveEventListener>();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
    }

    public void addCurve(CustomCurve aCurve) {
        FCurves.add(aCurve);
        writeInfo(String.format("Curve %s: Definition [%s -> %s] with solution procedures [%s] added", aCurve.getName(), aCurve.getDefinition().getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
        raiseCurveAddedEvent(aCurve);
    }

    public void addEventListener(CurveEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public void removeEventListener(CurveEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public void LoadCurves() {
        DoLoadCurves();
        InternalCalculateCurves();
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleClearLog() {
    }

}
