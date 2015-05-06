package Curves;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;

import java.util.Iterator;

public abstract class CustomCurve extends NGObject {

    protected CustomCurveDefinition FDefinition;
    protected CustomCurveSolutionProcedure FSolutionProcedure;
    protected CurveParameterValueList FParameterValues;
    protected CurveParameterValueList FSolveParameterValues;
    protected CurveManager FCurveManager;
    protected NGLogManager FLogManager;

    protected void writeInfo(String aInfo) {
        if (FLogManager != null) {
            FLogManager.writeLog(aInfo, NGLogEntry.LogType.Info);
        }
    }

    protected void writeWarning(String aWarning) {
        if (FLogManager != null) {
            FLogManager.writeLog(aWarning, NGLogEntry.LogType.Warning);
        }
    }

    protected void writeError(String aError) {
        if (FLogManager != null) {
            FLogManager.writeLog(aError, NGLogEntry.LogType.Error);
        }
    }


    protected void BeforeCalculate(String aProblemName) {
        FSolveParameterValues.AssignFrom(FParameterValues);
        DoBeforeCalculate(aProblemName);
    }

    protected void DoBeforeCalculate(String aProblemName) {

    }

    protected void InternalCalculate(String aProblemName) {
        DoCalculate(aProblemName);
    }

    protected void DoCalculate(String aProblemName) {
        FSolutionProcedure.SolveProblem(aProblemName, FSolveParameterValues);
    }

    protected void AfterCalculate(String aProblemName) {
        DoAfterCalculate(aProblemName);
    }

    protected void DoAfterCalculate(String aProblemName) {

    }

    public CustomCurve(CurveManager aCurveManager, CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super();
        FCurveManager = aCurveManager;
        if (FCurveManager != null) {
            FLogManager = FCurveManager.getLogManager();
        }
        FDefinition = aDefinition;
        FSolutionProcedure = aSolutionProcedure;
        FParameterValues = new CurveParameterValueList();
        FSolveParameterValues = new CurveParameterValueList();
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

    public CustomCurveDefinition getDefinition() {
        return FDefinition;
    }

    public CustomCurveSolutionProcedure getSolutionProcedure() {
        return FSolutionProcedure;
    }

    public void Calculate(String aProblemName) {
        BeforeCalculate(aProblemName);
        try {
            InternalCalculate(aProblemName);
        } finally {
            AfterCalculate(aProblemName);
        }
    }

    public void Calculate() {
        Iterator<CurveProblemDefinition> itr = FSolutionProcedure.getProblems();
        while (itr.hasNext()) {
            CurveProblemDefinition problem = itr.next();
            Calculate(problem.getName());
        }
    }

    public void setParamterValue(String aName, double aValue) {
        FParameterValues.setValue(aName, aValue);
    }

    public double getParameterValue(String aName) {
        return FParameterValues.getValue(aName);
    }

}
