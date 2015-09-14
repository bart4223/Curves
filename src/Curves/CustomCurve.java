package Curves;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.UUID;

public abstract class CustomCurve extends NGObject {

    protected CustomCurveDefinition FDefinition;
    protected CustomCurveSolutionProcedure FSolutionProcedure;
    protected CurveParameterValueList FParameterValues;
    protected CurveParameterValueList FSolveParameterValues;
    protected CurveManager FCurveManager;
    protected NGLogManager FLogManager;
    protected String FID;
    protected String FName;
    protected Color FLineColor;
    protected Integer FLineSize;
    protected Double FScale;

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
                CurveParameterDefinition paramX = FDefinition.getParameterDefintion(CurveParameterDefinition.Kind.X);
                Iterator<CurveParameterDefinitionArea> itr = paramX.getDefinitionAreas();
                while (itr.hasNext()) {
                    CurveParameterDefinitionArea area = itr.next();
                    for (double x = area.getMin() * FScale; x <= area.getMax() * FScale; x = x + FScale) {
                        DoBeforeCalculate(aProblemName);
                        try {
                            setSolveParameterValue(paramX.getName(), x);
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

    public CustomCurve(CurveManager aCurveManager, String aName, CustomCurveDefinition aDefinition) {
        this(aCurveManager, aName, aDefinition, null);
    }

    public CustomCurve(CurveManager aCurveManager, String aName, CustomCurveDefinition aDefinition, CustomCurveSolutionProcedure aSolutionProcedure) {
        super();
        FCurveManager = aCurveManager;
        FID = UUID.randomUUID().toString();
        FName = aName;
        if (FCurveManager != null) {
            FLogManager = FCurveManager.getLogManager();
        }
        FDefinition = aDefinition;
        FSolutionProcedure = aSolutionProcedure;
        if (FSolutionProcedure == null) {
            try {
                FSolutionProcedure = (CustomCurveSolutionProcedure)FDefinition.getDefaultSolutionProcedure().getConstructor().newInstance();
                FSolutionProcedure.setLogManager(aCurveManager.getLogManager());
            }
            catch (Exception e) {
                writeError(e.getMessage());
            }
        }
        FParameterValues = new CurveParameterValueList();
        FSolveParameterValues = new CurveParameterValueList();
        FLineColor = Color.BLACK;
        FLineSize = 1;
        FScale = 1.0;
    }

    public CurveManager getCurveManager() {
        return FCurveManager;
    }

    public String getID() {
        return FID;
    }

    public String getName() {
        return FName;
    }

    public void setName(String aName) {
        FName = aName;
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

    public void setLineColor(Color aColor) {
        FLineColor = aColor;
    }

    public Color getLineColor() {
        return FLineColor;
    }

    public void setLineSize(Integer aLineSize) {
        FLineSize = aLineSize;
    }

    public Integer getLineSize() {
        return FLineSize;
    }

    public void setScale(Double aScale) {
        FScale = aScale;
    }

    public Double getScale() {
        return FScale;
    }

    public void setParameterValue(String aName, double aValue) {
        FParameterValues.setValue(aName, aValue);
    }

    public double getParameterValue(String aName) {
        return FParameterValues.getValue(aName);
    }

    public String getFormula() {
        String res = FDefinition.getFormula();
        Iterator<CurveParameterDefinition> itr = FDefinition.getParameters();
        while (itr.hasNext()) {
            CurveParameterDefinition paramDef = itr.next();
            if (paramDef.getKind() == CurveParameterDefinition.Kind.Factor) {
                res = res.replaceAll(paramDef.getName(), String.format("%.2f",getParameterValue(paramDef.getName())));
            }
        }
        return res;
    }

    public String getDescription() {
        return FDefinition.getDescription();
    }

    public void addDefinitionArea(String aName, Double aMin, Double aMax) {
        FDefinition.addDefinitionArea(aName, aMin, aMax);
    }

}
