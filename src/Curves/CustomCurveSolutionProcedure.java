package Curves;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public abstract class CustomCurveSolutionProcedure extends NGObject {

    protected ArrayList<CurveProblemDefinition> FSolveProblems;

    protected void DoStartSolve(CurveProblemDefinition aProblem) {

    }

    protected void DoSolve(CurveProblemDefinition aProblem) {
        try {
            getClass().getMethod(aProblem.getMethodName()).invoke(this);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void DoEndSolve(CurveProblemDefinition aProblem) {

    }

    protected CurveProblemDefinition getSolveProblem(String aName) {
        for (CurveProblemDefinition problem : FSolveProblems) {
            if (problem.getName().equals(aName)) {
                return problem;
            }
        }
        return null;
    }

    public CustomCurveSolutionProcedure() {
        super();
        FSolveProblems = new ArrayList<CurveProblemDefinition>();
    }

    public void Solve(CurveProblemDefinition aProblem) {
        DoStartSolve(aProblem);
        try {
            DoSolve(aProblem);
        }
        finally {
            DoEndSolve(aProblem);
        }
    }

    public Boolean canSolveProblem(CurveProblemDefinition aProblem) {
        return getSolveProblem(aProblem.getName()) != null;
    }

}
