package Curves;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGLogEntry;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class CustomCurveSolutionProcedure extends NGObject {

    protected CopyOnWriteArrayList<CurveProblemDefinition> FSolveProblems;
    protected NGLogManager FLogManager;

    protected void writeInfo(String aInfo) {
        writeInfo(0, aInfo);
    }

    protected void writeInfo(int aLogLevel, String aInfo) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aInfo, NGLogEntry.LogType.Info);
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

    protected void DoStartSolveProblem(String aProblemName) {

    }

    protected Boolean DoSolveProblem(String aProblemName, CurveParameterValueList aValues) {
        CurveProblemDefinition problem = getSolveProblem(aProblemName);
        Boolean res = problem != null;
        if (res) {
            try {
                getClass().getMethod(problem.getSolveMethodName(), CurveParameterValueList.class).invoke(this, aValues);
            }
            catch (Exception e) {
                writeError(e.getMessage());
            }
        }
        return res;
    }

    protected void DoEndSolveProblem(String aProblemName) {

    }

    protected CurveProblemDefinition getSolveProblem(String aName) {
        for (CurveProblemDefinition problem : FSolveProblems) {
            if (problem.getName().equals(aName)) {
                return problem;
            }
        }
        return null;
    }

    protected void addSolveProblem(CurveProblemDefinition.Kind aKind, String aName, String aMethodName) {
        addSolveProblem(aKind, aName, aMethodName, "");
    }

    protected void addSolveProblem(CurveProblemDefinition.Kind aKind, String aName, String aMethodName, String aDescription) {
        CurveProblemDefinition problem = new CurveProblemDefinition(aKind, aName, aMethodName, aDescription);
        FSolveProblems.add(problem);
    }

    public CustomCurveSolutionProcedure() {
        super();
        FSolveProblems = new CopyOnWriteArrayList<CurveProblemDefinition>();
        FLogManager = null;
    }

    public Boolean SolveProblem(String aProblemName, CurveParameterValueList aValues) {
        Boolean res = canSolveProblem(aProblemName);
        if (res) {
            DoStartSolveProblem(aProblemName);
            try {
                res = DoSolveProblem(aProblemName, aValues);
            }
            finally {
                DoEndSolveProblem(aProblemName);
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

    public String getSolveProblemsAsString() {
        String res = "";
        for (CurveProblemDefinition problem : FSolveProblems) {
            res = NGStrings.addString(res, problem.getName(), ",");
        }
        return res;
    }

    public Iterator<CurveProblemDefinition> getProblems() {
        return FSolveProblems.iterator();
    }

}
