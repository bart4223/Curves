package Curves;

import Curves.Definitions.FirstDegreePolynomialFunctionDefinition;
import Curves.Solutions.FirstDegreePolynomialFunctionSolutionProcedure;
import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public class CurveManager extends NGComponent implements NGLogEventListener {

    protected ArrayList<CustomCurve> FCurves;

    protected void LoadCurves() {
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
    }

    protected void DoCalculateCurves() {
        for (CustomCurve curve : FCurves) {
            DoCalculateCurve(curve);
        }
    }

    protected void BeginCalculateCurves() {

    }

    protected void CalculateCurves() {
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

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        LoadCurves();
    }

    @Override
    protected void DoAfterInitialize() {
        super.DoAfterInitialize();
        CalculateCurves();
    }

    public CurveManager() {
        this(null);
    }

    public CurveManager(NGComponent aOwner) {
        super(aOwner);
        FCurves = new ArrayList<CustomCurve>();
        FLogManager = new NGLogManager();
        FLogManager.addEventListener(this);
    }

    public void addCurve(CustomCurve aCurve) {
        FCurves.add(aCurve);
        writeInfo(String.format("Curve %s: Definition [%s -> %s] with solution procedures [%s] added", aCurve.getName(), aCurve.getDefinition().getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleClearLog() {
    }

}
