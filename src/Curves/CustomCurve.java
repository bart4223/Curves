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
    protected String FName;

    protected void writeInfo(String aInfo) {
        writeInfo(0, aInfo);
    }

    protected void writeInfo(int aLogLevel, String aInfo) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, String.format("Curve %s: %s", getName(), aInfo), NGLogEntry.LogType.Info, toString());
        }
    }

    protected void writeWarning(String aWarning) {
        if (FLogManager != null) {
            FLogManager.writeLog(String.format("Curve %s: %s", getName(), aWarning), NGLogEntry.LogType.Warning, toString());
        }
    }

    protected void writeError(String aError) {
        if (FLogManager != null) {
            FLogManager.writeLog(String.format("Curve %s: %s", getName(), aError), NGLogEntry.LogType.Error, toString());
        }
    }

    protected void BeforeInternalCalculate(String aProblemName) {

    }

    protected CurveProblemDefinition getSolveProblem(String aProblemName) {
        return FSolutionProcedure.getSolveProblem(aProblemName);
    }

    protected void DoBeforeCalculate(String aProblemName) {
        FSolveParameterValues.AssignFrom(FParameterValues);
    }

    protected void InternalCalculate(String aProblemName) {
        switch (getSolveProblem(aProblemName).getKind()) {
            case Value:
                CurveParameterDefinition param = FDefinition.getParameterDefintion(CurveParameterDefinition.Kind.X);
                Iterator<CurveParameterDefinitionArea> itr = param.getDefinitionAreas();
                while (itr.hasNext()) {
                    CurveParameterDefinitionArea area = itr.next();
                    for (double x = area.getMin(); x <= area.getMax(); x = x + 1.0) {
                        DoBeforeCalculate(aProblemName);
                        try {
                            setSolveParameterValue(param.getName(), x);
                            DoCalculate(aProblemName);
                        } finally {
                            DoAfterCalculate(aProblemName);
                        }
                    }
                }
        }
    }

    protected void DoCalculate(String aProblemName) {
        FSolutionProcedure.SolveProblem(aProblemName, FSolveParameterValues);
    }

    protected void DoAfterCalculate(String aProblemName) {

    }

    protected void AfterInternalCalculate(String aProblemName) {

    }

    protected void setSolveParameterValue(String aName, double aValue) {
        FSolveParameterValues.setValue(aName, aValue);
    }

    protected double getSolveParameterValue(String aName) {
        return FSolveParameterValues.getValue(aName);
    }

    public CustomCurve(CurveManager aCurveManager, String aName, CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super();
        FCurveManager = aCurveManager;
        FName = aName;
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

    public String getName() {
        return FName;
    }

    public CustomCurveDefinition getDefinition() {
        return FDefinition;
    }

    public CustomCurveSolutionProcedure getSolutionProcedure() {
        return FSolutionProcedure;
    }

    public void Calculate(String aProblemName) {
        BeforeInternalCalculate(aProblemName);
        try {
            InternalCalculate(aProblemName);
        } finally {
            AfterInternalCalculate(aProblemName);
        }
    }

    public void Calculate() {
        Iterator<CurveProblemDefinition> itr = FSolutionProcedure.getProblems();
        while (itr.hasNext()) {
            CurveProblemDefinition problem = itr.next();
            Calculate(problem.getName());
        }
    }

    public void setParameterValue(String aName, double aValue) {
        FParameterValues.setValue(aName, aValue);
    }

    public double getParameterValue(String aName) {
        return FParameterValues.getValue(aName);
    }

}