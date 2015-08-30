package Curves;

import Curves.Definitions.FirstDegreePolynomialFunctionDefinition;
import Curves.Definitions.SecondDegreePolynomialFunctionDefinition;
import Curves.Solutions.FirstDegreePolynomialFunctionSolutionProcedure;
import Curves.Solutions.SecondDegreePolynomialFunctionSolutionProcedure;
import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CurveManager extends NGComponent {

    protected ArrayList<CustomCurve> FCurves;
    protected ArrayList<CurveEventListener> FEventListeners;
    protected CustomCurve FCurrentCurve;

    protected void DoLoadCurves() {
        // ToDo load from curve composition
        // CurveSolution I
        CustomCurveSolutionProcedure proc = new FirstDegreePolynomialFunctionSolutionProcedure();
        proc.setLogManager(FLogManager);
        // Curve I.1
        Curve2D curve = new Curve2D(this, "First", new FirstDegreePolynomialFunctionDefinition(-10, 10, -400, 400, -400, 400), proc);
        curve.setLineColor(Color.BLUE);
        curve.setParameterValue("a", 1.0);
        curve.setParameterValue("b", 0.0);
        addCurve(curve);
        FCurrentCurve = curve;
        // Curve I.2
        curve = new Curve2D(this, "Second", new FirstDegreePolynomialFunctionDefinition(-10, 10, -400, 400, -400, 400), proc);
        curve.setLineColor(Color.RED);
        curve.setParameterValue("a", 0.5);
        curve.setParameterValue("b", 10.0);
        addCurve(curve);
        // CurveSolution II
        proc = new SecondDegreePolynomialFunctionSolutionProcedure();
        proc.setLogManager(FLogManager);
        // Curve II.1
        curve = new Curve2D(this, "Third", new SecondDegreePolynomialFunctionDefinition(-10, 10, -100, 100, -400, 400, -40, 40), proc);
        curve.setLineColor(Color.GREEN);
        curve.setParameterValue("a", 0.5);
        curve.setParameterValue("b", 0.0);
        curve.setParameterValue("c", 0.0);
        addCurve(curve);
    }

    protected void BeginCalculateCurve(CustomCurve aCurve) {

    }

    protected void EndCalculateCurve(CustomCurve aCurve) {

    }

    protected void DoCalculateCurve(CustomCurve aCurve) {
        aCurve.Calculate();
        raiseCurveCalculatedEvent(aCurve);
    }

    protected void InternalCalculateCurve(CustomCurve aCurve) {
        BeginCalculateCurve(aCurve);
        try {
            DoCalculateCurve(aCurve);
        } finally {
            EndCalculateCurve(aCurve);
        }
    }

    protected void DoCalculateCurves() {
        for (CustomCurve curve : FCurves)
            InternalCalculateCurve(curve);
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

    protected synchronized void raiseCurrentCurveChangedEvent(CustomCurve aCurve) {
        CurveEvent event = new CurveEvent(this, aCurve);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleCurrentCurveChanged(event);
        }
    }

    protected CustomCurve getCurve(String aName) {
        for (CustomCurve curve : FCurves) {
            if (curve.getName().equals(aName)) {
                return curve;
            }
        }
        return null;
    }

    public void setCurrentCurve(CustomCurve aCurve) {
        FCurrentCurve = aCurve;
        writeInfo(String.format("Current curve is [%s]",FCurrentCurve.getName()));
        raiseCurrentCurveChangedEvent(FCurrentCurve);
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
        FCurrentCurve = null;
    }

    public void addCurve(CustomCurve aCurve) {
        FCurves.add(aCurve);
        writeInfo(String.format("Curve %s: Definition [%s -> %s] with solution procedures [%s] added", aCurve.getName(), aCurve.getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
        raiseCurveAddedEvent(aCurve);
        setCurrentCurve(aCurve);
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

    public CustomCurve getCurrentCurve() {
        return FCurrentCurve;
    }

    public CustomCurve setCurrentCurve(String aName) {
        setCurrentCurve(getCurve(aName));
        return FCurrentCurve;
    }

    public void addLogListener(NGLogEventListener aLogListener) {
        FLogManager.addEventListener(aLogListener);
    }

    public void removeLogListener(NGLogEventListener aLogListener) {
        FLogManager.removeEventListener(aLogListener);
    }

    public void CalculateCurves() {
        InternalCalculateCurves();
    }

    public void CalculateCurve(CustomCurve aCurve) {
        InternalCalculateCurve(aCurve);
    }

}
