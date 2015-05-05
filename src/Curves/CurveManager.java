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
        Curve2D curve = new Curve2D(new FirstDegreePolynomialFunctionDefinition(1, 1, 0, 0, 0, 10), proc);
        addCurve(curve);
        curve.setParamterValue("a", 1.0);
        curve.setParamterValue("b", 0.0);
        curve.setParamterValue("x", 0.0);
    }

    protected void CalculateCurves() {
        for (CustomCurve curve : FCurves) {
            curve.Calculate();
        }
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        LoadCurves();
    }

    @Override
    protected void DoAfterInitialize() {
        CalculateCurves();
        super.DoAfterInitialize();
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
        writeInfo(String.format("Curve Definition [%s -> %s] with solution procedures [%s] added", aCurve.getDefinition().getFormula(), aCurve.getDefinition().getName(), aCurve.getSolutionProcedure().getSolveProblemsAsString()));
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        System.out.println(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleClearLog() {
    }

}
