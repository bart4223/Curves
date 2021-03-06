package Curves;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;
import javafx.scene.paint.Color;

import java.util.EventObject;
import java.util.concurrent.CopyOnWriteArrayList;

public class CurveManager extends NGComponent {

    protected CopyOnWriteArrayList<CustomCurve> FCurves;
    protected CopyOnWriteArrayList<CurveEventListener> FEventListeners;
    protected CustomCurve FCurrentCurve;
    protected Integer FCurveLineSize;
    protected Double FScale;

    protected void DoLoadCurves() {
        // ToDo load from curve composition
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

    protected synchronized void raiseCurveChangedEvent(CustomCurve aCurve) {
        CurveEvent event = new CurveEvent(this, aCurve);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleCurveChanged(event);
        }
    }

    protected synchronized void raiseCurveRemovedEvent(CustomCurve aCurve) {
        CurveEvent event = new CurveEvent(this, aCurve);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleCurveRemoved(event);
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

    protected synchronized void raiseScaleChangedEvent() {
        EventObject event = new EventObject(this);
        for (CurveEventListener listener : FEventListeners) {
            listener.handleScaleChanged(event);
        }
    }

    protected CustomCurve getCurveByName(String aName) {
        for (CustomCurve curve : FCurves) {
            if (curve.getName().equals(aName)) {
                return curve;
            }
        }
        return null;
    }

    protected CustomCurve getCurveByID(String aID) {
        for (CustomCurve curve : FCurves) {
            if (curve.getID().equals(aID)) {
                return curve;
            }
        }
        return null;
    }

    public void setCurrentCurve(CustomCurve aCurve) {
        FCurrentCurve = aCurve;
        if (FCurrentCurve != null)
            writeInfo(String.format("Current curve is [%s]",FCurrentCurve.getName()));
        else
            writeInfo("No current curve");
        raiseCurrentCurveChangedEvent(FCurrentCurve);
    }

    public void setCurveNewName(CustomCurve aCurve, String aName) {
        aCurve.setName(aName);
        raiseCurveChangedEvent(FCurrentCurve);
    }

    public void setCurveLineColor(CustomCurve aCurve, Color aColor) {
        aCurve.setLineColor(aColor);
        raiseCurveChangedEvent(FCurrentCurve);
    }

    public CurveManager() {
        this("");
    }

    public CurveManager(String aName) {
        this(null, aName);
    }

    public CurveManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FCurves = new CopyOnWriteArrayList<CustomCurve>();
        FEventListeners = new CopyOnWriteArrayList<CurveEventListener>();
        FLogManager = new NGLogManager();
        FCurrentCurve = null;
        FCurveLineSize = 1;
        FScale = 1.0;
    }

    public void addCurve(CustomCurve aCurve) {
        aCurve.setLineSize(FCurveLineSize);
        aCurve.setScale(FScale);
        FCurves.add(aCurve);
        writeInfo(String.format("Curve %s: Definition [%s -> %s] with solution procedures [%s] added", aCurve.getName(), aCurve.getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
        raiseCurveAddedEvent(aCurve);
        setCurrentCurve(aCurve);
        CalculateCurve(aCurve);
    }

    public void removeCurrentCurve() {
        removeCurve(getCurrentCurve());
    }

    public void removeCurve(CustomCurve aCurve) {
        if (aCurve != null) {
            setCurrentCurve(null);
            FCurves.remove(aCurve);
            writeInfo(String.format("Curve %s: Definition [%s -> %s] with solution procedures [%s] removed", aCurve.getName(), aCurve.getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
            raiseCurveRemovedEvent(aCurve);
            if (FCurves.size() > 0)
                setCurrentCurve(FCurves.get(0));
        }
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

    public CustomCurve setCurrentCurveByName(String aName) {
        setCurrentCurve(getCurveByName(aName));
        return FCurrentCurve;
    }

    public CustomCurve setCurrentCurveByID(String aID) {
        setCurrentCurve(getCurveByID(aID));
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

    public void setAllCurveLineSize(Integer aLineSize) {
        FCurveLineSize = aLineSize;
        for (CustomCurve curve : FCurves) {
            curve.setLineSize(FCurveLineSize);
            raiseCurveChangedEvent(curve);
        }
    }

    public void setAllCurveScale(Double aScale) {
        FScale = aScale;
        for (CustomCurve curve : FCurves) {
            curve.setScale(FScale);
            CalculateCurve(curve);
        }
        raiseScaleChangedEvent();
    }

    public Double getScale() {
        return FScale;
    }

}
