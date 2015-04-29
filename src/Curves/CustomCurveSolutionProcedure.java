package Curves;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public abstract class CustomCurveSolutionProcedure extends NGObject {

    protected ArrayList<CurveProblemDefinition> FSolveProblems;
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

    protected void DoStartSolveProblem(CurveProblemDefinition aProblem) {

    }

    protected Boolean DoSolveProblem(CurveProblemDefinition aProblem, CurveParameterValueList aValues) {
        Boolean res = false;
        try {
            res = (Boolean)getClass().getMethod(aProblem.getMethodName(), CurveParameterValueList.class).invoke(this, aValues);
        }
        catch (Exception e) {
            writeError(e.getMessage());
        }
        return res;
    }

    protected void DoEndSolveProblem(CurveProblemDefinition aProblem) {

    }

    protected CurveProblemDefinition getSolveProblem(String aName) {
        for (CurveProblemDefinition problem : FSolveProblems) {
            if (problem.getName().equals(aName)) {
                return problem;
            }
        }
        return null;
    }

    protected void addSolveProblem(String aName, String aMethodName) {
        addSolveProblem(aName, aMethodName, "");
    }

    protected void addSolveProblem(String aName, String aMethodName, String aDescription) {
        CurveProblemDefinition problem = new CurveProblemDefinition(aName, aDescription, aMethodName);
        FSolveProblems.add(problem);
    }

    public CustomCurveSolutionProcedure() {
        super();
        FSolveProblems = new ArrayList<CurveProblemDefinition>();
        FLogManager = null;
    }

    public Boolean SolveProblem(CurveProblemDefinition aProblem, CurveParameterValueList aValues) {
        Boolean res = canSolveProblem(aProblem.getName());
        if (res) {
            DoStartSolveProblem(aProblem);
            try {
                res = DoSolveProblem(aProblem, aValues);
            }
            finally {
                DoEndSolveProblem(aProblem);
            }
        }
        return res;
    }

    public Boolean canSolveProblem(String aName) {
        return getSolveProblem(aName) != null;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
    }

}
